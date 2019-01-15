package com.newhope.moneyfeed.common.vo;

/**
 * @ClassName: BaseMessage
 * @Description: 发送消息给微信时的基类
 * @author luoxl
 * @date 2016年9月22日 下午1:49:47
 * 
 */
public class BaseMessage {
	//接收人
	private String ToUserName;
	//发送人
	private String FromUserName;
	//创建时间
	private String CreateTime;
	//消息类型
	private String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
}
