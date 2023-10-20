package com.platform.dto;

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
    private int education;
    private String auditorLevel;
    private String phone;
    private String registrationNumber;
    private String technology;
}
