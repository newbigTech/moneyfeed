package com.newhope.moneyfeed.goods.web.controller;

import com.newhope.moneyfeed.common.cache.CacheData;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.goods.api.constant.CommonConstant;
import com.newhope.moneyfeed.goods.api.enums.SyncEBSItemStatusEnums;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.goods.biz.service.ProductService;
import com.newhope.goods.biz.service.ProductSkuService;
import com.newhope.goods.biz.service.SkuService;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.ProductSkusQueryDto;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;
import com.newhope.moneyfeed.goods.api.rest.ProductSkuAPI;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/20 15:24
 */
@RestController
public class ProductSkuController extends AbstractController implements ProductSkuAPI {

    @Autowired
    private ProductSkuService productSkuService;
    @Autowired
    private ProductService productSer;
    @Autowired
    private CacheData cacheData;

    @Override
    public BaseResponse<ProductSkuListDtoResult> getProductSku(@RequestBody ProductSkusQueryDto dtoReq) {
        ProductSkuListDtoResult result = productSkuService.getProductSku(dtoReq);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse<?> productSkuInit(@RequestBody String orgId) {
        BaseResponse result = new BaseResponse();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());
        String  key = CommonConstant.SYNC_EBS_MATERIAL_KEY;
        if (StringUtils.isNotEmpty(orgId)) {
            key += orgId;
        }
        Object status = cacheData.getData(key);
        if (SyncEBSItemStatusEnums.COMPLETE.getValue().equals(status)) {
            productSer.asyncProduct(orgId);
        } else {
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg("中台正在同步ebs商品数据，请稍后再试");
        }
        return buildJson(result.getCode(), result.getMsg());
    }
}
