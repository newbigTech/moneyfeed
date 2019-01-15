package com.newhope.openapi.api.vo.request.customer;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class AllotIntentionCustomerReq {
	
	@NotNull
	@ApiModelProperty("主键Id")
	private Long applyId;
	
	@NotNull
	@ApiModelProperty("分配的商户Id")
	private Long shopId;
	
	@ApiModelProperty("平台处理意见")
	private String platformDealMsg;
	
	public Long getApplyId() {
		return applyId;
	}

	public Long getShopId() {
		return shopId;
	}

	public String getPlatformDealMsg() {
		return platformDealMsg;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setPlatformDealMsg(String platformDealMsg) {
		this.platformDealMsg = platformDealMsg;
	}



}
