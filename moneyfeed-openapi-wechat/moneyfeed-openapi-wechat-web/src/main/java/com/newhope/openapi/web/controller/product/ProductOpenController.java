package com.newhope.openapi.web.controller.product;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.rest.product.ProductOpenApi;
import com.newhope.openapi.api.vo.request.product.ProductQueryReq;
import com.newhope.openapi.api.vo.response.product.ProductSkuListResult;
import com.newhope.openapi.biz.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : tom
 * @project: moneyfeed-openapi-wechat
 * @date : 2018/11/19 18:49
 */
@RestController
public class ProductOpenController extends AbstractController implements ProductOpenApi {

    @Autowired
    private ProductService productService;

    @Override
    public BaseResponse<ProductSkuListResult> getProduct(ProductQueryReq productQueryReq) {
        ProductSkuListResult result = productService.getProduct(productQueryReq, false);
        return buildJson(result);
    }

    @Override
    public BaseResponse<ProductSkuListResult> getProductSku(ProductQueryReq dtoReq) {
        ProductSkuListResult result = productService.getProductSku(dtoReq, true);
        return buildJson(result);
    }
}
