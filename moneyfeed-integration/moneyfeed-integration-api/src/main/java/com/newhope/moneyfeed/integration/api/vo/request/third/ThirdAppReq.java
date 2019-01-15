package com.newhope.moneyfeed.integration.api.vo.request.third;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: ThirdAppReq
 * @Description: 第三方平台请求req
 * 
 */
public class ThirdAppReq<T> implements Serializable {
	
	private static final long serialVersionUID = 3874540954437385788L;
	
	@ApiModelProperty(name = "apikey", notes = "apikey(第三方账号)", required = true)
	private String apikey;
	@ApiModelProperty(name = "timestamp", notes = "时间戳，10位，精确到秒", required = true)
	private Long timestamp;
	@ApiModelProperty(name = "sign", notes = "MD5签名串,MD5(data, secret_key, utf-8)data=apikey+timestamp+业务参数JSON字符串(按参数首字母ASCII码顺序)", required = true)
	private String sign;
	@ApiModelProperty(name = "data", notes = "业务数据")
	private T data;
	
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
