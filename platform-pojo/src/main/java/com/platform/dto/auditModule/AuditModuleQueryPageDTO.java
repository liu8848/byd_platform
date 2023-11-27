package com.platform.dto.auditModule;

import com.platform.dto.BasePageQueryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "分页检索审核模块")
public class AuditModuleQueryPageDTO extends BasePageQueryDTO implements Serializable {
    @Schema(description = "主键")
    private Long id;
    @Schema(description = "审核模块名称")
    private String moduleName;
}
