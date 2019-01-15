package com.newhope.moneyfeed.common.util.wechat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: SignUtil
 * @Description: 接入微信时验证签名的工具类
 * @author luoxl
 * @date 2016年9月22日 下午1:49:15
 * 
 */
public class SignUtil {
	private final static Logger logger = LoggerFactory.getLogger(SignUtil.class);

	/**  
	* @Title: checkSignatureJubaozhu  
	* @Description: 验证签名
	* @param @param signature
	* @param @param timestamp
	* @param @param nonce
	* @param @param token
	* @param @return    设定文件  
	* @return boolean    返回类型  
	* @throws  
	*/
	public static boolean checkSignatureJubaozhu(String signature, String timestamp, String nonce, String token) {
		return checkSignature(signature, timestamp, nonce, token);
	}
	
	/**  
	* @Title: checkSignatureBajietong  
	* @Description: 验证八戒通签名
	* @param @param signature
	* @param @param timestamp
	* @param @param nonce
	* @param @param token
	* @param @return    设定文件  
	* @return boolean    返回类型  
	* @throws  
	*/
	public static boolean checkSignatureBajietong(String signature, String timestamp, String nonce, String token) {
		return checkSignature(signature, timestamp, nonce, token);
	}
	
	/**
	 * 验证签名
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce, String token) {
		if (StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce)) {
			return false;
		}
		
		String[] arr = new String[] { token, timestamp, nonce };
		// 将 token, timestamp, nonce 三个参数进行字典排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行 shal 加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			logger.error("微信引入时验证签名出错" + e);
		}
		content = null;
		logger.info(tmpStr);
		logger.info(signature);
		logger.info(signature.toUpperCase());
		// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param digest
	 * @return
	 */
	private static String byteToStr(byte[] digest) {
		String strDigest = "";
		for (int i = 0; i < digest.length; i++) {
			strDigest += byteToHexStr(digest[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexStr(byte b) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(b >>> 4) & 0X0F];
		tempArr[1] = Digit[b & 0X0F];
		String s = new String(tempArr);
		return s;
	}
}
