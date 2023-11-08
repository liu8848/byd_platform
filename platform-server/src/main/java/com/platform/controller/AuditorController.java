package com.platform.controller;

import com.alibaba.excel.EasyExcel;
import com.platform.annotations.EducationValidate;
import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.dto.auditors.AuditorPageQueryDTO;
import com.platform.entity.Auditor;
import com.platform.entity.AuditorStandingBookInWork;
import com.platform.exception.BaseException;
import com.platform.result.PageResult;
import com.platform.result.Result;
import com.platform.service.AuditorService;
import com.platform.utils.CustomMergeStrategy;
import com.platform.utils.ExcelUtil;
import com.platform.vo.AuditorDisplayVO;
import com.platform.vo.AuditorStandingBookInWorkVO;
import com.platform.vo.StandingBookAuditorExportVO;
import io.swagger.v3.oas.annotations.Operation;
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
    public Result<PageResult<AuditorStandingBookInWork>> getPageQueryOnWorkAuditor(AuditorPageQueryDTO pageQueryDTO) {
        PageResult<AuditorStandingBookInWork> result = auditorService.getPageQueryStandingBookInWork(pageQueryDTO);
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

}
