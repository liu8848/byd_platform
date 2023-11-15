package com.platform.dto.FactoryContact;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.data.WriteCellData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "工厂体系接口人创建数据模型")
public class FactoryContactCreateDTO {

    @ExcelProperty(value = "事业部")
    private String buName;

    @Schema(description = "事业部编号")
    @ExcelProperty(value = "事业部编号")
    private Long buId;

    @Schema(description = "备案工厂编号")
    @ExcelProperty(value = "备案工厂编号")
    private Long recordFactoryId;

    @Schema(description = "体系接口人工号")
    @ExcelProperty(value = "体系接口人工号")
    private Long employeeId;

    @Schema(description = "体系接口人姓名")
    @ExcelProperty(value = "体系接口人姓名")
    private String employeeName;

    @Schema(description = "邮箱")
    @ExcelProperty(value = "邮箱")
    private String email;

    @Schema(description = "电话")
    @ExcelProperty(value = "电话")
    private String phone;
}
