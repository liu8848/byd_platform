package com.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dictionary implements Serializable {
    private String dictId;
    private Long dictValue;
    private String dictName;
}
