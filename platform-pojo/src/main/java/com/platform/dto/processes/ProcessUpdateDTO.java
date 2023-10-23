package com.platform.dto.processes;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "流程信息修改数据模型")
public class ProcessUpdateDTO implements Serializable {
    @Schema(description = "流程编号")
    private String id;

    @Schema(description = "流程名称")
    private String name;

    @Schema(description = "审批人工号")
    private Long approverId;

    @Schema(description = "流程状态")
    private String status;
}
