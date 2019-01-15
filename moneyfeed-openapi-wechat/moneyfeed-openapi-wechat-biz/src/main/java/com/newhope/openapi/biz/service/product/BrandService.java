package com.newhope.openapi.biz.service.product;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.goods.api.dto.request.BrandQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.BrandDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.BrandListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.openapi.api.vo.request.product.BrandQueryReq;
import com.newhope.openapi.api.vo.response.product.BrandListResult;
import com.newhope.openapi.api.vo.response.product.BrandResult;
import com.newhope.openapi.biz.rpc.feign.product.BrandFeignClient;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-openapi-wechat
 * @date : 2018/12/24 08:55
 */
@Service
public class BrandService {

    @Autowired
    private BrandFeignClient brandFeignClient;
    @Autowired
    RSessionCache rSessionCache;

    public BrandListResult queryBrandList(BrandQueryReq brandQueryReq) {
        BrandListResult result = new BrandListResult();
        BrandQueryDtoReq dtoReq = new BrandQueryDtoReq();
        dtoReq.setSaleCateId(brandQueryReq.getSaleCateId());
        dtoReq.setSaleCateLevel(brandQueryReq.getSaleCateLevel());
        ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (userInfo == null || userInfo.getVisitShop() == null || userInfo.getCustomer() == null) {
            result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
            result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
            return result;
        }
        dtoReq.setShopId(userInfo.getVisitShop().getShop().getId());
        dtoReq.setCustomerNum(userInfo.getCustomer().getCustomer().getEbsCustomerNum());
        BaseResponse<BrandListDtoResult> feignResp = brandFeignClient.queryProductBrandList(dtoReq);
        result.setCode(feignResp.getCode());
        result.setMsg(feignResp.getMsg());

        if (!feignResp.isSuccess() || null == feignResp.getData()
                || CollectionUtils.isEmpty(feignResp.getData().getBrandDtoResults())) {
            return result;
        }

        List<BrandResult> brandResultList = new ArrayList<>();
        for (BrandDtoResult brandDtoResult : feignResp.getData().getBrandDtoResults()) {
            BrandResult brandResult = new BrandResult();
            BeanUtils.copyProperties(brandDtoResult, brandResult);
            brandResultList.add(brandResult);
        }
        result.setBrandResults(brandResultList);
        return result;
    }


}
