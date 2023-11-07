package com.platform.controller;

import com.platform.commonModel.Dictionary;
import com.platform.result.Result;
import com.platform.service.dict.DictionaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/dict")
@Tag(name = "数据字典管理")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/factory")
    @Operation(summary = "获取工厂列表")
    public Result<List<Dictionary>> getFactoryDict() {
        List<Dictionary> factoryDict = dictionaryService.getFactoryDict();
        return Result.success(factoryDict);
    }

    @GetMapping("/technology")
    @Operation(summary = "获取专业检查列表")
    public Result<List<Dictionary>> getTechnologyDict() {
        List<Dictionary> technologyDict = dictionaryService.getTechnologyDict();
        return Result.success(technologyDict);
    }

    @GetMapping("/normalDict")
    @Operation(summary = "获取普通字典")
    public Result<List<Dictionary>> getNormalDict() {
        List<Dictionary> normalDict = dictionaryService.getNormalDict();
        return Result.success(normalDict);
    }

}
