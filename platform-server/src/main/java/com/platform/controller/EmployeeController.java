package com.platform.controller;

import com.platform.annotaionExtend.OperationLog;
import com.platform.dto.employees.EmployeeCreateDTO;
import com.platform.entity.Employee;
import com.platform.mapper.EmployeeMapper;
import com.platform.result.Result;
import com.platform.service.employee.EmployeeService;
import com.platform.vo.EmployeeDisplayVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Slf4j
@Tag(name = "员工相关接口")
public class EmployeeController {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    @OperationLog
    @Operation(summary = "获取员工列表")
    public Result<List<EmployeeDisplayVo>> getAll() {
        List<EmployeeDisplayVo> employees = employeeMapper.getAll();
        return Result.success(employees);
    }

    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id) {
        Employee employee=employeeService.getById(id);
        return Result.success(employee);
    }

    @PostMapping
    @Operation(summary = "添加员工")
    public Result<Employee> insert(@RequestBody EmployeeCreateDTO createDTO){
        Employee result = employeeService.insert(createDTO);
        return Result.success(result);
    }
}
