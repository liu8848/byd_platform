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
@Schema(description = "备案工厂创建数据模型")
public class FactoryCreateDTO implements Serializable {
    @Schema(description = "工厂名称")
    private String name;
    @Schema(description = "事业部编号")
    private Long buId;
    @Schema(description = "星级")
    private String level;
}
