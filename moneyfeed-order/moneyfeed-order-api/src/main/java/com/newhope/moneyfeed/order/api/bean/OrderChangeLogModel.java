package com.newhope.moneyfeed.order.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   订单变更日志表
 */
public class OrderChangeLogModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 143037330837066672L;

	/** 订单ID */
    private Long orderId;

    /** 操作类型 */
    private String operType;

    /** 业务数据 */
    private String bizData;

    /** 操作人ID */
    private Long operUserId;

    /** 操作人姓名 */
    private String operUserName;

    /** 操作来源 */
    private String operSource;

    /** 订单号 */
    private String orderNo;

    /** 订单状态 */
    private String orderStatus;

    /** 备注 */
    private String remark;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getBizData() {
        return bizData;
    }

    public void setBizData(String bizData) {
        this.bizData = bizData;
    }

    public Long getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Long operUserId) {
        this.operUserId = operUserId;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public String getOperSource() {
        return operSource;
    }

    public void setOperSource(String operSource) {
        this.operSource = operSource;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}