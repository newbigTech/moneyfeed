package com.newhope.moneyfeed.integration.api.enums.order;

/**
 * Created by yuyanlin on 2018/11/21
 */
public enum EbsOrderTypeEnum {
    AUTOMATIC_TYPE("自动订单，无需人工审核类型"),
    MANUAL_REVIEW_TYPE("人工审核类型");

    private String desc;

    EbsOrderTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
