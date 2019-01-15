package com.newhope.openapi.api.enums;

/**
 * Create by yyq on 2018/12/25
 */
public enum LabelCreateTypeEnums {

    MANUAL_LABEL("手动标签","MANUAL_LABEL"),
    CONDITION_LABEL("条件标签", "CONDITION_LABEL");

    private String desc;

    private String value;

    private LabelCreateTypeEnums(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public String getValue() {
        return value;
    }
}
