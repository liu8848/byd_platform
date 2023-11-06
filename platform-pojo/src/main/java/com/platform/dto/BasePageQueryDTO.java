package com.platform.dto;

import lombok.Data;

@Data
public class BasePageQueryDTO {

    private String fields = "";

    private int page = 0;
    private int size = 10;
}
