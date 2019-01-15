package com.newhope.moneyfeed.order.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   订单拉料信息表
 */
public class OrderFeedTransportModel extends BaseModel {
 
	private static final long serialVersionUID = -6306435099478599051L;

	/** 订单ID */
    private Long orderId;

    /** 司机姓名 */
    private String driverName;

    /** 司机电话 */
    private String driverMobile;

    /** 车牌号 */
    private String carNo;

    /** 司机身份证号 */
    private String idCard;

    /** 系统来源 */
    private String source;

    /** 订单号 */
    private String orderNo;

    /** EBS车牌号 */
    private String ebsCarNo;

    /** EBS车牌号 */
    private String remark;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        this.driverMobile = driverMobile;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getEbsCarNo() {
        return ebsCarNo;
    }

    public void setEbsCarNo(String ebsCarNo) {
        this.ebsCarNo = ebsCarNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}