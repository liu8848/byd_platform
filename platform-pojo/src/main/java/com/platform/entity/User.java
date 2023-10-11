package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String username;
    private String password;
    private Long employeeId;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date updateTime;
    private Long createUser;
    private Long updateUser;
}
