package com.platform.vo.factoryContact;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Schema(description = "工厂体系接口人数据展示模型")
public class FactoryContactVO implements Serializable {


    @Schema(description = "事业部编号")
    @TableField(value = "bu_id")
    @ExcelIgnore
    private Long buId;

    @Schema(description = "事业部名称")
    @ExcelProperty(value = "事业部")
    private String buName;

    @Schema(description = "备案工厂编号")
    @TableField("record_factory_id")
    @ExcelIgnore
    private Long recordFactoryId;
    @Schema(description = "备案工厂名称")
    @ExcelProperty(value = "备案工厂")
    private String recordFactoryName;

    @Schema(description = "体系接口人工号")
    @TableField("employee_id")
    @ExcelIgnore
    private Long employeeId;
    @Schema(description = "员工姓名")
    @ExcelProperty(value = "体系接口人")
    private String employeeName;

    @Schema(description = "电话")
    @ExcelProperty(value = "电话")
    private String phone;

    @Schema(description = "邮箱")
    @ExcelProperty(value = "邮箱")
    private String email;

    @Schema(description = "创建人")
    @ExcelProperty(value = "创建人")
    private String createUserName;
    @Schema(description = "创建人工号")
    @ExcelIgnore
    private Long createUserId;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ExcelProperty(value = "创建时间")
    @ColumnWidth(25)
    private LocalDateTime createTime;

    @Schema(description = "修改人姓名")
    @ExcelProperty(value = "修改人")
    private String updateUserName;
    @Schema(description = "修改人工号")
    @ExcelIgnore
    private Long updateUserId;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @ExcelProperty("创建时间")
    @ColumnWidth(25)
    private LocalDateTime updateTime;
}
