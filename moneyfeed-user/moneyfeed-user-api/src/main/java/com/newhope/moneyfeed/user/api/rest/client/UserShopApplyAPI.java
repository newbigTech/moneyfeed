package com.newhope.moneyfeed.user.api.rest.client;


import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.client.ModifyUserShopApplyDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.UserShopApplyDtoReq;

import com.newhope.moneyfeed.user.api.dto.response.client.UserApplyListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Api(description = "意向客户申请接口", tags = "UserShopApplyAPI", protocols = "http")
@RequestMapping("/uc/user/apply")
public interface UserShopApplyAPI {
    @ApiOperation(value = "意向客户申请接口", notes = "意向客户申请接口")
    @RequestMapping(method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, paramType = "body", dataType = "UserShopApplyDtoReq")
    })
    BaseResponse<DtoResult> create(@Valid @RequestBody UserShopApplyDtoReq request);
    
    @ApiOperation(value = "分配意向客户", notes = "分配意向客户", protocols = "http")
	@RequestMapping(value = "/allotshop", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<DtoResult> allotIntentionCustomer(
    		@Valid @RequestBody ModifyUserShopApplyDtoReq modifyUserShopApplyDtoReq) ;
	
    @ApiOperation(value = "商户修改客户备注", notes = "商户修改客户备注", protocols = "http")
	@RequestMapping(value = "/modify/msg", method = RequestMethod.POST, produces = {"application/json"})
    BaseResponse<DtoResult> modifyIntentionCustomerShopDealMsg(
    		@Valid @RequestBody ModifyUserShopApplyDtoReq modifyUserShopApplyDtoReq) ;

    @ApiOperation(value = "意向客户查询接口不分页", notes = "意向客户查询接口不分页")
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, paramType = "body", dataType = "UserShopApplyDtoReq")
    })
    BaseResponse<UserApplyListResult> find(@RequestBody UserShopApplyDtoReq request);
	
}