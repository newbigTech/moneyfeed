package com.newhope.openapi.api.vo.request.customer;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class ModifyIntentionCustomerMsg {
	
	@NotNull
	@ApiModelProperty("主键Id")
	private Long applyId;
	
	@ApiModelProperty("商户处理意见")
	private String shopDealMsg;

	public Long getApplyId() {
		return applyId;
	}

	public String getShopDealMsg() {
		return shopDealMsg;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public void setShopDealMsg(String shopDealMsg) {
		this.shopDealMsg = shopDealMsg;
	}

}
