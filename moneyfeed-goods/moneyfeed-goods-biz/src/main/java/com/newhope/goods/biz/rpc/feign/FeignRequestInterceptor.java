package com.newhope.goods.biz.rpc.feign;


import com.newhope.moneyfeed.common.constant.CommonConstant;
import com.newhope.moneyfeed.common.util.ContextHolderUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate requestTemplate) {
		/*HttpServletRequest request = ContextHolderUtil.getRequest();
		requestTemplate.header(CommonConstant.HTTP_HEADER_APPCODE, request.getHeader(CommonConstant.HTTP_HEADER_APPCODE));
		requestTemplate.header(CommonConstant.HTTP_HEADER_SOURCE, CommonConstant.HTTP_HEADER_SOURCE_WECHAT);
		requestTemplate.header(CommonConstant.HTTP_HEADER_TOKEN, request.getHeader(CommonConstant.HTTP_HEADER_TOKEN));*/
	}

}
