package com.platform.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.platform.constant.MessageConstant;
import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.dto.auditors.AuditorInspectionCreateDTO;
import com.platform.dto.auditors.AuditorPageQueryDTO;
import com.platform.entity.*;
import com.platform.enums.LevelMatch;
import com.platform.exception.BaseException;
import com.platform.exception.FactoryNotExistException;
import com.platform.mapper.AuditorMapper;
import com.platform.mapper.EmployeeMapper;
import com.platform.mapper.FactoryMapper;
import com.platform.mapper.ProfessionInspectionMapper;
import com.platform.result.PageResult;
import com.platform.service.AuditorService;
import com.platform.utils.TransUtil;
import com.platform.vo.AuditorDisplayVO;
import com.platform.vo.AuditorStandingBookInWorkVO;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AuditorServiceImpl implements AuditorService {

    @Autowired
    private AuditorMapper auditorMapper;
    @Autowired
    private FactoryMapper factoryMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private ProfessionInspectionMapper inspectionMapper;
    @Autowired
    private TransUtil transUtil;


    @Override
    @Transactional
    public Auditor insert(AuditorCreateDTO auditorCreateDTO) {
        //检查审核员是否存在
        if(auditorMapper.countAuditorById(auditorCreateDTO.getEmployeeId())>=1){
            throw new BaseException("审核员工号:"+auditorCreateDTO.getEmployeeId()+" 已存在！");
        }
        //检查员工是否存在
        Employee employee=employeeMapper.getById(auditorCreateDTO.getEmployeeId());
        if(employee==null){
            throw new BaseException("员工工号："+auditorCreateDTO.getEmployeeId()+"不存在！");
        }
        //检查备案工厂是否存在
        Factory factory=factoryMapper.getFactoryById(auditorCreateDTO.getRecordFactoryId());
        if(factory==null){
            throw new BaseException("备案工厂："+auditorCreateDTO.getRecordFactoryId()+"不存在！");
        }
        //检查工艺类型
        String[] inspections=auditorCreateDTO.getTechnology().split(",");
        List<AuditorInspectionCreateDTO> inspectionList=new ArrayList<>();
        for (String str:inspections) {
            Long inspectionId=Long.parseLong(str);
            ProfessionInspection inspectionEntity = inspectionMapper.getById(inspectionId);
            if(inspectionEntity==null){
                throw new BaseException("工艺编号："+inspectionId+" 不存在");
            }
            inspectionList.add(new AuditorInspectionCreateDTO(auditorCreateDTO.getEmployeeId(), inspectionId));
        }

        Auditor auditor = Auditor.builder()
                .employeeId(auditorCreateDTO.getEmployeeId())
                .recordFactoryId(auditorCreateDTO.getRecordFactoryId())
                .education(auditorCreateDTO.getEducation())
                .auditorLevel(auditorCreateDTO.getAuditorLevel())
                .phone(auditorCreateDTO.getPhone())
                .registrationNumber(auditorCreateDTO.getRegistrationNumber())
                .technology(auditorCreateDTO.getTechnology()).build();

        auditorMapper.insert(auditor);
        auditorMapper.deleteAuditorInspectionByEmployeeId(auditor.getEmployeeId());
        auditorMapper.insertAuditorInspection(inspectionList);
        updateOnWorkStandingBook(auditor.getRecordFactoryId());
        return auditor;
    }

    /***
     * 更新在职审核员台账
     * @param recordFactoryId 备案工厂编号
     */
    public void updateOnWorkStandingBook(Long recordFactoryId){
        AuditorStandingBookInWork asibw = auditorMapper.getStandingBookInWorkByRecordFactoryId(recordFactoryId);
        Factory factory = factoryMapper.getFactoryById(recordFactoryId);
        if(asibw==null){
            asibw=new AuditorStandingBookInWork();
            asibw.setRecordFactoryId(recordFactoryId);

            if(factory==null){
                throw new FactoryNotExistException(MessageConstant.FACTORY_NOT_EXIST);
            }
            asibw.setBuId(factory.getBu().getBuId());
        }
        asibw.setLevel(factory.getLevel());
        List<Auditor> auditors= auditorMapper.getAuditorByRecordFactoryId(recordFactoryId).stream().filter(a->a.getEmployee().getStatus()==1).toList();

        Map<Integer, Long> collect = auditors.stream()
                .collect(
                        Collectors.groupingBy(Auditor::getAuditorLevel, Collectors.counting())
                );

        asibw.setSa(collect.getOrDefault(1,0L));
        asibw.setA(collect.getOrDefault(2,0L));
        asibw.setPa(collect.getOrDefault(3,0L));
        asibw.setLevel(factory.getLevel());

        if(AuditorStandingBookInWork.match(collect,factory.getLevel())){
            asibw.setLevelMatch(LevelMatch.MATCH);
            asibw.setWarnTime(null);
        }else {
            asibw.setLevelMatch(LevelMatch.NOT_MATCH);
            LocalDateTime now=LocalDateTime.now();
            if (asibw.getWarnTime()==null)
                asibw.setWarnTime(now);
        }
        if(asibw.getId()==null){
            auditorMapper.insertAuditorStandingBook(asibw);
        }
        else
            auditorMapper.updateAuditorStandingBook(asibw);

    }

    @Override
    public List<AuditorStandingBookInWorkVO> getStandingBookInWork() {
        List<Auditor> auditors = auditorMapper.getAuditor();
        Stream<Long> recordFactoryIdList = auditors.stream().map(Auditor::getRecordFactoryId).distinct();
        recordFactoryIdList.forEach(this::updateOnWorkStandingBook);
        List<AuditorStandingBookInWork> standingBookInWork = auditorMapper.getStandingBookInWork();
        Map<Long, List<Auditor>> auditorMap = auditors.stream().collect(Collectors.groupingBy(Auditor::getRecordFactoryId));
        standingBookInWork.forEach(a->a.setAuditors(transUtil.auditorListToVOList(auditorMap.getOrDefault(a.getRecordFactoryId(),null))));
        return transUtil.auditorStandingBookInWorkListToVoList(standingBookInWork);
    }

    @Override
    public PageResult<AuditorStandingBookInWork> getPageQueryStandingBookInWork(AuditorPageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getSize());
        Page<AuditorStandingBookInWork>  pageResult = auditorMapper.getPageQueryStandingBookInWork(pageQueryDTO);
        long total = pageResult.getTotal();
        List<AuditorStandingBookInWork> records = pageResult.getResult();
        List<Long> recordFactoryIdList = records.stream().map(AuditorStandingBookInWork::getRecordFactoryId).distinct().toList();
        List<Auditor> auditor = auditorMapper.getAuditor();

        Stream<Auditor> auditorStream = auditor.stream();

        auditorStream=auditorStream.filter(a->recordFactoryIdList.contains(a.getRecordFactoryId()));

        if(pageQueryDTO.getAuditorLevel()!=null){
            auditorStream=auditorStream.filter(a->a.getAuditorLevel()==pageQueryDTO.getAuditorLevel());
        }

        if(!StringUtil.isNullOrEmpty(pageQueryDTO.getEmployeeName())){
            auditorStream=auditorStream.filter(a->a.getEmployee().getName().contains(pageQueryDTO.getEmployeeName()));
        }


        Map<Long, List<Auditor>> auditorMap = auditorStream.collect(Collectors.groupingBy(Auditor::getRecordFactoryId));
        records.removeIf(r->!auditorMap.containsKey(r.getRecordFactoryId()));

        records.forEach(r->r.setAuditors(transUtil.auditorListToVOList(auditorMap.get(r.getRecordFactoryId()))));
        return new PageResult<>(total,records);
    }

    @Override
    public List<AuditorDisplayVO> getAuditorAll() {
        List<Auditor> auditors = auditorMapper.getAuditor();
        return transUtil.auditorListToVOList(auditors);
    }

    @Override
    public AuditorDisplayVO getAuditorByEmployeeId(Long employeeId) {
        Auditor auditor=auditorMapper.getAuditorByEmployeeId(employeeId);

        return transUtil.auditorToVO(auditor);
    }

    @Override
    public void deleteAuditorByEmployeeId(Long employeeId) {
        Auditor auditor = auditorMapper.getAuditorByEmployeeId(employeeId);
        if(auditor==null){
            throw new BaseException("审核员不存在！");
        }
        auditorMapper.deleteByEmployeeId(employeeId);
    }

    @Override
    public void updateAuditorArrangement(Long employeeId, Boolean isArrange) {
        Auditor auditor = auditorMapper.getAuditorByEmployeeId(employeeId);
        if(auditor==null){
            throw new BaseException("审核员不存在！");
        }

        if(!isArrange){
            auditorMapper.updateAuditorArrangement(employeeId,isArrange);
            return;
        }

        AuditorStandingBookInWork standingBook = auditorMapper.getStandingBookInWorkByRecordFactoryId(auditor.getRecordFactoryId());
        long totalAuditorCnt=standingBook.getA()+ standingBook.getSa()+ standingBook.getPa();

        auditorMapper.updateAuditorArrangement(employeeId,isArrange);
    }
}
