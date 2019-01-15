package com.newhope.order.biz.rpc.feign;


import javax.servlet.http.HttpServletRequest;

import com.newhope.moneyfeed.common.constant.CommonConstant;
import com.newhope.moneyfeed.common.util.ContextHolderUtil;
import org.springframework.stereotype.Component;


import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate requestTemplate) {
		HttpServletRequest request = ContextHolderUtil.getRequest();
		if(request == null){
			return;
		}
		requestTemplate.header(CommonConstant.HTTP_HEADER_APPCODE, request.getHeader(CommonConstant.HTTP_HEADER_APPCODE));
		requestTemplate.header(CommonConstant.HTTP_HEADER_SOURCE, CommonConstant.HTTP_HEADER_SOURCE_WECHAT);
		requestTemplate.header(CommonConstant.HTTP_HEADER_TOKEN, request.getHeader(CommonConstant.HTTP_HEADER_TOKEN));
	}

}
