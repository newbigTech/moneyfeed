package com.newhope.openapi.api.vo.request.msg;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class SmsAuthCodeReq implements Serializable {

	private static final long serialVersionUID = 6171096406704618627L;

	@ApiModelProperty(name="mobile", notes="注册手机号", required=true)
	private String mobile;
	
	@ApiModelProperty(name="templateType", notes="短信模板类型", required=false)
	private String templateType;
	
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
