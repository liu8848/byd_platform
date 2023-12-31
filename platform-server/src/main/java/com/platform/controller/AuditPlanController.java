package com.platform.controller;


import com.platform.constant.ContentTypeConstant;
import com.platform.dto.auditPlan.AuditPlanCreateDTO;
import com.platform.dto.auditPlan.AuditPlanPageQueryDTO;
import com.platform.dto.auditPlan.AuditPlanUpdateDTO;
import com.platform.entity.AuditPlan;
import com.platform.entity.UploadFile;
import com.platform.exception.BaseException;
import com.platform.result.PageResult;
import com.platform.result.Result;
import com.platform.result.UpdateResult;
import com.platform.service.auditPlan.AuditPlanService;
import com.platform.vo.auditPlan.AuditPlanDisplayVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/api/auditPlan")
@Tag(name = "审核计划相关接口")
public class AuditPlanController {

    @Autowired
    private AuditPlanService auditPlanService;


    @PostMapping(value = "/create")
    @Operation(summary = "创建审核方案")
    public Result<AuditPlan> createAuditPlan(AuditPlanCreateDTO createDTO){
        //检验上传文件是否符合要求
        if(createDTO.getWordFile().isEmpty()||createDTO.getPdfFile().isEmpty()){
            throw new BaseException("上传文件缺失，必须包含word与pdf文档");
        }
        Map<String, MultipartFile> fileMap=new HashMap<>();
        fileMap.put(createDTO.getWordFile().getContentType(),createDTO.getWordFile());
        fileMap.put(createDTO.getPdfFile().getContentType(), createDTO.getPdfFile());

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

    @GetMapping("/{id}")
    @Operation(summary = "通过id获取审核方案")
    public Result<AuditPlanDisplayVO> getAuditPlanById(@PathVariable Long id){
        AuditPlanDisplayVO result = auditPlanService.getById(id);
        return Result.success(result);
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
    public Result<PageResult<AuditPlanDisplayVO>> getAuditPlanByQueryPage(AuditPlanPageQueryDTO pageQueryDTO){
        PageResult<AuditPlanDisplayVO> result=auditPlanService.queryPage(pageQueryDTO);
        return Result.success(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "通过id删除审核方案")
    public Result<String> deleteAuditPlanByID(@PathVariable Long id){
        auditPlanService.deleteById(id);
        return Result.success("删除成功");
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新审核方案")
    public Result<UpdateResult<AuditPlan>> updateAuditPlan(@PathVariable Long id,
                                                           AuditPlanUpdateDTO updateDTO){
        UpdateResult<AuditPlan> updateResult=auditPlanService.updateAuditPlan(id,updateDTO);
        return Result.success(updateResult);
    }

    @GetMapping("/download")
    @Operation(summary = "下载审核方案文档")
    public Result<String> downloadAuditPlan(String uuid,
                                            String fileName,
                                            HttpServletResponse response){
        auditPlanService.download(uuid,fileName,response);
        return null;
    }



}
