package com.platform.controller;

import com.alibaba.excel.EasyExcel;
import com.platform.annotations.EducationValidate;
import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.dto.auditors.AuditorPageQueryDTO;
import com.platform.dto.auditors.AuditorStandingBookChangePageQueryDTO;
import com.platform.dto.auditors.AuditorUpdateDTO;
import com.platform.entity.Auditor;
import com.platform.entity.AuditorStandingBookChange;
import com.platform.entity.AuditorStandingBookInWork;
import com.platform.exception.BaseException;
import com.platform.result.PageResult;
import com.platform.result.Result;
import com.platform.service.AuditorService;
import com.platform.utils.CustomMergeStrategy;
import com.platform.utils.ExcelUtil;
import com.platform.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@Validated
@RequestMapping("/api/auditor")
@Tag(name = "审核员台账相关接口")
public class AuditorController {
    @Autowired
    private AuditorService auditorService;


    @GetMapping("/list")
    @Operation(summary = "获取在职审核员台账信息")
    public Result<List<AuditorStandingBookInWorkVO>> getOnWorkAuditor() throws NoSuchFieldException {
        List<AuditorStandingBookInWorkVO> standingBookInWork = auditorService.getStandingBookInWork();
        return Result.success(standingBookInWork);
    }

    @GetMapping("/all")
    @Operation(summary = "获取所有审核员信息")
    public Result<List<AuditorDisplayVO>> getAllAuditor() {
        List<AuditorDisplayVO> auditorAll = auditorService.getAuditorAll();
        return Result.success(auditorAll);
    }

    @PostMapping("/add")
    @Operation(summary = "添加审核员")
    public Result<Auditor> createAuditor(@Validated @RequestBody AuditorCreateDTO auditorCreateDTO,
                                         BindingResult bindingResult) {
        Auditor auditor = auditorService.insert(auditorCreateDTO);
        return Result.success(auditor);
    }

    @GetMapping("/pageQuery")
    @Operation(summary = "在职审核员台账分页查询")
    @Parameters({
            @Parameter(name = "levelMatch",description = "星级匹配情况",in = ParameterIn.QUERY),
            @Parameter(name = "level",description = "星级",in = ParameterIn.QUERY),
            @Parameter(name = "buId",description = "事业部",in = ParameterIn.QUERY),
            @Parameter(name = "recordFactoryId",description = "备案工厂",in = ParameterIn.QUERY),
            @Parameter(name = "employeeName",description = "姓名",in = ParameterIn.QUERY),
            @Parameter(name = "auditorLevel",description = "审核员等级",in = ParameterIn.QUERY),
            @Parameter(name = "page",description = "页码",in = ParameterIn.QUERY),
            @Parameter(name = "size",description = "每页记录数",in = ParameterIn.QUERY),
    })
    public Result<PageResult<AuditorStandingBookInWorkVO>> getPageQueryOnWorkAuditor(AuditorPageQueryDTO pageQueryDTO) {
        PageResult<AuditorStandingBookInWorkVO> result = auditorService.getPageQueryStandingBookInWork(pageQueryDTO);
        return Result.success(result);
    }

    @DeleteMapping("/{employeeId}")
    @Operation(summary = "通过工号删除审核员")
    public Result<String> deleteAuditor(@PathVariable Long employeeId) {
        auditorService.deleteAuditorByEmployeeId(employeeId);
        return Result.success("删除成功");
    }

    @PutMapping("/{employeeId}")
    @Operation(summary = "更新审核员优先安排状态")
    public Result<String> updateAuditorArrangement(@PathVariable Long employeeId, Boolean isArrange) {
        auditorService.updateAuditorArrangement(employeeId, isArrange);
        return Result.success();
    }

    @PostMapping("/insertByCollection")
    @Operation(summary = "批量导入审核员")
    public Result<List<AuditorDisplayVO>> importAuditor(@Valid @RequestBody  List<AuditorCreateDTO> createDTOS) {

        List<AuditorDisplayVO> auditors = auditorService.importAuditors(createDTOS);
        return Result.success(auditors);
    }

    @PostMapping("/importAuditorsByExcel")
    @Operation(summary = "导入excel批量添加审核员")
    public Result<List<AuditorDisplayVO>>
    importAuditorsByExcel(@RequestParam("file")MultipartFile file,
                          @RequestParam(value = "updateSupport",required = false,defaultValue = "false")
                          Boolean updateSupport) throws IOException {
        List<AuditorCreateDTO> createDTOS = ExcelUtil.read(file, AuditorCreateDTO.class);
        List<AuditorDisplayVO> auditors = auditorService.importAuditors(createDTOS);
        return Result.success(auditors);
    }

    @GetMapping("/exportStandingBookInWork")
    @Operation(summary = "导出在职审核员工作台账Excel")
    public void exportStandingBookInWorkExcel(HttpServletResponse response){
        try{
            String fileName="在职审核员台账"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-hh:mm:ss"));
            //设置响应头
            ExcelUtil.setExcelResponseProp(response, fileName);

            //获取导出数据
            List<AuditorStandingBookInWorkVO> standingBookInWork = auditorService.getStandingBookInWork();
            //将数据平铺
            List<StandingBookAuditorExportVO> exportData=new ArrayList<>();
            for (AuditorStandingBookInWorkVO auditorStandingBookInWorkVO: standingBookInWork) {
                for (AuditorDisplayVO auditorDisplayVO :auditorStandingBookInWorkVO.getAuditors()) {
                    StandingBookAuditorExportVO tmp=new StandingBookAuditorExportVO();
                    BeanUtils.copyProperties(auditorStandingBookInWorkVO,tmp);
                    BeanUtils.copyProperties(auditorDisplayVO,tmp);
                    exportData.add(tmp);
                }
            }

            log.info("开始写入数据:{}.xlsx",fileName);
            //将数据写入Excel
            EasyExcel.write(response.getOutputStream()).head(StandingBookAuditorExportVO.class)
                    .registerWriteHandler(new CustomMergeStrategy(StandingBookAuditorExportVO.class))
                    .sheet().doWrite(exportData);
            log.info("数据写入完成");
        }catch (UnsupportedEncodingException ex){
            throw new BaseException(ex.getMessage());
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }


    @GetMapping("/auditorStandingBookChange/list")
    @Operation(summary = "获取事业部变动审核员台账")
    public Result<List<AuditorStandingBookChangeDisplayVO>> getAuditorStandingBookChange(){
        List<AuditorStandingBookChangeDisplayVO> result=auditorService.getAuditorStandingBookChange();
        return Result.success(result);
    }

    @GetMapping("/auditorStandingBookChange/pageAndCondition")
    @Operation(summary = "分页条件查询事业部变动审核员台账")
    @Parameters(
            {
                    @Parameter(name = "buId",description = "事业部编号",in = ParameterIn.QUERY),
                    @Parameter(name = "buName",description = "事业部名称",in = ParameterIn.QUERY),
                    @Parameter(name = "recordFactoryId",description = "备案工厂编号",in = ParameterIn.QUERY),
                    @Parameter(name = "recordFactoryName",description = "备案工厂名称",in = ParameterIn.QUERY),
                    @Parameter(name = "employeeId",description = "工号",in = ParameterIn.QUERY),
                    @Parameter(name = "employeeId",description = "姓名",in = ParameterIn.QUERY),
                    @Parameter(name = "auditorLevel",description = "审核员等级",in = ParameterIn.QUERY),
                    @Parameter(name = "page",description = "页码",in = ParameterIn.QUERY),
                    @Parameter(name = "size",description = "每页记录数",in = ParameterIn.QUERY)
            }
    )
    public Result<PageResult<AuditorStandingBookChangeDisplayVO>> queryAndPageAuditorStandingBookChange
            (AuditorStandingBookChangePageQueryDTO pageQueryDTO){
        PageResult<AuditorStandingBookChangeDisplayVO> pageResult= auditorService.queryAndPageAuditorStandingBookChange(pageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/auditorStandingBookChange/{employeeId}")
    @Operation(summary = "编辑事业部变动审核员台账")
    @Parameters(
            {
                    @Parameter(name = "employeeId", description = "工号", required = true),
                    @Parameter(name = "recordFactoryId", description = "备案工厂", required = true,in = ParameterIn.QUERY)
            }
    )
    public Result<String> updateAuditorStandingBookChange(@PathVariable Long employeeId,
                                                          Long recordFactoryId){
        auditorService.updateAuditorStandingBookChange(employeeId,recordFactoryId);
        return Result.success("编辑成功");
    }

    @DeleteMapping("/auditorStandingBookChange/{employeeId}")
    @Operation(summary = "删除事业部变动审核员台账")
    public Result<String> deleteAuditorStandingBookChange(@PathVariable Long employeeId){
        auditorService.deleteAuditorStandingBookChange(employeeId);
        return Result.success("删除成功");
    }

    @GetMapping("/auditorStandingBookOutWork/list")
    @Operation(summary = "离职审核员台账查询")
    public Result<List<AuditorStandingBookOutWorkVO>> getAuditorStandingBookOutWork(){
        List<AuditorStandingBookOutWorkVO> result=auditorService.getAuditorStandingBookOutWork();
        return Result.success(result);
    }

    @GetMapping("/auditorStandingBookOutWork/list/export")
    @Operation(summary = "导出离职审核员台账EXCEL")
    public void exportAuditorStandingBookOutWork(HttpServletResponse response){
        try{
            String fileName="离职审核员台账"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
            //设置响应头
            ExcelUtil.setExcelResponseProp(response, fileName);

            //获取导出数据
            List<AuditorStandingBookOutWorkVO> result=auditorService.getAuditorStandingBookOutWork();

            log.info("开始写入数据:{}.xlsx",fileName);
            //将数据写入Excel
            EasyExcel.write(response.getOutputStream()).head(AuditorStandingBookOutWorkVO.class)
                    .sheet().doWrite(result);
            log.info("数据写入完成");
        }catch (UnsupportedEncodingException ex){
            throw new BaseException(ex.getMessage());
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }

}
