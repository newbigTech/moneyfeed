package com.newhope.moneyfeed.api.enums;

/**
 * Created by liming on 2018/12/4.
 */
public enum UserOperEventType {

    ORDER("订单"),
    PAY("支付"),
    RECHARGE("充值");


    private String desc;

    UserOperEventType(String desc) {
    this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}