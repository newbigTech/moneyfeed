package com.newhope.openapi.web.controller.wechat;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.common.util.wechat.MessageConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.cache.core.Cache;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.request.wechat.WechatDtoReq;
import com.newhope.moneyfeed.api.dto.response.wechat.WechatDtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.request.WeChatReq;
import com.newhope.moneyfeed.api.vo.request.wechat.JsConfigReq;
import com.newhope.moneyfeed.api.vo.response.wechat.JsConfigResult;
import com.newhope.moneyfeed.api.vo.response.wechat.RedirectResult;
import com.newhope.openapi.api.rest.wechat.WechatOpenAPI;
import com.newhope.openapi.biz.rpc.feign.wechat.WechatFeignClient;

@RestController
public class WechatOpenController extends AbstractController implements WechatOpenAPI {
	private static final Logger logger = LoggerFactory.getLogger(WechatOpenController.class);

	@Autowired
	WechatFeignClient wechatFeignClient;

	@Autowired
	@Qualifier("wechatCache")
	Cache wechatCache;

	@Override
	public void coreJoinGetJubaozhu(WeChatReq weChat, PrintWriter out) {
		logger.info("[WechatController.coreJoinGetJubaozhu]接入聚宝猪开始");
		try {
			WechatDtoReq wechatDtoReq = new WechatDtoReq();
			BeanUtils.copyProperties(weChat, wechatDtoReq);
			BaseResponse<WechatDtoResult> apiResult = wechatFeignClient.coreJoinGetJubaozhu(wechatDtoReq);
			boolean b = false;
			if (apiResult.getCode().equals(ResultCode.SUCCESS.getCode())) {
				b = apiResult.getData().getPass();
			}
			logger.info("WechatController.coreJoinGetJubaozhu接入聚宝猪验签" + b);
			if (b) {
				out.print(weChat.getEchostr());
			} else {
				logger.error("[WechatController.coreJoinGetJubaozhu],微信请求验签错误!");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("[WechatController.coreJoinGetJubaozhu],微信接入时出现异常!", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		logger.info("[WechatController.coreJoinGetJubaozhu]接入聚宝猪结束");
	}

	@Override
	public void accessPointJubaozhu(HttpServletRequest request, HttpServletResponse response) {
		logger.info("[accessPointJubaozhu]POST开始{}");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			//获得微信发送过来的数据转换为map
			Map<String, String> map = MessageConverterUtil.xmlToMap(request);
			WechatDtoReq wechatDtoReq = new WechatDtoReq();
			wechatDtoReq.setMap(map);
			BaseResponse<WechatDtoResult> apiResult = wechatFeignClient.accessPointJubaozhuPost(wechatDtoReq);
			if(ResultCode.SUCCESS.getCode().equals(apiResult.getCode())){
				//要回复给微信的消息
				String messageReturn = apiResult.getData().getMessageReturn();
				if(!StringUtils.isEmpty(messageReturn)){
					logger.info("[accessPointJubaozhu]输出到公众号的消息是{}" + messageReturn);
					out.print(messageReturn);
				}
			}
		} catch (Exception e) {
			logger.error("[WechatController.accessPointJubaozhu]微信向服务器交互发生异常!", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		logger.info("[accessPointJubaozhu]POST结束{}");
	}



	@Override
	public BaseResponse<JsConfigResult> getJsConfig(HttpServletRequest request, @RequestBody JsConfigReq requestBody) {
		return wechatFeignClient.getJsConfig(requestBody);
	}


	@Override
	public BaseResponse<RedirectResult> oauth() {
		return wechatFeignClient.oauth();
	}
}
