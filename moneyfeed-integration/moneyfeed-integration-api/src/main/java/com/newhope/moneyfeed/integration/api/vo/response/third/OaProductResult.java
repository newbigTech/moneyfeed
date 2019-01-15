package com.newhope.moneyfeed.integration.api.vo.response.third;

import java.io.Serializable;
import java.util.List;

public class OaProductResult implements Serializable{

	private static final long serialVersionUID = -3989610945993945896L;

	private String productName;

	private Long productId;
	/**
	 * 1分级定价 0非分级定价
	 */
	private Boolean levelFlag;

	private List<OaProductSpuResult> intervalList;

	public Boolean getLevelFlag() {
		return levelFlag;
	}

	public void setLevelFlag(Boolean levelFlag) {
		this.levelFlag = levelFlag;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<OaProductSpuResult> getIntervalList() {
		return intervalList;
	}

	public void setIntervalList(List<OaProductSpuResult> intervalList) {
		this.intervalList = intervalList;
	}

}
