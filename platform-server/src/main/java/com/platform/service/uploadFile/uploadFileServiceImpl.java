package com.platform.service.uploadFile;

import com.platform.mapper.UploadFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class uploadFileServiceImpl implements uploadFileService{
    @Autowired
    private UploadFileMapper uploadFileMapper;
}
