package com.newhope.moneyfeed.common.util;

import java.util.Random;

/**  
* @ClassName: SmsCodeUtil  
* @Description: 短信验证码工具类 
* @author luoxl
* @date 2016年10月10日 上午10:59:11  
*    
*/
public class SmsCodeUtil {
	private static final int min = 1000;
	private static final int max = 9999;
	/**  
	* @Title: getSmsCode  
	* @Description: 生成4位验证码  
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws  
	*/
	public static String getSmsCode() {
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		return String.valueOf(tmp % (max - min + 1) + min);
	}

	/**
	 * 店铺红包授权码生成规则
	 *
	 * @param @return 设定文件
	 * @return String    返回类型
	 * @Description: 生成4位验证码
	 */
	public static String getShopAuthCode() {
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		return String.valueOf(tmp % (max - min + 1) + min);
	}
}
