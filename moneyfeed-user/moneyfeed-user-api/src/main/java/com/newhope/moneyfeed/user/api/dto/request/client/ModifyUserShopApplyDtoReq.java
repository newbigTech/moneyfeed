package com.newhope.moneyfeed.user.api.dto.request.client;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class ModifyUserShopApplyDtoReq {
	
	@NotNull
	@ApiModelProperty("主键Id")
	private Long applyId;
	
	@ApiModelProperty("分配的商户Id")
	private Long shopId;
	
	@ApiModelProperty("平台处理意见")
	private String platformDealMsg;
	
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

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getPlatformDealMsg() {
		return platformDealMsg;
	}

	public void setPlatformDealMsg(String platformDealMsg) {
		this.platformDealMsg = platformDealMsg;
	}

}
