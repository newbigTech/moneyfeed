package com.newhope.moneyfeed.common.util.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.newhope.moneyfeed.common.vo.Textmessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

/**
 * @ClassName: MessageUtil
 * @Description: 消息处理类
 * @author rock
 * @date 2016年3月6日 下午12:31:29
 * 
 */

public class MessageInitUtil {
	private static final Logger logger = LoggerFactory.getLogger(MessageInitUtil.class);

	// 消息类型，文本
	public static final String MESSAGE_TEXT = "text";
	// 消息类型,图片
	public static final String MESSAGE_IMAGE = "image";
	// 消息类型,事件
	public static final String MESSAGE_EVENT = "event";
	// 消息类型，图文消息
	public static final String MESSAGE_NEWS = "news";
	// 消息类型，发送地理位置
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_LOCATION1 = "location_select";
	// 事件类型，关注
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	// 事件类型，取消关注
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	// 事件类型，点击
	public static final String MESSAGE_CLICK = "CLICK";
	// 事件类型，点击菜单跳转链接时的事件推送
	public static final String MESSAGE_VIEW = "VIEW";
	// 事件类型，扫码
	public static final String MESSAGE_SCANCODE = "scancode_push";

	/**  
	* @Title: xmlToMap  
	* @Description:  接收到微信服务器发送的消息后，转为map
	* @param @param request
	* @param @return
	* @param @throws DocumentException
	* @param @throws IOException    设定文件  
	* @return Map<String,String>    返回类型  
	* @throws  
	*/
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws DocumentException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream inStream = request.getInputStream();
		Document document = reader.read(inStream);

		Element root = document.getRootElement();
		List<Element> list = root.elements();
		for (Element element : list) {
			map.put(element.getName(), element.getText());
		}
		inStream.close();
		return map;
	}

	/**  
	* @Title: textMessageToXml  
	* @Description: 向微信服务器发送文本消息时，转为xml
	* @param @param textmessage
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws  
	*/
	public static String textMessageToXml(Textmessage textmessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", textmessage.getClass());
		return xstream.toXML(textmessage);
	}
	
	/**  
	* @Title: initText  
	* @Description: 组装一个向微信服务器发送的文本消息，按文本消息的对象格式组装
	* @param @param toUserName
	* @param @param fromUserName
	* @param @param content
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws  
	*/
	public static String initText(String toUserName, String fromUserName, String content) {
		Textmessage textmessage = new Textmessage();
		textmessage.setFromUserName(toUserName);
		textmessage.setToUserName(fromUserName);
		textmessage.setMsgType(MessageInitUtil.MESSAGE_TEXT);
		textmessage.setCreateTime(String.valueOf(new Date().getTime()));
		textmessage.setContent(content);
		String message = MessageInitUtil.textMessageToXml(textmessage);
		logger.info("组装一个向微信服务器发送的文本消息，按文本消息的对象格式组装,返回给微信的XML：" + message);
		return message;
	}

}
