package com.newhope.moneyfeed.order.api.dto.request.order.nh;

import com.newhope.moneyfeed.order.api.dto.request.order.nh.rule.FeedTransportDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.product.ProductDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.shop.UcShopDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.user.CustomerUserDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel("nh创建订单对象")
public class OrderNHCreateDtoReq implements Serializable {

	private static final long serialVersionUID = 7938235192876770436L;

	@ApiModelProperty(name = "customerUser", notes = "用户客户")
	private CustomerUserDtoReq user;

	@ApiModelProperty(name = "shop", notes = "商户")
	private UcShopDtoReq shop;

	@ApiModelProperty(name = "pullFeedRule", notes = "订单拉料规则")
	private FeedTransportDtoReq pullFeedRule;

	@ApiModelProperty(name = "products", notes = "订单商品列表")
	private List<ProductDtoReq> products;

	@ApiModelProperty(name = "orderChannel", notes = "下单渠道")
	private String orderChannel;
	
	@ApiModelProperty(name = "proxy", notes = "是否代理")
	private boolean proxy;
	
	@ApiModelProperty(name = "proxName", notes = "代下单人姓名")
	private String proxName;

	@ApiModelProperty(name = "proxUserId", notes = "代下单人ID")
	private Long proxUserId;

	

	public boolean getProxy() {
		return proxy;
	}

	public void setProxy(boolean proxy) {
		this.proxy = proxy;
	}

	public UcShopDtoReq getShop() {
		return shop;
	}

	public void setShop(UcShopDtoReq shop) {
		this.shop = shop;
	}

	public CustomerUserDtoReq getUser() {
		return user;
	}

	public void setUser(CustomerUserDtoReq user) {
		this.user = user;
	}

	public List<ProductDtoReq> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDtoReq> products) {
		this.products = products;
	}

	public FeedTransportDtoReq getPullFeedRule() {
		return pullFeedRule;
	}

	public void setPullFeedRule(FeedTransportDtoReq pullFeedRule) {
		this.pullFeedRule = pullFeedRule;
	}

	public String getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
	}

	public String getProxName() {
		return proxName;
	}

	public void setProxName(String proxName) {
		this.proxName = proxName;
	}

	public Long getProxUserId() {
		return proxUserId;
	}

	public void setProxUserId(Long proxUserId) {
		this.proxUserId = proxUserId;
	}
}
