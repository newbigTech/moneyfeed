package com.newhope.openapi.api.vo.response.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Create by yyq on 2018/11/19
 */
public class OrderTransportResult implements Serializable {
    private static final long serialVersionUID = -3328641188653756756L;

    /**
     * 车牌号
     */
    @ApiModelProperty(name = "carNo", notes = "车牌号")
    private String carNo;
    /**
     * 司机姓名
     */
    @ApiModelProperty(name = "driverName", notes = "司机姓名")
    private String driverName;

    /**
     * 司机电话
     */
    @ApiModelProperty(name = "driverMobile", notes = "司机电话")
    private String driverMobile;
    /**
     * 司机身份证号
     */
    @ApiModelProperty(name = "idCard", notes = "司机身份证号")
    private String idCard;

    @ApiModelProperty(name = "id", notes = "拉料主键")
    private Long id;

    @ApiModelProperty(name = "ebsCarNo", notes = "Ebs车牌号")
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
