package com.newhope.openapi.api.rest.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.customer.AllotIntentionCustomerReq;
import com.newhope.openapi.api.vo.request.customer.CustomerContactQueryReq;
import com.newhope.openapi.api.vo.request.customer.CustomerQueryReq;
import com.newhope.openapi.api.vo.request.customer.IntentionCustomerQueryReq;
import com.newhope.openapi.api.vo.request.customer.ModifyIntentionCustomerMsg;
import com.newhope.openapi.api.vo.request.customer.UpdateCustomerPropertyReq;
import com.newhope.openapi.api.vo.response.customer.CustomerContactListResult;
import com.newhope.openapi.api.vo.response.customer.CustomerListResult;
import com.newhope.openapi.api.vo.response.customer.CustomerPropertyResult;
import com.newhope.openapi.api.vo.response.customer.IntentionCustomerListResult;
import com.newhope.openapi.api.vo.response.customer.IntentionCustomerResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "Customer", description = "客户中心", protocols = "http")
public interface CustomerAPI {

	@ApiOperation(value = "客户联系人分页查询", notes = "客户联系人分页查询", protocols = "http")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "shopId", value = "shopId", paramType = "query", required = false, dataType = "Long"),
        @ApiImplicitParam(name = "contactName", value = "联系人姓名", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "contactNobile", value = "联系人手机", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "customerName", value = "所属客户", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "beginDatetime", value = "开始时间", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "endDatetime", value = "结束时间", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "enable", value = "状态", paramType = "query", required = false, dataType = "Boolean"),
        @ApiImplicitParam(name = "page", value = "当前页", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query", required = true, dataType = "Integer")
	})
	@RequestMapping(value = "/customer/contact/list", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<CustomerContactListResult> queryCustomerContactList(CustomerContactQueryReq customerQueryReq,
			HttpServletRequest request);
	
	@ApiOperation(value = "导出客户联系人", notes = "导出客户联系人", protocols = "http")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "shopId", value = "shopId", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "contactName", value = "联系人姓名", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "contactNobile", value = "联系人手机", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "customerName", value = "所属客户", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "beginDatetime", value = "开始时间", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "endDatetime", value = "结束时间", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "enable", value = "状态", paramType = "query", required = false, dataType = "Boolean")
	})
	@RequestMapping(value = "/customer/contact/export", method = RequestMethod.GET, produces = {"application/json"})
	public void exportCustomerContact(CustomerContactQueryReq customerQueryReq, HttpServletResponse response,
			HttpServletRequest request);
	
	
	@ApiOperation(value = "意向客户分页查询", notes = "意向客户分页查询", protocols = "http")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "shopId", value = "店铺Id", paramType = "query", required = false, dataType = "Long"),
        @ApiImplicitParam(name = "name", value = "姓名", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "beginDatetime", value = "开始时间", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "endDatetime", value = "结束时间", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "allotShopName", value = "分配商户名称", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "intentionShopName", value = "意向商户名称", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "status", value = "状态：ALLOTTING-待分配；ALLOTTED-已分配", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "page", value = "当前页", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query", required = true, dataType = "Integer")
	})
	@RequestMapping(value = "/customer/intention/list", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<IntentionCustomerListResult> queryIntentionCustomerList(IntentionCustomerQueryReq intentionCustomerQueryReq,
			HttpServletRequest request);
	
	@ApiOperation(value = "导出意向客户", notes = "导出意向客户", protocols = "http")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "shopId", value = "店铺Id", paramType = "query", required = false, dataType = "Long"),
        @ApiImplicitParam(name = "name", value = "姓名", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "beginDatetime", value = "开始时间", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "endDatetime", value = "结束时间", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "allotShopName", value = "分配商户名称", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "intentionShopName", value = "意向商户名称", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "status", value = "状态：ALLOTTING-待分配；ALLOTTED-已分配", paramType = "query", required = false, dataType = "String")
	})
	@RequestMapping(value = "/customer/intention/export", method = RequestMethod.GET, produces = {"application/json"})
	public void exportIntentionCustomer(IntentionCustomerQueryReq intentionCustomerQueryReq, HttpServletResponse response,
			HttpServletRequest request);
	
	@ApiOperation(value = "根据意向客户申请id查询详情", notes = "根据意向客户申请id查询详情", protocols = "http")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "applyId", value = "申请Id", paramType = "query", required = true, dataType = "Long")
	})
	@RequestMapping(value = "/customer/intention", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<IntentionCustomerResult> queryIntentionCustomerInfo(@RequestParam(name="applyId",required=true)Long applyId);
	
	@ApiOperation(value = "分配意向客户", notes = "分配意向客户", protocols = "http")
	@RequestMapping(value = "/customer/intention/allot", method = RequestMethod.POST, produces = {"application/json"})
	public BaseResponse<Result> allotIntentionCustomer(@Valid @RequestBody AllotIntentionCustomerReq allotIntentionCustomerReq);
	
	@ApiOperation(value = "商户修改客户备注", notes = "商户修改客户备注", protocols = "http")
	@RequestMapping(value = "/customer/intention/modify", method = RequestMethod.POST, produces = {"application/json"})
	public BaseResponse<Result> modifyIntentionCustomerShopDealMsg(@Valid @RequestBody ModifyIntentionCustomerMsg modifyIntentionCustomerMsg);

	@ApiOperation(value = "客户管理列表查询", notes = "客户管理列表查询", protocols = "http")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "shopId", value = "shopId", paramType = "query", required = false, dataType = "Long"),
        @ApiImplicitParam(name = "customerName", value = "客户名称", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "customerAsName", value = "客户别名", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "customerNum", value = "客户编码", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "status", value = "交易状态", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "page", value = "当前页", paramType = "query", required = false, dataType = "Long"),
        @ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query", required = false, dataType = "Integer")
	})
	@RequestMapping(value = "/customer/list", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<CustomerListResult> queryCustomerList(CustomerQueryReq customerQueryReq, HttpServletRequest request);
	
	@ApiOperation(value = "导出客户", notes = "导出意向客户", protocols = "http")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "customerName", value = "客户名称", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "customerAsName", value = "客户别名", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "customerNum", value = "客户编码", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "status", value = "交易状态", paramType = "query", required = false, dataType = "String")
	})
	@RequestMapping(value = "/customer/export", method = RequestMethod.GET, produces = {"application/json"})
	public void exportCustomer(CustomerQueryReq customerQueryReq, HttpServletResponse response,
			HttpServletRequest request);
	
	@ApiOperation(value = "查询所有客户信息", notes = "查询所有客户信息", protocols = "http")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "shopId", value = "shopId", paramType = "query", required = false, dataType = "Long"),
        @ApiImplicitParam(name = "customerName", value = "客户名称", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "customerAsName", value = "客户别名", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "customerNum", value = "客户编码", paramType = "query", required = false, dataType = "String"),
        @ApiImplicitParam(name = "status", value = "交易状态", paramType = "query", required = false, dataType = "String")
	})
	@RequestMapping(value = "/customer/all", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<CustomerListResult> queryAllCustomerList(CustomerQueryReq customerQueryReq, HttpServletRequest request);

	
	@ApiOperation(value = "查询客户线上设置详情", notes = "查询客户线上设置详情", protocols = "http")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "customerId", value = "客户Id", paramType = "query", required = true, dataType = "Long"),
        @ApiImplicitParam(name = "shopId", value = "shopId", paramType = "query", required = true, dataType = "Long")
	})
	@RequestMapping(value = "/customer/Property", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<CustomerPropertyResult> queryCustomerPropertyInfo(@RequestParam(name="customerId",required=true)Long customerId, 
			@RequestParam(name="shopId",required=true)Long shopId, HttpServletRequest request);
	
	@ApiOperation(value = "商户客户线上交易设置", notes = "商户客户线上交易设置", protocols = "http")
	@RequestMapping(value = "/customer/Property", method = RequestMethod.POST, produces = {"application/json"})
	public BaseResponse<Result> modifyCustomerPropertyInfo(@Valid @RequestBody UpdateCustomerPropertyReq updateCustomerPropertyReq,
			HttpServletRequest request);
	
	@ApiOperation(value = "客户对应的用户分页查询", notes = "客户对应的用户分页查询", protocols = "http")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "shopId", value = "shopId", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "contactName", value = "联系人姓名", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "contactNobile", value = "联系人手机", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "customerName", value = "所属客户", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "beginDatetime", value = "开始时间", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "endDatetime", value = "结束时间", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "enable", value = "状态", paramType = "query", required = false, dataType = "Boolean"),
		@ApiImplicitParam(name = "page", value = "当前页", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query", required = true, dataType = "Integer")
	})
	@RequestMapping(value = "/customer/user/list", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<CustomerContactListResult> queryCustomerClientUser(CustomerContactQueryReq customerQueryReq,
			HttpServletRequest request);

	}
