package com.newhope.moneyfeed.order.api.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:下单渠道
 * @author: dongql
 * @date: 2018/11/30 20:07
 */
public enum OrderChannelEnum {
    /** 客户端 */
    CLIENT("客户端", "CLIENT"),
    /** 商城后台 */
    BM("商城后台", "BM");

    private String value;

    private String desc;

    OrderChannelEnum(String desc, String value) {
        this.desc = desc;
        this.value = value;
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
        for (OrderChannelEnum type : OrderChannelEnum.values()) {
            map.put(type.getValue(), type.getDesc());
        }
        return map;
    }
}
