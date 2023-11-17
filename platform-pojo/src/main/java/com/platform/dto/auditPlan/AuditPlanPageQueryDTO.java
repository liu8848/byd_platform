package com.platform.dto.auditPlan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.platform.dto.BasePageQueryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "审核计划分页查询数据模型")
public class AuditPlanPageQueryDTO extends BasePageQueryDTO implements Serializable {

    @Schema(description = "方案名称")
    private String fileName;

    @Schema(description = "开始发布时间字符串")
    private String startPublishTimeStr;

    @Schema(description = "结束发布时间字符串")
    private String endPublishTimeStr;

    @Schema(description = "开始发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startPublishTime;

    @Schema(description = "结束发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endPublishTime;

}
