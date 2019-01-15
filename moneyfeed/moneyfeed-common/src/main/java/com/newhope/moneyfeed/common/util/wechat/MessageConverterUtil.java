package com.newhope.moneyfeed.common.util.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Lists;
import com.newhope.moneyfeed.common.constant.WechatConstant;
import com.newhope.moneyfeed.common.vo.News;
import com.newhope.moneyfeed.common.vo.NewsMessage;
import com.newhope.moneyfeed.common.vo.Textmessage;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;


/**
 * @ClassName: MessageConverterUtil
 * @Description: 消息转换处理类，把收到微信的xml转换成map,把要发给微信的消息转成xml
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年9月22日 下午1:38:59
 * 
 */
public class MessageConverterUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageConverterUtil.class);

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


	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item",News.class);
		return xstream.toXML(newsMessage);
	}

	/**
	 * @Title: menuText 
	 * @Description: 组装一个文本消息字符串 
	 * @param 
	 * @return 设定文件
	 * @return
	 * String 返回类型 @throws
	 */

	public static String menuText(StringBuilder menuStr) {
		return menuStr.toString();
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
	public static String initText(String fromUserName, String toUserName, String content) {
		Textmessage textmessage = new Textmessage();
		textmessage.setFromUserName(fromUserName);
		textmessage.setToUserName(toUserName);
		textmessage.setMsgType(MessageConverterUtil.MESSAGE_TEXT);
		textmessage.setCreateTime(String.valueOf(System.currentTimeMillis()));
		textmessage.setContent(content);
		String message = MessageConverterUtil.textMessageToXml(textmessage);
		return message;
	}

	public static String initNewsText(String toUserName, String fromUserName){
		NewsMessage newsMessage=new NewsMessage();
		News news=new News();
		news.setDescription("请点击图片完成登录");
		news.setTitle("欢迎关注料你富，赶快点击登录吧！");
		news.setPicUrl("http://yanfa-prod.oss-cn-shanghai.aliyuncs.com/moneyfeed/image/20181129/liaonifu.png");
		news.setUrl(WechatConstant.WECHAT_INDEX);
		News newTwo=new News();
		newTwo.setTitle("不认识我们？点击这里☞【开启指引】两分钟了解！");
		newTwo.setUrl("https://mp.weixin.qq.com/s/bOFEfYYpdeN4VzzM9TPUlQ");
		List<News> artList= Lists.newArrayList(news,newTwo);
		newsMessage.setArticleCount(artList.size());
		newsMessage.setArticles(artList);
		newsMessage.setMsgType("news");
		newsMessage.setFromUserName(fromUserName);
		newsMessage.setToUserName(toUserName);
		newsMessage.setCreateTime(String.valueOf(System.currentTimeMillis()));
		String message = MessageConverterUtil.newsMessageToXml(newsMessage);
		return message;
	}

	/**
	 * 对象到xml的处理
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		@Override
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				@Override
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				@Override
				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

}
