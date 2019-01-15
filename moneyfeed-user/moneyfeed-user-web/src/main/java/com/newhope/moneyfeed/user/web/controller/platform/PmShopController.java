package com.newhope.moneyfeed.user.web.controller.platform;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.platform.*;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmLabelListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmShopDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmShopListDtoResult;
import com.newhope.moneyfeed.user.api.rest.platform.PmShopAPI;
import com.newhope.moneyfeed.user.web.controller.AbstractController;
import com.newhope.user.user.biz.service.platform.PmShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Create by yyq on 2018/12/20
 */
@RestController
public class PmShopController extends AbstractController implements PmShopAPI {

    @Autowired
    PmShopService pmShopService;

    @Override
    public BaseResponse<DtoResult> createShop(@Valid @RequestBody ShopDtoReq requestBody) {
        DtoResult result = pmShopService.createShop(requestBody);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse<UcPmShopListDtoResult> shopList(@RequestBody ShopPageDtoReq requestBody) {
        UcPmShopListDtoResult result = pmShopService.shopList(requestBody);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse<DtoResult> modifyShop(@Valid @RequestBody ShopModifyDtoReq requestBody) {
        DtoResult result = pmShopService.modifyShop(requestBody);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse<UcPmShopDtoResult> shopDetail(@RequestParam("shopId") Long shopId) {
        UcPmShopDtoResult result = pmShopService.shopDetail(shopId);
        return buildJson(result.getCode(), result.getMsg(), result);
    }

}
