package com.newhope.moneyfeed.common.vo;

/**  
* @ClassName: Textmessage  
* @Description: 文本消息
* @author luoxl
* @date 2016年9月22日 下午2:08:33  
*    
*/
public class Textmessage extends BaseMessage{
	/*参数	描述
	ToUserName	开发者微信号
	FromUserName	 发送方帐号（一个OpenID）
	CreateTime	 消息创建时间 （整型）
	MsgType	 text
	Content	 文本消息内容
	MsgId	 消息id，64位整型*/
	
	private String Content;
	private String MsgId;
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
}











