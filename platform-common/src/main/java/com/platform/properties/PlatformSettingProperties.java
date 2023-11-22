package com.platform.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "platform-setting")
public class PlatformSettingProperties {

    //默认上传文件根目录
    @Value("${platform-setting.upload-path.default-path}")
    private String DEFAULT_PATH;


}
