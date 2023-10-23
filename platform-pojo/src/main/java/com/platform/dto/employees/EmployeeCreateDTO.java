package com.platform.dto.employees;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.platform.enums.Education;
import com.platform.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "创建员工数据模型")
public class EmployeeCreateDTO implements Serializable {
    private String name;
    private String grade;
    @JsonProperty("gender")
    private Gender gender;
    @JsonProperty("education")
    private Education education;
    private String email;
    private String phone;
    private Long factoryId;
    private Long departmentId;
    private Long locationId;
    private int workStatus;
}
