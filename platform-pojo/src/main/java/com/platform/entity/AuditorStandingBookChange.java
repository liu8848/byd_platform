package com.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "事业部变动审核员台账")
@TableName(value = "auditor_standing_book_change")
public class AuditorStandingBookChange extends BaseEntity implements Serializable {

    @TableId
    @Schema(description = "主键")
    private Long id;

    @Schema(description = "工号")
    @TableField(value = "employee_id")
    private Long employeeId;

    @Schema(description = "原事业部编号")
    @TableField(value = "old_bu_id")
    private Long oldBuId;


    @Schema(description = "现事业部编号")
    @TableField(value = "now_bu_id")
    private Long nowBuId;

    @Schema(description = "原工厂编号")
    @TableField(value = "old_factory_id")
    private Long oldFactoryId;

    @Schema(description = "现工厂编号")
    @TableField(value = "now_factory_id")
    private Long nowFactoryId;

}
