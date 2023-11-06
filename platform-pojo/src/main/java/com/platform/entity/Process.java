package com.platform.entity;

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
@Schema(description = "流程的实体模型")
public class Process implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "流程编号")
    private String id;

    @Schema(description = "流程名称")
    private String name;

    @Schema(description = "申请人工号")
    private long applicantId;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "审批人工号")
    private Long approverId;

    @Schema(description = "审批时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime approveTime;

    @Schema(description = "修改人工号")
    private Long updateUser;

    @Schema(description = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "流程状态")
    private String status;
}
