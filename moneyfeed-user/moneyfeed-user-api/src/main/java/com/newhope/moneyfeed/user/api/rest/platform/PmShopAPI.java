package com.newhope.moneyfeed.user.api.rest.platform;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.platform.*;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmLabelListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmShopDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmShopListDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Create by yyq on 2018/12/20
 */
@Api(value = "PmShop", description = "平台店铺管理REST API", protocols = "http")
@RequestMapping(value = "/pm/shop")
public interface PmShopAPI {
    @ApiOperation(value = "新建店铺", notes = "新建店铺", protocols = "http")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<DtoResult> createShop(@Valid @RequestBody ShopDtoReq requestBody);

    @ApiOperation(value = "店铺列表查询", notes = "店铺列表查询", protocols = "http")
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<UcPmShopListDtoResult> shopList(@RequestBody ShopPageDtoReq requestBody);

    @ApiOperation(value = "更新店铺", notes = "更新店铺", protocols = "http")
    @RequestMapping(value = "/modify", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<DtoResult> modifyShop(@Valid @RequestBody ShopModifyDtoReq requestBody);

    @ApiOperation(value = "店铺详情查询", notes = "店铺详情查询", protocols = "http")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public BaseResponse<UcPmShopDtoResult> shopDetail(@RequestParam("shopId") Long shopId);


}
