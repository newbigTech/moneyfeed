package com.newhope.moneyfeed.user.api.rest.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementPostListDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerAccountListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerAccountStatementListDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "CustomerAccount", description = "客户账户中心API", protocols = "http")
public interface CustomerAccountAPI {
	
	@ApiOperation(value="查询客户账户列表", notes="查询客户账户列表", protocols="http")
	@RequestMapping(value = "/customer/account", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<CustomerAccountListDtoResult> queryCustomerAccount(@RequestBody CustomerAccountQueryDtoReq requestBody);
	
	
	@ApiOperation(value="查询客户账户流水列表", notes="查询客户账户流水列表", protocols="http")
	@RequestMapping(value = "/customer/account/statement/query", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<CustomerAccountStatementListDtoResult> queryCustomerAccountStatement(@RequestBody CustomerAccountStatementQueryDtoReq requestBody);
	
	
	@ApiOperation(value="保存客户账户流水列表", notes="保存客户账户流水列表", protocols="http")
	@RequestMapping(value = "/customer/account/statement/save", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
	public BaseResponse<DtoResult> saveCustomerAccountStatement(@RequestBody CustomerAccountStatementPostListDtoReq requestBody);
	
}
