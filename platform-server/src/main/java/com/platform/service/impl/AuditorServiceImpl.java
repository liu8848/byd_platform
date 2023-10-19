package com.platform.service.impl;

import com.platform.entity.Auditor;
import com.platform.mapper.AuditorMapper;
import com.platform.service.AuditorService;
import com.platform.vo.AuditorDisplayVO;
import com.platform.vo.OnWorkAuditorDisplayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuditorServiceImpl implements AuditorService {

    @Autowired
    private AuditorMapper auditorMapper;
    @Override
    public List<OnWorkAuditorDisplayVO> getOnWorkAuditor() {
        List<Auditor> auditor = auditorMapper.getAuditor();

        Map<Long,List<Auditor>> result=auditor.stream().collect(
                Collectors.groupingBy(Auditor::getRecordFactoryId)
        );

        List<OnWorkAuditorDisplayVO> onWorkAuditorDisplayVOS=new ArrayList<>();
        for (var item:result.entrySet()) {
            List<AuditorDisplayVO> list = item.getValue().stream()
                    .map(a -> AuditorDisplayVO.builder()
                            .factoryName(a.getEmployee().getDepartment().getFactory().getName())
                            .departmentName(a.getEmployee().getDepartment().getName())
                            .employeeName(a.getEmployee().getName())
                            .employeeId(a.getEmployeeId())
                            .grade(a.getEmployee().getGrade())
                            .gender(a.getEmployee().getGender())
                            .education(a.getEducation())
                            .auditorLevel(a.getAuditorLevel())
                            .email(a.getEmployee().getEmail())
                            .phone(a.getPhone())
                            .registrationNumber(a.getRegistrationNumber())
                            .locationName(a.getEmployee().getLocationId().toString())
                            .technologyName(a.getTechnology())
                            .workStatus(a.getEmployee().getWorkStatus())
                            .type(a.getType())
                            .build()
                    ).toList();

            String buName = auditor.get(0).getEmployee().getDepartment().getFactory().getBu().getBuName();
            String name = auditor.get(0).getEmployee().getDepartment().getFactory().getName();
            Map<String, Long> auditorLevelCnt = list.stream().collect(Collectors.groupingBy(AuditorDisplayVO::getAuditorLevel, Collectors.counting()));
            OnWorkAuditorDisplayVO vo = OnWorkAuditorDisplayVO.builder()
                    .buName(buName)
                    .recordFactoryName(name)
                    .auditorVOList(list)
                    .auditorLevelCnt(auditorLevelCnt)
                    .build();
            onWorkAuditorDisplayVOS.add(vo);
        }

        return onWorkAuditorDisplayVOS;
    }
}
