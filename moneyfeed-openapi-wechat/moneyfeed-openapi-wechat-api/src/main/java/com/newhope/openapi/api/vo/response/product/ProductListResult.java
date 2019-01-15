package com.newhope.openapi.api.vo.response.product;


import com.newhope.moneyfeed.api.vo.response.PageResult;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/19 18:56
 */
public class ProductListResult extends PageResult implements Serializable {
    private static final long serialVersionUID = 5183694040130220213L;

    @ApiModelProperty(name = "productDtoReusltList", notes = "商品集合")
    private List<ProductResult> productDtoReusltList;

    public List<ProductResult> getProductDtoReusltList() {
        return productDtoReusltList;
    }

    public void setProductDtoReusltList(List<ProductResult> productDtoReusltList) {
        this.productDtoReusltList = productDtoReusltList;
    }
}
