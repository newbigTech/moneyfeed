package com.newhope.moneyfeed.pay.api.enums;


import java.util.LinkedHashMap;
import java.util.Map;

public enum CheckPayStatusEnum {

	CHECK_PAY_WAITTING("待对账", "CHECK_PAY_WAITTING"),
	CHECK_PAY_SUCESS("已核对 ", "CHECK_PAY_SUCESS"),
	CHECK_PAY_FAIL("核对有误 ", "CHECK_PAY_FAIL"),
	NOT_CHECK("无需对账 ", "NOT_CHECK");
	
    private String value;

    private String desc;

    CheckPayStatusEnum(String desc, String value) {
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
        for (CheckPayStatusEnum type : CheckPayStatusEnum.values()) {
            map.put(type.getValue(), type.getDesc());
        }
        return map;
    }
}
