package com.platform.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "审核员展示数据模型")
public class AuditorDisplayVO implements Serializable {
    private String factoryName;
    private String departmentName;
    private String employeeName;
    private Long employeeId;
    private String grade;
    private String gender;
    private int education;
    private String auditorLevel;
    private String email;
    private String phone;
    private String registrationNumber;
    private String locationName;
    private String technologyName;
    private int workStatus;
    private int type;
}
