package com.newhope.moneyfeed.user.api.dto.request.client;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class UserShopQueryDtoReq {
	
    @ApiModelProperty("商城id")
    @NotNull(message = "商城id不能为空")
    private Long shopId;
    
    @ApiModelProperty("角色code集合")
    private List<String> roleCodeList;

	public Long getShopId() {
		return shopId;
	}

	public List<String> getRoleCodeList() {
		return roleCodeList;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setRoleCodeList(List<String> roleCodeList) {
		this.roleCodeList = roleCodeList;
	}

}
