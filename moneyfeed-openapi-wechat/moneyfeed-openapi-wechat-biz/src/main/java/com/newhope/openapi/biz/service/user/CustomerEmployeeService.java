package com.newhope.openapi.biz.service.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.dto.request.user.UserRemoveDtoReq;
import com.newhope.moneyfeed.api.dto.request.wechat.WechatMsgDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.wechat.WechatMsgTypeEnums;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.constant.CommonConstant;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerClientUserDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerClientUserUpdateDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerContactQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.RoleQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserThirdAccountDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerClientUserMappingDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerClientUserMappingDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleListDtoResult;
import com.newhope.moneyfeed.user.api.enums.platform.SysSourceTypeEnums;
import com.newhope.openapi.api.vo.request.user.CustomerClientUserReq;
import com.newhope.openapi.api.vo.request.user.CustomerClientUserUpdateReq;
import com.newhope.openapi.api.vo.response.user.ClientRoleListResult;
import com.newhope.openapi.api.vo.response.user.ClientRoleResult;
import com.newhope.openapi.api.vo.response.user.CustomerEmployeeListResult;
import com.newhope.openapi.api.vo.response.user.CustomerEmployeeResult;
import com.newhope.openapi.biz.rpc.feign.user.ClientUserFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.CustomerFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.RoleFeignClient;
import com.newhope.openapi.biz.rpc.feign.wechat.BaseUserFeignClient;
import com.newhope.openapi.biz.rpc.feign.wechat.WechatMsgFeignClient;


@Service
public class CustomerEmployeeService {
	
	@Autowired
	CustomerFeignClient customerFeignClient;
	
	@Autowired
	RoleFeignClient roleFeignClient;
	
	@Autowired
	BaseUserFeignClient baseUserFeignClient;
	
	@Autowired
	WechatMsgFeignClient wechatMsgFeignClient;
	
	@Autowired
	ClientUserFeignClient clientUserFeignClient;
	
	
	public CustomerEmployeeListResult queryCustomerEmployeeList(Long customerId) {
		CustomerEmployeeListResult result = new CustomerEmployeeListResult();
		
		CustomerContactQueryDtoReq queryParam = new CustomerContactQueryDtoReq();
		queryParam.setCustomerId(customerId);
		queryParam.setPage(null);
		queryParam.setPageSize(null);
		BaseResponse<CustomerClientUserMappingDtoListResult> feignResp = customerFeignClient.queryCustomerEmployee(queryParam);
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		if(feignResp.isSuccess() && null != feignResp.getData() 
				&& !CollectionUtils.isEmpty(feignResp.getData().getCustomerContacts())) {
			List<CustomerEmployeeResult> CustomerEmployees = new ArrayList<CustomerEmployeeResult>();
			CustomerEmployeeResult customerEmployeeResult = null;
			for (CustomerClientUserMappingDtoResult dtoResult : feignResp.getData().getCustomerContacts()) {
				customerEmployeeResult = new CustomerEmployeeResult();
				BeanUtils.copyProperties(dtoResult, customerEmployeeResult);
				
				customerEmployeeResult.setEmployeeName(dtoResult.getContactName());
				customerEmployeeResult.setEmployeePhone(dtoResult.getContactPhone());
				
				CustomerEmployees.add(customerEmployeeResult);
			}
			result.setCustomerEmployees(CustomerEmployees);
		}
		
		return result;
	}
	
	public Result updateCustomerEmployee(CustomerClientUserUpdateReq customerClientUserReq) {
		Result result = new Result();
		
		boolean sendMsg = false;
		//获取员工信息
		CustomerEmployeeResult CustomerEmployeeResult = this.getCustomerEmployee(customerClientUserReq.getCustomerClientUserId());
		if(null == CustomerEmployeeResult.getRoleId() 
				|| CustomerEmployeeResult.getRoleId().intValue() != customerClientUserReq.getRoleId().intValue()) {
			sendMsg = true;
		}
		
		CustomerClientUserUpdateDtoReq queryParam = new CustomerClientUserUpdateDtoReq();
		BeanUtils.copyProperties(customerClientUserReq, queryParam);
		BaseResponse<DtoResult> feignResp = customerFeignClient.updateCustomerEmployee(queryParam);
		
		if(feignResp.isSuccess()) {
			//如果更改为离职状态，或者角色变更需删除缓存
			if(!customerClientUserReq.getEnable() || sendMsg) {
				
				UserRemoveDtoReq userRemoveDtoReq = new UserRemoveDtoReq();
				userRemoveDtoReq.setUserIds(Arrays.asList(customerClientUserReq.getClientUserId()));
				userRemoveDtoReq.setThirdSource(CommonConstant.HTTP_HEADER_SOURCE_WECHAT);
				baseUserFeignClient.removeLoginInfo(userRemoveDtoReq);
			}
			if(sendMsg) {//角色变更发送微信消息
				//获取第三方微信账号
				BaseResponse<ClientUserThirdAccountDtoResult> thirdAccountFeignResp = 
						clientUserFeignClient.queryUserThirdAccount(CustomerEmployeeResult.getClientUserId(), null, CommonConstant.HTTP_HEADER_SOURCE_WECHAT);
				if(thirdAccountFeignResp.isSuccess() && null != thirdAccountFeignResp.getData()) {
					WechatMsgDtoReq wechatMsgDtoReq = new WechatMsgDtoReq();
					wechatMsgDtoReq.setOpenid(thirdAccountFeignResp.getData().getAccount().getThirdAccount());
					wechatMsgDtoReq.setParams(Arrays.asList(customerClientUserReq.getRoleName()));
					wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.CLIENT_ROLE_CHANGE);
					wechatMsgFeignClient.sendWechatMsg(wechatMsgDtoReq);
				}
			}
		}
		
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		
		return result;
	} 
    
    public Result saveCustomerEmployee(CustomerClientUserReq customerClientUserReq) {
    	Result result = new Result();
    	
    	if(null != customerClientUserReq.getClientUserId() && null != customerClientUserReq.getCustomerClientUserId()) {
    		//验证手机号是否有更改
    		CustomerContactQueryDtoReq queryParam = new CustomerContactQueryDtoReq();
    		queryParam.setCustomerClientUserId(customerClientUserReq.getCustomerClientUserId());
    		queryParam.setPage(null);
    		queryParam.setPageSize(null);
    		BaseResponse<CustomerClientUserMappingDtoListResult> feignResp = customerFeignClient.queryCustomerEmployee(queryParam);
    		
    		//手机号不相同，将原来的记录删除
    		if(null != feignResp.getData() 
    				&& !CollectionUtils.isEmpty(feignResp.getData().getCustomerContacts())
    				&& !customerClientUserReq.getContactPhone().equals(feignResp.getData().getCustomerContacts().get(0).getContactPhone())) {
    			CustomerClientUserUpdateDtoReq customerClientUserUpdateDtoReq = new CustomerClientUserUpdateDtoReq();
    			BeanUtils.copyProperties(customerClientUserReq, customerClientUserUpdateDtoReq);
    			customerFeignClient.removeCustomerEmployee(customerClientUserUpdateDtoReq);
    			
    			//重新保存关系
    			CustomerClientUserDtoReq customerClientUserDtoReq = new CustomerClientUserDtoReq();
        		BeanUtils.copyProperties(customerClientUserReq, customerClientUserDtoReq);
        		BaseResponse<DtoResult> resp = customerFeignClient.saveCustomerEmployee(customerClientUserDtoReq); 
        		result.setCode(resp.getCode());
        		result.setMsg(resp.getMsg());
    		}else {
    			CustomerClientUserUpdateDtoReq customerClientUserUpdateDtoReq = new CustomerClientUserUpdateDtoReq();
    			BeanUtils.copyProperties(customerClientUserReq, customerClientUserUpdateDtoReq);
    			customerClientUserUpdateDtoReq.setEnable(true);
    			BaseResponse<DtoResult> updatefeignResp = customerFeignClient.updateCustomerEmployee(customerClientUserUpdateDtoReq);
    			result.setCode(updatefeignResp.getCode());
        		result.setMsg(updatefeignResp.getMsg());
    		}
    	}else {
    		CustomerClientUserDtoReq customerClientUserDtoReq = new CustomerClientUserDtoReq();
    		BeanUtils.copyProperties(customerClientUserReq, customerClientUserDtoReq);
    		BaseResponse<DtoResult> feignResp = customerFeignClient.saveCustomerEmployee(customerClientUserDtoReq); 
    		result.setCode(feignResp.getCode());
    		result.setMsg(feignResp.getMsg());
    	}
    	
		
		return result;
    }
    
   
    public ClientRoleListResult getClientRole(){
    	ClientRoleListResult result = new ClientRoleListResult();
    	RoleQueryDtoReq roleParam = new RoleQueryDtoReq();
    	roleParam.setEnable(true);
    	roleParam.setSourceType(SysSourceTypeEnums.CLIENT.name());
    	roleParam.setPage(null);
    	roleParam.setPageSize(null);
    	BaseResponse<UcPmRoleListDtoResult> feignResult = roleFeignClient.getRole(roleParam);
    	if(feignResult.isSuccess()&&feignResult.getData()!=null
    			&&CollectionUtils.isNotEmpty(feignResult.getData().getRoleList())){
    		for(UcPmRoleModel role : feignResult.getData().getRoleList()){
    			ClientRoleResult roleResult = new ClientRoleResult();
    			roleResult.setCode(role.getCode());
    			roleResult.setComment(role.getComment());
    			roleResult.setId(role.getId());
    			roleResult.setName(role.getName());
    			result.getRoleList().add(roleResult);
    		}
    	}
    	result.setCode(feignResult.getCode());
    	result.setMsg(feignResult.getMsg());
    	return result;
    }
    
    public CustomerEmployeeResult getCustomerEmployee(Long customerClientUserId) {
    	CustomerEmployeeResult result = new CustomerEmployeeResult();
    	
    	CustomerContactQueryDtoReq queryParam = new CustomerContactQueryDtoReq();
		queryParam.setCustomerClientUserId(customerClientUserId);
		queryParam.setPage(null);
		queryParam.setPageSize(null);
		BaseResponse<CustomerClientUserMappingDtoListResult> feignResp = customerFeignClient.queryCustomerEmployee(queryParam);
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		if(feignResp.isSuccess() && null != feignResp.getData() 
				&& !CollectionUtils.isEmpty(feignResp.getData().getCustomerContacts())) {
			CustomerClientUserMappingDtoResult dtoResult = feignResp.getData().getCustomerContacts().get(0);
			BeanUtils.copyProperties(dtoResult, result);
			result.setEmployeeName(dtoResult.getContactName());
			result.setEmployeePhone(dtoResult.getContactPhone());
		}
		
		return result;
    }
}
