package com.platform.dto;

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
@Schema(description = "专业检查分页条件检索数据模型")
public class ProfessionInspectionPageQueryDTO implements Serializable {
    @Schema(description = "事件名称")
    private String name;
    @Schema(description = "创建人工号")
    private Long createUserId;
    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "页码")
    private int page;
    @Schema(description = "每页记录数")
    private int size;
}
