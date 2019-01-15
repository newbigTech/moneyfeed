package com.newhope.moneyfeed.order.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   订单规则表
 */
public class OrderRuleModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4786034173383078874L;

	/** 商户ID */
    private Long ucShopId;

    /** 商户电话 */
    private String ucShopMobile;

    /** 拉料开始天 */
    private Integer transportStartDay;

    /** 拉料结束天 */
    private Integer transportEndDay;

    /** 拉料时间 */
    private String transportTime;

    /** 下单后一定时间内完成支付 */
    private Integer limitTimeCreateEnd;

    /** 订单支付限制时间类型 */
    private String limitTimeType;

    /** 可编辑时间天 */
    private Integer canModifyDay;

    /** 可编辑时间 */
    private String canModifyTime;

    /** 拉料前一定时间内完成支付天 */
    private Integer limitTimeTransportDay;

    /** 拉料前一定时间内完成支付小时 */
    private String limitTimeTransportTime;

    /** 通知手机号码 */
    private String notifyMobile;

    /** 操作人ID */
    private Long operUserId;

    /** 操作人姓名 */
    private String operUserName;

    public Long getUcShopId() {
        return ucShopId;
    }

    public void setUcShopId(Long ucShopId) {
        this.ucShopId = ucShopId;
    }

    public String getUcShopMobile() {
        return ucShopMobile;
    }

    public void setUcShopMobile(String ucShopMobile) {
        this.ucShopMobile = ucShopMobile;
    }

    public Integer getTransportStartDay() {
        return transportStartDay;
    }

    public void setTransportStartDay(Integer transportStartDay) {
        this.transportStartDay = transportStartDay;
    }

    public Integer getTransportEndDay() {
        return transportEndDay;
    }

    public void setTransportEndDay(Integer transportEndDay) {
        this.transportEndDay = transportEndDay;
    }

    public String getTransportTime() {
        return transportTime;
    }

    public void setTransportTime(String transportTime) {
        this.transportTime = transportTime;
    }

    public Integer getLimitTimeCreateEnd() {
        return limitTimeCreateEnd;
    }

    public void setLimitTimeCreateEnd(Integer limitTimeCreateEnd) {
        this.limitTimeCreateEnd = limitTimeCreateEnd;
    }

    public String getLimitTimeType() {
        return limitTimeType;
    }

    public void setLimitTimeType(String limitTimeType) {
        this.limitTimeType = limitTimeType;
    }

    public Integer getCanModifyDay() {
        return canModifyDay;
    }

    public void setCanModifyDay(Integer canModifyDay) {
        this.canModifyDay = canModifyDay;
    }

    public String getCanModifyTime() {
        return canModifyTime;
    }

    public void setCanModifyTime(String canModifyTime) {
        this.canModifyTime = canModifyTime;
    }

    public Integer getLimitTimeTransportDay() {
        return limitTimeTransportDay;
    }

    public void setLimitTimeTransportDay(Integer limitTimeTransportDay) {
        this.limitTimeTransportDay = limitTimeTransportDay;
    }

    public String getLimitTimeTransportTime() {
        return limitTimeTransportTime;
    }

    public void setLimitTimeTransportTime(String limitTimeTransportTime) {
        this.limitTimeTransportTime = limitTimeTransportTime;
    }

    public String getNotifyMobile() {
        return notifyMobile;
    }

    public void setNotifyMobile(String notifyMobile) {
        this.notifyMobile = notifyMobile;
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
}