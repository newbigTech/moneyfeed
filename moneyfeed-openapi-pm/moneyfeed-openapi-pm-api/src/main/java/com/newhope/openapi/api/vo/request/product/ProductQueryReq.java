package com.newhope.openapi.api.vo.request.product;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/19 15:24
 */
public class ProductQueryReq implements Serializable {
	private static final long serialVersionUID = -201944247861814862L;

	@ApiModelProperty(name = "productId", value = "商品id")
	private Long productId;

	@ApiModelProperty(name = "productName", value = "商品名称")
	private String productName;

	@ApiModelProperty(name = "suppliesCode", value = "物料编号")
	private String suppliesCode;

	@ApiModelProperty(name = "suppliesId", value = "物料id")
	private String suppliesId;

	@ApiModelProperty(name = "suppliesDescribe", value = "物料描述")
	private String suppliesDescribe;

	@ApiModelProperty(name = "secondaryUom", value = "辅助单位")
	private String secondaryUom;

	@ApiModelProperty(name = "primaryUom", value = "主单位")
	private String primaryUom;

	@ApiModelProperty(name = "oneCateId", value = "级分类id")
	private Long oneCateId;

	@ApiModelProperty(name = "oneCateDesc", value = "一级分类描述")
	private String oneCateDesc;

	@ApiModelProperty(name = "twoCateId", value = "二级分类ID")
	private Long twoCateId;

	@ApiModelProperty(name = "twoCateDesc", value = "二级分类描述")
	private String twoCateDesc;

	@ApiModelProperty(name = "threeCateId", value = "三级分类描述")
	private Long threeCateId;

	@ApiModelProperty(name = "threeCateDesc", value = "三级分类描述")
	private String threeCateDesc;

	@ApiModelProperty(name = "fourCateId", value = "四级分类ID")
	private Long fourCateId;

	@ApiModelProperty(name = "fourCateDesc", value = "四级分类描述")
	private String fourCateDesc;

	@ApiModelProperty(name = "productCode", value = "平台商品code")
	private String productCode;

	@ApiModelProperty(name = "brandId", value = "品牌id")
	private Long brandId;

	@ApiModelProperty(name = "brandName", value = "品牌名称")
	private String brandName;

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSuppliesCode() {
		return suppliesCode;
	}

	public void setSuppliesCode(String suppliesCode) {
		this.suppliesCode = suppliesCode;
	}

	public String getSuppliesId() {
		return suppliesId;
	}

	public void setSuppliesId(String suppliesId) {
		this.suppliesId = suppliesId;
	}

	public String getSuppliesDescribe() {
		return suppliesDescribe;
	}

	public void setSuppliesDescribe(String suppliesDescribe) {
		this.suppliesDescribe = suppliesDescribe;
	}

	public String getSecondaryUom() {
		return secondaryUom;
	}

	public void setSecondaryUom(String secondaryUom) {
		this.secondaryUom = secondaryUom;
	}

	public String getPrimaryUom() {
		return primaryUom;
	}

	public void setPrimaryUom(String primaryUom) {
		this.primaryUom = primaryUom;
	}

	public Long getOneCateId() {
		return oneCateId;
	}

	public void setOneCateId(Long oneCateId) {
		this.oneCateId = oneCateId;
	}

	public String getOneCateDesc() {
		return oneCateDesc;
	}

	public void setOneCateDesc(String oneCateDesc) {
		this.oneCateDesc = oneCateDesc;
	}

	public Long getTwoCateId() {
		return twoCateId;
	}

	public void setTwoCateId(Long twoCateId) {
		this.twoCateId = twoCateId;
	}

	public String getTwoCateDesc() {
		return twoCateDesc;
	}

	public void setTwoCateDesc(String twoCateDesc) {
		this.twoCateDesc = twoCateDesc;
	}

	public Long getThreeCateId() {
		return threeCateId;
	}

	public void setThreeCateId(Long threeCateId) {
		this.threeCateId = threeCateId;
	}

	public String getThreeCateDesc() {
		return threeCateDesc;
	}

	public void setThreeCateDesc(String threeCateDesc) {
		this.threeCateDesc = threeCateDesc;
	}

	public Long getFourCateId() {
		return fourCateId;
	}

	public void setFourCateId(Long fourCateId) {
		this.fourCateId = fourCateId;
	}

	public String getFourCateDesc() {
		return fourCateDesc;
	}

	public void setFourCateDesc(String fourCateDesc) {
		this.fourCateDesc = fourCateDesc;
	}
}
