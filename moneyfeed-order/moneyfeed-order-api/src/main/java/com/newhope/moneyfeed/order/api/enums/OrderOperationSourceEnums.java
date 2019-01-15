package com.newhope.moneyfeed.order.api.enums;

/**
 * 修改拉料信息来源
 * @author hp
 *
 */
public enum OrderOperationSourceEnums {
    /**
     * 农资商城
     */
    MONEYFEED("农资商城", "MONEYFEED"),
    BM("后台管理系统", "BM"),
    /**
     * EBS
     */
    EBS("EBS", "EBS");

    private String desc;
    private String value;

    OrderOperationSourceEnums(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
