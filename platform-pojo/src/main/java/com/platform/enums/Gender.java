package com.platform.enums;


import com.fasterxml.jackson.annotation.JsonValue;
import com.platform.Constant.PojoMessageConstant;
import lombok.Getter;

@Getter
public enum Gender {
    MALE(1,"男"),
    FEMALE(0,"女");

    @JsonValue
    private final int value;
    private final String str;
    Gender(int val,String str){
        this.value=val;
        this.str=str;
    }
    public static Gender fromValue(int val){
        return switch (val) {
            case 0 -> FEMALE;
            case 1 -> MALE;
            default -> throw new IllegalArgumentException(PojoMessageConstant.GENDER_ILLEGAL);
        };
    }

    public static Gender fromValue(String str){
        return switch (str) {
            case "女" -> FEMALE;
            case "男" -> MALE;
            default -> throw new IllegalArgumentException(PojoMessageConstant.GENDER_ILLEGAL);
        };
    }

    @Override
    public String toString() {
        return this.str;
    }
}
