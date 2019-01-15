package com.newhope.moneyfeed.goods.api.exbean;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/19 15:24
 */
public class ProductExModel implements Serializable {
	private static final long serialVersionUID = -201944247861814862L;

	// 商品id
	private Long productId;

	// 商品名称
	private String productName;

	// 公司id
	@Deprecated
	private Long companyId;

	// 物料编号
	private String suppliesCode;

	// 物料id
	private String suppliesId;

	// 物料描述
	private String suppliesDescribe;

	// 辅助单位
	private String secondaryUom;

	// 主单位
	private String primaryUom;

	// 级分类id
	private Long oneCateId;

	// 一级分类描述
	private String oneCateDesc;

	// 二级分类ID
	private Long twoCateId;

	// 二级分类描述
	private String twoCateDesc;

	// 三级分类描述
	private Long threeCateId;

	// 三级分类描述
	private String threeCateDesc;

	// 四级分类ID
	private Long fourCateId;

	// 四级分类描述
	private String fourCateDesc;

	// 平台商品code
	private String productCode;

	@ApiModelProperty(name = "brandId", notes = "品牌id")
	private Long brandId;

	@ApiModelProperty(name = "brandName", notes = "品牌名称")
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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
