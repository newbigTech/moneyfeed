package com.newhope.moneyfeed.api.vo.request.wechat;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MediaReq implements Serializable {

	private static final long serialVersionUID = 969593228002072148L;

	@ApiModelProperty(name = "mediaId", notes = "微信媒介文件id", required = true)
	private String mediaId;

	@ApiModelProperty(name = "shopId", notes = "店铺id", required = false)
	private Long shopId;

	@ApiModelProperty(name = "markFlag", notes = "是否加水印", required = false)
	private Boolean markFlag;
	
	@ApiModelProperty(name = "type", notes = "文件类型,图片:IMAGE;语音,VOICE", required = true)
	String type;
	
	@ApiModelProperty(name = "appCode", notes = "appCode", required = true)
	private String appCode;
	
	public Long getShopId() {
		return shopId;
	}

	public Boolean getMarkFlag() {
		return markFlag;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setMarkFlag(Boolean markFlag) {
		this.markFlag = markFlag;
	}


	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

}
