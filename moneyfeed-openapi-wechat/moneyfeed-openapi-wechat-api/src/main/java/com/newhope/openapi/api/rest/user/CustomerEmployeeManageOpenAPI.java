package com.newhope.openapi.api.rest.user;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.user.CustomerClientUserReq;
import com.newhope.openapi.api.vo.request.user.CustomerClientUserUpdateReq;
import com.newhope.openapi.api.vo.response.user.ClientRoleListResult;
import com.newhope.openapi.api.vo.response.user.CustomerEmployeeListResult;
import com.newhope.openapi.api.vo.response.user.CustomerEmployeeResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description = "客户员工管理", tags = "CustomerEmployeeManageOpenAPI", protocols = "http")
public interface CustomerEmployeeManageOpenAPI {
	
	@ApiOperation(value = "根据条件查询客户的员工列表", notes = "客户联系人分页查询", protocols = "http")
	@RequestMapping(value = "/employee/list", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<CustomerEmployeeListResult> queryCustomerEmployeeList(HttpServletRequest request);
	
	@ApiOperation(value = "编辑客户员工信息", notes = "编辑客户员工信息", protocols = "http")
    @RequestMapping(value = "/employee", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<Result> updateCustomerEmployee(@Valid @RequestBody CustomerClientUserUpdateReq customerClientUserReq); 
    
    @ApiOperation(value = "添加客户员工", notes = "添加客户员工", protocols = "http")
    @RequestMapping(value = "/employee", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<Result> saveCustomerEmployee(@Valid @RequestBody CustomerClientUserReq customerClientUserReq); 
   
    @ApiOperation(value = "获取客户端角色列表", notes = "获取客户端角色列表", protocols = "http")
    @RequestMapping(value = "/role", method = RequestMethod.GET, produces = {"application/json"})
    public BaseResponse<ClientRoleListResult> getClientRole();
    
    @ApiOperation(value = "查询员工详情", notes = "查询员工详情", protocols = "http")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "customerClientUserId", value = "主键Id", paramType = "query", required = true, dataType = "Long")
	})
	@RequestMapping(value = "/employee", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<CustomerEmployeeResult> getCustomerEmployee(@RequestParam(name="customerClientUserId",required=true)Long customerClientUserId);
	
}
