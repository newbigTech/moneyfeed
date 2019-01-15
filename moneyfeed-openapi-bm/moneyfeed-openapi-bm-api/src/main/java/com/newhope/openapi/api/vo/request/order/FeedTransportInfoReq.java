package com.newhope.openapi.api.vo.request.order;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/30 15:03
 */
public class FeedTransportInfoReq implements Serializable{
    private static final long serialVersionUID = 8999452474215387154L;

    @ApiModelProperty(name = "planTransportTime", notes = "计划拉料日期")
    private Date planTransportTime;

    @ApiModelProperty(name = "driverName", notes = "司机姓名")
    private String driverName;

    @ApiModelProperty(name = "driverMobile", notes = "司机电话")
    private String driverMobile;

    /** 车牌号 */
    @ApiModelProperty(name = "carNo", notes = "车牌号")
    private String carNo;

    /** 司机身份证号 */
    @ApiModelProperty(name = "idCard", notes = "身份证号")
    private String idCard;

    public Date getPlanTransportTime() {
        return planTransportTime;
    }

    public void setPlanTransportTime(Date planTransportTime) {
        this.planTransportTime = planTransportTime;
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
}
