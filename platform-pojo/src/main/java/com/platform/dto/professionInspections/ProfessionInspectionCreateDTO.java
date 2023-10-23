package com.platform.dto.professionInspections;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "创建专业检查数据模型")
public class ProfessionInspectionCreateDTO implements Serializable {
    @Schema(description = "专业检查名称")
    private String name;
}
