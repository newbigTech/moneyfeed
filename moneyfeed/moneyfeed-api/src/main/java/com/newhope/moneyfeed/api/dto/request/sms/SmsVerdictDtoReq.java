package com.newhope.moneyfeed.api.dto.request.sms;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class SmsVerdictDtoReq implements Serializable {

    private static final long serialVersionUID = 5456942980929592641L;

    @NotEmpty(message = "短信验证码不能为空")
    @ApiModelProperty(name = "smsCode", notes = "验证码", required = true)
    private String smsCode;

    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(name = "mobile", notes = "手机号", required = true)
    private String mobile;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
