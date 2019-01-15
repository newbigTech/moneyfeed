package com.newhope.moneyfeed.order.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   订单回滚表
 */
public class OrderRollbackModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7903897167277218323L;

	/** 订单ID */
    private String orderId;

    /** 订单号 */
    private String orderNo;

    /** 是否处理，初始为0，已处理为1 */
    private Boolean rollbackFlag;

    /** 订单事件类型 */
    private String type;

    /** 业务数据 */
    private String bizData;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Boolean getRollbackFlag() {
        return rollbackFlag;
    }

    public void setRollbackFlag(Boolean rollbackFlag) {
        this.rollbackFlag = rollbackFlag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBizData() {
        return bizData;
    }

    public void setBizData(String bizData) {
        this.bizData = bizData;
    }
}