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

    @ExcelProperty("事业部编号")
    private WriteCellData<String> id;

    @Schema(description = "事业部编号")
    @ExcelIgnore
    private Long buId;



    @Schema(description = "备案工厂编号")
    @ExcelProperty(value = "备案工厂")
    private Long recordFactoryId;

    @Schema(description = "体系接口人编号")
    @ExcelProperty(value = "体系接口人")
    private Long employeeId;
}
