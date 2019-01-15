package com.newhope.moneyfeed.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newhope.moneyfeed.common.constant.CommonConstant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ContextHolderUtil {
	/**
	 * SpringMvc下获取request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		
		if(RequestContextHolder.getRequestAttributes() != null) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			return request;
		}
		return null;

	}
	
   public static HttpServletResponse getResponse() {
		
		if(RequestContextHolder.getRequestAttributes() != null) {
			HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
			return response;
		}
		return null;

	}

	/**
	 * SpringMvc下获取session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}

	/**
	 * get token from request header
	 * @return
	 */
	public static String getTokenByHeader() {
		return getRequest().getHeader(CommonConstant.HTTP_HEADER_TOKEN);
	}
	
	/**
	 * get app code from request header
	 * @return
	 */
	public static String getAppcodeByHeader() {
		return getRequest().getHeader(CommonConstant.HTTP_HEADER_APPCODE);
	}
	
	/**
	 * get app code from request header
	 * @return
	 */
	public static String getSourceByHeader() {
		return getRequest().getHeader(CommonConstant.HTTP_HEADER_SOURCE);
	}
}