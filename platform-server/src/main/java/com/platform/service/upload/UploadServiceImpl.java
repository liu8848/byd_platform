package com.platform.service.upload;

import com.platform.exception.BaseException;
import com.platform.properties.UploadPathProperties;
import com.platform.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadPathProperties pathProperties;
    @Override
    public String upload(MultipartFile file, String baseDir) throws Exception {
        String originalFileName=file.getOriginalFilename();
        log.info(pathProperties.getDEFAULT_PATH());

        if (ObjectUtils.isEmpty(originalFileName)){
            throw new BaseException("上传文件不能为空");
        }

        String fileLocation=null;
        if(baseDir!=null){
            fileLocation= FileUploadUtils.upload(baseDir,file);
        }else {
            fileLocation=FileUploadUtils.upload(file);
        }
        return fileLocation;
    }
}
