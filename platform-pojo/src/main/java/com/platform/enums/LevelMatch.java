package com.platform.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum LevelMatch {
    MATCH(1,"符合"),
    NOT_MATCH(0,"预警"),
    NA(-1,"NA");

    LevelMatch(int val,String str){
        this.value=val;
        this.str=str;
    }

    @JsonValue
    @EnumValue
    private final int value;
    private final String str;
}
