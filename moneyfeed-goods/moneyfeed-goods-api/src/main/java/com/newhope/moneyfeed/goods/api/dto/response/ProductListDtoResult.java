package com.newhope.moneyfeed.goods.api.dto.response;


import java.io.Serializable;
import java.util.List;


import com.newhope.moneyfeed.api.dto.response.PageDtoResult;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/19 18:56
 */
public class ProductListDtoResult extends PageDtoResult implements Serializable {
    private static final long serialVersionUID = 5183694040130220213L;

    @ApiModelProperty(name = "productDtoReusltList", notes = "商品集合")
    private List<ProductDtoResult> productDtoReusltList;

    public List<ProductDtoResult> getProductDtoReusltList() {
        return productDtoReusltList;
    }

    public void setProductDtoReusltList(List<ProductDtoResult> productDtoReusltList) {
        this.productDtoReusltList = productDtoReusltList;
    }
}
