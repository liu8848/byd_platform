package com.platform.dto.auditors;

import com.platform.dto.BasePageQueryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuditorStandingBookChangePageQueryDTO
        extends BasePageQueryDTO
        implements Serializable {
    @Schema(description = "事业部编号")
    private Long buId;

    @Schema(description = "事业部名称")
    private String buName;

    @Schema(description = "备案工厂编号")
    private Long recordFactoryId;

    @Schema(description = "备案工厂名称")
    private String recordFactoryName;

    @Schema(description = "工号")
    private Long employeeId;

    @Schema(description = "姓名")
    private String employeeName;

    @Schema(description = "审核员等级")
    private Long auditorLevel;
}
