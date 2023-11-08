package com.platform.dto.employees;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.platform.annotations.EducationValidate;
import com.platform.annotations.GenderConvertor;
import com.platform.annotations.GradeValidate;
import com.platform.constant.ValidateMessageConstant;
import com.platform.utils.convertors.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "创建员工数据模型")
public class EmployeeCreateDTO implements Serializable {

    @Schema(description = "员工姓名")
    @NotNull(message = ValidateMessageConstant.NAME_NOT_NULL)
    @ExcelProperty(value = "姓名")
    private String name;

    @Schema(description = "员工级别")
    @GradeValidate
    @ExcelProperty(value = "级别",converter = GradeConvertor.class)
    private int grade;

    @Schema(description = "性别")
    @ExcelProperty(value = "性别",converter = GenderConvertor.class)
    private int gender;

    @Schema(description = "学历")
    @EducationValidate
    @ExcelProperty(value = "学历",converter = EducationConvertor.class)
    private int education;

    @Schema(description = "邮箱")
    @Email(message = "邮箱格式错误")
    @ExcelProperty("邮箱")
    private String email;

    @Schema(description = "工厂编号")
    @ExcelProperty(value = "工厂",converter = FactoryConvertor.class)
    private Long factoryId;

    @Schema(description = "部门编号")
    @ExcelProperty(value = "部门",converter = DepartmentConvertor.class)
    private Long departmentId;

    @Schema(description = "工作地点编号")
    @ExcelProperty(value = "工作地点",converter = LocationConvertor.class)
    private Long locationId;

    @Schema(description = "在职状态")
    @ExcelProperty(value = "在职状态",converter = WorkStatusConvertor.class)
    private int workStatus;
}
