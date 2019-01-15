package com.newhope.openapi.api.vo.request.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *新增订单规则req
 *
 * @author bena.peng
 * @date 2019/1/8 10:02
 */


public class OrderRuleReq implements Serializable {
    private static final long serialVersionUID = -3793448371669147776L;

    @ApiModelProperty(name = "ucShopId", notes = "商户ID")
    private Long ucShopId;
    @ApiModelProperty(name = "ucShopMobile", notes = "商户电话")

    private String ucShopMobile;
    @ApiModelProperty(name = "transportStartDay", notes = "可以提前多少天提交订单,拉料开始天")
    private Integer transportStartDay;
    @ApiModelProperty(name = "transportEndDay", notes = "可以提前多少天提交订单,拉料结束天")
    private Integer transportEndDay;
    @ApiModelProperty(name = "transportTime", notes = "多少点前可以提交当天订单,拉料时间")
    private String transportTime;
    @ApiModelProperty(name = "limitTimeCreateEnd", notes = "下单后一定时间内完成支付")
    private Integer limitTimeCreateEnd;
    @ApiModelProperty(name = "limitTimeType", notes = "订单支付限制时间类型")
    private String limitTimeType;
    @ApiModelProperty(name = "canModifyDay", notes = "订单编辑时间配置,可编辑时间天")
    private Integer canModifyDay;
    @ApiModelProperty(name = "canModifyTime", notes = "订单编辑时间配置,可编辑时间")
    private String canModifyTime;
    @ApiModelProperty(name = "limitTimeTransportDay", notes = "拉料前一定时间内完成支付天")
    private Integer limitTimeTransportDay;
    @ApiModelProperty(name = "limitTimeTransportTime", notes = "拉料前一定时间内完成支付小时")
    private String limitTimeTransportTime;
    @ApiModelProperty(name = "notifyMobile", notes = "通知手机号码")
    private String notifyMobile;

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
}
