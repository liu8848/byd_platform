package com.platform.service.employee;

import com.platform.dto.employees.EmployeeCreateDTO;
import com.platform.entity.Employee;
import com.platform.vo.EmployeeDisplayVo;

public interface EmployeeService {
    Employee insert(EmployeeCreateDTO createDTO);

    EmployeeDisplayVo getById(Long id);
}
