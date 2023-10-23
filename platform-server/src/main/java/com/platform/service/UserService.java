package com.platform.service;

import com.platform.dto.employees.EmployeeLoginDTO;
import com.platform.vo.EmployeeLoginVO;

public interface UserService {
    EmployeeLoginVO login(EmployeeLoginDTO employeeLoginDTO);
}
