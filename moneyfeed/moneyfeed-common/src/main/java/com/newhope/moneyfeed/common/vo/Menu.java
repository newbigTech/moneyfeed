package com.newhope.moneyfeed.common.vo;


/**  
* @ClassName: Menu  
* @Description: 自定义菜单实体类,返回给微信服务器的类
* @author luoxl
* @date 2016年9月22日 下午2:01:45  
*    
*/
public class Menu {
	//自定义菜单中包含的三个一级菜单
	private Button[] button;
	
	Matchrule matchrule;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}

	public Matchrule getMatchrule() {
		return matchrule;
	}

	public void setMatchrule(Matchrule matchrule) {
		this.matchrule = matchrule;
	}
}
