package com.platform.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Education {

    PRIMARY_SCHOOL(1,"小学"),
    JUNIOR_MIDDLE_SCHOOL(2,"中学"),
    HIGH_SCHOOL(3,"高中"),
    UNIVERSITY(4,"大学"),
    POSTGRADUATE(5,"研究生"),
    DOCTOR(6,"博士");

    @JsonValue
    @EnumValue
    public final int value;
    public final String str;

    Education(int val,String str){
        this.value=val;
        this.str=str;
    }

    public static Education fromValue(int val){
        return switch (val){
            case 1 ->PRIMARY_SCHOOL;
            case 2 ->JUNIOR_MIDDLE_SCHOOL;
            case 3->HIGH_SCHOOL;
            case 4->UNIVERSITY;
            case 5->POSTGRADUATE;
            case 6->DOCTOR;
            default -> throw new IllegalArgumentException();
        };
    }

    public int getValue() {
        return value;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return this.str;
    }
}
