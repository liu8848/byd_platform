package com.platform.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "事业部变动审核员台账视图模型")
public class AuditorStandingBookChangeDisplayVO implements Serializable {

    @Schema(description = "现事业部编号")
    private Long nowBuId;

    @Schema(description = "现事业部名称")
    private String nowBuName;

    @Schema(description = "原事业部编号")
    private Long oldBuId;

    @Schema(description = "原事业部名称")
    private String oldBuName;

    @Schema(description = "备案工厂编号")
    private Long recordFactoryId;

    @Schema(description = "备案工厂名称")
    private String recordFactoryName;

    @Schema(description = "现工厂编号")
    private Long nowFactoryId;

    @Schema(description = "现工厂名称")
    private String nowFactoryName;

    @Schema(description = "原工厂编号")
    private Long oldFactoryId;

    @Schema(description = "原工厂名称")
    private String oldFactoryName;

    @Schema(description = "部门编号")
    private Long departmentId;

    @Schema(description = "部门名称")
    private String departmentName;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "工号")
    private Long employeeId;

    @Schema(description = "级别")
    private Long grade;
    @Schema(description = "级别名称")
    private String gradeName;

    @Schema(description = "性别")
    private Long gender;
    @Schema(description = "性别名称")
    private String genderName;

    @Schema(description = "学历")
    private Long education;
    @Schema(description = "学历名称")
    private String educationName;

    @Schema(description = "审核员级别")
    private Long auditorLevel;
    @Schema(description = "审核员级别名称")
    private String auditorLevelName;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机")
    private String phone;

    @Schema(description = "注册编号")
    private String registrationNumber;

    @Schema(description = "工作地点编号")
    private Long locationId;

    @Schema(description = "工作地点名称")
    private String locationName;

    @Schema(description = "工艺类别")
    private String technology;

    @Schema(description = "任职状态")
    private Long workStatus;
    @Schema(description = "任职状态名称")
    private String workStatusName;
}
