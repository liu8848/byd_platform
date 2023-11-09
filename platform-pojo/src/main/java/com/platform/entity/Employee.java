package com.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@TableName(value = "employee")
public class Employee extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "工号")
    @TableId
    private Long id;


    @Schema(description = "姓名")
    @TableField(value = "name")
    private String name;

    @Schema(description = "级别")
    @TableField(value = "grade")
    private Integer grade;

    @Schema(description = "性别")
    @TableField(value = "gender")
    private Integer gender;

    @Schema(description = "学历")
    @TableField(value = "education")
    private Integer education;

    @Schema(description = "邮箱")
    @TableField(value = "email")
    private String email;

    @Schema(description = "电话")
    @TableField(value = "phone")
    private String phone;

    @Schema(description = "事业部编号")
    @TableField(value = "bu_id")
    private Long buId;

    @Schema(description = "事业部名称")
    private String buName;

    @Schema(description = "工厂编号")
    @TableField(value = "factory_id")
    private Long factoryId;

    @Schema(description = "工厂名")
    private String factoryName;

    @Schema(description = "部门编号")
    @TableField(value = "department_id")
    private Long departmentId;

    @Schema(description = "工作地点编号")
    @TableField(value = "location_id")
    private Long locationId;

    @Schema(description = "任职状态")
    @TableField(value = "work_status")
    private Integer workStatus;

    @Schema(description = "所属部门")
    private Department department;

}
