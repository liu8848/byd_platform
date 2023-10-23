package com.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    private Long id;
    private String name;
    private String grade;
    private int gender;
    private int education;
    private String email;
    private String phone;
    private Long factoryId;
    private Long departmentId;
    private Long locationId;
    private int workStatus;

    @Schema(description = "所属部门")
    private Department department;

}
