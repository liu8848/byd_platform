package com.platform.dto.auditors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditorInspectionCreateDTO implements Serializable {
    private Long employeeId;
    private Long inspectionId;
}
