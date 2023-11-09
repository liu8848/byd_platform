package com.platform.dto.employees;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.platform.annotations.EducationValidate;
import com.platform.annotations.GradeValidate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "员工信息更新数据模型")
public class EmployeeUpdateDTO implements Serializable {

    @Schema(description = "工号")
    private Long id;


    @Schema(description = "姓名")
    private String name;

    @Schema(description = "级别")
    private int grade;

    @Schema(description = "性别")
    private int gender;

    @Schema(description = "学历")
    private int education;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "事业部编号")
    private Long buId;

    @Schema(description = "工厂编号")
    private Long factoryId;


    @Schema(description = "部门编号")
    private Long departmentId;

    @Schema(description = "工作地点编号")
    private Long locationId;

    @Schema(description = "任职状态")
    private int workStatus;
}
