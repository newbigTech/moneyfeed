package com.newhope.moneyfeed.common.util.wechat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newhope.moneyfeed.common.constant.WechatConstant;

/**  
* @ClassName: WeixinUtil  
* @Description: 微信服务器和本地服务器交互工具类
* @author luoxl
* @date 2016年9月22日 下午3:12:42  
*    
*/
public class WechatUtil {
	private static final Logger logger = LoggerFactory.getLogger(WechatUtil.class);
	
	public static Map<String, Object> dataMap = new HashMap<>();

	/**
	 * @Title: doGetStr
	 * @Description: 向微信服务器发送get消息的方法
	 * @param @param url
	 * @param @return 设定文件
	 * @return JSONObject 返回类型
	 * @throws
	 */

	public static JSONObject doGetStr(String url) {
//		logger.info("WechartUtil.doGetStr时url:"+url);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
//			logger.info("entity:"+ JSON.toJSONString(entity));
			if (entity != null) {
				String resultString = EntityUtils.toString(entity, "UTF-8");
				jsonObject = JSONObject.parseObject(resultString);
//				logger.info("resultString:"+ JSON.toJSONString(resultString));
			}
		} catch (ClientProtocolException e) {
			logger.error("WechartUtil.doGetStr时出现错误,url:"+url, e);
		} catch (IOException e) {
			logger.error("WechartUtil.doGetStr时出现错误,url:"+url, e);
		}
//		logger.info("WechartUtil.doGetStr时url END:"+url);
		return jsonObject;
	}

	/**
	 * @Title: doPostStr
	 * @Description: 向微信服务器发送post消息的方法
	 * @param @param url
	 * @param @param outStr
	 * @param @return 设定文件
	 * @return JSONObject 返回类型
	 * @throws
	 */

	public static JSONObject doPostStr(String url, String outStr) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			String reString = EntityUtils.toString(response.getEntity(), "UTF-8");
			jsonObject = JSONObject.parseObject(reString);
		} catch (UnsupportedEncodingException e) {
			logger.error("WechartUtil.doPostStr时出现错误", e);
		} catch (ClientProtocolException e) {
			logger.error("WechartUtil.doPostStr时出现错误", e);
		} catch (IOException e) {
			logger.error("WechartUtil.doPostStr时出现错误", e);
		}
		return jsonObject;
	}

	/**
	 * @Title: createMenu
	 * @Description: 向微信服务器发送请求，生成自定义菜单,该方法也是修改菜单
	 * @param @param token
	 * @param @param menu
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */

	public static int createMenu(String token, String menu) {
		int result = 0;
		String url = WechatConstant.CREATE_PERSONAL_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		logger.info("生成自定义菜单", jsonObject);
		if (jsonObject != null) {
			result = jsonObject.getIntValue("errcode");
		}
		logger.info("生成的自定义菜单是", menu);
		return result;
	}
	
	/**  
	* @Title: createMenuDefault  
	* @Description: 创建默认菜单
	* @param @param token
	* @param @param menu
	* @param @return    设定文件  
	* @return int    返回类型  
	* @throws  
	*/
	public static int createMenuDefault(String token, String menu) {
		int result = 0;
		String url = WechatConstant.CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		logger.info("生成默认菜单", jsonObject);
		if (jsonObject != null) {
			result = jsonObject.getIntValue("errcode");
		}
		logger.info("生成默认菜单是", menu);
		return result;
	}
	
	/**  
	* @Title: getAccessTokenJubaozhu  
	* @Description: 获得微信访问token
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws  
	*/
	public static String getAccessTokenJubaozhu() {
		String url = WechatConstant.ACCESS_TOKEN_URL.replace("APPID", WechatConstant.APPID).replace("APPSECRET", WechatConstant.APPSCRET);
		return getAccessToken(url);
	}
	
	/**  
	* @Title: getAccessTokenBajietong  
	* @Description: 获得八戒通微信访问token
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws  
	*/
	public static String getAccessTokenBajietong() {
		String url = WechatConstant.ACCESS_TOKEN_URL.replace("APPID", WechatConstant.APPID_BAJIETONG).replace("APPSECRET", WechatConstant.APPSCRET_BAJIETONG);
		return getAccessToken(url);
	}
	
	/**
	 * 获取APP端访问token
	 * @return
	 */
	public static String getAccessTokenJbzApp() {
		String url = WechatConstant.ACCESS_TOKEN_URL.replace("APPID", WechatConstant.APPID_JBZ_APP).replace("APPSECRET", WechatConstant.APPSCRET_JBZ_APP);
		return getAccessToken(url);
	}
	
	/**
	 * @Title: getAccessToken
	 * @Description: 获得微信访问token
	 * @param @return 设定文件
	 * @return AccessToken 返回类型
	 * @throws
	 */
	public static String getAccessToken(String url) {
		logger.info(">>>>>>>>>>>>>>>"+url);
		JSONObject jsonObject = doGetStr(url);
		logger.info(">>>>>>>>>>>>>>>jsonObject"+ JSON.toJSONString(jsonObject));
		String accessToken = jsonObject.getString("access_token");
		logger.info(">>>>>>>>>>>>>>>accessToken"+ accessToken);
		return accessToken;
	}

	/**  
	* @Title: getAccessTokenForAuthJubaozhu  
	* @Description: 获取网页授权AUTHTOKEN
	* @param @param code
	* @param @return    设定文件  
	* @return JSONObject    返回类型  
	* @throws  
	*/
	public static JSONObject getAccessTokenForAuthJubaozhu(String code) {
		String url = WechatConstant.GET_ACCESS_TOKEN_URL.replace("APPID", WechatConstant.APPID).replace("SECRET", WechatConstant.APPSCRET).replace("CODE", code);
		return getAccessTokenForAuth(code, url);
	}
	
	/**  
	* @Title: getAccessTokenForAuthBajietong  
	* @Description: 获取网页授权八戒通AUTHTOKEN
	* @param @param code
	* @param @return    设定文件  
	* @return JSONObject    返回类型  
	* @throws  
	*/
	public static JSONObject getAccessTokenForAuthBajietong(String code) {
		String url = WechatConstant.GET_ACCESS_TOKEN_URL.replace("APPID", WechatConstant.APPID_BAJIETONG).replace("SECRET", WechatConstant.APPSCRET_BAJIETONG).replace("CODE", code);
		return getAccessTokenForAuth(code, url);
	}
	
	/**
	 * 获取网页授权APP端AUTHTOKEN
	 * @param code
	 * @return
	 */
	public static JSONObject getAccessTokenForAuthJubaozhuApp(String code) {
		String url = WechatConstant.GET_ACCESS_TOKEN_URL.replace("APPID", WechatConstant.APPID_JBZ_APP).replace("SECRET", WechatConstant.APPSCRET_JBZ_APP).replace("CODE", code);
		return getAccessTokenForAuth(code, url);
	}
	
	/**
	 * @Title: getAccessTokenForAuth
	 * @Description: 获取网页授权AUTHTOKEN
	 * @param @param code
	 * @param @return 设定文件
	 * @return JSONObject 返回类型
	 * @throws
	 */
	public static JSONObject getAccessTokenForAuth(String code,String url) {
		JSONObject jsonObject = doGetStr(url);
		return jsonObject;
	}

	/**
	 * @Title: getAuthUserInfo
	 * @Description: "获取网页授权用户信息
	 * @param @param authToken
	 * @param @param openId
	 * @param @return 设定文件
	 * @return JSONObject 返回类型
	 * @throws
	 */
	public static JSONObject getAuthUserInfo(String authToken, String openId) {
		String url = WechatConstant.GET_USERINFO_URL.replace("ACCESS_TOKEN", authToken).replace("OPENID", openId);
		JSONObject jsonObject = doGetStr(url);
		return jsonObject;
	}
	
	
	/**  
	* @Title: getUserInfo  
	* @Description: 通过token获取用户信息
	* @param @param token
	* @param @param openId
	* @param @return    设定文件  
	* @return JSONObject    返回类型  
	* @throws  
	*/
	public static JSONObject getUserInfo(String token, String openId, String appCode) {
		String url = WechatConstant.GET_USER_URL.replace("token_replace", token).replace("openId_replace", openId);
		JSONObject jsonObject = doGetStr(url);
		return jsonObject;
	}

	/**  
	* @Title: getTicket  
	* @Description: 获取jsapi_ticket
	* @param @param access_token
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws  
	*/
	public static String getTicket(String access_token) {
		String ticket = null;
		String url = WechatConstant.GET_JSTICKET_URL.replaceAll("access_token_replace", access_token);//这个url链接和参数不能变  
		try {
			JSONObject jsonObject = doGetStr(url);
			ticket = jsonObject.getString("ticket");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}

	/**  
	* @Title: SHA1  
	* @Description: 拿到了jsapi_ticket之后就要参数名排序和拼接字符串，并加密了。以下为sha1的加密算法
	* @param @param decript
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws  
	*/
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String  
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数  
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			logger.error("SHA1出异常", e);
		}
		return "";
	}

//	public static void main(String[] args) {
//		//1、获取AccessToken  
//		String accessToken = getAccessToken("APP1");
//
//		//2、获取Ticket  
//		String jsapi_ticket = getTicket(accessToken);
//
//		//3、时间戳和随机字符串  
//		String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串  
//		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳  
//
//		System.out.println("accessToken:" + accessToken + "\njsapi_ticket:" + jsapi_ticket + "\n时间戳：" + timestamp + "\n随机字符串：" + noncestr);
//
//		//4、获取url  
//		String url = WechatConstant.HOMEPAGE_URL + "/wechart/testjs.do";
//
//		//5、将参数排序并拼接字符串  
//		String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
//
//		//6、将字符串进行sha1加密  
//		String signature = SHA1(str);
//		System.out.println("参数：" + str + "\n签名：" + signature);
//	}
}
