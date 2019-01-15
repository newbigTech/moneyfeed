package com.newhope.moneyfeed.user.api.rest.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.client.EbsCustomerListPostDtoReq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="EbsCustomer", description="ebs客户中心API", protocols="http")
public interface EbsCustomerAPI {
	
	
	    @ApiOperation(value = "新增/修改EBS客户集合", notes = "新增/修改EBS客户集合", protocols = "http")
	    @ApiImplicitParams({
            @ApiImplicitParam(name = "dtoReq", required = true, paramType = "body", dataType = "EbsCustomerListPostDtoReq")
	    })
	    @RequestMapping(value = "ebs/customer", method = RequestMethod.POST)
	    BaseResponse<DtoResult> modifyEbsCustomerList(@RequestBody EbsCustomerListPostDtoReq dtoReq);

}
