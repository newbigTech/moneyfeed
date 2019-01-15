package com.newhope.moneyfeed.user.dal.dao.client;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.user.api.dto.request.platform.ShopPageDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmShopDtoResult;
import org.apache.ibatis.annotations.Param;

import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopQueryDtoReq;
import com.newhope.moneyfeed.user.dal.BaseDao;

public interface UcShopDao extends BaseDao<UcShopModel> {
    
	 List<UcShopModel> searchShop(@Param("queryParam") ShopQueryDtoReq queryParam, @Param("start") Long start,
             @Param("size") Integer size);

	 Long searchShopCount(@Param("queryParam") ShopQueryDtoReq queryParam);

    PageList<UcPmShopDtoResult> shopList(@Param("param") ShopPageDtoReq requestBody, PageBounds pageBounds);

	List<UcPmShopDtoResult> shopDetail(@Param("param") UcShopModel ucShopModel);
}