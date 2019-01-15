package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.util.Date;

/**
 *   用户中心-客户端用户表
 */
public class UcClientUserModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -828407021539828204L;

	/** 用户编号,自动生成,日期+5位流水号             */
    private String code;

    /** 用户账号,自动生成 8位随机码 */
    private String account;

    /** 手机号 */
    private String mobile;

    /** 用户名 */
    private String name;

    /** 密码 */
    private String password;

    /** 最后访问时间 */
    private Date lastLoginTime;

    /** 用户来源 */
    private String source;

    /** 是否可用 */
    private Boolean enable;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}