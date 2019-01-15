package com.newhope.moneyfeed.order.api.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Create by yyq on 2018/11/17
 */
public class OrderTransportDtoResult implements Serializable {
    private static final long serialVersionUID = 9215179272650518117L;


    @ApiModelProperty(name = "carNo", notes = "车牌号")
    private String carNo;

    @ApiModelProperty(name = "driverName", notes = "司机姓名")
    private String driverName;

    @ApiModelProperty(name = "driverMobile", notes = "司机电话")
    private String driverMobile;

    @ApiModelProperty(name = "idCard", notes = "司机身份证号")
    private String idCard;

    @ApiModelProperty(name = "id", notes = "主键id，详情编辑中使用")
    private Long id;

    @ApiModelProperty(name = "ebsCarNo", notes = "EBS车牌号")
    private String ebsCarNo;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEbsCarNo() {
        return ebsCarNo;
    }

    public void setEbsCarNo(String ebsCarNo) {
        this.ebsCarNo = ebsCarNo;
    }
}
