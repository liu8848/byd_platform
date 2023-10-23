package com.platform.dto.processes;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "创建流程数据模型")
public class ProcessCreateDTO implements Serializable {

    @Schema(description = "流程名称")
    private String name;

    @Schema(description = "审批人工号")
    private long approverId;

}
