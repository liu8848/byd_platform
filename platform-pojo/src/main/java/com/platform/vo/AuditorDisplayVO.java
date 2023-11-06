package com.platform.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "审核员展示数据模型")
public class AuditorDisplayVO implements Serializable {
    @Schema(description = "工厂编号")
    private Long factoryId;
    @Schema(description = "工厂名")
    private String factoryName;
    @Schema(description = "部门编号")
    private Long departmentId;
    @Schema(description = "部门名称")
    private String departmentName;
    @Schema(description = "员工姓名")
    private String employeeName;
    @Schema(description = "员工工号")
    private Long employeeId;
    @Schema(description = "等级")
    private String grade;
    @Schema(description = "性别")
    private String gender;
    @Schema(description = "学历")
    private String education;
    @Schema(description = "审核员等级")
    private String auditorLevel;
    @Schema(description = "电子邮箱")
    private String email;
    @Schema(description = "电话")
    private String phone;
    @Schema(description = "注册编号")
    private String registrationNumber;
    @Schema(description = "工作地点编号")
    private Long locationId;
    @Schema(description = "工作地点名称")
    private String locationName;
    @Schema(description = "工艺类别名称")
    private String technologyName;
    @Schema(description = "在职状态")
    private String status;
    @Schema(description = "类别")
    private String type;
    @Schema(description = "优先安排")
    private Boolean arrangement;
}
