package com.newhope.moneyfeed.feedback.api.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackDistributedDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackPageDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackStatusDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.ShopPageDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.UserPageDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackDetailDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackShopListDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackUserListDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "售后OpenApi",description = "售后模块API",protocols = "http")
public interface FeedbacksApi {
	
    @ApiOperation(value = "新建留言",notes = "新建留言",protocols = "http")
    @ApiImplicitParam(name = "dtoReq",value = "新建留言Json",required = true,paramType = "body",dataType = "FeedbackDtoReq")
    @RequestMapping(value = "feedback/add",method = RequestMethod.POST,consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<DtoResult> feedbackReqAdd(@Valid @RequestBody FeedbackDtoReq dtoReq);
    
    
    @ApiOperation(value = "客户留言列表查询",notes = "客户留言列表查询",protocols = "http")
    @ApiImplicitParam(name = "dtoReq",value = "客户留言列表查询Json",required = true,paramType = "body",dataType = "FeedbackPageDtoReq")
    @RequestMapping(value = "feedback/list",method = RequestMethod.POST,consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<FeedbackDtoResult> feedbackList(@RequestBody FeedbackPageDtoReq dtoReq);
    
    
//	@ApiOperation(value = "导出客户留言列表", notes = "导出客户留言列表", protocols = "http")
//	@RequestMapping(value = "feedback/export", method = RequestMethod.POST)
//	BaseResponse<DtoResult> feedbackExport(FeedbackPageDtoReq dtoReq, HttpServletResponse response);
    
     
    @ApiOperation(value = "商户完结",notes = "商户完结",protocols = "http")
    @ApiImplicitParam(name = "dtoReq",value = "商户完结Json",required = true,paramType = "body",dataType = "FeedbackStatusDtoReq")
    @RequestMapping(value = "feedback/modify",method = RequestMethod.POST,consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<DtoResult> feedbackStatusModify(@Valid @RequestBody FeedbackStatusDtoReq dtoReq);
    
    
    @ApiOperation(value = "分配商户/完结",notes = "分配商户/完结",protocols = "http")
    @ApiImplicitParam(name = "dtoReq",value = "分配商户/完结Json",required = true,paramType = "body",dataType = "FeedbackDistributedDtoReq")
    @RequestMapping(value = "feedback/distributed",method = RequestMethod.POST,consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<DtoResult> feedbackDistributedModify(@Valid @RequestBody FeedbackDistributedDtoReq dtoReq);
    
    
    @ApiOperation(value = "详情页查询",notes = "详情页查询",protocols = "http")
    @ApiImplicitParam(name = "id", value = "详情页查询Json", required = true, paramType="path", dataType="String")
    @RequestMapping(value = "feedback/{id}",method = RequestMethod.GET)
    BaseResponse<FeedbackDetailDtoResult> feedbackDetail(@PathVariable("id") Long id);
    
    
    @ApiOperation(value = "联系人列表模糊查询",notes = "联系人列表模糊查询",protocols = "http")
    @ApiImplicitParam(name = "dtoReq",value = "联系人列表模糊查询Json",required = true,paramType = "body",dataType = "UserPageDtoReq")
    @RequestMapping(value = "feedback/user/list",method = RequestMethod.POST,consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<FeedbackUserListDtoResult> queryUserList(@RequestBody UserPageDtoReq dtoReq);
    
    
    @ApiOperation(value = "商户列表模糊查询",notes = "商户列表模糊查询",protocols = "http")
    @ApiImplicitParam(name = "dtoReq",value = "商户列表查询Json",required = true,paramType = "body",dataType = "ShopPageDtoReq")
    @RequestMapping(value = "feedback/shop/list",method = RequestMethod.POST,consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<FeedbackShopListDtoResult> queryShopList(@RequestBody ShopPageDtoReq dtoReq);
    
}
