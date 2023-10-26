package com.platform.controller;

import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.dto.auditors.AuditorPageQueryDTO;
import com.platform.entity.Auditor;
import com.platform.entity.AuditorStandingBookInWork;
import com.platform.result.PageResult;
import com.platform.result.Result;
import com.platform.service.AuditorService;
import com.platform.vo.AuditorDisplayVO;
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

    @GetMapping("/all")
    @Operation(summary = "获取所有审核员信息")
    public Result<List<AuditorDisplayVO>>getAllAuditor(){
        List<AuditorDisplayVO> auditorAll = auditorService.getAuditorAll();
        return Result.success(auditorAll);
    }

    @PostMapping("/add")
    @Operation(summary = "添加审核员")
    public Result<Auditor> createAuditor(@RequestBody AuditorCreateDTO auditorCreateDTO){
        Auditor auditor=auditorService.insert(auditorCreateDTO);
        return Result.success(auditor);
    }

    @GetMapping("/pageQuery")
    @Operation(summary = "在职审核员台账分页查询")
    public Result<PageResult<AuditorStandingBookInWork>> getPageQueryOnWorkAuditor(AuditorPageQueryDTO pageQueryDTO){
        PageResult<AuditorStandingBookInWork> result = auditorService.getPageQueryStandingBookInWork(pageQueryDTO);
        return Result.success(result);
    }
}
