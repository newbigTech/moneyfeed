package com.newhope.moneyfeed.api.bean;

import java.math.BigDecimal;
import java.util.Date;

public class UcLogModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4582075956248661708L;

	/** 事件类型 订单，支付 */
    private String eventType;

    /** 事件ID */
    private Long eventId;

    private String ebsOrderId;

    /** 订单id */
    private Long orderId;

    /** 银行流水号 */
    private String payCode;

    /** 事件发生日期 */
    private Date eventDate;

    /** 事件发生前的金额 */
    private BigDecimal beforeEventAmount;

    /** 事件发生后的金额 */
    private BigDecimal afterEventAmount;

    /** 事件发生前状态 */
    private String beforeEventStatus;

    /** 事件发生后的状态 */
    private String afterEventStatus;

    /** 事件操作类型 ，新增，删除，编辑 */
    private String eventOperationType;

    /** 事件发生前对象JSON字符串 */
    private String beforeEventModel;

    /** 事件发生后对象JSON字符串 */
    private String afterEventModel;

    private String dataStatus;

    /** 备注 */
    private String comment;

    /** 来源（订单中心，支付中心，ebs中台） */
    private String source;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEbsOrderId() {
        return ebsOrderId;
    }

    public void setEbsOrderId(String ebsOrderId) {
        this.ebsOrderId = ebsOrderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public BigDecimal getBeforeEventAmount() {
        return beforeEventAmount;
    }

    public void setBeforeEventAmount(BigDecimal beforeEventAmount) {
        this.beforeEventAmount = beforeEventAmount;
    }

    public BigDecimal getAfterEventAmount() {
        return afterEventAmount;
    }

    public void setAfterEventAmount(BigDecimal afterEventAmount) {
        this.afterEventAmount = afterEventAmount;
    }

    public String getBeforeEventStatus() {
        return beforeEventStatus;
    }

    public void setBeforeEventStatus(String beforeEventStatus) {
        this.beforeEventStatus = beforeEventStatus;
    }

    public String getAfterEventStatus() {
        return afterEventStatus;
    }

    public void setAfterEventStatus(String afterEventStatus) {
        this.afterEventStatus = afterEventStatus;
    }

    public String getEventOperationType() {
        return eventOperationType;
    }

    public void setEventOperationType(String eventOperationType) {
        this.eventOperationType = eventOperationType;
    }

    public String getBeforeEventModel() {
        return beforeEventModel;
    }

    public void setBeforeEventModel(String beforeEventModel) {
        this.beforeEventModel = beforeEventModel;
    }

    public String getAfterEventModel() {
        return afterEventModel;
    }

    public void setAfterEventModel(String afterEventModel) {
        this.afterEventModel = afterEventModel;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}