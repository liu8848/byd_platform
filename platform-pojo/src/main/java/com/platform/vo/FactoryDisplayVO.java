package com.platform.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Schema(description = "备案工厂数据展示模型")
public class FactoryDisplayVO implements Serializable {
    @Schema(description = "备案工厂编号")
    private Long factoryId;
    @Schema(description = "事业部名称")
    private String buName;
    @Schema(description = "备案工厂名称")
    private String factoryName;
    @Schema(description = "星级")
    private String level;
    @Schema(description = "创建人")
    private String createUser;
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
}
