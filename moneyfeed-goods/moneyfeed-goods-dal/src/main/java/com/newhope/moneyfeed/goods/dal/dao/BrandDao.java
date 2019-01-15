package com.newhope.moneyfeed.goods.dal.dao;

import com.newhope.moneyfeed.goods.api.bean.BrandModel;
import com.newhope.moneyfeed.goods.api.dto.request.BrandQueryDtoReq;
import com.newhope.moneyfeed.goods.api.exbean.BrandExModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandDao extends BaseDao<BrandModel> {
    Long queryBrandListCount(@Param("param") BrandQueryDtoReq dtoReq);

    List<BrandExModel> queryBrandList(@Param("param") BrandQueryDtoReq dtoReq,@Param("start") Long start,@Param("pageSize") Integer pageSize);

    List<BrandExModel> queryProductBrandList(@Param("param") BrandQueryDtoReq dtoReq);
}