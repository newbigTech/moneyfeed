package com.newhope.moneyfeed.api.vo.response.wechat;

import com.newhope.moneyfeed.api.vo.response.Result;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


public class JsConfigResult extends Result implements Serializable {
	private static final long serialVersionUID = -5636174663649474301L;
	
	@ApiModelProperty(name="appId", notes="APPID")
	private String appId;
	@ApiModelProperty(name="timestamp", notes="时间戳")
    private String timestamp;
	@ApiModelProperty(name="nonceStr", notes="随机字符串")
    private String nonceStr;
	@ApiModelProperty(name="signature", notes="签名")
    private String signature;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
