package com.newhope.moneyfeed.integration.api.enums.order;

/**
 * Created by yuyanlin on 2018/11/20
 */
public enum MoneyfeedToEbsPayTypeEnum {

    BANK_PAY("银行支付"),
    EBS_BALANCE("EBS余额支付");

    private String desc;

    MoneyfeedToEbsPayTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
