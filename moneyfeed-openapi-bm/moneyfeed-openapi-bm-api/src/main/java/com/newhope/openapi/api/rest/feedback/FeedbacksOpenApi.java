package com.newhope.openapi.api.rest.feedback;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.feedback.FeedbackDistributedReq;
import com.newhope.openapi.api.vo.request.feedback.FeedbackPageReq;
import com.newhope.openapi.api.vo.request.feedback.FeedbackStatusReq;
import com.newhope.openapi.api.vo.request.feedback.ShopPageReq;
import com.newhope.openapi.api.vo.request.feedback.UserPageReq;
import com.newhope.openapi.api.vo.response.feedback.FeedbackDetailResult;
import com.newhope.openapi.api.vo.response.feedback.FeedbackResult;
import com.newhope.openapi.api.vo.response.feedback.FeedbackShopListResult;
import com.newhope.openapi.api.vo.response.feedback.FeedbackUserListResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "售后OpenApi",description = "售后模块API",protocols = "http")
public interface FeedbacksOpenApi {
	
    @ApiOperation(value = "客户留言列表查询",notes = "客户留言列表查询",protocols = "http")
    @ApiImplicitParam(name = "req",value = "客户留言列表查询Json",required = true,paramType = "body",dataType = "FeedbackPageReq")
    @RequestMapping(value = "feedback/list",method = RequestMethod.POST,consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<FeedbackResult> feedbackList(@Valid @RequestBody FeedbackPageReq req);
    
	@ApiOperation(value = "导出客户留言列表", notes = "导出客户留言列表", protocols = "http")
	@RequestMapping(value = "feedback/export", method = RequestMethod.GET)
	BaseResponse<Result> feedbackExport(FeedbackPageReq req, HttpServletResponse response);
    
     
    @ApiOperation(value = "商户完结",notes = "商户完结",protocols = "http")
    @ApiImplicitParam(name = "req",value = "商户完结Json",required = true,paramType = "body",dataType = "FeedbackStatusReq")
    @RequestMapping(value = "feedback/modify",method = RequestMethod.POST,consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<Result> feedbackStatusModify(@Valid @RequestBody FeedbackStatusReq req);
    
    
    @ApiOperation(value = "分配商户/完结",notes = "分配商户/完结",protocols = "http")
    @ApiImplicitParam(name = "req",value = "分配商户/完结Json",required = true,paramType = "body",dataType = "FeedbackDistributedReq")
    @RequestMapping(value = "feedback/distributed",method = RequestMethod.POST,consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<Result> feedbackDistributedModify(@Valid @RequestBody FeedbackDistributedReq req);
    
    
    @ApiOperation(value = "详情页查询",notes = "详情页查询",protocols = "http")
    @ApiImplicitParam(name = "req", value = "处理完成/关闭留言Json", required = true, paramType="path", dataType="String")
    @RequestMapping(value = "feedback/{id}",method = RequestMethod.GET)
    BaseResponse<FeedbackDetailResult> feedbackDetail(@PathVariable("id") Long id);
    
    
    @ApiOperation(value = "联系人列表模糊查询",notes = "联系人列表模糊查询",protocols = "http")
    @ApiImplicitParam(name = "req",value = "联系人列表模糊查询Json",required = true,paramType = "body",dataType = "UserPageReq")
    @RequestMapping(value = "feedback/user/list",method = RequestMethod.POST,consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<FeedbackUserListResult> queryUserList(@RequestBody UserPageReq req);
    
    
    @ApiOperation(value = "商户列表模糊查询",notes = "商户列表模糊查询",protocols = "http")
    @ApiImplicitParam(name = "req",value = "商户列表查询Json",required = true,paramType = "body",dataType = "ShopPageReq")
    @RequestMapping(value = "feedback/shop/list",method = RequestMethod.POST,consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<FeedbackShopListResult> queryShopList(@RequestBody ShopPageReq req);
    
}
