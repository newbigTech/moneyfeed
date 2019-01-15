package com.newhope.goods.biz.service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.goods.api.dto.request.BrandUpdateDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductEbsQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductUpdateDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/20 15:25
 */
public interface ProductService {
    /**
     * 初始化商品数据
     *
     * @return
     * @param orgId
     */
    DtoResult syncProduct(String orgId);

    /**
     * 根据公司code更新商品
     *
     * @param dtoReq
     * @return
     */
    DtoResult productCompany(ProductUpdateDtoReq dtoReq);

    /**
     * 修改指定商品
     *
     * @param dtoReq
     * @return
     */
    DtoResult productSingle(ProductUpdateDtoReq dtoReq);

    /**
     * 获取商品信息
     *
     * @param dtoReq
     * @return
     */
    ProductSkuListDtoResult getProduct(ProductQueryDtoReq dtoReq);

    /**
     * 根据ebs条件查询商品信息
     *
     * @param dtoReq
     * @return
     */
    ProductSkuListDtoResult getEbsProduct(ProductEbsQueryDtoReq dtoReq);

    /**
     * 异步同步
     * @param orgId
     */
    void asyncProduct(String orgId);

    boolean updateBrand(BrandUpdateDtoReq dtoReq);

    boolean deleteBrand(BrandUpdateDtoReq dtoReq);

	/**
	 * 根据productCode更新product属性
	 * @param updateProductReq
	 * @return
	 */
	boolean updateProduct(ProductDtoReq updateProductReq);
}
