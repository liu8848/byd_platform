package com.platform.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private int grade;
    private int gender;
    private int education;
    private String email;
    private String phone;
    private Long factoryId;
    private String factoryName;
    private Long departmentId;
    private Long locationId;
    private int workStatus;

    @Schema(description = "所属部门")
    private Department department;

}
