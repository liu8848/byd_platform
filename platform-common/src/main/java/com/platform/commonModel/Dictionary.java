package com.platform.commonModel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "数据字典模型")
public class Dictionary implements Serializable {
    @Schema(description = "字典编码")
    private String dictId;
    @Schema(description = "字典值")
    @ExcelProperty("值")
    private Long dictValue;
    @Schema(description = "字典标签")
    @ExcelProperty("名称")
    private String dictName;
    @Schema(description = "字典描述")
    private String dictDesc;

}
