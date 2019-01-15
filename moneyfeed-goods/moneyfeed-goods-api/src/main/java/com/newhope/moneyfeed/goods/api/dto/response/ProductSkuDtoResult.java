package com.newhope.moneyfeed.goods.api.dto.response;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/20 16:22
 */
public class ProductSkuDtoResult extends ProductDtoResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "productSkuId", notes = "skuid")
	private Long productSkuId;

	@ApiModelProperty(name = "companyName", notes = "公司名称")
	private String companyName;

	@ApiModelProperty(name = "orgId", notes = "组织ID")
	private String orgId;

	@ApiModelProperty(name = "companyShortCode", notes = "业务实体代码")
	private String companyShortCode;

	@ApiModelProperty(name = "organizationName", notes = "库存组织名称")
	private String organizationName;

	@ApiModelProperty(name = "organizationCode", notes = "库存组织代码")
	private String organizationCode;

	@ApiModelProperty(name = "price", notes = "价格")
	private BigDecimal price;

	@ApiModelProperty(name = "quantity", notes = "数量")
	private Integer quantity;

	@ApiModelProperty(name = "spec", notes = "规格")
	private String spec="";

	@ApiModelProperty(name = "productSkuAttributesExModels", notes = "属性")
	private List<ProductSkuAttributesDtoResult> productSkuAttributesExModels;

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public List<ProductSkuAttributesDtoResult> getProductSkuAttributesExModels() {
		return productSkuAttributesExModels;
	}

	public void setProductSkuAttributesExModels(List<ProductSkuAttributesDtoResult> productSkuAttributesExModels) {
		this.productSkuAttributesExModels = productSkuAttributesExModels;
	}

	public Long getProductSkuId() {
		return productSkuId;
	}

	public void setProductSkuId(Long productSkuId) {
		this.productSkuId = productSkuId;
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

	public String getCompanyShortCode() {
		return companyShortCode;
	}

	public void setCompanyShortCode(String companyShortCode) {
		this.companyShortCode = companyShortCode;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
