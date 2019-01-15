package com.newhope.moneyfeed.order.api.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**  
* @ClassName: OrderPayTypeEnum  
* @Description: 订单支付方式
* @author luoxl
* @date 2018年12月5日 上午11:36:09  
*    
*/
public enum OrderPayTypeEnum {
	NONE("无", "NONE"),
    OFF_LINE("线下支付", "OFF_LINE"),
    BANK_CARD("银行卡", "BANK_CARD"),
    BALANCE("余额", "BALANCE");

    private String value;

    private String desc;

    OrderPayTypeEnum(String desc, String value) {
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
        for (OrderPayTypeEnum type : OrderPayTypeEnum.values()) {
            map.put(type.getValue(), type.getDesc());
        }
        return map;
    }
}
