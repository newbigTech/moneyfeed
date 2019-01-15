package com.newhope.moneyfeed.integration.api.bean.ebs.baseData;


import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   第三方平台model
 */
public class ThirdAppModel extends BaseModel {

	private static final long serialVersionUID = -5900259726449789957L;
	
	private String apiKey;
	private String secretKey;
	private String thirdName;
	
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getThirdName() {
		return thirdName;
	}
	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}
}