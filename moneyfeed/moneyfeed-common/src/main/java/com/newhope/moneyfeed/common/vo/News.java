package com.newhope.moneyfeed.common.vo;

/**  
* @ClassName: News  
* @Description: 图文消息
* @author luoxl
* @date 2016年9月22日 下午2:01:53  
*    
*/
public class News{
	//标题
	private String Title;
	//描述
	private String Description;
	//图片路径
	private String PicUrl;
	//跳转url
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
}
