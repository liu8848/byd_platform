package com.platform.dto.businessdivisions;

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
@Schema(description = "事业部修改数据模型")
public class BusinessDivisionUpdateDTO implements Serializable {
    @Schema(description = "事业部名称")
    private String buName;
}
