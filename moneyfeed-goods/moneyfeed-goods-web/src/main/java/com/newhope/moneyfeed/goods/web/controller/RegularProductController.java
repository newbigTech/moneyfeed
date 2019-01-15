package com.newhope.moneyfeed.goods.web.controller;

import com.newhope.goods.biz.service.RegularProductService;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.RegularProductDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;
import com.newhope.moneyfeed.goods.api.rest.RegularProductApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegularProductController extends AbstractController implements RegularProductApi {

    @Autowired
    private RegularProductService regularProductService;


    @Override
    public BaseResponse<ProductSkuListDtoResult> queryRegularProductList(@RequestBody RegularProductDtoReq dtoReq) {
        ProductSkuListDtoResult result = regularProductService.queryRegularProductList(dtoReq);
        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> cleanRegularProduct() {
        return buildJson(regularProductService.cleanRegularProduct());
    }
}
