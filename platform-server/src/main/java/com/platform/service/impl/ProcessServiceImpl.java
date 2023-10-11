package com.platform.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.platform.constant.MessageConstant;
import com.platform.context.BaseContext;
import com.platform.dto.ProcessCreateDTO;
import com.platform.dto.ProcessPageQueryDTO;
import com.platform.dto.ProcessUpdateDTO;
import com.platform.entity.Process;
import com.platform.exception.AuthorityException;
import com.platform.exception.ProcessNotExistException;
import com.platform.mapper.ProcessMapper;
import com.platform.result.PageResult;
import com.platform.service.ProcessService;
import com.platform.vo.ProcessApplicantVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessMapper processMapper;

    @Override
    public List<Process> getByApplicantId(Long applicantId) {
        return processMapper.getByApplicantId(applicantId);
    }

    @Override
    public PageResult<ProcessApplicantVO> pageQueryByApplicant(ProcessPageQueryDTO processPageQueryDTO) {
        PageHelper.startPage(processPageQueryDTO.getPage(),processPageQueryDTO.getSize());

        Page<ProcessApplicantVO> page=processMapper.queryPageApplicant(processPageQueryDTO);
        long total= page.getTotal();
        List<ProcessApplicantVO> records=page.getResult();

        return new PageResult<>(total, records);
    }

    @Override
    public void createProcess(ProcessCreateDTO processCreateDTO) {

        Process process=new Process();

        BeanUtils.copyProperties(processCreateDTO,process);

        String id= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String nextIdStr=processMapper.getMaxProcessId(id);
        Long nextId=(nextIdStr==null||nextIdStr.isEmpty())?1L:Long.parseLong(nextIdStr);
        id+=String.format("%06d",nextId);

        process.setId(id);
        process.setApplicantId(BaseContext.getCurrentId().getEmployeeId());
        process.setCreateTime(LocalDateTime.now());
        process.setUpdateUser(BaseContext.getCurrentId().getEmployeeId());
        process.setUpdateTime(LocalDateTime.now());
//        process.setApproverId(1L);
        process.setStatus("0");

        processMapper.insert(process);
    }

    @Override
    public void deleteProcessById(String processId) {
        Process process = processMapper.selectProcessById(processId);

        if(process==null){
            throw new ProcessNotExistException(MessageConstant.PROCESS_NOT_EXIST);
        }

        if(process.getApplicantId()!=BaseContext.getCurrentId().getEmployeeId()){
            throw new AuthorityException(MessageConstant.NO_AUTHORITY_DELETE);
        }

        processMapper.deleteByProcessId(processId);
    }

    @Override
    public void updateProcessById(ProcessUpdateDTO processUpdateDTO) {
        Process process=processMapper.selectProcessById(processUpdateDTO.getId());

        if(process==null){
            throw new ProcessNotExistException(MessageConstant.PROCESS_NOT_EXIST);
        }

        if (process.getApplicantId()!=BaseContext.getCurrentId().getEmployeeId()){
            throw new AuthorityException(MessageConstant.NO_AUTHORITY_UPDATE);
        }
        BeanUtils.copyProperties(processUpdateDTO,process);
        process.setUpdateUser(BaseContext.getCurrentId().getEmployeeId());
        process.setUpdateTime(LocalDateTime.now());
        processMapper.update(process);
    }
}
