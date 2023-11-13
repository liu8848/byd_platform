package com.platform.controller;

import com.platform.dto.FactoryContact.FactoryContactCreateDTO;
import com.platform.entity.FactoryContact;
import com.platform.result.Result;
import com.platform.service.contact.ContactService;
import com.platform.vo.factoryContact.FactoryContactVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
