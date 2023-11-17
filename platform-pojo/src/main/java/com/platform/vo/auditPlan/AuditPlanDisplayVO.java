package com.platform.vo.auditPlan;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "审核计划展示数据模型")
public class AuditPlanDisplayVO implements Serializable {

    @Schema(description = "文件名称")
    private String fileName;

    @Schema(description = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishTime;

    @Schema(description = "Word文档名称")
    private String wordName;

    @Schema(description = "Word文档存放路径")
    private String wordPath;

    @Schema(description = "pdf文档名称")
    private String pdfName;

    @Schema(description = "pdf文档存放路径")
    private String pdfPath;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "创建人工号")
    private Long createUser;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;


    @Schema(description = "更新人工号")
    private Long updateUser;

    @Schema(description = "修改人姓名")
    private String updateUserName;

}
