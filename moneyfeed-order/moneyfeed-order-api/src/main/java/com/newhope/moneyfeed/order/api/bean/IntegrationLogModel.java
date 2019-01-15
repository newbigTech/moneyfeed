package com.newhope.moneyfeed.order.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   订单规则表
 */
public class IntegrationLogModel extends BaseModel {
    /** 类型:商城发送-SEND,商城接收-RECIVE */
    private String type;

    /** 拉料开始天 */
    private Long orderId;

    /** 拉料结束天 */
    private String bizData;

    /** 备注 */
    private String remark;

    /** 备注 */
    private String operType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getBizData() {
        return bizData;
    }

    public void setBizData(String bizData) {
        this.bizData = bizData;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }
}