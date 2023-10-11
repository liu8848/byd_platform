package com.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "事业部分页搜索条件数据模型")
@Builder
public class BusinessDivisionPageQueryDTO implements Serializable {
    @Schema(description = "事业部编号")
    private Long buId;
    @Schema(description = "事业部名称")
    private String buName;

    @Schema(description = "页码")
    private Integer page;
    @Schema(description = "每页显示记录数")
    private Integer size;
}
