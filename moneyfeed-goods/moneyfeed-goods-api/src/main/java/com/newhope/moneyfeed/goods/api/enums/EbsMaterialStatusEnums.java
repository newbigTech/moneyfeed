package com.newhope.moneyfeed.goods.api.enums;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/17 10:07
 */
public enum EbsMaterialStatusEnums {

    Active("激活", "Active"),
    Inactive("未激活", "Inactive"),
    OPM("OPM", "OPM");

    private String desc;

    private String value;

    private EbsMaterialStatusEnums(String desc, String value) {
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
