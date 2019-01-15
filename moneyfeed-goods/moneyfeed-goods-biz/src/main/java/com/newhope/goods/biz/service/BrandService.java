package com.newhope.goods.biz.service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.goods.api.dto.request.BrandQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.BrandUpdateDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.BrandListDtoResult;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/12/19 14:38
 */
public interface BrandService {
    /**
     * 查询品牌
     *
     * @param dtoReq
     * @return
     */
    BrandListDtoResult queryBrandList(BrandQueryDtoReq dtoReq);

    DtoResult addBrand(BrandUpdateDtoReq dtoReq);

    DtoResult updateBrand(BrandUpdateDtoReq dtoReq);

    DtoResult deleteBrand(Long id);

    BrandListDtoResult queryProductBrandList(BrandQueryDtoReq dtoReq);

    BrandListDtoResult queryBrandList();

}
