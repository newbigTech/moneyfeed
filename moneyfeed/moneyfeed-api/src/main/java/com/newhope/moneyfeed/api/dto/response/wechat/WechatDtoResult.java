package com.newhope.moneyfeed.api.dto.response.wechat;


import com.newhope.moneyfeed.api.dto.response.DtoResult;

import java.io.Serializable;

/**  
* @ClassName: WechatDtoResult  
* @Description: 微信返回类
* @author luoxl
* @date 2018年5月9日 下午4:34:38  
*    
*/
public class WechatDtoResult extends DtoResult implements Serializable {
	private static final long serialVersionUID = -6143982290992089809L;
	//要回复给微信的消息
	private String messageReturn;
	private boolean pass;

	public String getMessageReturn() {
		return messageReturn;
	}

	public void setMessageReturn(String messageReturn) {
		this.messageReturn = messageReturn;
	}

	public boolean getPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}
}
