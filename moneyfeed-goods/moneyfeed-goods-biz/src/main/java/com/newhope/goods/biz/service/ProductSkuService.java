package com.newhope.goods.biz.service;

import com.newhope.moneyfeed.goods.api.dto.request.ProductSkusQueryDto;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/20 15:27
 */
public interface ProductSkuService {
    /**
     * 根据sku获取商品详情
     * @param dtoReq
     * @return
     */
    ProductSkuListDtoResult getProductSku(ProductSkusQueryDto dtoReq);
}
