package com.newhope.openapi.api.vo.response.product;

import com.newhope.moneyfeed.common.util.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author : tom
 * @project: moneyfeed-openapi-pm
 * @date : 2018/12/24 08:46
 */
public class BrandResult implements Serializable {

    private static final long serialVersionUID = 3205717181444495016L;


    @ExcelField(title = "品牌id")
    @ApiModelProperty(name = "brandId", notes = "品牌id")
    private Long brandId;

    @ExcelField(title = "品牌名")
    @ApiModelProperty(name = "brandName", notes = "品牌名")
    private String brandName;

    @ExcelField(title = "品牌标记商品数量")
    @ApiModelProperty(name = "productCount", notes = "品牌标记商品数量")
    private Integer productCount;

    @ExcelField(title = "入驻商户")
    @ApiModelProperty(name = "shopCount", notes = "入驻商户")
    private Integer shopCount;

    @ExcelField(title = "商户名称")
    @ApiModelProperty(name = "shopNames", notes = "商户名称")
    private String shopNames;

    public String getShopNames() {
        return shopNames;
    }

    public void setShopNames(String shopNames) {
        this.shopNames = shopNames;
    }

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

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getShopCount() {
        return shopCount;
    }

    public void setShopCount(Integer shopCount) {
        this.shopCount = shopCount;
    }
}
