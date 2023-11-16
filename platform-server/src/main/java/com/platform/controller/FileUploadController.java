package com.platform.controller;

import com.platform.service.upload.UploadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@Tag(name = "测试文件上传")
@RequestMapping("/api/test/upload")
public class FileUploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping
    public String upload(@RequestParam("file")MultipartFile file) throws Exception{

        String fileLocation = uploadService.upload(file,"/test");
        return null;
    }
}
