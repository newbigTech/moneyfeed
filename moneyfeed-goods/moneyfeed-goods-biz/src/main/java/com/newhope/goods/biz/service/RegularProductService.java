package com.newhope.goods.biz.service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.goods.api.dto.request.RegularProductDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;

public interface RegularProductService {
    /**
     * 清洗常购清单数据
     */
    DtoResult cleanRegularProduct();


    /**
     * 常购清单查询接口
     * @param dtoResult
     * @return
     */
    ProductSkuListDtoResult queryRegularProductList(RegularProductDtoReq dtoResult);
}
