package com.newhope.openapi.api.vo.request.user;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Create by yyq on 2018/12/26
 */
public class ModifyUserInfoBySmscodeReq implements Serializable {

    private static final long serialVersionUID = -8844958850083725030L;
    @NotBlank(message="用户姓名为空")
    @ApiModelProperty(name="name", notes="用户姓名", required=true)
    private String name;
    @NotBlank(message="手机号为空")
    @ApiModelProperty(name="mobile", notes="登录手机号", required=true)
    private String mobile;
    @ApiModelProperty(name="smscode", notes="短信验证码", required=false)
    private String smscode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmscode() {
        return smscode;
    }

    public void setSmscode(String smscode) {
        this.smscode = smscode;
    }
}
