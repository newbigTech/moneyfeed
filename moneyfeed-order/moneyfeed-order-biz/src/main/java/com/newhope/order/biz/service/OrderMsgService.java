package com.newhope.order.biz.service;

import java.util.List;
import java.util.Map;

import com.newhope.moneyfeed.api.enums.wechat.WechatMsgTypeEnums;

public interface OrderMsgService {
	
	/**
	 * 发送wechat站点消息
	 * @param userId 平台用户id
	 * @param msgType WechatMsgTypeEnums
	 * @param params 消息模板值参数
	 * @param url 消息跳转链接
	 * @creator : pudongliang  
	 * @dateTime : 2018年11月29日下午2:16:53
	 */
	void sendWechatMsg(Long userId , WechatMsgTypeEnums msgType , List<String> params,String url);
	
	/**
	 * 发送wechat站点消息
	 * @param userId 平台用户id
	 * @param msgType WechatMsgTypeEnums
	 * @param params 消息模板值参数
	 * @creator : pudongliang  
	 * @dateTime : 2018年11月29日下午2:16:53
	 */
	void sendWechatMsg(Long userId , WechatMsgTypeEnums msgType , List<String> params);
	
	/**
	 * 发送aliyun短信消息
	 * @param mobile (mobile="137xxx,138xxx")
	 * @param templateId SmsTemplateEnums
	 * @param authCode 是否验证码
	 * @param paramMap 模板kv参数
	 * @creator : pudongliang  
	 * @dateTime : 2018年11月29日下午2:14:26
	 */
	void sendSmsMsg(String mobile , String templateId ,boolean authCode, Map<String, String> paramMap);
	
	/**
	 * 发送aliyun短信消息
	 * @param mobile (mobile="137xxx,138xxx")
	 * @param templateId SmsTemplateEnums
	 * @param paramMap 模板kv参数
	 * @creator : pudongliang  
	 * @dateTime : 2018年11月29日下午2:14:26
	 */
	void sendSmsMsg(String mobile, String templateId, Map<String, String> paramMap);
	
	
}
