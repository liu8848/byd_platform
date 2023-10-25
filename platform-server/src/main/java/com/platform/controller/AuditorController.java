package com.platform.controller;

import com.platform.constant.RedisKeyConstant;
import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.entity.Auditor;
import com.platform.entity.AuditorStandingBookInWork;
import com.platform.result.Result;
import com.platform.service.AuditorService;
import com.platform.utils.RedisUtil;
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


    @GetMapping("/list")
    @Operation(summary = "获取在职审核员台账信息")
    public Result<List<AuditorStandingBookInWork>> getOnWorkAuditor() throws NoSuchFieldException {
//        List<OnWorkAuditorDisplayVO> onWorkAuditor = auditorService.getOnWorkAuditor();
        List<AuditorStandingBookInWork> standingBookInWork = auditorService.getStandingBookInWork();
        return Result.success(standingBookInWork);
    }

    @PostMapping("/add")
    @Operation(summary = "添加审核员")
    public Result<Auditor> createAuditor(@RequestBody AuditorCreateDTO auditorCreateDTO){
        Auditor auditor=auditorService.insert(auditorCreateDTO);
        return Result.success(auditor);
    }
}
