package com.newhope.moneyfeed.order.api.dto.request.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Create by yyq on 2018/11/19
 */
public class OrderLogDtoReq implements Serializable {
    private static final long serialVersionUID = 1698523132659275733L;
    @ApiModelProperty(name = "orderId", notes = "'订单ID'")
    private Long orderId;
    @ApiModelProperty(name = "operType", notes = "'操作类型'")
    private String operType;
    @ApiModelProperty(name = "bizData", notes = "'业务数据'")
    private String bizData;
    @ApiModelProperty(name = "operUserId", notes = "'操作人ID'")
    private Long operUserId;
    @ApiModelProperty(name = "operUserName", notes = "操作人姓名")
    private String operUserName;
    @ApiModelProperty(name = "operSource", notes = "操作来源")
    private String operSource;
    @ApiModelProperty(name = "orderNo", notes = "订单号")
    private String orderNo;
    @ApiModelProperty(name = "orderStatus", notes = "订单状态")
    private String orderStatus;
    @ApiModelProperty(name = "remark", notes = "备注")
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
