package com.newhope.openapi.biz.service.product;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;

import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.goods.api.dto.request.SaleCategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.SaleCategoryDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.SaleCategoryListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.openapi.api.vo.request.product.SaleCategoryQueryReq;
import com.newhope.openapi.api.vo.response.product.SaleCategoryListResult;
import com.newhope.openapi.api.vo.response.product.SaleCategoryResult;
import com.newhope.openapi.biz.rpc.feign.product.SaleCategoryFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleCatetoryService {

    @Autowired
    private SaleCategoryFeignClient saleCategoryFeignClient;


    @Autowired
    private RSessionCache rSessionCache;

    public SaleCategoryListResult querySaleCategory(SaleCategoryQueryReq queryReq){
        SaleCategoryListResult saleCategoryListResult = new SaleCategoryListResult();

        SaleCategoryQueryDtoReq saleCategoryQueryDtoReq = new SaleCategoryQueryDtoReq();
        BeanUtils.copyProperties(queryReq,saleCategoryQueryDtoReq);
        BaseResponse<SaleCategoryListDtoResult> feignResult = saleCategoryFeignClient.querySaleCategory(saleCategoryQueryDtoReq);

        saleCategoryListResult.setCode(feignResult.getCode());
        saleCategoryListResult.setMsg(feignResult.getMsg());
        if (!ResultCode.SUCCESS.getCode().equals(feignResult.getCode()) || null == feignResult.getData()) {
            return saleCategoryListResult;
        }

        for (SaleCategoryDtoResult saleCategoryDtoResult : feignResult.getData().getSaleCategoryDtoResultList()) {
            SaleCategoryResult saleCategoryResult = new SaleCategoryResult();
            BeanUtils.copyProperties(saleCategoryDtoResult, saleCategoryResult);
            saleCategoryListResult.getSaleCategoryResultList().add(saleCategoryResult);
        }

        return saleCategoryListResult;
    }



    public SaleCategoryListResult queryCustomerSaleCategory(SaleCategoryQueryReq queryReq){
        SaleCategoryListResult saleCategoryListResult = new SaleCategoryListResult();

        SaleCategoryQueryDtoReq dtoReq = new SaleCategoryQueryDtoReq();
        BeanUtils.copyProperties(queryReq,dtoReq);
        ClientUserCacheDtoResult userInfo = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (userInfo != null) {
            dtoReq.setShopId(userInfo.getVisitShop().getShop().getId());
            dtoReq.setCustomerNo(userInfo.getCustomer().getCustomer().getEbsCustomerNum());
        }
        BaseResponse<SaleCategoryListDtoResult> feignResult = saleCategoryFeignClient.queryCustomerSaleCategory(dtoReq);

        saleCategoryListResult.setCode(feignResult.getCode());
        saleCategoryListResult.setMsg(feignResult.getMsg());
        if (!ResultCode.SUCCESS.getCode().equals(feignResult.getCode()) || null == feignResult.getData()) {
            return saleCategoryListResult;
        }

        for (SaleCategoryDtoResult saleCategoryDtoResult : feignResult.getData().getSaleCategoryDtoResultList()) {
            SaleCategoryResult saleCategoryResult = new SaleCategoryResult();
            BeanUtils.copyProperties(saleCategoryDtoResult, saleCategoryResult);
            saleCategoryListResult.getSaleCategoryResultList().add(saleCategoryResult);
        }

        return saleCategoryListResult;
    }

}
