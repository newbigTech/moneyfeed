package com.newhope.moneyfeed.api.dto.request.wechat;

import com.newhope.moneyfeed.api.enums.wechat.WechatMsgTypeEnums;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class WechatMsgDtoReq implements Serializable {

	private static final long serialVersionUID = -3181877339097992352L;
	//微信消息接收者ID
	private String openid;
	//消息跳转的url
	private String url;

	//参数列表按模板参数顺序
	private List<String> params;

	private WechatMsgTypeEnums wechatMsgTypeEnums;


	public String getOpenid() {
		return openid;
	}
	public String getUrl() {
		return url;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public WechatMsgTypeEnums getWechatMsgTypeEnums() {
		return wechatMsgTypeEnums;
	}

	public void setWechatMsgTypeEnums(WechatMsgTypeEnums wechatMsgTypeEnums) {
		this.wechatMsgTypeEnums = wechatMsgTypeEnums;
	}
}
