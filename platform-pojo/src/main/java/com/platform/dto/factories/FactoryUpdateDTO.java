package com.platform.dto.factories;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "修改备案工厂数据模型")
public class FactoryUpdateDTO implements Serializable {
    @Schema(description = "备案工厂名称")
    private String name;
    @Schema(description = "所属事业部编号")
    private Long buId;
    @Schema(description = "星级")
    private int level;
    @Schema(description = "预警时间")
    private LocalDateTime warnTime;
}
