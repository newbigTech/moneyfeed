package com.newhope.openapi.api.vo.request.pay;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class SubMerchInfoReq implements Serializable {

	private static final long serialVersionUID = 6262150989031363415L;

	@JSONField(ordinal=1)
	private String subMerchId;
	
	@JSONField(ordinal=2)
	private String channelCode;

	public String getSubMerchId() {
		return subMerchId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setSubMerchId(String subMerchId) {
		this.subMerchId = subMerchId;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	
	
}
