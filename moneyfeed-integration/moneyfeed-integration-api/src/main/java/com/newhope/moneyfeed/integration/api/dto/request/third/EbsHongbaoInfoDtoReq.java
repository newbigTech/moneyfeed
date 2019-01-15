package com.newhope.moneyfeed.integration.api.dto.request.third;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @description:
 * @author: dongql
 * @date: 2018/5/7 20:35
 */
public class EbsHongbaoInfoDtoReq implements Serializable{
    private static final long serialVersionUID = -3245090227659976873L;
    @NotBlank(message="mobile为必传参数")
    @ApiModelProperty(name = "mobile", notes = "手机号", required = true)
    private String mobile;
    @NotBlank(message="authCode为必传参数")
    @ApiModelProperty(name = "authCode", notes = "用户编号", required = true)
    private String authCode;

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



}
