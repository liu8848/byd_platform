package com.platform.service.employee;

import com.platform.annotaionExtend.DictHelper;
import com.platform.annotaionExtend.DictParam;
import com.platform.constant.RedisKeyConstant;
import com.platform.dto.employees.EmployeeCreateDTO;
import com.platform.entity.Employee;
import com.platform.mapper.EmployeeMapper;
import com.platform.utils.TransUtil;
import com.platform.vo.EmployeeDisplayVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private TransUtil transUtil;

    @Override
    public Employee insert(EmployeeCreateDTO createDTO) {
        Employee employee = Employee.builder()
                .name(createDTO.getName())
                .grade(createDTO.getGrade())
                .gender(createDTO.getGender())
                .education(createDTO.getEducation())
                .email(createDTO.getEmail())
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
            @DictParam(targetField = "factoryName", field = "factoryId", dictType = RedisKeyConstant.FACTORY)
    })
    public EmployeeDisplayVo getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        return transUtil.employeeToVO(employee);
    }

    @Override
    @Transactional
    public List<Employee> importEmployee(List<EmployeeCreateDTO> createDTOS) {
        List<Employee> employees = transUtil.employeesCreateListToEmployeeList(createDTOS);
        employeeMapper.importEmployee(employees);
        return employees;
    }
}
