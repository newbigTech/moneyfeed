package com.newhope.moneyfeed.pay.api.enums;


import java.util.LinkedHashMap;
import java.util.Map;

public enum PayStatusEnum {

	PAY_WAITTING("待支付", "PAY_WAITTING"),
	PAY_PROGRESS("进行中 ", "PAY_PROGRESS"),
    PAY_SUCESS("支付成功 ", "PAY_SUCESS"),
    PAY_FAIL("支付失败", "PAY_FAIL"),
    PAY_USELESS("无用", "PAY_USELESS")
    ;

    private String value;

    private String desc;

    PayStatusEnum(String desc, String value) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Map<String, String> getEnumsMap() {
        Map<String, String> map = new LinkedHashMap<>();
        for (PayStatusEnum type : PayStatusEnum.values()) {
            map.put(type.getValue(), type.getDesc());
        }
        return map;
    }
}
