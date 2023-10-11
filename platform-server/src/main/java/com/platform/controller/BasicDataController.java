package com.platform.controller;

import com.platform.constant.MessageConstant;
import com.platform.dto.BusinessDivisionCreateDTO;
import com.platform.dto.BusinessDivisionPageQueryDTO;
import com.platform.dto.BusinessDivisionUpdateDTO;
import com.platform.dto.FactoryPageQueryDTO;
import com.platform.entity.BusinessDivision;
import com.platform.entity.Factory;
import com.platform.exception.BaseException;
import com.platform.exception.BusinessDivisionNotExistException;
import com.platform.result.PageResult;
import com.platform.result.Result;
import com.platform.service.BusinessDivisionService;
import com.platform.service.FactoryService;
import com.platform.vo.FactoryDisplayVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/basicData")
@Slf4j
@Tag(name = "基础数据相关接口")
public class BasicDataController {

    @Autowired
    private BusinessDivisionService businessDivisionService;
    @Autowired
    private FactoryService factoryService;

    @PostMapping("/businessDivision")
    @Operation(summary = "添加事业部")
    public Result<String> addBusinessDivision(@RequestBody BusinessDivisionCreateDTO businessDivisionCreateDTO){

        businessDivisionService.add(businessDivisionCreateDTO);

        return Result.success("添加成功");
    }

    @GetMapping("/businessDivision/{buId}")
    @Operation(summary = "根据事业部编号获取事业部信息")
    public Result<BusinessDivision> getByBuId(@PathVariable Long buId){
        BusinessDivision bu = businessDivisionService.getByBuId(buId);
        return Result.success(bu);
    }

    @GetMapping("/businessDivision")
    @Operation(summary = "根据搜索条件分页获取事业部信息")
    @Parameter(name = "businessDivisionPageQueryDTO",description = "事业部分页搜索模型",in = ParameterIn.QUERY)
    public Result<PageResult<BusinessDivision>> getBUPageByQuery(BusinessDivisionPageQueryDTO businessDivisionPageQueryDTO){
        PageResult<BusinessDivision> buPage = businessDivisionService.getBUPageByQuery(businessDivisionPageQueryDTO);
        return Result.success(buPage);
    }

    @DeleteMapping("/businessDivision/{buId}")
    @Operation(summary = "根据事业部编号删除")
    public Result<String> deleteBUByBuId(@PathVariable long buId){
        BusinessDivision bu = businessDivisionService.getByBuId(buId);
        if(bu==null)
            throw new BusinessDivisionNotExistException(MessageConstant.BUSINESSDIVISION_NOT_EXIST);
        long factoryCnt=factoryService.countFactoryByBuId(buId);
        if(factoryCnt>0)
            throw new BaseException("事业部中存在工厂，无法删除！");

        businessDivisionService.deleteByBuId(buId);
        return Result.success("事业部："+bu.getBuName()+"  删除成功！");
    }

    @PutMapping("/businessDivision/{buId}")
    @Operation(summary = "修改事业部信息")
    public Result<String> updateBU(@PathVariable long buId,
            @RequestBody BusinessDivisionUpdateDTO businessDivisionUpdateDTO){
        BusinessDivision businessDivision = BusinessDivision.builder().buId(buId).build();
        BeanUtils.copyProperties(businessDivisionUpdateDTO,businessDivision);
        businessDivisionService.updateBU(businessDivision);
        return Result.success("修改成功");
    }

    @GetMapping("/factory/{id}")
    @Operation(summary = "通过工厂编号获取工厂信息")
    public Result<Factory> getFactoryById(@PathVariable Long id){
        Factory factory = factoryService.getFactoryById(id);
        return Result.success(factory);
    }

    @GetMapping("/factory")
    @Operation(summary = "分页查询搜索备案工厂信息")
    @Parameter(name = "factoryPageQueryDTO",description = "分页查询备案工厂数据模型",in = ParameterIn.QUERY)
    public Result<PageResult<FactoryDisplayVO>> getFactoryPageByQuery(FactoryPageQueryDTO factoryPageQueryDTO){
        PageResult<FactoryDisplayVO> result = factoryService.getFactoryPageByQuery(factoryPageQueryDTO);

        return Result.success(result);
    }

}
