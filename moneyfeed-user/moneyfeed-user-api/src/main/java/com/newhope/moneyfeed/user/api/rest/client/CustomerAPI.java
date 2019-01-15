package com.newhope.moneyfeed.user.api.rest.client;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserShopApplyQueryReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerClientUserDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerClientUserUpdateDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerContactQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.EbsCustomerQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ModifyCustomerDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserShopApplyDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerClientUserMappingDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerLabelListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.EbsCustomerDtoListResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "Customer", description = "客户中心API", protocols = "http")
public interface CustomerAPI {


    @ApiOperation(value = "根据条件查询客户信息", notes = "根据条件查询客户信息", protocols = "http")
    @RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<CustomerDtoListResult> queryCustomer(@RequestBody CustomerQueryDtoReq userDtoReq);
   
    @ApiOperation(value = "根据店铺查询关联客户信息", notes = "根据店铺查询关联客户信息", protocols = "http")
    @RequestMapping(value = "/shop/mapping/customer", method = RequestMethod.GET, produces = {"application/json"})
    public BaseResponse<CustomerDtoListResult> queryShopCustomerMapping(@RequestParam(value = "shopId", required = true) Long shopId);

    @ApiOperation(value = "根据用户查询关联客户信息", notes = "根据用户查询关联客户信息", protocols = "http")
    @RequestMapping(value = "/user/mapping/customer", method = RequestMethod.GET, produces = {"application/json"})
    public BaseResponse<CustomerDtoListResult> queryUserCustomerMapping(@RequestParam(value = "userId", required = true) Long userId);
    
    @ApiOperation(value = "根据条件查询ebs客户信息", notes = "根据条件查询ebs客户信息", protocols = "http")
    @RequestMapping(value = "/customer/ebs", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<EbsCustomerDtoListResult> queryEbsCustomer(@RequestBody EbsCustomerQueryDtoReq userDtoReq);

    @ApiOperation(value = "根据条件查询意向客户申请", notes = "根据条件查询意向客户申请", protocols = "http")
    @RequestMapping(value = "/customer/apply/list", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<ClientUserShopApplyDtoListResult> queryUserShopApply(@RequestBody ClientUserShopApplyQueryReq queryParam);

    @ApiOperation(value = "根据条件查询客户的联系人列表", notes = "根据条件查询客户的联系人列表", protocols = "http")
    @RequestMapping(value = "/customer/contact/list", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<CustomerClientUserMappingDtoListResult> queryCustomerContact(@Valid @RequestBody CustomerContactQueryDtoReq queryParam);
    
    @ApiOperation(value = "根据条件查询客户对应的用户列表", notes = "根据条件查询客户对应的用户列表", protocols = "http")
    @RequestMapping(value = "/customer/user/list", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<CustomerClientUserMappingDtoListResult> queryCustomerClientUser(@Valid @RequestBody CustomerContactQueryDtoReq queryParam);
    
    @ApiOperation(value = "更改客户信息", notes = "更改客户信息", protocols = "http")
    @RequestMapping(value = "/customer/modify", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<DtoResult> modifyCustomer(@Valid @RequestBody ModifyCustomerDtoReq modifyCustomerDtoReq);

    @ApiOperation(value = "根据条件查询客户的员工列表", notes = "根据条件查询客户的员工列表", protocols = "http")
    @RequestMapping(value = "/customer/employee/list", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<CustomerClientUserMappingDtoListResult> queryCustomerEmployee(@Valid @RequestBody CustomerContactQueryDtoReq queryParam);
   
    @ApiOperation(value = "编辑客户员工信息", notes = "编辑客户员工信息", protocols = "http")
    @RequestMapping(value = "/customer/employee", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<DtoResult> updateCustomerEmployee(@Valid @RequestBody CustomerClientUserUpdateDtoReq customerClientUserDtoReq); 
    
    @ApiOperation(value = "添加客户员工", notes = "添加客户员工", protocols = "http")
    @RequestMapping(value = "/customer/employee", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<DtoResult> saveCustomerEmployee(@Valid @RequestBody CustomerClientUserDtoReq customerClientUserDtoReq); 
    
    @ApiOperation(value = "删除客户员工", notes = "删除客户员工", protocols = "http")
    @RequestMapping(value = "/customer/employee", method = RequestMethod.DELETE, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<DtoResult> removeCustomerEmployee(@Valid @RequestBody CustomerClientUserUpdateDtoReq customerClientUserDtoReq); 
    
    @ApiOperation(value = "查询客户所属标签", notes = "查询客户所属标签", protocols = "http")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "customerId", value = "客户Id", paramType = "query", required = true, dataType = "Long")
	})
	@RequestMapping(value = "/customer/label", method = RequestMethod.GET, produces = {"application/json"})
	public BaseResponse<CustomerLabelListDtoResult> getCustomerLabel(@RequestParam(name="customerId",required=true)Long customerId);

    @ApiOperation(value = "获取客户详细信息", notes = "获取客户详细信息", protocols = "http")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "customerId", value = "客户Id", paramType = "query", required = true, dataType = "Long")
	})
	@RequestMapping(value = "/customer", method = RequestMethod.GET, produces = {"application/json"})
	BaseResponse<CustomerDtoResult> getCustomerInfo(@RequestParam(name="customerId",required=true)Long customerId);
	
    @ApiOperation(value = "根据条件查询客户信息(平台端调用)", notes = "根据条件查询客户信息(平台端调用)", protocols = "http")
    @RequestMapping(value = "/customer/list", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<CustomerDtoListResult> queryCustomerList(@RequestBody CustomerQueryDtoReq userDtoReq);
   
    @ApiOperation(value = "获取客户所有员工中最后一次登录时间", notes = "获取客户所有员工中最后一次登录时间", protocols = "http")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "customerId", value = "客户Id", paramType = "query", required = true, dataType = "Long")
	})
    @RequestMapping(value = "/customer/logintime", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<CustomerDtoResult> getCustomerLastLoginTime(@RequestParam(name="customerId",required=true)Long customerId);

    @ApiOperation(value = "查询所有客户", notes = "查询所有客户", protocols = "http")
    @RequestMapping(value = "/customer/all", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public BaseResponse<CustomerDtoListResult> queryCustomerAllList(@RequestBody CustomerQueryDtoReq userDtoReq);
   
    @ApiOperation(value = "根据userId获取客户详细信息", notes = "根据userId获取客户详细信息", protocols = "http")
   	@ApiImplicitParams({
           @ApiImplicitParam(name = "userId", value = "userId", paramType = "query", required = true, dataType = "Long")
   	})
   	@RequestMapping(value = "/customer/userid", method = RequestMethod.GET, produces = {"application/json"})
   	BaseResponse<CustomerDtoResult> getCustomerInfoByUser(@RequestParam(name="userId",required=true)Long userId);
   	
}
