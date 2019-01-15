package com.newhope.moneyfeed.order.api.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/17 19:09
 */
public enum OrderStatusEnum {
    /** 核价中 */
    CREATING("核价中", "CREATING"),
    /** 待支付 */
    WAITING_FOR_PAYMENT("待支付", "WAITING_FOR_PAYMENT"),
    /** 支付中 */
    PAYING("处理中", "PAYING"),
    /** 已支付 */
    PAID("已支付", "PAID"),
    /** 下单失败 */
    EBS_SYNC_FAILED("下单失败", "EBS_SYNC_FAILED"),
    /** 备货中 */
    MATERIAL_PREPARING("备货中", "MATERIAL_PREPARING"),
    /** 待拉料 */
    WAITING_PULL_MATERIAL("待拉料", "WAITING_PULL_MATERIAL"),
    /** 待提货(线下支付) */
    WAITING_OFF_LINE_PAY("待提货", "WAITING_OFF_LINE_PAY"),
    /** 已进厂 */
    ALREADY_ENTRY_FACTORY("已进厂", "ALREADY_ENTRY_FACTORY"),
    /** 完成交易 */
    COMPLETED("完成交易", "COMPLETED"),
    /** 已退款 */
    REFUNDED("已退款", "REFUNDED"),
    /** 客户关闭中 */
    CUSTOMER_REPEALING("客户关闭中", "CUSTOMER_REPEALING"),
    /** 客户关闭 */
    CUSTOMER_REPEAL("客户关闭", "CUSTOMER_REPEAL"),
    /** 商户关闭 */
    SHOP_REPEAL("商户关闭", "SHOP_REPEAL"),
    /** 系统关闭 */
    CLOSED("系统关闭", "CLOSED");

    private String value;

    private String desc;

    OrderStatusEnum(String desc, String value) {
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
        for (OrderStatusEnum type : OrderStatusEnum.values()) {
            map.put(type.getValue(), type.getDesc());
        }
        return map;
    }
}
