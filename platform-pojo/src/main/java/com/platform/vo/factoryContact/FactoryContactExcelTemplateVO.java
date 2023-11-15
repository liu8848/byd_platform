package com.platform.vo.factoryContact;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.data.WriteCellData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "工厂体系接口人导入模板数据模型")
public class FactoryContactExcelTemplateVO {

    @ExcelProperty(value = "事业部")
    @ColumnWidth(20)
    private String buName;
    @ExcelProperty(value = "事业部编号")
    private WriteCellData<String> buId;

    @ExcelProperty(value = "备案工厂")
    @ColumnWidth(20)
    private String recordFactoryName;
    @ExcelProperty(value = "备案工厂编号")
    private WriteCellData<String> recordFactoryId;

    @ExcelProperty(value = "体系接口人姓名")
    private String employeeName;

    @ExcelProperty(value = "体系接口人工号")
    private String employeeId;


    @ExcelProperty(value = "邮箱")
    private String email;

    @ExcelProperty(value = "电话")
    private String phone;
}
