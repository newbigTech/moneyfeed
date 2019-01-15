package com.newhope.moneyfeed.integration.api.vo.request.third;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class SynDiePlanReq implements Serializable {

	private static final long serialVersionUID = 7225842900265324616L;

	@NotBlank(message = "thirdId为必传参数")
	@ApiModelProperty(name = "thirdId", notes = "业务系统主键id", required = true)
	private String thirdId;

	@NotBlank(message = "uuid为必传参数")
	@ApiModelProperty(name = "uuid", notes = "单次业务uuid", required = true)
	private String uuid;
	
	@NotEmpty(message = "bizList为必传参数")
	@ApiModelProperty(name = "bizList", notes = "死淘业务详情List", required = true)
	private List<SynDiePlanDetailReq> bizList;

	public String getThirdId() {
		return thirdId;
	}

	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<SynDiePlanDetailReq> getBizList() {
		return bizList;
	}

	public void setBizList(List<SynDiePlanDetailReq> bizList) {
		this.bizList = bizList;
	}

}
