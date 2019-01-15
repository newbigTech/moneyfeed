package com.newhope.moneyfeed.user.api.bean.platform;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-商户用户表
 */
public class UcPmUserModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1012130029812420046L;

	/** 用户姓名 */
    private String name;

    /** 用户手机号 */
    private String mobile;

    /** 用户密码 */
    private String password;

    /** 是否可用 */
    private Boolean enable;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}