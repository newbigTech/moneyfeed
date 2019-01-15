package com.newhope.moneyfeed.integration.api.dto.request.ebs.order;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商城请求EBS创建订单，订单中的商品参数
 *
 * Created by yuyanlin on 2018/11/20
 */
public class MoneyFeedToEbsOrderCreateProductDtoReq implements Serializable {

    private static final long serialVersionUID = 1771105699915629274L;

    @NotBlank(message = "organizationCode不能为空")
    @ApiModelProperty(name = "organizationCode", value = "库存组织（EBS编码）")
    private String organizationCode;

    @NotBlank(message = "suppliesCode不能为空")
    @ApiModelProperty(name = "suppliesCode", value = "商品编码（EBS编码）")
    private String suppliesCode;

    @NotBlank(message = "unit不能为空")
    @ApiModelProperty(name = "unit", value = "商品单位")
    private String unit;

    @NotNull(message = "productCount不能为空")
    @ApiModelProperty(name = "productCount", value = "商品数量")
    private BigDecimal productCount;

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getSuppliesCode() {
        return suppliesCode;
    }

    public void setSuppliesCode(String suppliesCode) {
        this.suppliesCode = suppliesCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getProductCount() {
        return productCount;
    }

    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }
}
