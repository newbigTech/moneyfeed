package com.newhope.moneyfeed.goods.api.dto.request;

import java.util.List;

import com.newhope.moneyfeed.api.dto.request.PageDtoReq;

import io.swagger.annotations.ApiModelProperty;

public class ProductSkusQueryDto extends PageDtoReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "shopId", notes = "店铺id")
	private Long shopId;

	@ApiModelProperty(name = "productCode", notes = "商品id")
	private String productCode;

	@ApiModelProperty(name = "suppliesId", notes = "物料id")
	private String suppliesId;

	@ApiModelProperty(name = "oneCateId", notes = "一级")
	private String oneCateId;

	@ApiModelProperty(name = "twoCateId", notes = "二级")
	private String twoCateId;

	@ApiModelProperty(name = "threeCateId", notes = "三级")
	private String threeCateId;

	@ApiModelProperty(name = "fourCateId", notes = "四级")
	private String fourCateId;

	@ApiModelProperty(name = "productName", notes = "产品名称")
	private String productName;

	@ApiModelProperty(name = "productSkuIds", notes = "商品sku Id")
	private List<Long> productSkuIds;

	@ApiModelProperty(name = "brandIds", notes = "品牌Id,以,分隔")
	private String brandIds;

	private Long[] brandIdsArray;

	public Long[] getBrandIdsArray() {
		return brandIdsArray;
	}

	public void setBrandIdsArray(Long[] brandIdsArray) {
		this.brandIdsArray = brandIdsArray;
	}

	public String getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(String brandIds) {
		this.brandIds = brandIds;
	}

	public List<Long> getProductSkuIds() {
		return productSkuIds;
	}

	public void setProductSkuIds(List<Long> productSkuIds) {
		this.productSkuIds = productSkuIds;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSuppliesId() {
		return suppliesId;
	}

	public void setSuppliesId(String suppliesId) {
		this.suppliesId = suppliesId;
	}

	public String getOneCateId() {
		return oneCateId;
	}

	public void setOneCateId(String oneCateId) {
		this.oneCateId = oneCateId;
	}

	public String getTwoCateId() {
		return twoCateId;
	}

	public void setTwoCateId(String twoCateId) {
		this.twoCateId = twoCateId;
	}

	public String getThreeCateId() {
		return threeCateId;
	}

	public void setThreeCateId(String threeCateId) {
		this.threeCateId = threeCateId;
	}

	public String getFourCateId() {
		return fourCateId;
	}

	public void setFourCateId(String fourCateId) {
		this.fourCateId = fourCateId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
