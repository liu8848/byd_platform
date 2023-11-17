package com.platform.dto.auditPlan;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "审核方案创建数据模型")
public class AuditPlanCreateDTO implements Serializable {

    @Schema(description = "文件名称")
    private String fileName;

    @Schema(description = "文件发布时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String publishTime;

    @Schema(description = "word文件名称")
    private String wordName;

    @Schema(description = "word文件储存地址")
    private String wordPath;

    @Schema(description = "pdf文件名称")
    private String pdfName;

    @Schema(description = "pdf文件储存地址")
    private String pdfPath;

}
