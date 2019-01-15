package com.newhope.moneyfeed.api.dto.request.sms;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Map;

/**
 * 发送短信请求参数
 *
 * @author: zhangyanyan
 * @date: 2018/11/19
 */
public class SmsSendDtoReq implements Serializable{
    private static final long serialVersionUID = -5557533903963777686L;

    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(name="mobile", notes="手机号，多个手机号用逗号分隔", required=true)
    private String mobile;

    @NotEmpty(message = "短信模板不能为空")
    @ApiModelProperty(name="templateId", notes="短信模板ID，模板枚举类：SmsTemplateEnums", required=true)
    private String templateId;

    @ApiModelProperty(name="paramMap", notes="短信模板参数值", required=false)
    private Map<String, String> paramMap;

    @ApiModelProperty(name="isAuthCode", notes="是否验证码短信,默认为false", required=false)
    private boolean isAuthCode = false;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public boolean isAuthCode() {
        return isAuthCode;
    }

    public void setAuthCode(boolean authCode) {
        isAuthCode = authCode;
    }
}
