package com.newhope.openapi.api.vo.response.product;

import java.util.List;

import com.newhope.moneyfeed.api.vo.response.PageResult;

public class ProductSkuResult extends PageResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List productSkuList;

	public List getProductSkuList() {
		return productSkuList;
	}

	public void setProductSkuList(List productSkuList) {
		this.productSkuList = productSkuList;
	}

}
