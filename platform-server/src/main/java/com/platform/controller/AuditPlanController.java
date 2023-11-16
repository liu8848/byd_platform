package com.platform.controller;

import com.platform.constant.ContentTypeConstant;
import com.platform.entity.AuditPlan;
import com.platform.exception.BaseException;
import com.platform.result.Result;
import com.platform.service.auditPlan.AuditPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Slf4j
@RequestMapping("/api/auditPlan")
@Tag(name = "审核计划相关接口")
public class AuditPlanController {

    @Autowired
    private AuditPlanService auditPlanService;

    @PostMapping("/create")
    @Operation(summary = "创建审核方案")
    public Result<AuditPlan> createAuditPlan(@RequestParam MultipartFile[] files,
                                             @RequestBody AuditPlan auditPlan){

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

        //开始上传文件
        auditPlanService.createAuditPlan(fileMap,auditPlan);


        return Result.success(auditPlan);
    }

}
