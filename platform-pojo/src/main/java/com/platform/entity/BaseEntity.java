package com.platform.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseEntity {

    @Schema(description = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    public LocalDateTime createTime;

    @Schema(description = "创建人工号")
    @TableField(value = "create_user",fill = FieldFill.INSERT)
    public Long createUser;

    @Schema(description = "修改时间")
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    public LocalDateTime updateTime;

    @Schema(description = "修改人工号")
    @TableField(value = "update_user",fill = FieldFill.UPDATE)
    public Long updateUser;

}
