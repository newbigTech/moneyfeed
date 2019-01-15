package com.newhope.moneyfeed.goods.dal.dao;

import com.newhope.moneyfeed.goods.api.bean.ProductModel;
import com.newhope.moneyfeed.goods.api.dto.request.BrandUpdateDtoReq;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProductDao extends BaseDao<ProductModel> {

	List<Map> selectNoSkuProductCodeList(@Param("fromDate") Date fromDate);


    Long batchUpdate(@Param("param") List<ProductModel> updateProduct);

    long deleteBrand(@Param("param") BrandUpdateDtoReq dtoReq);
}