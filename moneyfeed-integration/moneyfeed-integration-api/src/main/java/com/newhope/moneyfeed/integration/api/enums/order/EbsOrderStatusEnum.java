package com.newhope.moneyfeed.integration.api.enums.order;

/**
 * Created by yuyanlin on 2018/11/20
 */
public enum EbsOrderStatusEnum {

    ENTERED("EBS已输入，已创建，待支付"),
    BOOKED("EBS已登记，已收款，待拉货"),
    CANCELLED("EBS订单，已被取消"),
    DELETE("EBS订单，已被删除"),
    CLOSED("EBS订单，已关闭");

    private String desc;

    EbsOrderStatusEnum(String desc) {
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
