package com.platform.controller;

import com.platform.dto.EmployeeLoginDTO;
import com.platform.entity.Employee;
import com.platform.mapper.EmployeeMapper;
import com.platform.result.Result;
import com.platform.vo.EmployeeDisplayVo;
import com.platform.vo.EmployeeLoginVO;
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


    @GetMapping
    @Operation(summary = "获取员工列表")
    public Result<List<EmployeeDisplayVo>> getAll() {
        List<EmployeeDisplayVo> employees = employeeMapper.getAll();
        return Result.success(employees);
    }

    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id) {
        return Result.success(employeeMapper.getById(id));
    }
}
