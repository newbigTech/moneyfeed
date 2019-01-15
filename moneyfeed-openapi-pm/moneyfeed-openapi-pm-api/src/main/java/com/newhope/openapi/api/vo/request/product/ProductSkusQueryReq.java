package com.newhope.openapi.api.vo.request.product;

import java.util.List;

import com.newhope.moneyfeed.api.vo.request.PageReq;

import io.swagger.annotations.ApiModelProperty;

public class ProductSkusQueryReq extends PageReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "shopId", value = "店铺id")
	private Long shopId;

	@ApiModelProperty(name = "productCode", value = "商品id")
	private String productCode;

	@ApiModelProperty(name = "suppliesId", value = "物料id")
	private String suppliesId;

	@ApiModelProperty(name = "oneCateId", value = "一级")
	private String oneCateId;

	@ApiModelProperty(name = "twoCateId", value = "二级")
	private String twoCateId;

	@ApiModelProperty(name = "threeCateId", value = "三级")
	private String threeCateId;

	@ApiModelProperty(name = "fourCateId", value = "四级")
	private String fourCateId;

	@ApiModelProperty(name = "productName", value = "产品名称")
	private String productName;

	@ApiModelProperty(name = "productSkuIds", value = "商品sku Id")
	private List<Long> productSkuIds;

	@ApiModelProperty(name = "customerNum", value = "客户编码")
	private String customerNum;

	@ApiModelProperty(name = "saleCateId", value = "目录id")
	private String saleCateId;

	@ApiModelProperty(name = "saleCateLevel", value = "目录层级")
	private Integer saleCateLevel;

	@ApiModelProperty(name = "brandIds", value = "品牌Id,以,分隔")
	private String brandIds;

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

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getSaleCateId() {
		return saleCateId;
	}

	public void setSaleCateId(String saleCateId) {
		this.saleCateId = saleCateId;
	}

	public Integer getSaleCateLevel() {
		return saleCateLevel;
	}

	public void setSaleCateLevel(Integer saleCateLevel) {
		this.saleCateLevel = saleCateLevel;
	}

}
