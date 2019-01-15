package com.newhope.moneyfeed.integration.api.enums.third;


public enum AliyunVodOperEnums{
	CREATE_UPLOAD_VIDEO("获取视频上传地址和凭证", "CREATE_UPLOAD_VIDEO"),
	REFRESH_UPLOAD_VIDEO("刷新视频上传凭证", "REFRESH_UPLOAD_VIDEO"); 

	private String desc;

	private String value;

	private AliyunVodOperEnums(String desc, String value) {
		this.desc = desc;
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public String getValue() {
		return value;
	}
}
