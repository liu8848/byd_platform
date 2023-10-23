package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema(description = "审核员")
public class Auditor extends BaseEntity implements Serializable{
    @Schema(description = "主键")
    private Long id;
    @Schema(description = "工号")
    private Long employeeId;
    @Schema(description = "备案工厂编号")
    private Long recordFactoryId;
    @Schema(description = "学历代码")
    private int education;
    private String educationName;
    @Schema(description = "审核员等级")
    private String auditorLevel;
    @Schema(description = "手机号码")
    private String phone;
    @Schema(description = "注册编号")
    private String registrationNumber;
    @Schema(description = "工艺类别代码")
    private String technology;
    @Schema(description = "类型代码")
    private int type;

    @Schema(description = "关联人员信息")
    private Employee employee;
}
