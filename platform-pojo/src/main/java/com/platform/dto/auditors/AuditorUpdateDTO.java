package com.platform.dto.auditors;

import com.platform.enums.AuditorLevel;
import com.platform.enums.Education;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditorUpdateDTO {
    private Long employeeId;
    private Education education;
    private AuditorLevel auditorLevel;
    private String phone;
    private String registerNumber;
}
