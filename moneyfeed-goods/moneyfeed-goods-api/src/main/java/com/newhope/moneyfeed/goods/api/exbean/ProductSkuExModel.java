package com.newhope.moneyfeed.goods.api.exbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/20 16:22
 */
public class ProductSkuExModel extends ProductExModel implements Serializable {
    private static final long serialVersionUID = -5849564275893514989L;

    /** skuId*/
    private Long productSkuId;

    /** 公司名称 */
    private String companyName;

    /** 组织ID */
    private String orgId;

    /** 业务实体代码 */
    private String companyShortCode;

    /** 库存组织名称 */
    private String organizationName;

    /** 库存组织代码 */
    private String organizationCode;

    /** 价格 */
    private BigDecimal price;

    /** 数量 */
    private Integer quantity;

    /** 属性*/
    private List<ProductSkuAttributesExModel> productSkuAttributesExModels;

    public List<ProductSkuAttributesExModel> getProductSkuAttributesExModels() {
        return productSkuAttributesExModels;
    }

    public void setProductSkuAttributesExModels(List<ProductSkuAttributesExModel> productSkuAttributesExModels) {
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
