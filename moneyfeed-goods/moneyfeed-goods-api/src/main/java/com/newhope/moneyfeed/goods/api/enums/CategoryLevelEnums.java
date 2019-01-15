package com.newhope.moneyfeed.goods.api.enums;

public enum CategoryLevelEnums {

    ONE_LEVEL(1,"一级目录"),
    TWO_LEVEL(2,"二级目录"),
    THREE_LEVEL(3,"三级目录"),
    FOUR_LEVEL(4,"四级目录");

    private String desc;

    private int value;

    CategoryLevelEnums(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
