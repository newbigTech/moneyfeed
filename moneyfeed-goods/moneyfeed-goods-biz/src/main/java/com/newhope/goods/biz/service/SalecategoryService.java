package com.newhope.goods.biz.service;


import com.newhope.moneyfeed.goods.api.bean.SalecategoryModel;
import com.newhope.moneyfeed.goods.api.dto.request.SaleCategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.SalecateProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.SaleCategoryListDtoResult;

import java.util.List;

public interface SalecategoryService {

    SaleCategoryListDtoResult querySaleCategory(SaleCategoryQueryDtoReq dtoReq);


    /**
     * 同步前端对应的商品
     *
     * @param dtoReq
     * @return
     */
    List<SalecategoryModel> queryAggregationSalecategory(SalecateProductQueryDtoReq dtoReq);

    SaleCategoryListDtoResult queryCustomerSaleCategory(SaleCategoryQueryDtoReq dtoReq) ;



}