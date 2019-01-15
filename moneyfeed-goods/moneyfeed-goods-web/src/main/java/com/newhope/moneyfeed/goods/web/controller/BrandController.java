package com.newhope.moneyfeed.goods.web.controller;

import com.newhope.goods.biz.service.BrandService;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.BrandQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.BrandUpdateDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.BrandListDtoResult;
import com.newhope.moneyfeed.goods.api.rest.BrandAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/12/19 14:27
 */
@RestController
public class BrandController extends AbstractController implements BrandAPI {

    @Autowired
    private BrandService brandService;

    @Override
    public BaseResponse<BrandListDtoResult> queryBrandList(@RequestBody BrandQueryDtoReq dtoReq) {
        BrandListDtoResult result=brandService.queryBrandList(dtoReq);
        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> addBrand(@RequestBody BrandUpdateDtoReq dtoReq) {
        DtoResult result=brandService.addBrand(dtoReq);
        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> updateBrand(@RequestBody BrandUpdateDtoReq dtoReq) {
        DtoResult result=brandService.updateBrand(dtoReq);
        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> deleteBrand(@PathVariable("id") Long id) {
        DtoResult result=brandService.deleteBrand(id);
        return buildJson(result);
    }

    @Override
    public BaseResponse<BrandListDtoResult> queryProductBrandList(@RequestBody BrandQueryDtoReq dtoReq) {
        BrandListDtoResult result=brandService.queryProductBrandList(dtoReq);
        return buildJson(result);
    }

    @Override
    public BaseResponse<BrandListDtoResult> queryBrandList() {
        BrandListDtoResult result=brandService.queryBrandList();
        return buildJson(result);
    }
}
