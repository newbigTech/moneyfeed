package com.newhope.moneyfeed.api.bean;

import java.util.Date;

public class SysWechatEventDataModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9024923272530626941L;

	private String openId;

    private Date createTime;

    private String evnId;

    private String wechatId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEvnId() {
        return evnId;
    }

    public void setEvnId(String evnId) {
        this.evnId = evnId;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }
}