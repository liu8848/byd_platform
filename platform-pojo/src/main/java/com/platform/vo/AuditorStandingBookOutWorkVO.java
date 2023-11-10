package com.platform.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "离职审核员台账展示数据模型")
public class AuditorStandingBookOutWorkVO implements Serializable {

    @Schema(description = "事业部编号")
    @ExcelIgnore
    private Long buId;

    @Schema(description = "事业部名称")
    @ExcelProperty(value = "事业部")
    private String buName;

    @Schema(description = "备案工厂编号")
    @ExcelIgnore
    private Long recordFactoryId;

    @Schema(description = "备案工厂名称")
    @ExcelProperty(value = "备案工厂")
    private String recordFactoryName;

    @Schema(description = "工厂编号")
    @ExcelIgnore
    private Long factoryId;

    @Schema(description = "工厂名称")
    @ExcelProperty(value = "工厂")
    private String factoryName;

    @Schema(description = "部门编号")
    @ExcelIgnore
    private Long departmentId;

    @Schema(description = "部门名称")
    @ExcelProperty(value = "部门")
    private String departmentName;

    @Schema(description = "姓名")
    @ExcelProperty(value = "姓名")
    private String name;

    @Schema(description = "工号")
    @ExcelProperty(value = "工号")
    private Long employeeId;

    @Schema(description = "级别")
    @ExcelIgnore
    private Long grade;
    @Schema(description = "级别名称")
    @ExcelProperty(value = "级别")
    private String gradeName;

    @Schema(description = "性别")
    @ExcelIgnore
    private Long gender;

    @Schema(description = "性别名称")
    @ExcelProperty(value = "性别")
    private String genderName;

    @Schema(description = "学历")
    @ExcelIgnore
    private Long education;
    @Schema(description = "学历名称")
    @ExcelProperty(value = "学历")
    private String educationName;

    @Schema(description = "审核员级别")
    @ExcelIgnore
    private Long auditorLevel;
    @Schema(description = "审核员级别名称")
    @ExcelProperty(value = "审核员级别")
    private String auditorLevelName;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机")
    private String phone;

    @Schema(description = "注册编号")
    private String registrationNumber;

    @Schema(description = "工作地点编号")
    @ExcelIgnore
    private Long locationId;

    @Schema(description = "工作地点名称")
    @ExcelProperty(value = "工作地点")
    private String locationName;

    @Schema(description = "工艺类别")
    private String technology;

    @Schema(description = "任职状态")
    @ExcelIgnore
    private Long workStatus;
    @Schema(description = "任职状态名称")
    @ExcelProperty(value = "任职状态")
    private String workStatusName;

    @Schema(description = "离职日期")
    @ExcelProperty(value = "离职时间")
    @DateTimeFormat("yyyy-MM-dd hh:mm:ss")
    private LocalDateTime departTime;

}
