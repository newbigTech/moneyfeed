package com.newhope.moneyfeed.integration.api.enums.common;

/**
 * MQ发送的消费者
 * Created by Dave Chen on 2018/12/17.
 */
public enum MQSendToClientEnum {
    order("发送给订单中心的MQ"),
    user("发送给用户中心的MQ"),
    product("发送给产品中心的MQ"),
    integration("发送给中台的MQ");

    private String desc;

    MQSendToClientEnum(String desc) {
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
