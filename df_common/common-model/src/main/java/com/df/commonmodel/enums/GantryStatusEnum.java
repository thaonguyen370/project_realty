package com.df.commonmodel.enums;

public enum GantryStatusEnum {
    AVAILABLE(0), MOVING(1), DEACTIVE(2), ASSIGNED(3);
    private int value;

    GantryStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
