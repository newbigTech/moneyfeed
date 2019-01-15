package com.newhope.moneyfeed.pay.api.enums;

/**
 * 异常对账信息枚举
 * @author bena.peng
 * @date 2018/12/20 11:13
 */


public enum  AbnormalBillEnum {
    SHOP_EXISTENCE_OUTSIDE_NON_EXISTENCE("商城有支付订单数据，支付平台无数据", "SHOP_EXISTENCE_OUTSIDE_NON_EXISTENCE"),
    SHOP_NON_EXISTENCE_OUTSIDE_EXISTENCE("商城无支付订单数据，支付平台有数据", "SHOP_NON_EXISTENCE_OUTSIDE_EXISTENCE"),
    SHOP_SUCCESS_OUTSIDE_FAIL("商城支付成功，支付平台失败", "SHOP_SUCCESS_OUTSIDE_FAIL"),
    SHOP_FAIL_OUTSIDE_SUCCESS("商城支付失败，支付平台成功", "SHOP_FAIL_OUTSIDE_SUCCESS"),
    SHOP_PROGRESS_OUTSIDE_FAIL("商城支付中，支付平台失败", "SHOP_PROGRESS_OUTSIDE_FAIL"),
    SHOP_PROGRESS_OUTSIDE_SUCCESS("商城支付中，支付平台成功", "SHOP_PROGRESS_OUTSIDE_SUCCESS"),
    SHOP_MORE_OUTSIDE_LESS("商城订单金额大于支付平台实付金额", "SHOP_MORE_OUTSIDE_LESS"),
    SHOP_LESS_OUTSIDE_MORE("商城订单金额小于支付平台实付金额", "SHOP_LESS_OUTSIDE_MORE")
    ;

    private String value;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    AbnormalBillEnum(String desc, String value) {
        this.desc = desc;
        this.value = value;

    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
