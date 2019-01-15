package com.newhope.moneyfeed.common.vo;



/**  
* @ClassName: ViewButton  
* @Description: 自定义菜单，URL跳转菜单
* @author luoxl
* @date 2016年9月22日 下午3:11:33  
*    
*/
public class ViewButton extends Button {
	//点击时跳转的url
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
