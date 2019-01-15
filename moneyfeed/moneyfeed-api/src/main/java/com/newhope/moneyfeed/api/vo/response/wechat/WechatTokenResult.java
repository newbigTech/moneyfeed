package com.newhope.moneyfeed.api.vo.response.wechat;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class WechatTokenResult implements Serializable {

	private static final long serialVersionUID = 6908989171743963756L;

	@ApiModelProperty(name = "token", notes = "token")
	private String token;
	@ApiModelProperty(name = "tokenDate", notes = "token生成时间")
	private Date tokenDate;

	public String getToken() {
		return token;
	}

	public Date getTokenDate() {
		return tokenDate;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setTokenDate(Date tokenDate) {
		this.tokenDate = tokenDate;
	}

}
