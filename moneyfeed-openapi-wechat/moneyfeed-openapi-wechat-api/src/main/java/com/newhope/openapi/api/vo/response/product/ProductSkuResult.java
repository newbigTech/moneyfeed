package com.newhope.openapi.api.vo.response.product;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-openapi-wechat
 * @date : 2018/11/22 15:07
 */
public class ProductSkuResult extends ProductResult implements Serializable {
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

    @ApiModelProperty(name = "productSkuAttributesExModels", notes = "属性")
    private List<ProductSkuAttributesResult> productSkuAttributesResults;

    public List<ProductSkuAttributesResult> getProductSkuAttributesResults() {
        return productSkuAttributesResults;
    }

    public void setProductSkuAttributesResults(List<ProductSkuAttributesResult> productSkuAttributesResults) {
        this.productSkuAttributesResults = productSkuAttributesResults;
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
