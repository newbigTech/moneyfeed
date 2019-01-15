package com.newhope.goods.biz.service;

import com.newhope.moneyfeed.goods.api.dto.request.AdProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.AdCategoryListDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.AdProductListDtoResult;

public interface AdProductService {

    AdProductListDtoResult queryAdProduct(AdProductQueryDtoReq dtoReq);

    AdCategoryListDtoResult queryAdCategory(AdProductQueryDtoReq dtoReq);


}
