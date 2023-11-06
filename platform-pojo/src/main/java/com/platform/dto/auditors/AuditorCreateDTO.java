package com.platform.dto.auditors;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "审核员创建数据模型")
public class AuditorCreateDTO implements Serializable {

    @Schema(description = "工号")
    @NotNull(message = "工号不能为空")
    private Long employeeId;

    @Schema(description = "备案工厂编号")
    @NotNull(message = "备案工厂不能为空")
    private Long recordFactoryId;

    @Schema(description = "学历")
    @NotNull(message = "学历不能为空")
    private int education;

    @Schema(description = "审核员等级")
    @NotNull(message = "审核员等级不能为空")
    private int auditorLevel;

    @Schema(description = "电话")
    @NotEmpty(message = "电话不能为空")
    @Length(min = 11,max = 11)
    private String phone;

    @Schema(description = "注册编号")
    @NotNull(message = "注册编号不能为空")
    private String registrationNumber;

    @Schema(description = "工艺列表")
    @NotNull(message = "工艺列表不能为空")
    private String technology;
}
