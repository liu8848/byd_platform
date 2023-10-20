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
@Builder
@Schema(description = "备案工厂分页搜索数据模型")
public class FactoryPageQueryDTO implements Serializable {
    @Schema(description = "所属事业部id")
    private Long buId;
    @Schema(description = "备案工厂名称")
    private String name;
    @Schema(description = "备案工厂星级")
    private int level;

    @Schema(description = "页码")
    private int page;
    @Schema(description = "每页显示记录数")
    private int size;
}
