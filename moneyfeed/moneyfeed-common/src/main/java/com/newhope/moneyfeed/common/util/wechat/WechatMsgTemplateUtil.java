package com.newhope.moneyfeed.common.util.wechat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jeewx.api.core.req.model.message.TemplateData;

public class WechatMsgTemplateUtil {

	public static TemplateData setTemplateData(String key, String color, String value) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add("first");
		keys.add("keynote1");
		keys.add("keynote2");
		keys.add("keynote3");
		keys.add("keyword1");
		keys.add("keyword2");
		keys.add("keyword3");
		keys.add("remark");
		if(!keys.contains(key)){
			throw new Exception("传入的key值字符串不正确!");
		}
		if(StringUtils.isEmpty(color)){
			color = "#69b1cc";
		}
		if("first".equals(key)){
			TemplateData first= new TemplateData();
			first.setColor(color);
			first.setValue(value);
			return first;
		}else if("keynote1".equals(key)){
			TemplateData keynote1= new TemplateData();
			keynote1.setColor(color);
			keynote1.setValue(value);
			return keynote1;
		}else if("keynote2".equals(key)){
			TemplateData keynote2= new TemplateData();
			keynote2.setColor(color);
			keynote2.setValue(value);
			return keynote2;
		}else if("keynote3".equals(key)){
			TemplateData keynote3= new TemplateData();
			keynote3.setColor(color);
			keynote3.setValue(value);
			return keynote3;
		}else if("keyword1".equals(key)){
			TemplateData keyword1= new TemplateData();
			keyword1.setColor(color);
			keyword1.setValue(value);
			return keyword1;
		}else if("keyword2".equals(key)){
			TemplateData keyword2= new TemplateData();
			keyword2.setColor(color);
			keyword2.setValue(value);
			return keyword2;
		}else if("keyword3".equals(key)){
			TemplateData keyword3= new TemplateData();
			keyword3.setColor(color);
			keyword3.setValue(value);
			return keyword3;
		}else if("remark".equals(key)){
			TemplateData remark= new TemplateData();
			remark.setColor(color);
			remark.setValue(value);
			return remark;
		}else{
			throw new Exception("传入的key值字符串不正确!");
		}
	}
}
