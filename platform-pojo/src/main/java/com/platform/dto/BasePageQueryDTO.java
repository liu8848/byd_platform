package com.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BasePageQueryDTO {

    private String fields = "";

    @Schema(description = "页码")
    private int page = 0;

    @Schema(description = "每页记录数")
    private int size = Integer.MAX_VALUE;
}
