package com.platform.controller;

import com.platform.constant.MessageConstant;
import com.platform.dto.*;
import com.platform.entity.BusinessDivision;
import com.platform.entity.Factory;
import com.platform.entity.ProfessionInspection;
import com.platform.exception.BaseException;
import com.platform.exception.BusinessDivisionNotExistException;
import com.platform.result.PageResult;
import com.platform.result.Result;
import com.platform.result.UpdateResult;
import com.platform.service.BusinessDivisionService;
import com.platform.service.FactoryService;
import com.platform.service.ProfessionInspectionService;
import com.platform.vo.FactoryDisplayVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basicData")
@Slf4j
@Tag(name = "基础数据相关接口")
public class BasicDataController {

    @Autowired
    private BusinessDivisionService businessDivisionService;
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private ProfessionInspectionService professionInspectionService;

    @PostMapping("/businessDivision")
    @Operation(summary = "添加事业部")
    public Result<String> addBusinessDivision(@RequestBody BusinessDivisionCreateDTO businessDivisionCreateDTO) {

        businessDivisionService.add(businessDivisionCreateDTO);

        return Result.success("添加成功");
    }

    @GetMapping("/businessDivision/{buId}")
    @Operation(summary = "根据事业部编号获取事业部信息")
    public Result<BusinessDivision> getByBuId(@PathVariable Long buId) {
        BusinessDivision bu = businessDivisionService.getByBuId(buId);
        return Result.success(bu);
    }

    @GetMapping("/businessDivision")
    @Operation(summary = "根据搜索条件分页获取事业部信息")
    @Parameter(name = "businessDivisionPageQueryDTO", description = "事业部分页搜索模型", in = ParameterIn.QUERY)
    public Result<PageResult<BusinessDivision>> getBUPageByQuery(BusinessDivisionPageQueryDTO businessDivisionPageQueryDTO) {
        PageResult<BusinessDivision> buPage = businessDivisionService.getBUPageByQuery(businessDivisionPageQueryDTO);
        return Result.success(buPage);
    }

    @DeleteMapping("/businessDivision/{buId}")
    @Operation(summary = "根据事业部编号删除")
    public Result<String> deleteBUByBuId(@PathVariable long buId) {
        BusinessDivision bu = businessDivisionService.getByBuId(buId);
        if (bu == null)
            throw new BusinessDivisionNotExistException(MessageConstant.BUSINESSDIVISION_NOT_EXIST);
        long factoryCnt = factoryService.countFactoryByBuId(buId);
        if (factoryCnt > 0)
            throw new BaseException("事业部中存在工厂，无法删除！");

        businessDivisionService.deleteByBuId(buId);
        return Result.success("事业部：" + bu.getBuName() + "  删除成功！");
    }

    @PutMapping("/businessDivision/{buId}")
    @Operation(summary = "修改事业部信息")
    public Result<String> updateBU(@PathVariable long buId,
                                   @RequestBody BusinessDivisionUpdateDTO businessDivisionUpdateDTO) {
        BusinessDivision businessDivision = BusinessDivision.builder().buId(buId).build();
        BeanUtils.copyProperties(businessDivisionUpdateDTO, businessDivision);
        businessDivisionService.updateBU(businessDivision);
        return Result.success("修改成功");
    }

    @GetMapping("/factory/{id}")
    @Operation(summary = "通过工厂编号获取工厂信息")
    public Result<Factory> getFactoryById(@PathVariable Long id) {
        Factory factory = factoryService.getFactoryById(id);
        return Result.success(factory);
    }

    @GetMapping("/factory")
    @Operation(summary = "分页查询搜索备案工厂信息")
    @Parameter(name = "factoryPageQueryDTO", description = "分页查询备案工厂数据模型", in = ParameterIn.QUERY)
    public Result<PageResult<FactoryDisplayVO>> getFactoryPageByQuery(FactoryPageQueryDTO factoryPageQueryDTO) {
        PageResult<FactoryDisplayVO> result = factoryService.getFactoryPageByQuery(factoryPageQueryDTO);

        return Result.success(result);
    }

    @PostMapping("/factory")
    @Operation(summary = "创建备案工厂")
    public Result<Factory> createFactory(@RequestBody FactoryCreateDTO factoryCreateDTO) {
        Factory factory = factoryService.addFactory(factoryCreateDTO);
        return Result.success(factory);
    }

    @DeleteMapping("/factory/{id}")
    @Operation(summary = "通过工厂编号删除备案工厂")
    public Result<Long> deleteFactoryById(@PathVariable Long id) {
        long row = factoryService.deleteFactoryById(id);
        return Result.success(row);
    }

    @PostMapping("/factory/collection")
    @Operation(summary = "批量导入备案工厂")
    public Result<List<Factory>> createFactoryByCollection(@RequestBody List<FactoryCreateDTO> factories) {
        List<Factory> factoryList = factoryService.addFactoryByCollection(factories);
        return Result.success(factoryList);
    }

    @PutMapping("/factory/{id}")
    @Operation(summary = "修改备案工厂信息")
    public Result<UpdateResult<Factory>> updateFactory(@PathVariable @Schema(description = "备案工厂编号") Long id,
                                                       @RequestBody FactoryUpdateDTO factoryUpdateDTO) throws IllegalAccessException {
        Factory factory = Factory.builder()
                .id(id)
                .name(factoryUpdateDTO.getName())
                .buId(factoryUpdateDTO.getBuId())
                .level(factoryUpdateDTO.getLevel())
                .build();

        UpdateResult<Factory> updateResult = factoryService.updateFactory(factory);

        return Result.success(updateResult);
    }

    @GetMapping("/inspection/{id}")
    @Operation(summary = "根据专业检查id查询专业检查信息")
    public Result<ProfessionInspection> getInspectionById(@PathVariable Long id) {
        return Result.success(professionInspectionService.getById(id));
    }

    @GetMapping("/inspection")
    @Operation(summary = "分页条件检索专业检查信息")
    public Result<PageResult<ProfessionInspection>> getInspectionPageByQuery(ProfessionInspectionPageQueryDTO queryDTO) {
        PageResult<ProfessionInspection> page = professionInspectionService.getPageByQuery(queryDTO);
        return Result.success(page);
    }

    @PostMapping("/inspection")
    @Operation(summary = "创建专业检索")
    public Result<ProfessionInspection> addProfessionInspection(@RequestBody ProfessionInspectionCreateDTO createDTO) {
        ProfessionInspection professionInspection = professionInspectionService.insert(createDTO);
        return Result.success(professionInspection);
    }

    @PutMapping("/inspection/{id}")
    @Operation(summary = "修改专业检查信息")
    public Result<UpdateResult<ProfessionInspection>> updateProfessionInspection(@PathVariable Long id,
                                                                                 @RequestBody ProfessionInspectionUpdateDTO updateDTO) throws IllegalAccessException {
        ProfessionInspection professionInspection = ProfessionInspection.builder()
                .id(id)
                .name(updateDTO.getName()).build();
        UpdateResult<ProfessionInspection> updateResult = professionInspectionService.update(professionInspection);
        return Result.success(updateResult);
    }

    @DeleteMapping("/inspection/{id}")
    @Operation(summary = "删除专业检查")
    public Result<String> deleteProfessionInspection(@PathVariable Long id) {
        int row = professionInspectionService.delete(id);
        return Result.success(String.format("成功删除%d条专业检查", row));
    }

    @PostMapping("/inspection/collection")
    @Operation(summary = "批量导入专业检查")
    public Result<List<ProfessionInspection>> addProfessionInspectionByCollection(@RequestBody List<ProfessionInspectionCreateDTO> createDTOList) {
        List<ProfessionInspection> professionInspectionList = professionInspectionService.insertByCollection(createDTOList);
        return Result.success(professionInspectionList);
    }

}
