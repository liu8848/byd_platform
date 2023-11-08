package com.platform.service.employee;

import com.platform.dto.employees.EmployeeCreateDTO;
import com.platform.entity.Employee;
import com.platform.vo.EmployeeDisplayVo;

import java.util.List;

public interface EmployeeService {
    Employee insert(EmployeeCreateDTO createDTO);

    EmployeeDisplayVo getById(Long id);

    List<Employee> importEmployee(List<EmployeeCreateDTO> createDTOS);
}
