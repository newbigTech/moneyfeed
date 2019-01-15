package com.newhope.moneyfeed.goods.dal.dao;

import com.newhope.moneyfeed.goods.api.bean.CategoryModel;
import com.newhope.moneyfeed.goods.api.dto.request.ProductQueryDtoReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao extends BaseDao<CategoryModel> {

    List<CategoryModel> queryAggregationCategory(@Param("param") ProductQueryDtoReq dtoReq);

}