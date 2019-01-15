package com.newhope.openapi.api.vo.request.order;

import com.newhope.moneyfeed.api.vo.request.PageReq;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 10:28
 */
public class OrderInfoQueryReq extends PageReq implements Serializable{
    private static final long serialVersionUID = -1073788381194940006L;

    @ApiModelProperty(name = "status", notes = "订单状态")
    private String status;
    @ApiModelProperty(name = "orderBeginDate", notes = "下单开始时间yyyy/MM/dd")
    private String orderBeginDate;
    @ApiModelProperty(name = "orderEndDate", notes = "下单结束时间yyyy/MM/dd")
    private String orderEndDate;
    @ApiModelProperty(name = "pullBeginDate", notes = "拉料开始时间yyyy/MM/dd")
    private String pullBeginDate;
    @ApiModelProperty(name = "pullEndDate", notes = "拉料结束时间yyyy/MM/dd")
    private String pullEndDate;
    @ApiModelProperty(name = "completeBeginDate", notes = "完成起始时间yyyy/MM/dd")
    private String completeBeginDate;
    @ApiModelProperty(name = "completeEndDate", notes = "完成结束时间yyyy/MM/dd")
    private String completeEndDate;
    @ApiModelProperty(name = "closeBeginDate", notes = "关闭起始时间yyyy/MM/dd")
    private String closeBeginDate;
    @ApiModelProperty(name = "closeEndDate", notes = "关闭结束时间yyyy/MM/dd")
    private String closeEndDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderBeginDate() {
        return orderBeginDate;
    }

    public void setOrderBeginDate(String orderBeginDate) {
        this.orderBeginDate = orderBeginDate;
    }

    public String getOrderEndDate() {
        return orderEndDate;
    }

    public void setOrderEndDate(String orderEndDate) {
        this.orderEndDate = orderEndDate;
    }

    public String getPullBeginDate() {
        return pullBeginDate;
    }

    public void setPullBeginDate(String pullBeginDate) {
        this.pullBeginDate = pullBeginDate;
    }

    public String getPullEndDate() {
        return pullEndDate;
    }

    public void setPullEndDate(String pullEndDate) {
        this.pullEndDate = pullEndDate;
    }

    public String getCompleteBeginDate() {
        return completeBeginDate;
    }

    public void setCompleteBeginDate(String completeBeginDate) {
        this.completeBeginDate = completeBeginDate;
    }

    public String getCompleteEndDate() {
        return completeEndDate;
    }

    public void setCompleteEndDate(String completeEndDate) {
        this.completeEndDate = completeEndDate;
    }

    public String getCloseBeginDate() {
        return closeBeginDate;
    }

    public void setCloseBeginDate(String closeBeginDate) {
        this.closeBeginDate = closeBeginDate;
    }

    public String getCloseEndDate() {
        return closeEndDate;
    }

    public void setCloseEndDate(String closeEndDate) {
        this.closeEndDate = closeEndDate;
    }
}
