package com.platform.service.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface UploadService {
    String upload(MultipartFile file, String baseDir) throws Exception;
}
