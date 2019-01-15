package com.newhope.openapi.biz.service.product;


import com.newhope.moneyfeed.api.vo.base.BaseResponse;

import com.newhope.moneyfeed.goods.api.dto.request.AdProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.AdCategoryDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.AdCategoryListDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.AdProductDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.AdProductListDtoResult;
import com.newhope.openapi.api.vo.request.product.AdProductQueryReq;
import com.newhope.openapi.api.vo.response.product.AdProductListResult;
import com.newhope.openapi.api.vo.response.product.AdProductListResult.CategoryAdProduct;
import com.newhope.openapi.api.vo.response.product.AdProductResult;
import com.newhope.openapi.biz.rpc.feign.product.AdProductFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdProductService {

    @Autowired
    private AdProductFeignClient adProductFeignClient;

    public AdProductListResult queryAdProduct(AdProductQueryReq queryReq){

        AdProductListResult adProductListResult = new AdProductListResult();
        AdProductQueryDtoReq adProductQueryDtoReq = new AdProductQueryDtoReq();
        BeanUtils.copyProperties(queryReq,adProductQueryDtoReq);
        BaseResponse<AdCategoryListDtoResult> categoryFeignResult = adProductFeignClient.queryAdProductCategory(adProductQueryDtoReq);

        for(AdCategoryDtoResult adCategory : categoryFeignResult.getData().getAdCategoryDtoResultList()){
            AdProductQueryDtoReq req = new AdProductQueryDtoReq();
            req.setThreeCateId(adCategory.getCategoryId());
            BaseResponse<AdProductListDtoResult> feignResult = adProductFeignClient.queryAdProduct(req);

            List<AdProductResult> adProductResultList = new ArrayList<>();
            for(AdProductDtoResult adProductDtoResult :feignResult.getData().getAdProductDtoResultList()){
                AdProductResult result = new AdProductResult();
                BeanUtils.copyProperties(adProductDtoResult,result);
                adProductResultList.add(result);
            }
            CategoryAdProduct categoryAdProduct = adProductListResult.new CategoryAdProduct();
            categoryAdProduct.setCategoryId(adCategory.getCategoryId());
            categoryAdProduct.setCategoryName(adCategory.getCategoryName());
            categoryAdProduct.setAdProductResults(adProductResultList);

            adProductListResult.getAdProductResults().add(categoryAdProduct);
        }

        adProductListResult.setCode(categoryFeignResult.getCode());
        adProductListResult.setMsg(categoryFeignResult.getMsg());
        return adProductListResult;
    }


}
