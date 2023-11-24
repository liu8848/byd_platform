package com.platform.dto.auditPlan;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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
    private LocalDateTime publishTime;

    @Schema(description = "word文件")
    private MultipartFile wordFile;


    @Schema(description = "pdf文件")
    private MultipartFile pdfFile;

}
