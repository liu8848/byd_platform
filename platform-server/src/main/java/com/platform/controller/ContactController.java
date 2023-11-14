package com.platform.controller;

import com.alibaba.excel.EasyExcel;
import com.platform.dto.FactoryContact.FactoryContactCreateDTO;
import com.platform.dto.FactoryContact.FactoryContactQueryPageDTO;
import com.platform.dto.FactoryContact.FactoryContactUpdateDTO;
import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.entity.FactoryContact;
import com.platform.result.PageResult;
import com.platform.result.Result;
import com.platform.result.UpdateResult;
import com.platform.service.contact.ContactService;
import com.platform.utils.CustomMergeStrategy;
import com.platform.utils.ExcelUtil;
import com.platform.vo.StandingBookAuditorExportVO;
import com.platform.vo.factoryContact.FactoryContactVO;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/contact")
@Slf4j
@Tag(name = "联系人相关接口")
@CrossOrigin
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/factoryContact/add")
    @Operation(summary = "创建工厂体系接口人")
    public Result<FactoryContact> addFactoryContact(@RequestBody FactoryContactCreateDTO createDTO){

        FactoryContact factoryContact=contactService.createFactoryContact(createDTO);

        return Result.success(factoryContact);
    }

    @GetMapping("/factoryContact/list")
    @Operation(summary = "检索所有工厂体系接口人")
    public Result<List<FactoryContactVO>> getFactoryContact(){

        List<FactoryContactVO> result=contactService.getFactoryContact();
        return Result.success(result);
    }

    @PutMapping("/factoryContact/{employeeId}")
    @Operation(summary = "修改工厂体系接口人")
    public Result<UpdateResult<FactoryContactUpdateDTO>> updateFactoryContact(@PathVariable Long employeeId,
                                                                     @RequestBody FactoryContactUpdateDTO updateDTO){
        updateDTO.setEmployeeId(employeeId);

        UpdateResult<FactoryContactUpdateDTO> updateResult=contactService.updateFactoryContact(updateDTO);
        return Result.success(updateResult);
    }

    @DeleteMapping("factoryContact/{employeeId}")
    @Operation(summary = "删除工厂体系接口人")
    public Result<String> deleteFactoryContact(@PathVariable Long employeeId){
        int row = contactService.deleteFactoryContent(employeeId);
        return Result.success("删除成功 "+row+" 条记录");
    }

    @GetMapping("/factoryContact/queryPage")
    @Operation(summary = "分页条件查询体系接口人")
    @Parameters(value = {
            @Parameter(name = "buId",description = "事业部编号",in = ParameterIn.QUERY),
            @Parameter(name = "recordFactoryId",description = "备案工厂编号",in = ParameterIn.QUERY),
            @Parameter(name ="page",description = "页码",in = ParameterIn.QUERY),
            @Parameter(name = "size",description = "每页记录数",in = ParameterIn.QUERY)
    })
    public Result<PageResult<FactoryContactVO>> getFactoryContactQueryPage(FactoryContactQueryPageDTO queryPageDTO){
        PageResult<FactoryContactVO> pageResult=contactService.getFactoryContactQueryPage(queryPageDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/factoryContact/export")
    @Operation(summary = "导出工厂体系接口人")
    @Parameters(value = {
            @Parameter(name = "buId",description = "事业部编号",in = ParameterIn.QUERY),
            @Parameter(name = "recordFactoryId",description = "备案工厂编号",in = ParameterIn.QUERY)
    })
    public void exportFactoryContact(FactoryContactQueryPageDTO queryPageDTO,
                                     HttpServletResponse response){
        //将分页记录数设置为最大
        queryPageDTO.setSize(Integer.MAX_VALUE);

        try {
            String fileName="工厂体系接口人"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
            //设置响应头
            ExcelUtil.setExcelResponseProp(response, fileName);
            //获取导出数据
            PageResult<FactoryContactVO> result = contactService.getFactoryContactQueryPage(queryPageDTO);
            log.info("开始写入数据:{}.xlsx",fileName);
            EasyExcel.write(response.getOutputStream()).head(FactoryContactVO.class)
                    .sheet().doWrite(result.getRecords());
            log.info("写入完成...................");
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }

    @GetMapping("/factoryContact/importTemplate")
    @Operation(summary = "获取工厂体系接口人导入模板")
    public void getFactoryContactTemplate(HttpServletResponse response){
        List<String> columnNames= Arrays.asList("事业部","备案工厂","姓名","工号");

    }

    @PostMapping("factoryContact/import")
    @Operation(summary = "导入工厂体系接口人")
    public Result<List<FactoryContact>> importFactoryContact(@RequestParam("file")MultipartFile file,
                                                             @RequestParam(value = "updateSupport",required = false,defaultValue = "false")
                                                             Boolean updateSupport){
        try {
            List<AuditorCreateDTO> createDTOS = ExcelUtil.read(file, AuditorCreateDTO.class);
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
        return null;
    }
}
