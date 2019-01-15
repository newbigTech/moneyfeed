package com.newhope.openapi.api.enums;

/**
 * Create by yyq on 2018/12/25
 */
public enum  LabelTargetTypeEnums {

    MERCHANT_LABEL("商户标签","MERCHANT_LABEL"),
    CUSTOMER_LABEL("客户标签", "CUSTOMER_LABEL");

    private String desc;

    private String value;

    private LabelTargetTypeEnums(String desc, String value) {
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
