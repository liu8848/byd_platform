package com.platform.controller;


import com.platform.constant.ContentTypeConstant;
import com.platform.dto.auditPlan.AuditPlanCreateDTO;
import com.platform.dto.auditPlan.AuditPlanPageQueryDTO;
import com.platform.entity.AuditPlan;
import com.platform.exception.BaseException;
import com.platform.result.PageResult;
import com.platform.result.Result;
import com.platform.service.auditPlan.AuditPlanService;
import com.platform.vo.auditPlan.AuditPlanDisplayVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/auditPlan")
@Tag(name = "审核计划相关接口")
public class AuditPlanController {

    @Autowired
    private AuditPlanService auditPlanService;

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "创建审核方案")
    @Parameters(value = {
            @Parameter(name = "fileName",description = "审核方案名称",required = true,in = ParameterIn.QUERY,schema = @Schema(type = "string")),
            @Parameter(name = "publishTime",description = "发布时间",required = true,in = ParameterIn.DEFAULT,schema = @Schema(type = "string(date-time)"))
    })
    public Result<AuditPlan> createAuditPlan(AuditPlanCreateDTO createDTO,
                                             @RequestParam("file") MultipartFile[] files){
        //检验上传文件是否符合要求
        if (files==null||files.length<2){
            throw new BaseException("上传文件缺失");
        }
        Map<String, List<MultipartFile>> fileMap = Arrays.stream(files).collect(Collectors.groupingBy(MultipartFile::getContentType));
        Set<String> key = fileMap.keySet();
        if (key.size()<2){
            throw new BaseException("文件必须包含Word与PDF");
        }
        if(!key.contains(ContentTypeConstant.PDF_CONTENT_TYPE)){
            throw new BaseException("PDF文件缺失");
        }
        if(!key.contains(ContentTypeConstant.DOC_CONTENT_TYPE)&&!key.contains(ContentTypeConstant.DOCX_CONTENT_TYPE)){
            throw new BaseException("Word文件缺失");
        }

        AuditPlan auditPlan;
        try {
            auditPlan=auditPlanService.createAuditPlan(fileMap,createDTO);
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }


        return Result.success(auditPlan);
    }


    @GetMapping("/queryPage")
    @Operation(summary = "按条件分页获取审核方案")
    @Parameters(value = {
            @Parameter(name = "fileName",description = "方案名称",in = ParameterIn.DEFAULT),
            @Parameter(name = "startPublishTime",description = "开始发布时间",in = ParameterIn.DEFAULT),
            @Parameter(name = "endPublishTime",description = "结束发布时间",in =ParameterIn.DEFAULT),
            @Parameter(name="page",description = "页码",in = ParameterIn.DEFAULT),
            @Parameter(name = "size",description = "记录数",in=ParameterIn.DEFAULT)
    })
    public Result<PageResult<AuditPlanDisplayVO>> getAuditPlanByPlanName(AuditPlanPageQueryDTO pageQueryDTO){
        PageResult<AuditPlanDisplayVO> result=auditPlanService.queryPage(pageQueryDTO);
        return Result.success(result);
    }

}
