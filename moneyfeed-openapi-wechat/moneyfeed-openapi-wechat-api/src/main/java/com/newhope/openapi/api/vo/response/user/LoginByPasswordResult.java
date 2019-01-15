package com.newhope.openapi.api.vo.response.user;



import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class LoginByPasswordResult extends Result{

	private static final long serialVersionUID = -5713138873132894370L;
	
	@ApiModelProperty(name = "firstLogin", notes = "是否首次登陆系统")
    private boolean firstLogin;

	public boolean getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
    
}
