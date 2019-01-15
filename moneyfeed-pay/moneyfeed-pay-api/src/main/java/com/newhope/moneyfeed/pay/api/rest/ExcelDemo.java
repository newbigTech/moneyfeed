package com.newhope.moneyfeed.pay.api.rest;

import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.common.util.excel.annotation.MyCell;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liming on 2018/12/19.
 */
public class ExcelDemo {


    @MyCell(index = 0)
    private String shopOrderId;

    @MyCell(index = 1)
    private String platformOrderId;

    @MyCell(index = 2)
    private BigDecimal money;

    @MyCell(index = 3)
    private BigDecimal cutMoney;

    @MyCell(index = 4)
    private String type;

    @MyCell(index = 5)
    private String orderStatus;

    @MyCell(index = 6,format = DateUtil.YYYY_MM_DD_HH_MM_SS)
    private Date createOrderTime;

    @MyCell(index = 7)
    private String remark;


    public String getShopOrderId() {
        return shopOrderId;
    }

    public void setShopOrderId(String shopOrderId) {
        this.shopOrderId = shopOrderId;
    }

    public String getPlatformOrderId() {
        return platformOrderId;
    }

    public void setPlatformOrderId(String platformOrderId) {
        this.platformOrderId = platformOrderId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getCutMoney() {
        return cutMoney;
    }

    public void setCutMoney(BigDecimal cutMoney) {
        this.cutMoney = cutMoney;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public Date getCreateOrderTime() {
        return createOrderTime;
    }

    public void setCreateOrderTime(Date createOrderTime) {
        this.createOrderTime = createOrderTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}