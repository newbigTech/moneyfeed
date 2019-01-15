package com.newhope.openapi.api.vo.request.order.pay;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class PayPasswordVerifyReq implements Serializable{
	
	private static final long serialVersionUID = -479638118911102231L;
	
	@NotBlank(message = "新密码")
    @Pattern(regexp = "^\\d{6}$", message = "密码只允许6位数字")
    @ApiModelProperty("新密码")
    private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
