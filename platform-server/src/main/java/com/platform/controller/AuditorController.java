package com.platform.controller;

import com.platform.constant.MessageConstant;
import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.entity.Auditor;
import com.platform.entity.Employee;
import com.platform.entity.Factory;
import com.platform.exception.AccountNotFoundException;
import com.platform.exception.FactoryNotExistException;
import com.platform.mapper.EmployeeMapper;
import com.platform.result.Result;
import com.platform.service.AuditorService;
import com.platform.service.FactoryService;
import com.platform.vo.OnWorkAuditorDisplayVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/auditor")
@Tag(name = "审核员台账相关接口")
public class AuditorController {
    @Autowired
    private AuditorService auditorService;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private FactoryService factoryService;


    @GetMapping("/list")
    @Operation(summary = "获取在职审核员台账信息")
    public Result<List<OnWorkAuditorDisplayVO>> getOnWorkAuditor() throws NoSuchFieldException {

        List<OnWorkAuditorDisplayVO> onWorkAuditor = auditorService.getOnWorkAuditor();
        return Result.success(onWorkAuditor);
    }

    @PostMapping("/add")
    @Operation(summary = "添加审核员")
    public Result<Auditor> createAuditor(@RequestBody AuditorCreateDTO auditorCreateDTO){
        Employee employee = employeeMapper.getById(auditorCreateDTO.getEmployeeId());
        if(employee==null){
            throw new AccountNotFoundException("该员工不存在！");
        }
        Factory factory = factoryService.getFactoryById(auditorCreateDTO.getRecordFactoryId());
        if(factory==null){
            throw new FactoryNotExistException(MessageConstant.FACTORY_NOT_EXIST);
        }
        Auditor auditor=auditorService.insert(auditorCreateDTO);
        return Result.success(auditor);
    }
}
