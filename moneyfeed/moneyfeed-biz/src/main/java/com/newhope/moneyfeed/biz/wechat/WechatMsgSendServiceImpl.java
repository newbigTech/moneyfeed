package com.newhope.moneyfeed.biz.wechat;

import com.newhope.moneyfeed.api.dto.request.wechat.WechatMsgDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.AppSourceEnums;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.service.wechat.WechatMsgSendService;
import com.newhope.moneyfeed.common.constant.WechatConstant;
import com.newhope.moneyfeed.common.util.wechat.WechatMsgTemplateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jeewx.api.core.req.model.message.IndustryTemplateMessageSend;
import org.jeewx.api.core.req.model.message.TemplateMessage;
import org.jeewx.api.wxsendmsg.JwTemplateMessageAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**  
* @ClassName: MsgSendService  
* @Description: 消息发送SERVICE
* @author A18ccms a18ccms_gmail_com  
* @date 2017年5月27日 下午2:05:27  
*    
*/
@Service
public class WechatMsgSendServiceImpl implements WechatMsgSendService{
	private final Logger logger = LoggerFactory.getLogger(WechatMsgSendServiceImpl.class);
	@Autowired
	WechatService wechatService;

	@Value("${feedback.templateid}")
	private String templateId;

	private String send(String templateId, String openid, String topColor, String url, TemplateMessage data)
			throws Exception {
		if (StringUtils.isEmpty(templateId)) {
			throw new Exception("templateId不能为空");
		}
		if (StringUtils.isEmpty(openid)) {
			throw new Exception("openid不能为空");
		}
		IndustryTemplateMessageSend industryTemplateMessageSend = new IndustryTemplateMessageSend();
		industryTemplateMessageSend.setAccess_token(wechatService.getToken());
		industryTemplateMessageSend.setTemplate_id(templateId);
		industryTemplateMessageSend.setTouser(openid);
		if (!StringUtils.isEmpty(url)) {
			industryTemplateMessageSend.setUrl(url);
		}
		industryTemplateMessageSend.setTopcolor(WechatConstant.MSG_TOP_COLOR);
		industryTemplateMessageSend.setData(data);
		return JwTemplateMessageAPI.sendTemplateMsg(industryTemplateMessageSend);
	}

	@Override
	public DtoResult sendMsg(WechatMsgDtoReq wechatMsgDtoReq) {
		if (StringUtils.isEmpty(wechatMsgDtoReq.getOpenid())) {
			throw new BizException("openid不能为空");
		}
		TemplateMessage data = null;
		String s="";
		Map<String,String> params=createParamsMap(wechatMsgDtoReq);
		try {
			data = getMsgData(params);
		    s= send(templateId, wechatMsgDtoReq.getOpenid(), null, wechatMsgDtoReq.getUrl(),
					data);
		} catch (Exception e) {
			logger.error("发送微信消息时系统异常:{}",e);
			throw new BizException("发送微信消息时系统异常");
		}
		// 调用微信接口发送消息

		if (!"ok".equals(s)) {
			logger.info("[MsgSendService.sendWechartMsg失败]" + s);
			return  DtoResult.fail(ResultCode.FAIL);
		}else {
			return DtoResult.success();
		}
	}


	private TemplateMessage getMsgData(Map<String, String> paramMap) throws Exception {
		TemplateMessage data = new TemplateMessage();
		Set<String> keys = paramMap.keySet();
		final Set<Map.Entry<String, String>> entries = paramMap.entrySet();
		for (String key : keys) {
			if ("first".equals(key)) {
				data.setFirst(WechatMsgTemplateUtil.setTemplateData("first", WechatConstant.MSG_PARAM_COLOR,
						paramMap.get("first")));
			} else if ("keyword1".equals(key)) {
				data.setKeyword1(WechatMsgTemplateUtil.setTemplateData("keyword1", WechatConstant.MSG_PARAM_COLOR,
						paramMap.get("keyword1")));
			} else if ("keyword2".equals(key)) {
				data.setKeyword2(WechatMsgTemplateUtil.setTemplateData("keyword2", WechatConstant.MSG_PARAM_COLOR,
						paramMap.get("keyword2")));
			} else if ("keyword3".equals(key)) {
				data.setKeyword3(WechatMsgTemplateUtil.setTemplateData("keyword3", WechatConstant.MSG_PARAM_COLOR,
						paramMap.get("keyword3")));
			} else if ("keynote1".equals(key)) {
				data.setKeynote1(WechatMsgTemplateUtil.setTemplateData("keynote1", WechatConstant.MSG_PARAM_COLOR,
						paramMap.get("keynote1")));
			} else if ("keynote2".equals(key)) {
				data.setKeynote2(WechatMsgTemplateUtil.setTemplateData("keynote2", WechatConstant.MSG_PARAM_COLOR,
						paramMap.get("keynote2")));
			} else if ("keynote3".equals(key)) {
				data.setKeynote3(WechatMsgTemplateUtil.setTemplateData("keynote3", WechatConstant.MSG_PARAM_COLOR,
						paramMap.get("keynote3")));
			} else if ("remark".equals(key)) {
				data.setRemark(WechatMsgTemplateUtil.setTemplateData("remark", WechatConstant.MSG_PARAM_COLOR,
						paramMap.get("remark")));
			} else {

			}
		}
		return data;
	}


	private Map<String,String> createParamsMap(WechatMsgDtoReq wechatMsgDtoReq){

			Map<String,String> parmas=new HashMap<String,String>(3);

			parmas.put("keyword1",wechatMsgDtoReq.getWechatMsgTypeEnums().getDesc());

		    parmas.put("keyword2","通知");

			String remark="";

		    if(CollectionUtils.isNotEmpty(wechatMsgDtoReq.getParams())){
				 remark= MessageFormat.format(wechatMsgDtoReq.getWechatMsgTypeEnums().getTemplateStr(),wechatMsgDtoReq.getParams().toArray());
			}else {
		    	remark=wechatMsgDtoReq.getWechatMsgTypeEnums().getTemplateStr();
			}

		    parmas.put("remark",remark);

		    return parmas;
	}



}
