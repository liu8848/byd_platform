package com.platform.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandingBookAuditorExportVO {

    @ExcelProperty(value = "事业部")
    @Schema(description = "事业部名称")
    private String buName;

    @ExcelProperty(value = "备案工厂")
    @Schema(description = "备案工厂名称")
    private String recordFactoryName;

    @ExcelProperty(value = "星级匹配情况")
    @Schema(description = "星级匹配")
    private String levelMatch;

    @ExcelProperty(value = "星级")
    @Schema(description = "星级")
    private String level;

    @ExcelProperty(value = "SA")
    @Schema(description = "资深审核员人数")
    private long sa;

    @ExcelProperty(value = "A")
    @Schema(description = "审核员人数")
    private long a;

    @ExcelProperty(value = "PA")
    @Schema(description = "实习审核员人数")
    private long pa;

    @Schema(description = "预警时间")
    @ExcelProperty(value = "预警时间")
    @DateTimeFormat("yyyy年MM月")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime warnTime;


    @Schema(description = "工厂名")
    @ExcelProperty(value = "工厂")
    private String factoryName;

    @Schema(description = "部门名称")
    @ExcelProperty(value = "部门")
    private String departmentName;

    @Schema(description = "员工姓名")
    @ExcelProperty(value = "姓名")
    private String employeeName;

    @Schema(description = "员工工号")
    @ExcelProperty(value = "工号")
    private Long employeeId;

    @Schema(description = "等级")
    @ExcelProperty(value = "级别")
    private String grade;

    @Schema(description = "性别")
    @ExcelProperty(value = "性别")
    private String gender;

    @Schema(description = "学历")
    @ExcelProperty(value = "学历")
    private String education;

    @Schema(description = "审核员等级")
    @ExcelProperty(value = "审核员级别")
    private String auditorLevel;

    @Schema(description = "电子邮箱")
    @ExcelProperty(value = "邮箱")
    private String email;

    @Schema(description = "电话")
    @ExcelProperty(value = "手机")
    private String phone;

    @Schema(description = "注册编号")
    @ExcelProperty(value = "注册编号")
    private String registrationNumber;


    @ExcelProperty(value = "工作地点")
    @Schema(description = "工作地点名称")
    private String locationName;


    @Schema(description = "工艺类别名称")
    @ExcelProperty(value = "工艺类别")
    private String technologyName;

    @Schema(description = "在职状态")
    @ExcelProperty(value = "任职状态")
    private String status;

    @Schema(description = "类别")
    @ExcelProperty(value = "类型")
    private String type;

    @Schema(description = "优先安排")
    @ExcelProperty(value = "优先安排")
    private Boolean arrangement;
}
