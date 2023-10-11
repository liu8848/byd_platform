package com.platform.vo;

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
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "展示用户信息的模型")
public class EmployeeDisplayVo implements Serializable {
    private Long id;
    private String name;
    private String grade;
    private String gender;
    private String education;
    private String email;
    private String phone;
    private String factoryName;
    private String departmentName;
    private String locationName;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
    private Long createUser;
    private Long updateUser;
}
