package com.platform.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum LevelMatch {
    MATCH(1L, "符合"),
    NOT_MATCH(0L, "预警"),
    NA(-1L, "NA");

    LevelMatch(Long val, String str) {
        this.value = val;
        this.str = str;
    }

    @JsonValue
    @EnumValue
    private final Long value;
    private final String str;
}
