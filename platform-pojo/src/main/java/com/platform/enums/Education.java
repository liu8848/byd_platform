package com.platform.enums;

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
    private final int value;
    private final String str;

    Education(int val,String str){
        this.value=val;
        this.str=str;
    }

    @Override
    public String toString() {
        return this.str;
    }
}
