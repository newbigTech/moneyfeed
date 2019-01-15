package com.newhope.moneyfeed.order.api.dto.request.integration;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class RepositoryLowDtoReq implements Serializable {

	private static final long serialVersionUID = -5479218855449445393L;

	@ApiModelProperty(name = "moneyfeedOrderId", notes = "商成订单ID")
	private Long moneyfeedOrderId;
	@ApiModelProperty(name = "repositoryLowFlag", notes = "本次库存是否够,够false,不够true")
	private Boolean repositoryLowFlag;
	@ApiModelProperty(name = "beforeRepositoryLowFlag", notes = "上次库存是否够,够false,不够true")
	private Boolean beforeRepositoryLowFlag;

	public Long getMoneyfeedOrderId() {
		return moneyfeedOrderId;
	}

	public Boolean getRepositoryLowFlag() {
		return repositoryLowFlag;
	}

	public Boolean getBeforeRepositoryLowFlag() {
		return beforeRepositoryLowFlag;
	}

	public void setMoneyfeedOrderId(Long moneyfeedOrderId) {
		this.moneyfeedOrderId = moneyfeedOrderId;
	}

	public void setRepositoryLowFlag(Boolean repositoryLowFlag) {
		this.repositoryLowFlag = repositoryLowFlag;
	}

	public void setBeforeRepositoryLowFlag(Boolean beforeRepositoryLowFlag) {
		this.beforeRepositoryLowFlag = beforeRepositoryLowFlag;
	}

}
