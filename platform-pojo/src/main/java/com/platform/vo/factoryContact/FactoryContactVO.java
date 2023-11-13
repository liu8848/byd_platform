package com.platform.vo.factoryContact;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private Long buId;

    @Schema(description = "事业部名称")
    private String buName;

    @Schema(description = "备案工厂编号")
    @TableField("record_factory_id")
    private Long recordFactoryId;
    @Schema(description = "备案工厂名称")
    private String recordFactoryName;

    @Schema(description = "体系接口人工号")
    @TableField("employee_id")
    private Long employeeId;
    @Schema(description = "员工姓名")
    private String employeeName;

    @Schema(description = "创建人")
    private String createUserName;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "修改人姓名")
    private String updateUserName;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;
}
