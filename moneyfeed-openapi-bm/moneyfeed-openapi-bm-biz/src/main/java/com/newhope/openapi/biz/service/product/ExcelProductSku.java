package com.newhope.openapi.biz.service.product;

import java.math.BigDecimal;

import com.newhope.moneyfeed.common.util.excel.annotation.ExcelField;

import io.swagger.annotations.ApiModelProperty;

public class ExcelProductSku {

	@ExcelField(title = "商品ID")
	@ApiModelProperty(name = "productCode", notes = "商品ID")
	private String productCode;

	@ExcelField(title = "所属公司")
	@ApiModelProperty(name = "companyName", notes = "所属公司")
	private String companyName;

	@ExcelField(title = "所属公司ID")
	@ApiModelProperty(name = "orgId", notes = "所属公司ID")
	private String orgId;

	@ExcelField(title = "物料ID")
	@ApiModelProperty(name = "suppliesId", notes = "物料ID")
	private String suppliesId;

	@ExcelField(title = "EBS名称")
	@ApiModelProperty(name = "productName", notes = "EBS名称")
	private String productName;

	@ExcelField(title = "EBS二级分类")
	@ApiModelProperty(name = "twoCateDesc", notes = "EBS二级分类")
	private String twoCateDesc;

	@ExcelField(title = "EBS三级分类")
	@ApiModelProperty(name = "threeCateDesc", notes = "EBS三级分类")
	private String threeCateDesc;

	@ExcelField(title = "EBS四级分类")
	@ApiModelProperty(name = "fourCateDesc", notes = "EBS四级分类")
	private String fourCateDesc;

	@ExcelField(title = "规格")
	@ApiModelProperty(name = "spec", notes = "规格")
	private String spec = "";

	@ExcelField(title = "厂价")
	@ApiModelProperty(name = "price", notes = "厂价")
	private BigDecimal price;

	@ExcelField(title = "品牌")
	@ApiModelProperty(name = "brandName", notes = "品牌名称")
	private String brandName;

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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSuppliesId() {
		return suppliesId;
	}

	public void setSuppliesId(String suppliesId) {
		this.suppliesId = suppliesId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTwoCateDesc() {
		return twoCateDesc;
	}

	public void setTwoCateDesc(String twoCateDesc) {
		this.twoCateDesc = twoCateDesc;
	}

	public String getThreeCateDesc() {
		return threeCateDesc;
	}

	public void setThreeCateDesc(String threeCateDesc) {
		this.threeCateDesc = threeCateDesc;
	}

	public String getFourCateDesc() {
		return fourCateDesc;
	}

	public void setFourCateDesc(String fourCateDesc) {
		this.fourCateDesc = fourCateDesc;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
