package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "事业部基础数据模型")
public class BusinessDivision implements Serializable {
    @Schema(description = "事业部主键Id")
    private Long buId;

    @Schema(description = "事业部名称")
    private String buName;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "创建人工号")
    private Long createUser;

    @Schema(description = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "修改人工号")
    private Long updateUser;

    @Schema(description = "事业部中包含的工厂集合")
    private List<Factory> factories;
}
