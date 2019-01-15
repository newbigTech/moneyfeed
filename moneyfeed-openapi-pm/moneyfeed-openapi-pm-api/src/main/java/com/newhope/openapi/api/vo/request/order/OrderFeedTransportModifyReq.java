package com.newhope.openapi.api.vo.request.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Create by yyq on 2018/11/19
 */
public class OrderFeedTransportModifyReq implements Serializable {
    private static final long serialVersionUID = -1473384274979480684L;

    @ApiModelProperty(name="id",notes="拉料信息id")
    private Long id;

    @ApiModelProperty(name="driverName",notes="司机姓名")
    private String  driverName;

    @ApiModelProperty(name="driverMobile",notes="司机电话")
    private String  driverMobile;

    @ApiModelProperty(name="carNo",notes="车牌号")
    private String  carNo;

    @ApiModelProperty(name="idCard",notes="司机身份证号")
    private String  idCard;
    @ApiModelProperty(name="planTransportTime",notes="计划拉料时间")
    private Date planTransportTime;
    @ApiModelProperty(name="orderId",notes="订单主键id")
    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getPlanTransportTime() {
        return planTransportTime;
    }

    public void setPlanTransportTime(Date planTransportTime) {
        this.planTransportTime = planTransportTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
