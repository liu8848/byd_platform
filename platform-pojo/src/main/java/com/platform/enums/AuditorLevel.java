package com.platform.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AuditorLevel {
    SA("SA", "资深审核员"),
    A("A", "审核员"),
    PA("PA", "实习审核员");

    AuditorLevel(String val, String str) {
        this.value = val;
        this.str = str;
    }

    @JsonValue
    @EnumValue
    private final String value;
    private final String str;
}
