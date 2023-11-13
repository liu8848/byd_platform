package com.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "工厂体系接口人")
@TableName(value = "factory_contact")
public class FactoryContact extends BaseEntity implements Serializable {
    @Schema(description = "主键id")
    @TableId
    private Long id;

    @Schema(description = "事业部编号")
    @TableField(value = "bu_id")
    private Long buId;

    @Schema(description = "备案工厂编号")
    @TableField("record_factory_id")
    private Long recordFactoryId;

    @Schema(description = "体系接口人工号")
    @TableField("employee_id")
    private Long employeeId;

}
