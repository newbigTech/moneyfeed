package com.newhope.moneyfeed.web.wechat;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.newhope.cache.core.Cache;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.request.wechat.WechatDtoReq;
import com.newhope.moneyfeed.api.dto.response.wechat.WechatDtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.rest.wechat.WechatAPI;
import com.newhope.moneyfeed.api.service.wechat.WechatEventService;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.request.wechat.JsConfigReq;
import com.newhope.moneyfeed.api.vo.response.wechat.JsConfigResult;
import com.newhope.moneyfeed.api.vo.response.wechat.RedirectResult;
import com.newhope.moneyfeed.api.vo.response.wechat.WechatJsticketResult;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.constant.WechatConstant;
import com.newhope.moneyfeed.common.util.wechat.MessageConverterUtil;
import com.newhope.moneyfeed.common.util.wechat.SignUtil;
import com.newhope.moneyfeed.common.util.wechat.WechatUtil;
import com.newhope.moneyfeed.biz.wechat.WechatService;
import com.newhope.moneyfeed.common.vo.News;
import com.newhope.moneyfeed.common.vo.NewsMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class WechatController extends AbstractController implements WechatAPI {
    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
	WechatService wechatService;

    @Autowired
	WechatEventService wechatEventService;

	@Autowired
	@Qualifier("wechatCache")
	Cache wechatCache;

	@Autowired
	RSessionCache rSessionCache;


  /*  @Override
    public BaseResponse<MediaResult> uploadPhoto(@RequestBody MediaReq requestBody) {
        MediaResult result = null;
        try {
            requestBody.setType(FileTypeEnums.IMAGE.name());
            if (requestBody.getAppCode() == null) {
                requestBody.setAppCode(SessionUtil.getValueByCookie("APP_CODE", ContextHolderUtil.getRequest()));
            }
            result = wechatService.uploadWechartFile(requestBody);
        } catch (Exception e) {
            logger.error("取微信服务器上的图片上传到WEB服务器出现异常", e);
            return buildJson(ResultCode.ERROR);
        }
        return buildJson(result.getCode(), result.getMsg(), result);
    }*/

	@Override
	public BaseResponse<WechatDtoResult> coreJoinGetJubaozhu(@RequestBody WechatDtoReq wechatDtoReq) {
		WechatDtoResult result = new WechatDtoResult();
		logger.info("[WechatController.coreJoinGetJubaozhu]接入开始");
		result.setCode(ResultCode.SUCCESS.getCode());
		try {
			String signature = wechatDtoReq.getSignature(); // 微信加密签名
			String timestamp = wechatDtoReq.getTimestamp(); // 时间戳
			String nonce = wechatDtoReq.getNonce();// 随机数
			String echostr = wechatDtoReq.getEchostr();// 随机字符串
			logger.info("[WechatController.coreJoinGetJubaozhu]接入参数" + signature + " " + timestamp + " " + nonce + " " + echostr);
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			boolean b = SignUtil.checkSignatureJubaozhu(signature, timestamp, nonce, WechatConstant.WECHAT_TOKEN);
			result.setPass(b);
			logger.info("WechatController.coreJoinGetJubaozhu接入验签" + b);
			if (b) {
				result.setCode(ResultCode.SUCCESS.getCode());
			} else {
				logger.error("[WechatController.coreJoinGetJubaozhu],微信请求验签错误!");
				result.setCode(ResultCode.FAIL.getCode());
				result.setMsg(ResultCode.FAIL.getDesc());
			}
		} catch (Exception e) {
			logger.error("[WechatController.coreJoinGetJubaozhu],微信接入时出现异常!", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		} 
		return buildJson(result);
	}

	@Override
	public BaseResponse<WechatDtoResult> accessPointJubaozhuPost(@RequestBody WechatDtoReq wechatDtoReq) {
		logger.info("[WechatController.accessPointJubaozhuPost]数据{}" + JSON.toJSONString(wechatDtoReq));
		WechatDtoResult result = new WechatDtoResult();
		result.setCode(ResultCode.SUCCESS.getCode());
		try {
			String msgType = wechatDtoReq.getMap().get("MsgType");
			if (MessageConverterUtil.MESSAGE_EVENT.equals(msgType)) {
				//用户关注公众号向用户发送欢迎消息网页授权链接
				String eventType = wechatDtoReq.getMap().get("Event");
				String toUserId = wechatDtoReq.getMap().get("FromUserName");
				String fromUserName = wechatDtoReq.getMap().get("ToUserName");
				if(MessageConverterUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
					String mesStr = MessageConverterUtil.initNewsText(toUserId, fromUserName);
					result.setMessageReturn(mesStr);
					//异步保存用户关注事件
					wechatEventService.saveSysWechatSubscirptEvent(toUserId);
				}
				if(MessageConverterUtil.MESSAGE_CLICK.equals(eventType)){
					String key=wechatDtoReq.getMap().get("EventKey");
					//如果是菜单联系我们发出的事件请求
					if(WechatConstant.CONTACTUS.equals(key)){
						String mesStr=MessageConverterUtil.initText(fromUserName,toUserId,WechatConstant.CONTACTUSTEXT);
						result.setMessageReturn(mesStr);
					}
				}
			}
		} catch (Exception e) {
			logger.error("[WechatController.accessPointJubaozhu]微信向服务器交互发生异常!", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}
		return buildJson(result);
	}

	@Override
	public BaseResponse<JsConfigResult> getJsConfig(@RequestBody JsConfigReq requestBody) {
		JsConfigResult result = new JsConfigResult();
		String appId = "";
		String url = "";
		try {
			//获取APP_CODE
			appId = WechatConstant.APPID; // 必填，公众号的唯一标识
			url = WechatConstant.HOMEPAGE_URL + requestBody.getUrl();
			//2、获取Ticket  
			String jsapi_ticket  = wechatService.getJsapiTicket();
			//3、时间戳和随机字符串
			String nonceStr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串  
			String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳  

			//5、将参数排序并拼接字符串  
			String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
			//6、将字符串进行sha1加密  
			String signature = WechatUtil.SHA1(str);

			result.setAppId(appId);
			result.setTimestamp(timestamp);
			result.setNonceStr(nonceStr);
			result.setSignature(signature);
			result.setCode(ResultCode.SUCCESS.getCode());
		} catch (Exception e) {
			logger.error("获取到的JSSDK配置参数出现异常", e);
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}
		return buildJson(result);
	}

	@Override
	public BaseResponse<RedirectResult> oauth() {
		RedirectResult result = new RedirectResult();
		Map<String, Object> resultMap = new HashMap<>();
		result.setCode(ResultCode.SUCCESS.getCode());
		resultMap.put("appid", WechatConstant.APPID);
		result.setResultMap(resultMap);
		return buildJson(result);
	}



}
