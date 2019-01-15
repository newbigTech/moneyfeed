package com.newhope.moneyfeed.goods.dal.dao;

import com.newhope.moneyfeed.goods.api.bean.RegularProductModel;
import com.newhope.moneyfeed.goods.api.dto.request.RegularProductDtoReq;
import com.newhope.moneyfeed.goods.api.exbean.ProductSkuExModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegularProductDao extends BaseDao<RegularProductModel> {

   List<RegularProductModel> selectCustomerProductByOrder();

   List<ProductSkuExModel> queryRegularProductList(@Param("param") RegularProductDtoReq dtoReq);
}