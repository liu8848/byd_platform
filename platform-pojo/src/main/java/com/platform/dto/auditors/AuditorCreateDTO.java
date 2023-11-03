package com.platform.dto.auditors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.platform.enums.AuditorLevel;
import com.platform.enums.Education;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditorCreateDTO implements Serializable {
    private Long employeeId;
    private Long recordFactoryId;
    @JsonProperty("education")
    private int education;
    @JsonProperty("auditorLevel")
    private int auditorLevel;
    private String phone;
    private String registrationNumber;
    private String technology;
}
