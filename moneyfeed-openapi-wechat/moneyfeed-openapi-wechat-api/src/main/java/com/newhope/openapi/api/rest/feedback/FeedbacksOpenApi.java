package com.newhope.openapi.api.rest.feedback;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.feedback.FeedbackReq;
import com.newhope.openapi.api.vo.request.feedback.ShopPageReq;
import com.newhope.openapi.api.vo.response.feedback.FeedbackShopListResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "售后OpenApi",description = "售后模块API",protocols = "http")
public interface FeedbacksOpenApi {
	
    @ApiOperation(value = "新建留言",notes = "新建留言",protocols = "http")
    @ApiImplicitParam(name = "req",value = "新建留言Json",required = true,paramType = "body",dataType = "FeedbackReq")
    @RequestMapping(value = "feedback/add",method = RequestMethod.POST,consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<Result> feedbackReqAdd(@Valid @RequestBody FeedbackReq req);
    
    @ApiOperation(value = "商户列表模糊查询",notes = "商户列表模糊查询",protocols = "http")
    @ApiImplicitParam(name = "req",value = "商户列表查询Json",required = true,paramType = "body",dataType = "ShopPageReq")
    @RequestMapping(value = "feedback/shop/list",method = RequestMethod.POST,consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<FeedbackShopListResult> queryShopList(@RequestBody ShopPageReq req);
    
}
