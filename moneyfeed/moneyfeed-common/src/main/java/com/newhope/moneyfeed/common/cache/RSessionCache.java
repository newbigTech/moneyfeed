package com.newhope.moneyfeed.common.cache;

import com.newhope.cache.core.Cache;
import com.newhope.cache.core.RedisCache;
import com.newhope.moneyfeed.common.constant.CommonConstant;
import com.newhope.moneyfeed.common.util.ContextHolderUtil;
import com.newhope.moneyfeed.common.util.MD5Util;
import com.newhope.moneyfeed.common.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class RSessionCache {
	private static final Logger logger = LoggerFactory.getLogger(RSessionCache.class);
	
	@Autowired
	@Qualifier("sessionCache")
	Cache sessionCache;
	
	RedisCache rSessionCache;

	@PostConstruct
	void init() {
		this.rSessionCache = (RedisCache) sessionCache;
	}
	
	/**
	 * set user data to cache with key(token)
	 * @param userModel
	 * @return
	 *
	 */
	public boolean setUserInfo(String token, Object userModel) {
		try {
			return rSessionCache.set(token, userModel,CommonConstant.COOKIE_MAX_AGE);
		} catch (Exception e) {
			logger.error("[RSessionCache.setUserInfo]设置缓存失败", e);
		}
		return false;
	}

	public boolean setUserInfo(String token, Object userModel,int timeout) {
		if(timeout==0){
			timeout=CommonConstant.COOKIE_MAX_AGE;
		}
		try {
			return rSessionCache.set(token, userModel,timeout);
		} catch (Exception e) {
			logger.error("[RSessionCache.setUserInfo]设置缓存失败", e);
		}
		return false;
	}

	/**
	 * set user data to cache and add RSESSIONID to cookie with key(token)
	 * @param response
	 * @param userModel
	 * @return
	 */
	public boolean setUserInfo(HttpServletResponse response, Object userModel) {
		String token = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		if (setUserInfo(token, userModel)) { //设置cookie成功
			SessionUtil.setCookie(response, CommonConstant.HTTP_HEADER_TOKEN, token);
            response.setHeader(CommonConstant.HTTP_HEADER_TOKEN, token); // 设置token到response header
			return true;
		}
		return false;
	}
	
	/**
	 * set user data to cache and add TOKEN to cookie with key(token)
	 * token = md5(userId + date, salt)
	 * @param response	response
	 * @param userModel	用户信息模型
	 * @param userId	用户i
	 * @param source	请求端source
	 * @return
	 */
	public boolean setUserInfo(HttpServletResponse response, Object userModel, String userId, String source) {
		if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(source)) {
			return false;
		}
		String token = MD5Util.sign(userId  + source, CommonConstant.salt, "utf-8");
		if (setUserInfo(token, userModel)) {
			SessionUtil.setCookie(response, CommonConstant.HTTP_HEADER_TOKEN, token); // 设置token到cookie
			response.setHeader(CommonConstant.HTTP_HEADER_TOKEN, token); // 设置token到response header
			return true;
		}
		return false;
	}

	/**
	 * set user data to cache and add TOKEN to cookie with key(token)
	 * token = md5(userId + date, salt)
	 * @param response	response
	 * @param userModel	用户信息模型
	 * @param userId	用户i
	 * @param source	请求端source
	 * @return
	 */
	public boolean setUserInfo(HttpServletResponse response, Object userModel, String userId, String source,int timeout) {
		if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(source)) {
			return false;
		}
		String token = MD5Util.sign(userId  + source, CommonConstant.salt, "utf-8");
		if (setUserInfo(token, userModel)) {
			SessionUtil.setCookie(response, CommonConstant.HTTP_HEADER_TOKEN, token,timeout); // 设置token到cookie
			response.setHeader(CommonConstant.HTTP_HEADER_TOKEN, token); // 设置token到response header
			return true;
		}
		return false;
	}
	/**
	 * set user data to cache
	 * token = md5(userId + date, salt)
	 * @param response	response
	 * @param userModel	用户信息模型
	 * @param userId	用户id
	 * @param appcode	appcode
	 * @param source	请求端source
	 * @return
	 */
	/*public boolean setUserInfo( Object userModel, String userId, String appcode, String source ) {
		if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(appcode) || StringUtils.isEmpty(source)) {
			return false;
		}
		String token = MD5Util.sign(userId + appcode + source, CommonConstant.salt, "utf-8");
		return setUserInfo(token, userModel);
	}*/

	/**
	 * set user data to cache
	 * token = md5(userId + date, salt)
	 * @return
	 */
	public boolean loginOut() {
        HttpServletRequest request = ContextHolderUtil.getRequest();
		String token=request.getHeader(CommonConstant.HTTP_HEADER_TOKEN);
        boolean removeFlag=true;
        try {
            removeFlag = rSessionCache.remove(token);
        } catch (Exception e) {
           logger.error("清除用户缓存失败",e);
           return Boolean.FALSE.booleanValue();
        }
        return removeFlag;
	}

	public boolean removeCookie(HttpServletResponse response){
		HttpServletRequest request = ContextHolderUtil.getRequest();
		boolean removeFlag=true;
		try {
			SessionUtil.removeCookie(request,response,CommonConstant.HTTP_HEADER_TOKEN);
		} catch (Exception e) {
			logger.error("清除用户cookie失败",e);
			return Boolean.FALSE.booleanValue();
		}
		return removeFlag;
	}

	/**
     * 根据用户ID/APPCODE/SOURCE，生成Token
     * @param userId
     * @return
     */
	public String getToken(String userId, String appcode, String source){
	    return MD5Util.sign(userId + appcode + source, CommonConstant.salt, "utf-8");
    }

	/**
	 * get user data from cache with key(token)
	 * @param token
	 * @return
	 */
	public Object getUserInfo(String token) {
		Object userInfo = null;
		try {
			userInfo = rSessionCache.get(token);
		} catch (Exception e) {
			logger.error("[SessionCache.getUserInfo]:获取用户缓存信息异常", e);
		}
		return userInfo;
	}

	/**
	 * get token from RSESSIONID by cookie and get user data from cache with key(token)
	 * @param request
	 * @return
	 */
	public Object getUserInfo(HttpServletRequest request) {
		String token=request.getHeader(CommonConstant.HTTP_HEADER_TOKEN);
		if (StringUtils.isEmpty(token)) {
			token = SessionUtil.getValueByCookie(CommonConstant.HTTP_HEADER_TOKEN, request);
		}
		return getUserInfo(token);
	}

	/**
	 * get token from http header and get user data from cache with key(token)
	 * @return
	 */
	public Object getUserInfo() {
		HttpServletRequest request = ContextHolderUtil.getRequest();
		if (null != request) {
			String token=request.getHeader(CommonConstant.HTTP_HEADER_TOKEN);
			token = "568630ba49a5e612a34f367edb00b5c1";
			if (StringUtils.isEmpty(token)) {
				token = SessionUtil.getValueByCookie(CommonConstant.HTTP_HEADER_TOKEN, request);
			}
	        return getUserInfo(token);
		}
		return null;
	}


	public boolean removeCache(String token){
        boolean remove=false;
		try {
            remove = rSessionCache.remove(token);
        } catch (Exception e) {
		    logger.error("移除用户缓存失败",e);
		    return false;
        }
        return remove;
	}
}
