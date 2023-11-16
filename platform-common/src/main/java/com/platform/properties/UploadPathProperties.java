package com.platform.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "upload-path")
@Data
public class UploadPathProperties {

    private String DEFAULT_PATH;
}
