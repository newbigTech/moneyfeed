package com.newhope.moneyfeed.user.api.bean.client;

import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   用户中心-客户端用户三方账号表
 */
public class UcClientUserThirdAccountModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1933493732422875648L;

	/** 用户ID */
    private Long userId;

    /** 第三方账号 */
    private String thirdAccount;

    /** 第三方账号来源 */
    private String thirdSource;

    /** 昵称 */
    private String nickName;

    /** 头像地址 */
    private String imgUrl;

    /** 是否可用 */
    private Boolean enable;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getThirdAccount() {
        return thirdAccount;
    }

    public void setThirdAccount(String thirdAccount) {
        this.thirdAccount = thirdAccount;
    }

    public String getThirdSource() {
        return thirdSource;
    }

    public void setThirdSource(String thirdSource) {
        this.thirdSource = thirdSource;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}