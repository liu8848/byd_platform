package com.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.platform.enums.AuditorLevel;
import com.platform.enums.Education;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema(description = "审核员")
@TableName("auditor")
public class Auditor extends BaseEntity implements Serializable{
    @Schema(description = "主键")
    @TableId
    private Long id;

    @Schema(description = "工号")
    @TableField("employee_id")
    private Long employeeId;

    @Schema(description = "备案工厂编号")
    @TableField("record_factory_id")
    private Long recordFactoryId;

    @Schema(description = "学历代码")
    @TableField("education")
    private Education education;

    @Schema(description = "审核员等级")
    @TableField("auditor_level")
    private AuditorLevel auditorLevel;

    @Schema(description = "手机号码")
    @TableField("phone")
    private String phone;

    @Schema(description = "注册编号")
    @TableField("registration_number")
    private String registrationNumber;

    @Schema(description = "工艺类别代码")
    private String technology;

    @Schema(description = "类型代码")
    private int type;

    @Schema(description = "关联人员信息")
    private Employee employee;
    @Schema(description = "备案工厂信息")
    private Factory recordFactory;
    @Schema(description = "工艺类别列表")
    private List<ProfessionInspection> technologyList;
}
