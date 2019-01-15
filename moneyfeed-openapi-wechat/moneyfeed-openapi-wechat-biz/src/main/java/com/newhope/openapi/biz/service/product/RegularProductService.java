package com.newhope.openapi.biz.service.product;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.goods.api.dto.request.RegularProductDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuAttributesDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.openapi.api.vo.response.product.ProductSkuAttributesResult;
import com.newhope.openapi.api.vo.response.product.ProductSkuListResult;
import com.newhope.openapi.api.vo.response.product.ProductSkuResult;
import com.newhope.openapi.biz.rpc.feign.product.RegularProductFeigenClient;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RegularProductService {

    @Autowired
    private RegularProductFeigenClient regularProductFeigenClient;
    @Autowired
    private RSessionCache rSessionCache;


    public ProductSkuListResult queryRegularProductList(){

        ProductSkuListResult productSkuListResult = new ProductSkuListResult();

        ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        RegularProductDtoReq dtoReq = new RegularProductDtoReq();

        if (userInfo == null || userInfo.getVisitShop() == null || userInfo.getCustomer() == null) {
            productSkuListResult.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
            productSkuListResult.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
            return productSkuListResult;
        }
        dtoReq.setCustomerNo(userInfo.getCustomer().getCustomer().getEbsCustomerNum());
        dtoReq.setShopId(userInfo.getVisitShop().getShop().getId());

        BaseResponse<ProductSkuListDtoResult> feignResult = regularProductFeigenClient.queryRegularProductList(dtoReq);
        productSkuListResult.setCode(feignResult.getCode());
        productSkuListResult.setMsg(feignResult.getMsg());

        if (!ResultCode.SUCCESS.getCode().equals(feignResult.getCode()) || null == feignResult.getData()) {
            return productSkuListResult;
        }
        if (CollectionUtils.isEmpty(feignResult.getData().getProductSkuList())) {
            return productSkuListResult;
        }

        List<ProductSkuResult> productReuslts = new ArrayList<>();
        conversionToVo(feignResult, productReuslts);
        productSkuListResult.setProductSkuResults(productReuslts);
        return productSkuListResult;
    }

    static void conversionToVo(BaseResponse<ProductSkuListDtoResult> feignResult, List<ProductSkuResult> productReuslts) {
        for (ProductSkuDtoResult productSkuDtoResult : feignResult.getData().getProductSkuList()) {
            ProductSkuResult productSkuResult = new ProductSkuResult();
            BeanUtils.copyProperties(productSkuDtoResult, productSkuResult);
            if (CollectionUtils.isNotEmpty(productSkuDtoResult.getProductSkuAttributesExModels())) {
                List<ProductSkuAttributesResult> skuAttributesList = new ArrayList<>();
                for (ProductSkuAttributesDtoResult productSkuAttributesDtoResult : productSkuDtoResult.getProductSkuAttributesExModels()) {
                    ProductSkuAttributesResult attributesResult = new ProductSkuAttributesResult();
                    BeanUtils.copyProperties(productSkuAttributesDtoResult, attributesResult);
                    skuAttributesList.add(attributesResult);
                }
                productSkuResult.setProductSkuAttributesResults(skuAttributesList);
            }
            productReuslts.add(productSkuResult);
        }
    }
}
