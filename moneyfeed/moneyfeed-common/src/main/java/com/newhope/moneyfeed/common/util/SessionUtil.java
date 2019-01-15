package com.newhope.moneyfeed.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newhope.moneyfeed.common.constant.CommonConstant;


/**  
* @ClassName: SessionUtil  
* @Description: session操作工具类
*    
*/
public class SessionUtil {

	/**  
	* @Title: getSession  
	* @Description: 返回session
	* @param @param request
	* @param @return    设定文件  
	* @return HttpSession    返回类型  
	* @throws  
	*/
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession(true);
	}

	/**  
	* @Title: setAttribute  
	* @Description: 设置进session
	* @param @param request
	* @param @param name
	* @param @param value    设定文件  
	* @return void    返回类型  
	* @throws  
	*/
	public static void setAttribute(HttpServletRequest request, String name, Object value) {
		getSession(request).setAttribute(name, value);
	}

	/**  
	* @Title: getAttribute  
	* @Description: 得到值  
	* @param @param request
	* @param @param name
	* @param @return    设定文件  
	* @return Object    返回类型  
	* @throws  
	*/
	public static Object getAttribute(HttpServletRequest request, String name) {
		return getSession(request).getAttribute(name);
	}

	/**  
	* @Title: removeAtrribute  
	* @Description: 移除
	* @param @param request
	* @param @param name    设定文件  
	* @return void    返回类型  
	* @throws  
	*/
	public static void removeAtrribute(HttpServletRequest request, String name) {
		getSession(request).removeAttribute(name);
	}

	/**  
	* @Title: getValueByCookie  
	* @Description: 返回字符串
	* @param @param name
	* @param @param request
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws  
	*/
	public static String getValueByCookie(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies || null == name || name.length() == 0) {
			return "";
		}
		if (cookies != null && name != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (name.equals(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return "";
	}

	/**  
	* @Title: getJSessionId  
	* @Description: 取jsessionid
	* @param @param request
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws  
	*/
	public static String getJSessionId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			String jsessionid = "";
			for (int i = 0; i < cookies.length; i++) {
				if ("JSESSIONID".equals(cookies[i].getName())) {
					jsessionid = cookies[i].getValue();
					return jsessionid;
				}
			}
		}
		return null;
	}

	/**  
	* getRSessionId  
	* @Description: 获取RSessionId: redis session id
	* @param request
	*/
	public static String getRSessionId(HttpServletRequest request) {
		if(request != null){
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				String rSessionId = "";
				for (int i = 0; i < cookies.length; i++) {
					if (CommonConstant.RSESSIONID_NAME.equals(cookies[i].getName())) {
						rSessionId = cookies[i].getValue();
						return rSessionId;
					}
				}
			}
		}
		
		return null;
	}
	
	/**  
	* @Title: removeCookie  
	* @Description: 移除cookie
	* @param @param request
	* @param @param response
	* @param @param name    设定文件  
	* @return void    返回类型  
	* @throws  
	*/
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		if (null == name) {
			return;
		}
		Cookie cookie = getCookie(request, name);
		if (null != cookie) {
			cookie.setPath("/");
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	/** 
	 * 根据Cookie名称得到Cookie对象，不存在该对象则返回Null 
	 *  
	 * @param request 
	 * @param name 
	 * @return 
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies || null == name || name.length() == 0) {
			return null;
		}
		Cookie cookie = null;
		for (Cookie c : cookies) {
			if (name.equals(c.getName())) {
				cookie = c;
				break;
			}
		}
		return cookie;
	}

	/** 
	 * 添加一条新的Cookie，默认1年过期时间(单位：秒) 
	 *  
	 * @param response 
	 * @param name 
	 * @param value 
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, CommonConstant.COOKIE_MAX_AGE);
	}

	/** 
	 * 添加一条新的Cookie，可以指定过期时间(单位：秒) 
	 *  
	 * @param response 
	 * @param name 
	 * @param value 
	 * @param maxValue 
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxValue) {
		if (null == name) {
			return;
		}
		if (null == value) {
			value = "";
		}
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		//cookie.setDomain(WechatConstant.DOMAIN);
		if (maxValue != 0) {
			cookie.setMaxAge(maxValue);
		} else {
			cookie.setMaxAge(CommonConstant.COOKIE_MAX_AGE);
		}
		response.addCookie(cookie);
	}
}
