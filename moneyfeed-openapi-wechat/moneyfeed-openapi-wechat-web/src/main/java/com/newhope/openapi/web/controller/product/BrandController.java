package com.newhope.openapi.web.controller.product;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.rest.product.BrandAPI;
import com.newhope.openapi.api.vo.request.product.BrandQueryReq;
import com.newhope.openapi.api.vo.response.product.BrandListResult;
import com.newhope.openapi.biz.service.product.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : tom
 * @project: moneyfeed-openapi-wechat
 * @date : 2018/12/19 14:27
 */
@RestController
public class BrandController extends AbstractController implements BrandAPI {

    @Autowired
    private BrandService brandService;


    @Override
    public BaseResponse<BrandListResult> queryBrandList(BrandQueryReq brandQueryReq) {
        BrandListResult result=brandService.queryBrandList(brandQueryReq);
        return buildJson(result);
    }
}
