package com.newhope.moneyfeed.api.vo.request.wechat;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class JsConfigReq implements Serializable {

	private static final long serialVersionUID = -5714902468933166915L;
	
	@ApiModelProperty(name="url", notes="需要访问页面的路径", required=true)
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
