package com.platform.utils;

import com.platform.annotaionExtend.DictHelper;
import com.platform.annotaionExtend.DictParam;
import com.platform.constant.DictKeyConstant;
import com.platform.constant.RedisKeyConstant;
import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.entity.Auditor;
import com.platform.entity.AuditorStandingBookInWork;
import com.platform.entity.Dictionary;
import com.platform.entity.Employee;
import com.platform.vo.AuditorDisplayVO;
import com.platform.vo.AuditorStandingBookInWorkVO;
import com.platform.vo.EmployeeDisplayVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TransUtil {

    @DictHelper(value = {
            @DictParam(field = "factoryId", targetField = "factoryName", dictType = RedisKeyConstant.FACTORY),
            @DictParam(field = "departmentId", targetField = "departmentName", dictType = RedisKeyConstant.DEPARTMENT),
            @DictParam(field = "locationId", targetField = "locationName", dictType = RedisKeyConstant.LOCATION)
    })
    public AuditorDisplayVO auditorToVO(Auditor auditor) {
        //转换工艺列表
        String[] piId = auditor.getTechnology().split(",");
        StringBuilder piName = new StringBuilder();
        Map<String, Dictionary> piMap = DictUtil.dictMap.get(RedisKeyConstant.PROFESSION_INSPECTION);
        for (int i = 0; i < piId.length; i++) {
            Dictionary dictItem = ((List<Dictionary>) piMap.getOrDefault(piId[i], null)).get(0);
            piName.append(dictItem == null ? "" : dictItem.getDictName());
            if (i < piId.length - 1) {
                piName.append("，");
            }
        }
        AuditorDisplayVO vo = AuditorDisplayVO.builder()
                .factoryId(auditor.getEmployee().getFactoryId())
                .departmentId(auditor.getEmployee().getDepartmentId())
                .employeeId(auditor.getEmployeeId())
                .employeeName(auditor.getEmployee().getName())
                .gender(DictUtil.getDictName(DictKeyConstant.GENDER, Integer.toString(auditor.getEmployee().getGender())))
                .grade(DictUtil.getDictName(DictKeyConstant.GRADE, Integer.toString(auditor.getEmployee().getGrade())))
                .education(DictUtil.getDictName(DictKeyConstant.EDUCATION, Integer.toString(auditor.getEducation())))
                .auditorLevel(DictUtil.getDictName(DictKeyConstant.AUDITOR_LEVEL, Integer.toString(auditor.getAuditorLevel())))
                .email(auditor.getEmployee().getEmail())
                .phone(auditor.getPhone())
                .registrationNumber(auditor.getRegistrationNumber())
                .locationId(auditor.getEmployee().getLocationId())
                .technologyName(piName.toString())
                .status(DictUtil.getDictName(DictKeyConstant.WORK_STATUS, Integer.toString(auditor.getEmployee().getStatus())))
                .type(DictUtil.getDictName(DictKeyConstant.AUDITOR_TYPE, Integer.toString(auditor.getType())))
                .arrangement(auditor.getArrangement())
                .build();
        return vo;
    }


    @DictHelper(value = {
            @DictParam(field = "factoryId", targetField = "factoryName", dictType = RedisKeyConstant.FACTORY),
            @DictParam(field = "departmentId", targetField = "departmentName", dictType = RedisKeyConstant.DEPARTMENT),
            @DictParam(field = "locationId", targetField = "locationName", dictType = RedisKeyConstant.LOCATION)
    })
    public List<AuditorDisplayVO> auditorListToVOList(List<Auditor> auditors) {
        List<AuditorDisplayVO> result = auditors.stream().map(this::auditorToVO).toList();
        return result;
    }

    @DictHelper(value = {
            @DictParam(field = "recordFactoryId", targetField = "recordFactoryName", dictType = RedisKeyConstant.FACTORY),
            @DictParam(field = "buId", targetField = "buName", dictType = RedisKeyConstant.BUSINESSDIVISION)
    })
    public AuditorStandingBookInWorkVO auditorStandingBookInWorkToVO(AuditorStandingBookInWork auditorStandingBookInWork) {
        AuditorStandingBookInWorkVO vo = AuditorStandingBookInWorkVO.builder()
                .buId(auditorStandingBookInWork.getBuId())
                .recordFactoryId(auditorStandingBookInWork.getRecordFactoryId())
                .levelMatch(auditorStandingBookInWork.getLevelMatch().getStr())
                .level(DictUtil.getDictName(DictKeyConstant.FACTORY_LEVEL, Integer.toString(auditorStandingBookInWork.getLevel())))
                .sa(auditorStandingBookInWork.getSa())
                .a(auditorStandingBookInWork.getA())
                .pa(auditorStandingBookInWork.getPa())
                .warnTime(auditorStandingBookInWork.getWarnTime())
                .auditors(auditorStandingBookInWork.getAuditors())
                .build();
        return vo;
    }

    @DictHelper(value = {
            @DictParam(field = "recordFactoryId", targetField = "recordFactoryName", dictType = RedisKeyConstant.FACTORY),
            @DictParam(field = "buId", targetField = "buName", dictType = RedisKeyConstant.BUSINESSDIVISION)
    })
    public List<AuditorStandingBookInWorkVO> auditorStandingBookInWorkListToVoList(List<AuditorStandingBookInWork> standingBookInWorkList) {
        return standingBookInWorkList.stream().map(this::auditorStandingBookInWorkToVO).toList();
    }

    @DictHelper(value = {
            @DictParam(field = "buId", targetField = "buName", dictType = RedisKeyConstant.BUSINESSDIVISION),
            @DictParam(field = "factoryId", targetField = "factoryName", dictType = RedisKeyConstant.FACTORY),
            @DictParam(field = "departmentId", targetField = "departmentName", dictType = RedisKeyConstant.DEPARTMENT),
            @DictParam(field = "locationId", targetField = "locationName", dictType = RedisKeyConstant.LOCATION)
    })
    public EmployeeDisplayVo employeeToVO(Employee employee) {
        EmployeeDisplayVo vo = EmployeeDisplayVo.builder()
                .employeeId(employee.getId())
                .employeeName(employee.getName())
                .grade(DictUtil.getDictName(DictKeyConstant.GRADE, Integer.toString(employee.getGrade())))
                .education(DictUtil.getDictName(DictKeyConstant.EDUCATION, Integer.toString(employee.getEducation())))
                .email(employee.getEmail())
                .gender(DictUtil.getDictName(DictKeyConstant.GENDER, Integer.toString(employee.getGender())))
                .phone(employee.getPhone())
                .buId(employee.getDepartment().getFactory().getBu().getBuId())
                .factoryId(employee.getFactoryId())
                .departmentId(employee.getDepartmentId())
                .locationId(employee.getLocationId())
                .createTime(employee.getCreateTime())
                .updateTime(employee.getUpdateTime())
                .createUser(employee.getCreateUser())
                .updateUser(employee.getUpdateUser())
                .build();
        return vo;
    }

    public Auditor auditorCreateToAuditor(AuditorCreateDTO auditorCreateDTO){
        Auditor auditor = Auditor.builder()
                .employeeId(auditorCreateDTO.getEmployeeId())
                .recordFactoryId(auditorCreateDTO.getRecordFactoryId())
                .education(auditorCreateDTO.getEducation())
                .auditorLevel(auditorCreateDTO.getAuditorLevel())
                .phone(auditorCreateDTO.getPhone())
                .registrationNumber(auditorCreateDTO.getRegistrationNumber())
                .technology(auditorCreateDTO.getTechnology())
                .build();
        return auditor;
    }


}
