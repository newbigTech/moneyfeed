package com.newhope.moneyfeed.order.api.enums;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 10:57
 */
public enum OrderStatusTypeEnum {
    /**    
     * 待支付
     */  
    TO_BE_PAID("待支付", "TO_BE_PAID"),
    /**
     * 待拉料
     */
    TO_PULL_MATERIAL("待拉料", "TO_PULL_MATERIAL"),
    /**
     * 已完成
     */
    COMPLETED("已完成","COMPLETED"),
    /**
     * 已关闭
     */
    CLOSED("已关闭","CLOSED");

    private String value;

    private String desc;

    OrderStatusTypeEnum(String desc, String value) {
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
        for (OrderStatusTypeEnum type : OrderStatusTypeEnum.values()) {
            map.put(type.getValue(), type.getDesc());
        }
        return map;
    }
}
