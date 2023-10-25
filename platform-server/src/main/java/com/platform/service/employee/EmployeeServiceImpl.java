package com.platform.service.employee;

import com.platform.annotaionExtend.DictHelper;
import com.platform.annotaionExtend.DictParam;
import com.platform.constant.RedisKeyConstant;
import com.platform.dto.employees.EmployeeCreateDTO;
import com.platform.entity.Employee;
import com.platform.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee insert(EmployeeCreateDTO createDTO) {
        Employee employee = Employee.builder()
                .name(createDTO.getName())
                .grade(createDTO.getGrade())
                .gender(createDTO.getGender())
                .education(createDTO.getEducation())
                .email(createDTO.getEmail())
                .phone(createDTO.getPhone())
                .factoryId(createDTO.getFactoryId())
                .departmentId(createDTO.getDepartmentId())
                .locationId(createDTO.getLocationId())
                .workStatus(createDTO.getWorkStatus())
                .build();

        employeeMapper.insert(employee);
        return employee;
    }

    @Override
    @DictHelper(value = {
            @DictParam(targetField = "factoryName",field = "factoryId",dictType = RedisKeyConstant.FACTORY)
    })
    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        return employee;
    }
}
