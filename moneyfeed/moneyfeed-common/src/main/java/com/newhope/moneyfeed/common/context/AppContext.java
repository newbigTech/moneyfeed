package com.newhope.moneyfeed.common.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;


public class AppContext {
	
	/** 应用上下文 */
	private static Map<String,Object> context = new HashMap<String,Object>();
	// 订单号存取容器，A/B段
	private static List<String> orderNoSegmentA = new ArrayList<String>();
	private static List<String> orderNoSegmentB = new ArrayList<String>();
	// 交割单号存取容器，A/B段
	private static List<String> deliveryOrderNoSegmentA = new ArrayList<String>();
	private static List<String> deliveryOrderNoSegmentB = new ArrayList<String>();
	// 商品发布单号存取容器，A/B段
	private static List<String> GoodsCodeSegmentA = new ArrayList<String>();
	private static List<String> GoodsCodeSegmentB = new ArrayList<String>();
	// 用户账号存取容器
	private static List<String> userAccountSegment = new ArrayList<String>();
	// 用户编码存取容器
	private static List<String> userCodeSegment = new ArrayList<String>();
	// 店铺编码存取容器
	private static List<String> shopCodeSegment = new ArrayList<String>();
	// 店铺编码存取容器
	private static List<String> pigFarmCodeSegment = new ArrayList<String>();
	// 店铺会员编码存取容器
	private static List<String> shopMemberCodeSegment = new ArrayList<String>();
	// 店铺子账号编码存取容器
	private static List<String> shopSubacctCodeSegment = new ArrayList<String>();
	// UUID生成库
	private static List<String> uuidStore = new ArrayList<String>();
	
	/** spring上下*/
	private static ApplicationContext springContext = ContextLoader.getCurrentWebApplicationContext();
	
	public static List<String> getUuidStore() {
		return uuidStore;
	}

	public static List<String> getDeliveryOrderNoSegmentA() {
		return deliveryOrderNoSegmentA;
	}

	public static List<String> getDeliveryOrderNoSegmentB() {
		return deliveryOrderNoSegmentB;
	}

	public static List<String> getGoodsCodeSegmentA() {
		return GoodsCodeSegmentA;
	}

	public static List<String> getGoodsCodeSegmentB() {
		return GoodsCodeSegmentB;
	}

	public static List<String> getShopMemberCodeSegment() {
		return shopMemberCodeSegment;
	}

	public static List<String> getShopSubacctCodeSegment() {
		return shopSubacctCodeSegment;
	}

	public static List<String> getPigFarmCodeSegment() {
		return pigFarmCodeSegment;
	}

	public static List<String> getShopCodeSegment() {
		return shopCodeSegment;
	}

	public static List<String> getUserCodeSegment() {
		return userCodeSegment;
	}

	public static List<String> getUserAccountSegment() {
		return userAccountSegment;
	}

	public static ApplicationContext getSpringContext() {
		return springContext;
	}

	public static void setSpringContext(ApplicationContext springContext) {
		AppContext.springContext = springContext;
	}

	public static Map<String,Object> getContext() {
		return context;
	}
	
	public static void setContextValue(String key, Object value) {
		context.put(key, value);
	}
	
	public static Object getContextValue(String key) {
		return context.get(key);
	}

	public static List<String> getOrderNoSegmentA() {
		return orderNoSegmentA;
	}
	
	public static List<String> getOrderNoSegmentB() {
		return orderNoSegmentB;
	}
	
}
