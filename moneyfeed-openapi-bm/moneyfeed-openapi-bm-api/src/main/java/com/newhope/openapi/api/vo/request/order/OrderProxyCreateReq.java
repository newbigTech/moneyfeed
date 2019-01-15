package com.newhope.openapi.api.vo.request.order;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class OrderProxyCreateReq implements Serializable {

	private static final long serialVersionUID = -9046481991178826396L;

	@Valid
	@NotEmpty(message = "商品信息不能为空")
	@ApiModelProperty(name = "pitems", notes = "商品列表", required=true)
	private List<ProductItemReq> pitems;
	@ApiModelProperty(name = "feedTransportInfo", notes = "拉料信息")
	private FeedTransportInfoReq feedTransportInfo;
	@NotNull(message = "商户ID不能为空")
	@ApiModelProperty(name = "userId", notes = "被代理用户ID", required=true)
	private Long userId;
	@NotNull(message = "商户ID不能为空")
	@ApiModelProperty(name = "shopId", notes = "商户ID", required=true)
	private Long shopId;

	public List<ProductItemReq> getPitems() {
		return pitems;
	}

	public void setPitems(List<ProductItemReq> pitems) {
		this.pitems = pitems;
	}

	public FeedTransportInfoReq getFeedTransportInfo() {
		return feedTransportInfo;
	}

	public void setFeedTransportInfo(FeedTransportInfoReq feedTransportInfo) {
		this.feedTransportInfo = feedTransportInfo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
}
