package com.platform.dto.auditPlan;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "更新审核方案数据模型")
public class AuditPlanUpdateDTO implements Serializable {

    private String fileName;

    private LocalDateTime publishTime;

    private MultipartFile wordFile;

    private MultipartFile pdfFile;
}
