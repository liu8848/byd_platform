package com.platform.dto.FactoryContact;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "工厂体系接口人创建数据模型")
public class FactoryContactCreateDTO {

    @Schema(description = "事业部编号")
    private Long buId;

    @Schema(description = "备案工厂编号")
    private Long recordFactoryId;

    @Schema(description = "体系接口人编号")
    private Long employeeId;
}
