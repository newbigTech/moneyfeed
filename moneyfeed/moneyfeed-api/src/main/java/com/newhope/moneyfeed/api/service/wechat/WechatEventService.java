package com.newhope.moneyfeed.api.service.wechat;

/**
 * Created by liming on 2019/1/7.
 */
public interface WechatEventService {

    /**
     * 保存关注微信公众号事件
     * @param openId
     */
    void saveSysWechatSubscirptEvent(String openId);

}