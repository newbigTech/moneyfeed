package com.newhope.openapi.web.controller.product;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.rest.product.RegularProductOpenApi;
import com.newhope.openapi.api.vo.response.product.ProductSkuListResult;
import com.newhope.openapi.biz.service.product.RegularProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegularProductOpenController extends AbstractController implements RegularProductOpenApi {

    @Autowired
    private RegularProductService regularProductService;
    @Override
    public BaseResponse<ProductSkuListResult> queryRegularProductList() {
        return buildJson(regularProductService.queryRegularProductList());
    }
}
