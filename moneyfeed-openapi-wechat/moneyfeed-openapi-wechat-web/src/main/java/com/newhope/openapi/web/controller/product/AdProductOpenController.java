package com.newhope.openapi.web.controller.product;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.rest.product.AdProductOpenApi;
import com.newhope.openapi.api.vo.request.product.AdProductQueryReq;
import com.newhope.openapi.api.vo.response.product.AdProductListResult;
import com.newhope.openapi.biz.service.product.AdProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdProductOpenController extends AbstractController implements AdProductOpenApi {

    @Autowired
    AdProductService adProductService;

    @Override
    public BaseResponse<AdProductListResult> queryAdProduct(AdProductQueryReq adProductQueryReq) {
        AdProductListResult result = adProductService.queryAdProduct(adProductQueryReq);
        return buildJson(result);
    }
}