package com.platform.controller;

import com.platform.dto.processes.ProcessCreateDTO;
import com.platform.dto.processes.ProcessPageQueryDTO;
import com.platform.dto.processes.ProcessUpdateDTO;
import com.platform.entity.Process;
import com.platform.result.PageResult;
import com.platform.result.Result;
import com.platform.service.ProcessService;
import com.platform.vo.ProcessApplicantVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/process")
@Slf4j
@Tag(name = "流程相关接口")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @GetMapping("/{applicantId}")
    @Operation(summary = "根据申请人工号查询流程")
    public Result<List<Process>> getByApplicantId(@PathVariable
                                                  @Schema(description = "申请人工号")
                                                  Long applicantId) {
        List<Process> processList = processService.getByApplicantId(applicantId);
        return Result.success(processList);
    }

    @GetMapping("/applicant")
    @Operation(summary = "根据申请人工号及搜索条件查询流程")
    @Parameter(name = "processPageQueryDTO", description = "流程分页查询搜索模型", in = ParameterIn.QUERY)
    public Result<PageResult<ProcessApplicantVO>> getProcessPageByApplicantIdAndQuery(ProcessPageQueryDTO processPageQueryDTO) {
        log.info("分页查询条件：{}", processPageQueryDTO);

        PageResult<ProcessApplicantVO> result = processService.pageQueryByApplicant(processPageQueryDTO);
        return Result.success(result);
    }

    @PostMapping()
    @Operation(summary = "创建流程")
    public Result<String> createProcess(@RequestBody ProcessCreateDTO processCreateDTO) {
        log.info("创建流程:{}", processCreateDTO);

        processService.createProcess(processCreateDTO);


        return Result.success("创建成功");

    }

    @DeleteMapping("/{processId}")
    @Operation(summary = "根据流程编号删除流程")
    public Result<String> deleteProcessById(@PathVariable String processId) {
        log.info("删除流程: 流程编号-{}", processId);

        processService.deleteProcessById(processId);

        return Result.success("删除成功");
    }

    @PutMapping("/{processId}")
    @Operation(summary = "根据流程编号修改流程信息")
    public Result<String> updateProcessById(@RequestBody ProcessUpdateDTO processUpdateDTO) {
        log.info("流程信息修改:{}", processUpdateDTO);

        processService.updateProcessById(processUpdateDTO);

        return Result.success("修改成功");
    }

}
