package com.platform.service.employee;

import com.platform.dto.employees.EmployeeCreateDTO;
import com.platform.entity.Employee;

public interface EmployeeService {
    Employee insert(EmployeeCreateDTO createDTO);

    Employee getById(Long id);
}
