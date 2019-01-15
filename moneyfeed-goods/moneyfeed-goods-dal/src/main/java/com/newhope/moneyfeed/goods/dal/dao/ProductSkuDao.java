package com.newhope.moneyfeed.goods.dal.dao;

import com.newhope.moneyfeed.goods.api.dto.request.ProductEbsQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductQueryDtoReq;
import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.goods.api.bean.ProductSkuModel;
import com.newhope.moneyfeed.goods.api.dto.request.ProductSkusQueryDto;
import com.newhope.moneyfeed.goods.api.exbean.ProductSkuExModel;

import java.util.List;

public interface ProductSkuDao extends BaseDao<ProductSkuModel> {

	PageList<ProductSkuExModel> queryProductSku(@Param("param") ProductSkusQueryDto dtoReq, PageBounds page);

	List<ProductSkuExModel> queryProductList(@Param("param") ProductQueryDtoReq dtoReq, @Param("start") Long start,
			@Param("pageSize") Integer pageSize);

	Long queryProductCount(@Param("param") ProductQueryDtoReq dtoReq);

	Long updateProductSkuPrice();

	Long selectShopIdByProductCode(@Param("productCode") String productCode);

    List<ProductSkuExModel> queryEbsProduct(@Param("param")ProductEbsQueryDtoReq dtoReq);
}