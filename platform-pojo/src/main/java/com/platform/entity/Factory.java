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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "备案工厂基本数据模型")
public class Factory implements Serializable {
    @Schema(description = "备案工厂主键编号")
    private Long id;

    @Schema(description = "备案工厂名称")
    private String name;

    @Schema(description = "工厂星级")
    private Long level;

    @Schema(description = "工厂所属事业部编号")
    private Long buId;

    @Schema(description = "预警时间")
    private LocalDateTime warnTime;

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

    @Schema(description = "工厂的所属事业部")
    private BusinessDivision bu;


}
