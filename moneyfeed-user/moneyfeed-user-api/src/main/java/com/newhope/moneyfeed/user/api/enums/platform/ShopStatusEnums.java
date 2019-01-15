package com.newhope.moneyfeed.user.api.enums.platform;

/**
 * Create by yyq on 2018/12/24
 */
public enum ShopStatusEnums {

    AWAITING_AUDIT("等待审核","AWAITING_AUDIT"),
    NORMAL_BUSINESS("正常营业","NORMAL_BUSINESS"),
    AUDIT_REJECT("审核拒绝","AUDIT_REJECT"),
    VIOLATION_CHANGE("违规整改","VIOLATION_CHANGE"),
    VIOLATION_CLOSED("违规关闭", "VIOLATION_CLOSED");
    private String desc;

    private String value;

    private ShopStatusEnums(String desc, String value) {
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
