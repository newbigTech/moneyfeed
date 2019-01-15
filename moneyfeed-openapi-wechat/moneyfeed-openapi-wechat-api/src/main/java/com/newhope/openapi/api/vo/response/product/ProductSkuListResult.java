package com.newhope.openapi.api.vo.response.product;

import com.newhope.moneyfeed.api.vo.response.PageResult;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-openapi-wechat
 * @date : 2018/11/22 15:06
 */
public class ProductSkuListResult extends PageResult implements Serializable {

    @ApiModelProperty(name = "productSkuDtoResults", notes = "商品信息")
    private List<ProductSkuResult> productSkuResults;

    public List<ProductSkuResult> getProductSkuResults() {
        return productSkuResults;
    }

    public void setProductSkuResults(List<ProductSkuResult> productSkuResults) {
        this.productSkuResults = productSkuResults;
    }
}
