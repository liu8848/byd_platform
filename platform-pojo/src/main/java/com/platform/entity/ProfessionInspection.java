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
@Schema(description = "专业检查数据模型")
public class ProfessionInspection implements Serializable {
    @Schema(description = "主键")
    private Long id;
    @Schema(description = "专业检查名称")
    private String name;
    @Schema(description = "创建人工号")
    private Long createUserId;
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
    @Schema(description = "修改人工号")
    private Long updateUser;
    @Schema(description = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "创建人信息")
    private Employee createUser;
}
