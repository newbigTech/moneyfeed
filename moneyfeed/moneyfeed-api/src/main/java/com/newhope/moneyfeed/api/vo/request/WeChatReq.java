package com.newhope.moneyfeed.api.vo.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: WeChat
 * @Description: 引入微信时的数据接收类
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年9月22日 上午10:43:33
 * 
 */
public class WeChatReq implements Serializable {
	private static final long serialVersionUID = 665915677405887593L;
	@ApiModelProperty(name="signature", notes="微信传入的signature", required=true)
	private String signature;
	@ApiModelProperty(name="timestamp", notes="微信传入的timestamp", required=true)
	private String timestamp;
	@ApiModelProperty(name="nonce", notes="微信传入的nonce", required=true)
	private String nonce;
	@ApiModelProperty(name="echostr", notes="微信传入的echostr", required=true)
	private String echostr;

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
