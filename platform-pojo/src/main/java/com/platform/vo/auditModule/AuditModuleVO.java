package com.platform.vo.auditModule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "审核模块展示模型")
public class AuditModuleVO implements Serializable {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "审核模块名称")
    private String moduleName;

    @Schema(description = "创建人工号")
    private Long createUser;

    @Schema(description = "创建人姓名")
    private String createUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改人工号")
    private Long updateUser;

    @Schema(description = "修改人姓名")
    private String updateUserName;

    @Schema(description = "修改时间")
    private LocalDateTime updateTime;

}
