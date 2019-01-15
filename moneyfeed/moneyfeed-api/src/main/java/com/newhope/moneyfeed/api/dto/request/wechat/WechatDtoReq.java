package com.newhope.moneyfeed.api.dto.request.wechat;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Map;

/**  
* @ClassName: WechatDtoReq  
* @Description: 微信请求DTO
* @author luoxl
* @date 2018年5月9日 下午4:31:32  
*    
*/
public class WechatDtoReq implements Serializable {

	private static final long serialVersionUID = 7639755842301785194L;
	
	@ApiModelProperty(name="map", notes="微信交互解析XML后的map")
	Map<String, String> map;
	@ApiModelProperty(name="signature", notes="微信传入的signature")
	private String signature;
	@ApiModelProperty(name="timestamp", notes="微信传入的timestamp")
	private String timestamp;
	@ApiModelProperty(name="nonce", notes="微信传入的nonce")
	private String nonce;
	@ApiModelProperty(name="echostr", notes="微信传入的echostr")
	private String echostr;

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String getSignature() {
		return signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
}
