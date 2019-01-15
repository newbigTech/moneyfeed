package com.newhope.openapi.web.controller.product;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.rest.product.BrandAPI;
import com.newhope.openapi.api.vo.request.product.BrandQueryReq;
import com.newhope.openapi.api.vo.request.product.BrandUpdateReq;
import com.newhope.openapi.api.vo.response.product.BrandListResult;
import com.newhope.openapi.biz.service.product.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
    public BaseResponse<BrandListResult> queryBrandList(BrandQueryReq req) {
        BrandListResult result=brandService.queryBrandList(req);
        return buildJson(result);
    }

    @Override
    public BaseResponse<Result> addBrand(@RequestBody BrandUpdateReq req) {
        Result result=brandService.addBrand(req);
        return buildJson(result);
    }

    @Override
    public BaseResponse<Result> updateBrand(@RequestBody BrandUpdateReq req) {
        Result result=brandService.updateBrand(req);
        return buildJson(result);
    }

    @Override
    public BaseResponse<Result> deleteBrand(@PathVariable("id") Long id) {
        Result result=brandService.deleteBrand(id);
        return buildJson(result);
    }

    @Override
    public BaseResponse<Result> brandExport(BrandQueryReq req, HttpServletResponse response) {
        Result result = brandService.brandExport(req, response);
        return buildJson(result);
    }

    @Override
    public BaseResponse<BrandListResult> queryBrandList() {
        BrandListResult result=brandService.queryBrandList();
        return buildJson(result);
    }
}
