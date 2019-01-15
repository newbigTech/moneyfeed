package com.newhope.moneyfeed.goods.dal.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.goods.api.bean.AdProductModel;
import com.newhope.moneyfeed.goods.api.dto.request.AdProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.exbean.AdCategoryExModel;
import org.apache.ibatis.annotations.Param;

public interface AdProductDao extends BaseDao<AdProductModel> {

    PageList<AdCategoryExModel> queryAdCategory(@Param("param") AdProductQueryDtoReq dtoReq);


}