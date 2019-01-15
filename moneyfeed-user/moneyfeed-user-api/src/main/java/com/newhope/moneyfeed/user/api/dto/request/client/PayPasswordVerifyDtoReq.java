package com.newhope.moneyfeed.user.api.dto.request.client;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class PayPasswordVerifyDtoReq implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6528535808752958160L;

	@NotBlank(message = "新密码")
    @Pattern(regexp = "^\\d{6}$", message = "密码只允许6位数字")
    @ApiModelProperty("新密码")
    private String password;

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty("用户id")
    private Long userId;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
}
