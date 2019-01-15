package com.newhope.moneyfeed.order.api.dto.request.feedtransport;

import java.io.Serializable;

import com.newhope.moneyfeed.order.api.enums.OrderOperationSourceEnums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("拉料信息")
public class OrderFeedTransportModifyDtoReq implements Serializable {

	private static final long serialVersionUID = 6663041787777513792L;

	@ApiModelProperty(name = "id", notes = "拉料信息id")
	private Long id;

	@ApiModelProperty(name = "driverName", notes = "司机姓名")
	private String driverName;

	@ApiModelProperty(name = "driverMobile", notes = "司机电话")
	private String driverMobile;

	@ApiModelProperty(name = "carNo", notes = "车牌号")
	private String carNo;

	@ApiModelProperty(name = "idCard", notes = "司机身份证号")
	private String idCard;

	@ApiModelProperty(name = "source", notes = "修改该信息的来源")
	private OrderOperationSourceEnums source;

	@ApiModelProperty(name = "orderId", notes = "订单主键id")
	private Long orderId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

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

	public OrderOperationSourceEnums getSource() {
		return source;
	}

	public void setSource(OrderOperationSourceEnums source) {
		this.source = source;
	}

}
