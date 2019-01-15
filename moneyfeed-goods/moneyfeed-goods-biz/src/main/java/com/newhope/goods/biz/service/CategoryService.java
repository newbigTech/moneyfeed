package com.newhope.goods.biz.service;

import com.newhope.moneyfeed.goods.api.dto.request.CategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.CategoryListDtoResult;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/20 15:27
 */
public interface CategoryService {

    CategoryListDtoResult queryCategory(CategoryQueryDtoReq dtoReq);

}
