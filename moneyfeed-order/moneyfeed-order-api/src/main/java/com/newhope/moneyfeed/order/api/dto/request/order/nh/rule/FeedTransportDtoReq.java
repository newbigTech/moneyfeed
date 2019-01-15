package com.newhope.moneyfeed.order.api.dto.request.order.nh.rule;

import java.io.Serializable;
import java.util.Date;

import com.newhope.commons.lang.DateUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "订单拉料对象")
public class FeedTransportDtoReq implements Serializable {

	private static final long serialVersionUID = 5323093975077453502L;

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
