package com.platform.controller;

import com.platform.result.Result;
import com.platform.service.AuditorService;
import com.platform.vo.OnWorkAuditorDisplayVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<List<OnWorkAuditorDisplayVO>> getOnWorkAuditor(){
        List<OnWorkAuditorDisplayVO> onWorkAuditor = auditorService.getOnWorkAuditor();
        return Result.success(onWorkAuditor);
    }
}
