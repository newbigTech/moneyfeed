package com.newhope.openapi.api.vo.request.pay;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class MerchParamReq implements Serializable {

	private static final long serialVersionUID = -4257699587686455863L;
	
	@JSONField(ordinal=1)
	private String merchName;
	@JSONField(ordinal=2)
	private String customerId;
	@JSONField(ordinal=3)
	private List<SubMerchInfoReq> subMerchInfos;
	
	public String getMerchName() {
		return merchName;
	}

	public List<SubMerchInfoReq> getSubMerchInfos() {
		return subMerchInfos;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public void setSubMerchInfos(List<SubMerchInfoReq> subMerchInfos) {
		this.subMerchInfos = subMerchInfos;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
