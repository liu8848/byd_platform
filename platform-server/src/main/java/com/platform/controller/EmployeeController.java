package com.platform.controller;

import com.platform.annotaionExtend.OperationLog;
import com.platform.dto.employees.EmployeeCreateDTO;
import com.platform.entity.Employee;
import com.platform.exception.BaseException;
import com.platform.mapper.EmployeeMapper;
import com.platform.result.Result;
import com.platform.service.employee.EmployeeService;
import com.platform.utils.ExcelUtil;
import com.platform.vo.EmployeeDisplayVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/employee")
@Slf4j
@Tag(name = "员工相关接口")
@Validated
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
    public Result<EmployeeDisplayVo> getById(@PathVariable Long id) {
        EmployeeDisplayVo employee = employeeService.getById(id);
        return Result.success(employee);
    }

    @PostMapping
    @Operation(summary = "添加员工")
    public Result<Employee> insert(@Validated @RequestBody EmployeeCreateDTO createDTO) {
        Employee result = employeeService.insert(createDTO);
        return Result.success(result);
    }

    @PostMapping("/importEmployees")
    @Operation(summary = "Excel导入员工信息")
    public Result<List<Employee>> importEmployee(@RequestParam("file")MultipartFile file,
                                                 @RequestParam(value = "updateSupport",required = false,defaultValue = "false")
                                                 Boolean updateSupport){
        List<Employee> employees;
        try {
            List<EmployeeCreateDTO> createDTOS = ExcelUtil.read(file, EmployeeCreateDTO.class);
            employees=employeeService.importEmployee(createDTOS);
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
        return null;
    }

}
