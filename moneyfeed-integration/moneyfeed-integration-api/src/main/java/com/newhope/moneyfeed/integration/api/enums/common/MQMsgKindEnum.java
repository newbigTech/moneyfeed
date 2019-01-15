package com.newhope.moneyfeed.integration.api.enums.common;

/**
 * 发送MQ类型
 * Created by Dave Chen on 2018/12/17.
 */
public enum MQMsgKindEnum {
    pay("获取支付结果，通知商场"),
    lock_library("查询锁库结果，通知商场"),
    recharge("获取充值结果，通知商场");
    private String desc;

    MQMsgKindEnum(String desc) {
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
