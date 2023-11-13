package com.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("create_user")
    private Long createUser;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("update_user")
    private Long updateUser;

}
