package com.platform.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "展示用户信息的模型")
public class EmployeeDisplayVo implements Serializable {
    private Long employeeId;
    private String employeeName;
    private String grade;
    private String gender;
    private String education;
    private String email;
    private String phone;
    private Long buId;
    private String buName;
    private Long factoryId;
    private String factoryName;
    private Long departmentId;
    private String departmentName;
    private Long locationId;
    private String locationName;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;
}
