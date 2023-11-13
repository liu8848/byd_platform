package com.platform.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "在职审核员台账数据展示模型")
public class AuditorStandingBookInWorkVO implements Serializable {
    @Schema(description = "事业部编号")
    private Long buId;

    @ExcelProperty(value = "事业部")
    @Schema(description = "事业部名称")
    private String buName;


    @Schema(description = "备案工厂编号")
    private Long recordFactoryId;

    @ExcelProperty(value = "备案工厂")
    @Schema(description = "备案工厂名称")
    private String recordFactoryName;

    @Schema(description = "星级匹配编码")
    private Long levelMatch;
    @ExcelProperty(value = "星级匹配情况")
    @Schema(description = "星级匹配")
    private String levelMatchName;

    @Schema(description = "星级编码")
    private Long level;
    @ExcelProperty(value = "星级")
    @Schema(description = "星级")
    private String levelName;

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

    @Schema(description = "审核员信息")
    private List<AuditorDisplayVO> auditors;
}
