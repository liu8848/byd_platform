package com.platform.dto.auditModule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "审核模块添加或更新数据模型")
public class AuditModuleCreateOrUpdateDTO implements Serializable {
    @Schema(description = "主键")
    private Long id;

    @Schema(description = "审核模块名称")
    private String moduleName;
}
