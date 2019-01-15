package com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs;

import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yuyanlin on 2018/11/21
 */
@XmlRootElement(name = "inv:P_REQUEST_LINE_ITEM")
public class EbsOrderCreateProductReq implements Serializable {
    private static final long serialVersionUID = -8014289064468273901L;

    @ApiModelProperty(name = "organizationCode", value = "库存组织（EBS编码）")
    private String organizationCode;

    @ApiModelProperty(name = "suppliesCode", value = "商品编码（EBS编码）")
    private String suppliesCode;

    @ApiModelProperty(name = "unit", value = "商品单位")
    private String unit;

    @ApiModelProperty(name = "productCount", value = "商品数量")
    private BigDecimal productCount;

    @XmlElement(name = "inv:VALUE2")
    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    @XmlElement(name = "inv:VALUE3")
    public String getSuppliesCode() {
        return suppliesCode;
    }

    public void setSuppliesCode(String suppliesCode) {
        this.suppliesCode = suppliesCode;
    }

    @XmlElement(name = "inv:VALUE4")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @XmlElement(name = "inv:VALUE5")
    public BigDecimal getProductCount() {
        return productCount;
    }

    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }
}
