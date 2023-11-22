package com.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "审核方案数据模型")
@TableName(value = "audit_plan")
public class AuditPlan extends BaseEntity implements Serializable {

    @Schema(description = "主键id")
    @TableField(value = "id")
    private Long id;

    @Schema(description = "文件名称")
    @TableField(value = "file_name")
    private String fileName;

    @Schema(description = "发布时间")
    @TableField(value = "publish_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishTime;

    @Schema(description = "word文档名称")
    @TableField(value = "word_name")
    private String wordName;

    @Schema(description = "word文档路径")
    @TableField(value = "word_path")
    private String wordPath;

    @Schema(description = "pdf文档名称")
    @TableField(value = "pdf_name")
    private String pdfName;

    @Schema(description = "pdf文档路径")
    @TableField(value = "pdf_path")
    private String pdfPath;
}
