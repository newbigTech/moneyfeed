package com.newhope.moneyfeed.integration.api.vo.request.third;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class SynMemberReq implements Serializable {

	private static final long serialVersionUID = 8704976813795051086L;

	@ApiModelProperty(name = "shopIds", notes = "店铺id集合", required = true)
	private List<Long> shopIds;

	@NotNull(message="mobile为必传参数")
	@ApiModelProperty(name = "mobile", notes = "会员电话", required = true)
	private String mobile;

	@NotNull(message="name为必传参数")
	@ApiModelProperty(name = "name", notes = "会员名称", required = true)
	private String name;

	@NotNull(message="thirdId为必传参数")
	@ApiModelProperty(name = "thirdId", notes = "第三方系统业务主键id", required = true)
	private String thirdId;

	public List<Long> getShopIds() {
		return shopIds;
	}

	public void setShopIds(List<Long> shopIds) {
		this.shopIds = shopIds;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThirdId() {
		return thirdId;
	}

	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}

}
