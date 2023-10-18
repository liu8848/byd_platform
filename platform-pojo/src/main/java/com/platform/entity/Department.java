package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "部门数据模型")
public class Department implements Serializable {
    private Long id;
    private String name;
    private Long factoryId;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
    private Long createUser;
    private Long updateUser;

    @Schema(description = "所属工厂信息")
    private Factory factory;
}
