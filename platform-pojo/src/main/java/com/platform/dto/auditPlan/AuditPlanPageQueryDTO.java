package com.platform.dto.auditPlan;

import com.platform.dto.BasePageQueryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "审核计划分页查询数据模型")
public class AuditPlanPageQueryDTO extends BasePageQueryDTO implements Serializable {

    @Schema(description = "方案名称")
    private String fileName;

    @Schema(description = "开始发布时间")
    private LocalDateTime startPublishTime;

    @Schema(description = "结束发布时间")
    private LocalDateTime endPublishTime;

}
