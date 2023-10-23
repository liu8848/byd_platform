package com.platform.dto.professionInspections;

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
@Schema(description = "修改专业检查数据模型")
public class ProfessionInspectionUpdateDTO implements Serializable {
    private String name;

}
