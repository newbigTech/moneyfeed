package com.newhope.moneyfeed.integration.api.enums.order;

/**
 * Created by yuyanlin on 2018/11/24
 */
public enum EbsOrderLogTypeEnum {

    TO_EBS_CREATE_REQUEST("发给EBS，请求创建订单"),
    TO_EBS_GET_CREATE_RESULT_REQUEST("发给EBS，获取创建订单结果"),
    TO_MONEYFEED_SEND_CREATE_RESULT("发给商城，正常的通知订单创建结果"),

    TO_MONEYFEED_SEND_CREATE_FAIL_RESULT_IN_FIRST_TIME("在接收创建订单的过程中，发给商城，告知创建失败"),

    TO_EBS_PAY_REQUEST("发给EBS，请求支付订单"),
    TO_EBS_GET_PAY_RESULT_REQUEST("发给EBS，请求支付订单结果"),
    TO_MONEYFEED_SEND_PAY_RESULT("发给商城，通知订单支付结果"),

    TO_EBS_CANCEL_REQUEST("发给EBS，请求取消订单"),
    TO_EBS_GET_CANCEL_RESULT_REQUEST("发给EBS，获取取消订单结果"),
    TO_MONEYFEED_SEND_CANCEL_RESULT("发给商城，通知订单取消结果"),
    
    TO_MONEYFEED_SEND_CANCEL_FAIL_RESULT_IN_FIRST_TIME("在接收取消订单的过程中，发给商城，告知取消订单失败"),

    TO_EBS_GET_ORDER_INFO("发给EBS，获取订单信息及状态"),

    TO_EBS_UPDATE_REQUEST("发给EBS，请求更新订单"),
    TO_EBS_GET_UPDATE_RESULT_REQUEST("发给EBS，获取更新订单结果"),
    TO_MONEYFEED_SEND_UPDATE_RESULT("发给商城，正常的通知订单创建结果"),
    
    TO_EBS_RECHARGE_REQUEST("发给EBS，请求订单充值/账户充值"),
    TO_EBS_GET_RECHARGE_RESULT_REQUEST("发给EBS，请求订单充值/账户充值结果"),
    TO_MONEYFEED_SEND_RECHARGE_RESULT("发给商城，通知订单充值/账户充值结果"),

    SEND_MQ_MSG("发送MQ消息");

    private String desc;

    EbsOrderLogTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
