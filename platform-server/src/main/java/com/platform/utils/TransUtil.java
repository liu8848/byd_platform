package com.platform.utils;

import com.platform.annotaionExtend.DictHelper;
import com.platform.annotaionExtend.DictParam;
import com.platform.constant.RedisKeyConstant;
import com.platform.entity.Auditor;
import com.platform.vo.AuditorDisplayVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransUtil {

    @DictHelper(value = {
            @DictParam(field = "factoryId",targetField = "factoryName",dictType = RedisKeyConstant.FACTORY),
            @DictParam(field = "departmentId",targetField = "departmentName",dictType = RedisKeyConstant.DEPARTMENT),
            @DictParam(field = "locationId",targetField = "locationName",dictType = RedisKeyConstant.LOCATION)
    })
    public AuditorDisplayVO auditorToVO(Auditor auditor){
        AuditorDisplayVO vo = AuditorDisplayVO.builder()
                .factoryId(auditor.getEmployee().getFactoryId())
                .departmentId(auditor.getEmployee().getDepartmentId())
                .employeeId(auditor.getEmployeeId())
                .employeeName(auditor.getEmployee().getName())
                .gender(auditor.getEmployee().getGender())
                .grade(auditor.getEmployee().getGrade())
                .education(auditor.getEducation())
                .auditorLevel(auditor.getAuditorLevel())
                .email(auditor.getEmployee().getEmail())
                .phone(auditor.getPhone())
                .registrationNumber(auditor.getRegistrationNumber())
                .locationId(auditor.getEmployee().getLocationId())
                .technologyName(auditor.getTechnology())
                .workStatus(auditor.getEmployee().getWorkStatus())
                .build();
        return vo;
    }

    @DictHelper(value = {
            @DictParam(field = "factoryId",targetField = "factoryName",dictType = RedisKeyConstant.FACTORY),
            @DictParam(field = "departmentId",targetField = "departmentName",dictType = RedisKeyConstant.DEPARTMENT),
            @DictParam(field = "locationId",targetField = "locationName",dictType = RedisKeyConstant.LOCATION)
    })
    public List<AuditorDisplayVO> auditorListToVOList(List<Auditor> auditors){
        List<AuditorDisplayVO> result = auditors.stream().map(this::auditorToVO).toList();
        return result;
    }
}
