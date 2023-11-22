package com.platform.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "platform-setting.date-format")
public class DateFormatProperties {

    private String DEFAULT_DATE_TIME_FORMAT;

    private String DEFAULT_DATE_FORMAT;

    private String DEFAULT_TIME_FORMAT;
}
