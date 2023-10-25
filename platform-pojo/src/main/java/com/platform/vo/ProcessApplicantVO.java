package com.platform.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "我的申请流程展示模型")
public class ProcessApplicantVO {
    @Schema(description = "名称")
    private String name;

    @Schema(description = "流程编号")
    private String id;

    @Schema(description = "审批人")
    private String approver;

    @Schema(description = "流程状态")
    private String status;

    @Schema(description = "申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
}
