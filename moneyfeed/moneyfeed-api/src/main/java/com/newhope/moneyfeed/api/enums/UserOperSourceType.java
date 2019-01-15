package com.newhope.moneyfeed.api.enums;

/**
 * Created by liming on 2018/12/4.
 */
public enum  UserOperSourceType {

    ORDER_CENTER("订单中心"),
    PAY_CENTER("支付中心"),
    MEDIUM_CENTER("中台");

    UserOperSourceType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}