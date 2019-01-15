package com.newhope.moneyfeed.user.web.controller.client;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.newhope.moneyfeed.user.api.rest.client.CustomerAPI;
import com.newhope.moneyfeed.user.web.controller.AbstractController;
import com.newhope.user.user.biz.service.client.UcClientUserShopApplyService;
import com.newhope.user.user.biz.service.client.UcCustomerClientUserMappingService;
import com.newhope.user.user.biz.service.client.UcCustomerService;

/**
 * @author 陈立
 */
@RestController
public class ClientCustomerController extends AbstractController implements CustomerAPI {
	
	
	@Autowired
	private UcClientUserShopApplyService userShopApplyService;
	
	@Autowired
	UcCustomerService ucCustomerService;
	
	@Autowired
	UcCustomerClientUserMappingService ucCustomerClientUserMappingService;
	
    @Override
    public BaseResponse<CustomerDtoListResult> queryCustomer(@RequestBody CustomerQueryDtoReq userDtoReq) {
    	CustomerDtoListResult result =  ucCustomerService.searchCustomer(userDtoReq);
    	
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
    }

    @Override
    public BaseResponse<EbsCustomerDtoListResult> queryEbsCustomer(@RequestBody EbsCustomerQueryDtoReq userDtoReq) {
        return null;
    }

	@Override
	public BaseResponse<ClientUserShopApplyDtoListResult> queryUserShopApply(@RequestBody ClientUserShopApplyQueryReq userShopApplyQueryReq) {
		ClientUserShopApplyDtoListResult result = new ClientUserShopApplyDtoListResult();
		result = userShopApplyService.queryUserShopApply(userShopApplyQueryReq);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override 
	public BaseResponse<CustomerDtoListResult> queryShopCustomerMapping(@RequestParam(value = "shopId", required = true) Long shopId) {
		CustomerDtoListResult result = new CustomerDtoListResult();
		result.setCustomers(ucCustomerService.queryShopCustomerMapping(shopId));
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override 
	public BaseResponse<CustomerDtoListResult> queryUserCustomerMapping(@RequestParam(value = "userId", required = true) Long userId) {
		CustomerDtoListResult result = new CustomerDtoListResult();
		result.setCustomers(ucCustomerService.queryUserCustomerMapping(userId));
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<CustomerClientUserMappingDtoListResult> queryCustomerContact(
			@RequestBody CustomerContactQueryDtoReq queryParam) {
		CustomerClientUserMappingDtoListResult result = new CustomerClientUserMappingDtoListResult();
		result = ucCustomerClientUserMappingService.queryCustomerContact(queryParam);
		return buildJson(result.getCode(), result.getMsg(), result);
	}
	
	@Override
	public BaseResponse<CustomerClientUserMappingDtoListResult> queryCustomerClientUser(
			@RequestBody CustomerContactQueryDtoReq queryParam) {
		CustomerClientUserMappingDtoListResult result = new CustomerClientUserMappingDtoListResult();
		result = ucCustomerClientUserMappingService.queryCustomerClientUser(queryParam);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> modifyCustomer(@RequestBody ModifyCustomerDtoReq modifyCustomerDtoReq) {
		DtoResult result = new DtoResult();
		try {
			result = ucCustomerService.modifyCustomer(modifyCustomerDtoReq);
		} catch (Exception e) {
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(e.getMessage());
		}
		return buildJson(result.getCode(), result.getMsg(), null);
	}
	
	@Override
	public BaseResponse<CustomerClientUserMappingDtoListResult> queryCustomerEmployee(
			@RequestBody CustomerContactQueryDtoReq queryParam) {
		CustomerClientUserMappingDtoListResult result = new CustomerClientUserMappingDtoListResult();
		result = ucCustomerClientUserMappingService.queryCustomerEmployee(queryParam);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> updateCustomerEmployee(@RequestBody CustomerClientUserUpdateDtoReq customerClientUserDtoReq) {
		DtoResult result = new DtoResult();
		try {
			result = ucCustomerClientUserMappingService.updateCustomerClientUser(customerClientUserDtoReq);
		} catch (BizException e) {
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(e.getMessage());
		}
		return buildJson(result.getCode(), result.getMsg(), null);
	}

	@Override
	public BaseResponse<DtoResult> saveCustomerEmployee(@RequestBody CustomerClientUserDtoReq customerClientUserDtoReq) {
		DtoResult result = new DtoResult();
		try {
			result = ucCustomerClientUserMappingService.saveCustomerEmployee(customerClientUserDtoReq);
		} catch (BizException e) {
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(e.getMessage());
		}
		return buildJson(result.getCode(), result.getMsg(), null);
	}

	@Override
	public BaseResponse<DtoResult> removeCustomerEmployee(@RequestBody CustomerClientUserUpdateDtoReq customerClientUserDtoReq) {
		DtoResult result = new DtoResult();
		try {
			result = ucCustomerClientUserMappingService.removeCustomerEmployee(customerClientUserDtoReq);
		} catch (BizException e) {
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(e.getMessage());
		}
		return buildJson(result.getCode(), result.getMsg(), null);
	}

	@Override
	public BaseResponse<CustomerLabelListDtoResult> getCustomerLabel(@RequestParam(name="customerId",required=true)Long customerId) {
		CustomerLabelListDtoResult result = new CustomerLabelListDtoResult();
		result = ucCustomerService.getCustomerLabel(customerId);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<CustomerDtoResult> getCustomerInfo(@RequestParam(name="customerId",required=true)Long customerId) {
		CustomerDtoResult result = new CustomerDtoResult();
		result = ucCustomerService.getCustomerInfo(customerId);
		return buildJson(result.getCode(), result.getMsg(), result);
	}
	
	@Override
	public BaseResponse<CustomerDtoListResult> queryCustomerList(@RequestBody CustomerQueryDtoReq userDtoReq) {
		CustomerDtoListResult result =  ucCustomerService.queryCustomerList(userDtoReq);
		
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
	}
	
	@Override
	public BaseResponse<CustomerDtoResult> getCustomerLastLoginTime(@RequestParam(name="customerId",required=true)Long customerId) {
		CustomerDtoResult result = new CustomerDtoResult();
		result = ucCustomerService.getCustomerLastLoginTime(customerId);
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
	}
	
	@Override
    public BaseResponse<CustomerDtoListResult> queryCustomerAllList(@RequestBody CustomerQueryDtoReq userDtoReq) {
    	CustomerDtoListResult result =  ucCustomerService.queryCustomerList(userDtoReq);
    	
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
    }
	
	@Override
	public BaseResponse<CustomerDtoResult> getCustomerInfoByUser(@RequestParam(name="userId",required=true)Long userId) {
		CustomerDtoResult result = new CustomerDtoResult();
		result = ucCustomerService.getCustomerInfoByUser(userId);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

}
