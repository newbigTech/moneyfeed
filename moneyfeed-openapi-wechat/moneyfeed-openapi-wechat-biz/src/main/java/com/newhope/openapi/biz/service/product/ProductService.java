package com.newhope.openapi.biz.service.product;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;

import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.goods.api.dto.request.ProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuAttributesDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.openapi.api.vo.request.product.ProductQueryReq;
import com.newhope.openapi.api.vo.response.product.ProductSkuAttributesResult;
import com.newhope.openapi.api.vo.response.product.ProductSkuListResult;
import com.newhope.openapi.api.vo.response.product.ProductSkuResult;
import com.newhope.openapi.biz.rpc.feign.product.ProductFeignClient;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-openapi-wechat
 * @date : 2018/11/19 18:50
 */
@Service
public class ProductService {

    @Autowired
    private ProductFeignClient productFeignClient;
    @Autowired
    private RSessionCache rSessionCache;

    public ProductSkuListResult getProduct(ProductQueryReq productQueryReq, boolean verify) {
        ProductSkuListResult productSkuListResult = new ProductSkuListResult();

        ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (userInfo == null || userInfo.getVisitShop() == null || userInfo.getCustomer() == null) {
            productSkuListResult.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
            productSkuListResult.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
            return productSkuListResult;
        }
        ProductQueryDtoReq dtoReq = new ProductQueryDtoReq();
        BeanUtils.copyProperties(productQueryReq, dtoReq);

        dtoReq.setCustomerNum(userInfo.getCustomer().getCustomer().getEbsCustomerNum());
        dtoReq.setShopId(userInfo.getVisitShop().getShop().getId());
        dtoReq.setVerify(verify);
        BaseResponse<ProductSkuListDtoResult> feignResult = productFeignClient.getProduct(dtoReq);

        productSkuListResult.setCode(feignResult.getCode());
        productSkuListResult.setMsg(feignResult.getMsg());

        if (!ResultCode.SUCCESS.getCode().equals(feignResult.getCode()) || null == feignResult.getData()) {
            return productSkuListResult;
        }
        productSkuListResult.setPage(feignResult.getData().getPage());
        productSkuListResult.setTotalCount(feignResult.getData().getTotalCount());
        productSkuListResult.setTotalPage(feignResult.getData().getTotalPage());
        if (CollectionUtils.isEmpty(feignResult.getData().getProductSkuList())) {
            return productSkuListResult;
        }

        List<ProductSkuResult> productReuslts = new ArrayList<>();
        RegularProductService.conversionToVo(feignResult, productReuslts);
        productSkuListResult.setProductSkuResults(productReuslts);
        return productSkuListResult;
    }

    public ProductSkuListResult getProductSku(ProductQueryReq dtoReq, boolean verify) {
        if (CollectionUtils.isEmpty(dtoReq.getProductSkuIds())) {
            throw new BizException(ResultCode.PARAM_ERROR);
        }
        return getProduct(dtoReq, verify);
    }
}
