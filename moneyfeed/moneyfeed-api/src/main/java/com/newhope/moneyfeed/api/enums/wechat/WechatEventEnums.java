package com.newhope.moneyfeed.api.enums.wechat;

/**
 * Created by liming on 2019/1/7.
 */
public enum  WechatEventEnums {

    SUBCRIPT("关注微信公众号","G00001");

    private String desc;

    private String val;

    WechatEventEnums(String desc, String val) {
        this.desc = desc;
        this.val = val;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}