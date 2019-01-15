package com.newhope.moneyfeed.pay.api.enums;


import java.util.LinkedHashMap;
import java.util.Map;

public enum EbsPayStatusEnum {

	EBS_PAY_WAITTING("待同步", "EBS_PAY_WAITTING"),
	EBS_PAY_PROGRESS("同步中 ", "EBS_PAY_PROGRESS"),
	EBS_PAY_SUCESS("支付成功 ", "EBS_PAY_SUCESS"),
	EBS_PAY_FAIL("支付失败", "EBS_PAY_FAIL");

    private String value;

    private String desc;

    EbsPayStatusEnum(String desc, String value) {
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
        for (EbsPayStatusEnum type : EbsPayStatusEnum.values()) {
            map.put(type.getValue(), type.getDesc());
        }
        return map;
    }
}
