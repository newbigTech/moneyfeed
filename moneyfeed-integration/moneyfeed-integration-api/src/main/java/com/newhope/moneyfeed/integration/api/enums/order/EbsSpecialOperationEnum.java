package com.newhope.moneyfeed.integration.api.enums.order;

/**
 * Ebs特殊操作枚举
 *
 * Created by yuyanlin on 2018/12/7
 */
public enum EbsSpecialOperationEnum {

    CANCEL_ORDER_IN_MANUAL_PRICE("人工核价中取消订单");

    private String desc;

    EbsSpecialOperationEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
