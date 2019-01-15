package com.newhope.moneyfeed.common.vo;

/**  
* @ClassName: AccessToken  
* @Description: 微信访问AccessToken实体类
* @author luoxl
* @date 2016年9月22日 下午1:49:37  
*    
*/
public class TestAccessToken {
	//访问token
	private String access_token;
	//token过期时间,秒
	private int expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
}
