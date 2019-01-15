package com.newhope.moneyfeed.goods.dal.dao;

import com.newhope.moneyfeed.goods.api.bean.SalecategoryModel;
import com.newhope.moneyfeed.goods.api.dto.request.SaleCategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.SalecateProductQueryDtoReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalecategoryDao extends BaseDao<SalecategoryModel> {

     List<SalecategoryModel> queryAggregationSalecategory(@Param("param")SalecateProductQueryDtoReq dtoReq);

     List<SalecategoryModel> queryCustomerSaleCategory(@Param("param")SaleCategoryQueryDtoReq dtoReq);

}