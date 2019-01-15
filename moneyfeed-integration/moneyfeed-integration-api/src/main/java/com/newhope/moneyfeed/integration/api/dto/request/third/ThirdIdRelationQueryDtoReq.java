package com.newhope.moneyfeed.integration.api.dto.request.third;

import java.io.Serializable;

public class ThirdIdRelationQueryDtoReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9140220739985435953L;

	/** 第三方ID */
	private String thirdId;

	/** 调用聚宝猪服务code */
	private String code;

	/** 是否可用 */
	private Boolean enable;

	/** 调用方key */
	private String apiKey;

	public String getThirdId() {
		return thirdId;
	}

	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

}
