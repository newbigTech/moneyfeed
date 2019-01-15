package com.newhope.moneyfeed.goods.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/20 16:55
 */
public class ProductSkuListDtoResult extends PageDtoResult implements Serializable {
	private static final long serialVersionUID = -1150035589193030304L;

	@ApiModelProperty(name = "productSkuDtoResults", notes = "商品信息")
	private List<ProductSkuDtoResult> productSkuList;

	public List<ProductSkuDtoResult> getProductSkuList() {
		return productSkuList;
	}

	public void setProductSkuList(List<ProductSkuDtoResult> productSkuList) {
		this.productSkuList = productSkuList;
	}

}
