package com.newhope.moneyfeed.goods.web.controller;


import com.newhope.goods.biz.service.AdProductService;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.AdProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.AdCategoryListDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.AdProductListDtoResult;
import com.newhope.moneyfeed.goods.api.rest.AdProductApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdProductController extends AbstractController implements AdProductApi {

    @Autowired
    private AdProductService adProductService;

    @Override
    public BaseResponse<AdProductListDtoResult> queryAdProduct(@RequestBody AdProductQueryDtoReq dtoReq) {
        AdProductListDtoResult reuslt = adProductService.queryAdProduct(dtoReq);
        return buildJson(reuslt);
    }

    @Override
    public BaseResponse<AdCategoryListDtoResult> queryAdProductCategory(@RequestBody AdProductQueryDtoReq dtoReq) {
        return buildJson(adProductService.queryAdCategory(dtoReq));
    }

}
