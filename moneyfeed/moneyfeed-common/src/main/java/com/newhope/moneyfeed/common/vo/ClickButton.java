package com.newhope.moneyfeed.common.vo;

/**  
* @ClassName: ClickButton  
* @Description: 自定义菜单子菜单，点击菜单
* @author luoxl
* @date 2016年9月22日 下午2:01:28  
*    
*/
public class ClickButton extends Button {
	//点击事件回送的key
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
