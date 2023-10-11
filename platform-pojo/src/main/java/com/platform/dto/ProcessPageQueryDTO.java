package com.platform.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "流程分页查询数据模型")
public class ProcessPageQueryDTO implements Serializable {

    @Schema(description = "申请人工号")
    @JsonProperty("applicantid")
    private Long applicantId;

    @Schema(description = "流程名称")
    private String name;

    @Schema(description = "流程状态")
    private String status;

    @Schema(description = "页码")
    private int page;

    @Schema(description = "每页显示记录数")
    private int size;
}
