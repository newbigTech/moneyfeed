package com.newhope.moneyfeed.pay.api.dto.req;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**  
* @ClassName: PayOrderInfoDtoReq  
* @Description: 创建支付订单数据
* @author luoxl
* @date 2018年12月25日 上午8:50:32  
*    
*/
public class PayOrderInfoDtoReq implements Serializable {

	private static final long serialVersionUID = 3733877042704322503L;

	@ApiModelProperty(name = "orderNo", notes = "商城订单号")
	private String orderNo;
	@ApiModelProperty(name = "userId", notes = "用户ID")
	private Long userId;
	@ApiModelProperty(name = "shopId", notes = "店铺ID")
	private Long shopId;
	@ApiModelProperty(name = "tradeTime", notes = "商城交易日期")
	private Date tradeTime;
	@ApiModelProperty(name = "amount", notes = "订单金额")
	private BigDecimal amount;
	@ApiModelProperty(name = "remark", notes = "备注")
	private String remark;
	@ApiModelProperty(name = "status", notes = "订单状态")
	private String status;
	@ApiModelProperty(name = "payType", notes = "支付类型")
	private String payType;
	@ApiModelProperty(name = "cusNo", notes = "客户号")
	private String cusNo;
	@ApiModelProperty(name = "merchno", notes = "商户号")
	private String merchno;
	@ApiModelProperty(name = "platformid", notes = "商户平台ID")
	private String platformid;
	@ApiModelProperty(name = "orgId", notes = "客户机构号")
	private String orgId;

	public String getOrderNo() {
		return orderNo;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getShopId() {
		return shopId;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getRemark() {
		return remark;
	}

	public String getStatus() {
		return status;
	}

	public String getPayType() {
		return payType;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCusNo() {
		return cusNo;
	}

	public String getMerchno() {
		return merchno;
	}

	public String getPlatformid() {
		return platformid;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}

	public void setMerchno(String merchno) {
		this.merchno = merchno;
	}

	public void setPlatformid(String platformid) {
		this.platformid = platformid;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
