package com.newhope.moneyfeed.user.api.dto.request.businessmanage;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class ModifyUserInfoBySmscodeDtoReq implements Serializable {


	private static final long serialVersionUID = -6348796722631329746L;
	@NotBlank(message="用户姓名为空")
	@ApiModelProperty(name="name", notes="用户姓名", required=true)
	private String name;
	@NotBlank(message="手机号为空")
	@ApiModelProperty(name="mobile", notes="登录手机号", required=true)
	private String mobile;
	@ApiModelProperty(name="smscode", notes="短信验证码", required=true)
	private String smscode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSmscode() {
		return smscode;
	}
	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}
}
