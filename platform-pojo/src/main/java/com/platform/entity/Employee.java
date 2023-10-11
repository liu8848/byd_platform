package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;
    private String name;
    private String grade;
    private String gender;
    private String education;
    private String email;
    private String phone;
    private Long factoryId;
    private Long departmentId;
    private Long locationId;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
    private Long createUser;
    private Long updateUser;
}
