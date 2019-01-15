package com.newhope.openapi.api.vo.request.order;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.newhope.openapi.api.vo.request.order.product.ProductItemReq;

import io.swagger.annotations.ApiModelProperty;

public class OrderBasePostReq implements Serializable {

	private static final long serialVersionUID = -7819532724983727218L;

	@Valid
	@NotEmpty(message = "商品信息不能为空")
	@ApiModelProperty(name = "pitems", notes = "商品列表", required = true)
	private List<ProductItemReq> pitems;

	@NotBlank(message = "拉料日期不能为空")
	@ApiModelProperty(name = "planTransportTime", notes = "拉料日期", required = true)
	private String planTransportTime;

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

	public List<ProductItemReq> getPitems() {
		return pitems;
	}

	public void setPitems(List<ProductItemReq> pitems) {
		this.pitems = pitems;
	}

	public String getPlanTransportTime() {
		return planTransportTime;
	}

	public void setPlanTransportTime(String planTransportTime) {
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
