package com.newhope.user.user.biz.service.client;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserModel;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserRoleModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerClientUserMappingModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerClientUserMappingExModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerClientUserDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerClientUserUpdateDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerContactQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerClientUserMappingDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerClientUserMappingDtoResult;
import com.newhope.moneyfeed.user.api.enums.client.CustomerEmployeeInviteStatusEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerSourceEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerUserActivateEnums;
import com.newhope.moneyfeed.user.api.enums.platform.ManagerRoleCodeEnums;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcCustomerClientUserMappingDao;
import com.newhope.user.user.biz.service.BaseService;
import com.newhope.user.user.biz.service.platform.UcPmRoleService;


@Service
public class UcCustomerClientUserMappingService extends BaseService<UcCustomerClientUserMappingModel> {
	
	
	@Autowired
	UcCustomerClientUserMappingDao ucCustomerClientUserMappingDao;
	
	@Autowired
	UcClientUserService ucClientUserService;
	
	@Autowired
	UcClientUserRoleService ucClientUserRoleService;
	
	@Autowired
	UcPmRoleService ucPmRoleService;
	
	@Override
	protected BaseDao<UcCustomerClientUserMappingModel> getDao() {
		return ucCustomerClientUserMappingDao;
	}

	public UcCustomerClientUserMappingModel getCustomerRelationOfUser(Long userId) {
		UcCustomerClientUserMappingModel params = new UcCustomerClientUserMappingModel();
		params.setClientUserId(userId);
		final List<UcCustomerClientUserMappingModel> userMappingModels = ucCustomerClientUserMappingDao.select(params);
		if (userMappingModels.isEmpty()) {
			throw new BizException(ResultCode.USER_CUSTOMER_NOT_EXIST);
		}
		return userMappingModels.get(0);
	}
	
	public CustomerClientUserMappingDtoListResult queryCustomerClientUser(
			 CustomerContactQueryDtoReq queryParam){
		CustomerClientUserMappingDtoListResult result = new CustomerClientUserMappingDtoListResult();
		
		PageList<UcCustomerClientUserMappingExModel> pageList = null;
		PageBounds pageBounds = null;
		
		if(null != queryParam.getPage() && null != queryParam.getPageSize()) {
			pageBounds = new PageBounds(queryParam.getPage().intValue(), queryParam.getPageSize());
			pageList = ucCustomerClientUserMappingDao.queryCustomerClientUser(queryParam, pageBounds);
			Paginator paginator = pageList.getPaginator();
	        if (paginator != null) {
	        	result.setPage((long) paginator.getPage());
	            result.setTotalCount((long) paginator.getTotalCount());
	            result.setTotalPage((long) paginator.getTotalPages());
	        }else {
	        	result.setPage(1L);
				result.setTotalCount(0L);
				result.setTotalPage(0L);
	        }
		}else {
			pageList = ucCustomerClientUserMappingDao.queryCustomerClientUser(queryParam);
		}
		
    	CustomerClientUserMappingDtoResult dtoResult = null;
    	for (UcCustomerClientUserMappingExModel model : pageList) {
    		dtoResult = new CustomerClientUserMappingDtoResult();
    		dtoResult.setCustomerClientUserId(model.getId());
    		dtoResult.setContactName(model.getClientUserName());
    		dtoResult.setContactPhone(model.getClientUserMobile());
    		dtoResult.setCustomerName(model.getCustomerName());
    		dtoResult.setEnable(model.getEnable());
    		dtoResult.setGmtCreated(model.getGmtCreated());
    		dtoResult.setSource(model.getSource());
    		dtoResult.setShopName(model.getShopName());
    		dtoResult.setClientUserId(model.getClientUserId());
    		dtoResult.setCustomerId(model.getCustomerId());
    		dtoResult.setCustomerNum(model.getEbsCustomerNum());
    		
    		result.getCustomerContacts().add(dtoResult);
		}
        	
		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
		
	}
	
	public CustomerClientUserMappingDtoListResult queryCustomerContact(
			CustomerContactQueryDtoReq queryParam){
		CustomerClientUserMappingDtoListResult result = new CustomerClientUserMappingDtoListResult();
		
		PageList<UcCustomerClientUserMappingExModel> pageList = null;
		PageBounds pageBounds = null;
		
		if(null != queryParam.getPage() && null != queryParam.getPageSize()) {
			pageBounds = new PageBounds(queryParam.getPage().intValue(), queryParam.getPageSize());
			pageList = ucCustomerClientUserMappingDao.queryCustomerContact(queryParam, pageBounds);
			Paginator paginator = pageList.getPaginator();
			if (paginator != null) {
				result.setPage((long) paginator.getPage());
				result.setTotalCount((long) paginator.getTotalCount());
				result.setTotalPage((long) paginator.getTotalPages());
			}else {
				result.setPage(1L);
				result.setTotalCount(0L);
				result.setTotalPage(0L);
			}
		}else {
			pageList = ucCustomerClientUserMappingDao.queryCustomerContact(queryParam);
		}
		
		CustomerClientUserMappingDtoResult dtoResult = null;
		for (UcCustomerClientUserMappingExModel model : pageList) {
			dtoResult = new CustomerClientUserMappingDtoResult();
			dtoResult.setCustomerClientUserId(model.getId());
			dtoResult.setContactName(model.getClientUserName());
			dtoResult.setContactPhone(model.getClientUserMobile());
			dtoResult.setCustomerName(model.getCustomerName());
			dtoResult.setEnable(model.getEnable());
			dtoResult.setGmtCreated(model.getGmtCreated());
			dtoResult.setSource(model.getSource());
			dtoResult.setShopName(model.getShopName());
			dtoResult.setClientUserId(model.getClientUserId());
			dtoResult.setCustomerId(model.getCustomerId());
			
			result.getCustomerContacts().add(dtoResult);
		}
		
		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
		
	}
	
	public CustomerClientUserMappingDtoListResult queryCustomerEmployee(
			CustomerContactQueryDtoReq queryParam){
		CustomerClientUserMappingDtoListResult result = new CustomerClientUserMappingDtoListResult();
		
		PageList<UcCustomerClientUserMappingExModel> pageList = null;
		PageBounds pageBounds = null;
		
		queryParam.setEnable(true);
		
		if(null != queryParam.getPage() && null != queryParam.getPageSize()) {
			pageBounds = new PageBounds(queryParam.getPage().intValue(), queryParam.getPageSize());
			pageList = ucCustomerClientUserMappingDao.queryCustomerEmployee(queryParam, pageBounds);
			Paginator paginator = pageList.getPaginator();
			if (paginator != null) {
				result.setPage((long) paginator.getPage());
				result.setTotalCount((long) paginator.getTotalCount());
				result.setTotalPage((long) paginator.getTotalPages());
			}else {
				result.setPage(1L);
				result.setTotalCount(0L);
				result.setTotalPage(0L);
			}
		}else {
			pageList = ucCustomerClientUserMappingDao.queryCustomerEmployee(queryParam);
		}
		
		CustomerClientUserMappingDtoResult dtoResult = null;
		for (UcCustomerClientUserMappingExModel model : pageList) {
			dtoResult = new CustomerClientUserMappingDtoResult();
			dtoResult.setCustomerClientUserId(model.getId());
			dtoResult.setContactName(model.getClientUserName());
			dtoResult.setContactPhone(model.getClientUserMobile());
			dtoResult.setCustomerName(model.getCustomerName());
			dtoResult.setEnable(model.getEnable());
			dtoResult.setGmtCreated(model.getGmtCreated());
			dtoResult.setSource(model.getSource());
			dtoResult.setClientUserId(model.getClientUserId());
			dtoResult.setCustomerId(model.getCustomerId());
			if(model.getLastLoginTime() == null) {
				dtoResult.setActivated(CustomerUserActivateEnums.NOT_ACTIVE.getValue());
				dtoResult.setStatus(CustomerEmployeeInviteStatusEnums.NOT_CONFIRMED.getValue());
			}else {
				dtoResult.setActivated(CustomerUserActivateEnums.ACTIVATED.getValue());
				dtoResult.setStatus(CustomerEmployeeInviteStatusEnums.NORMAL.getValue());
			}
			dtoResult.setRoleId(model.getRoleId());
			dtoResult.setRoleName(model.getRoleName());
			
			result.getCustomerContacts().add(dtoResult);
		}
		
		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
	}
	
	public DtoResult updateCustomerClientUser(CustomerClientUserUpdateDtoReq customerClientUserDtoReq) {
		DtoResult result = new DtoResult();
		
		if(StringUtils.isNotBlank(customerClientUserDtoReq.getSmsCode())) {//短信验证码不为空，表示手机号更改
			//验证新手机号是否存在
			UcClientUserModel clientUserModel = new UcClientUserModel();
			clientUserModel.setMobile(customerClientUserDtoReq.getContactPhone());
			clientUserModel.setEnable(true);
			List<UcClientUserModel> userList = ucClientUserService.query(clientUserModel);
			
			if(!userList.isEmpty()) {
				result.setCode(ResultCode.USER_MOBILE_EXIST.getCode());
				result.setMsg(ResultCode.USER_MOBILE_EXIST.getDesc());
				return result;
			}
		}
		
		//获取客户端管理员角色Id
		UcPmRoleModel ucPmRoleModel = new UcPmRoleModel();
		ucPmRoleModel.setCode(ManagerRoleCodeEnums.CLIENT.getValue());
		ucPmRoleModel.setEnable(true);
		List<UcPmRoleModel> pmRoleList = ucPmRoleService.query(ucPmRoleModel);
		Long adminId = pmRoleList.get(0).getId();
		
		/**
		 * 更新角色信息
		 * 1、先删除原角色关系
		 * 2、再重新添加角色关系
		 */
		
		UcClientUserRoleModel oleClientUserRoleModel = new UcClientUserRoleModel();
		oleClientUserRoleModel.setUserId(customerClientUserDtoReq.getClientUserId());
		oleClientUserRoleModel.setEnable(true);
		List<UcClientUserRoleModel> oldUserRoleList = ucClientUserRoleService.query(oleClientUserRoleModel);
		/*
		 * 如果当前编辑的人员是管理员，现在变更为其他角色，需要判断当前所属客户还有其他员工为管理员
		 */
		if(!oldUserRoleList.isEmpty() && oldUserRoleList.get(0).getRoleId().intValue() == adminId.intValue() 
				&& customerClientUserDtoReq.getRoleId().intValue() != adminId.intValue()) {
			int adminNum = ucClientUserRoleService.getCustomerRoleCount(customerClientUserDtoReq.getCustomerId(), adminId);
			if(adminNum == 1) {//当前客户只有一个管理员，不能变更为其他角色
				result.setCode(ResultCode.USER_ADMIN_ROLE_NOT_CHANGE.getCode());
				result.setMsg(ResultCode.USER_ADMIN_ROLE_NOT_CHANGE.getDesc());
				return result;
			}
		}
		UcClientUserRoleModel newClientUserRoleModel = new UcClientUserRoleModel();
		newClientUserRoleModel.setEnable(false);
		
		ucClientUserRoleService.update(oleClientUserRoleModel, newClientUserRoleModel);
		
		if(customerClientUserDtoReq.getEnable()) {
			//重新建立关系
			UcClientUserRoleModel clientUserRoleModel = new UcClientUserRoleModel();
			clientUserRoleModel.setUserId(customerClientUserDtoReq.getClientUserId());
			clientUserRoleModel.setRoleId(customerClientUserDtoReq.getRoleId());
			clientUserRoleModel.setEnable(true);
			ucClientUserRoleService.save(Arrays.asList(clientUserRoleModel));
		}
		
		//更新user表
		UcClientUserModel oldClientUserModel = new UcClientUserModel();
		oldClientUserModel.setId(customerClientUserDtoReq.getClientUserId());
		
		UcClientUserModel newClientUserModel = new UcClientUserModel();
		newClientUserModel.setMobile(customerClientUserDtoReq.getContactPhone());
		newClientUserModel.setName(customerClientUserDtoReq.getContactName());
		
		ucClientUserService.update(oldClientUserModel, newClientUserModel);
		
		//更新customer与user的关系
		UcCustomerClientUserMappingModel customerClientUserModel =  new UcCustomerClientUserMappingModel();
		customerClientUserModel.setId(customerClientUserDtoReq.getCustomerClientUserId());
		
		UcCustomerClientUserMappingModel newCustomerClientUserModel =  new UcCustomerClientUserMappingModel();
		newCustomerClientUserModel.setClientUserName(customerClientUserDtoReq.getContactName());
		newCustomerClientUserModel.setClientUserMobile(customerClientUserDtoReq.getContactPhone());
		newCustomerClientUserModel.setEnable(customerClientUserDtoReq.getEnable());
		
		super.update(customerClientUserModel, newCustomerClientUserModel);
		
		return DtoResult.success();
	}
	
	@Transactional
	public DtoResult saveCustomerEmployee(CustomerClientUserDtoReq customerClientUserDtoReq) {
		DtoResult result = new DtoResult();
		
		//验证新手机号是否存在
		UcClientUserModel clientUserModel = new UcClientUserModel();
		clientUserModel.setMobile(customerClientUserDtoReq.getContactPhone());
		clientUserModel.setEnable(true);
		List<UcClientUserModel> userList = ucClientUserService.query(clientUserModel);
		
		if(!userList.isEmpty()) {
			clientUserModel = userList.get(0);
			/*
			 * 该手机号已经是user，需要判断该手机号是否与customer有关系，若存在正常关系，则不允许添加为该客户员工
			 */
			UcCustomerClientUserMappingModel customerClientUserModel =  new UcCustomerClientUserMappingModel();
			customerClientUserModel.setClientUserMobile(customerClientUserDtoReq.getContactPhone());
			customerClientUserModel.setEnable(true);
			List<UcCustomerClientUserMappingModel> customerClientUserMappingList = super.query(customerClientUserModel);
			if(!customerClientUserMappingList.isEmpty()) {
				result.setCode(ResultCode.USER_MOBILE_EXIST.getCode());
				result.setMsg(ResultCode.USER_MOBILE_EXIST.getDesc());
				return result;
			}
		}else {
			clientUserModel.setName(customerClientUserDtoReq.getContactName());
			clientUserModel.setEnable(true);
			clientUserModel.setSource(CustomerSourceEnums.SYSTEM.getValue());
			
			ucClientUserService.save(Arrays.asList(clientUserModel));
		}
		
		
		//添加customer与user的关系
		UcCustomerClientUserMappingModel customerClientUserModel =  new UcCustomerClientUserMappingModel();
		customerClientUserModel.setClientUserMobile(customerClientUserDtoReq.getContactPhone());
		customerClientUserModel.setClientUserName(customerClientUserDtoReq.getContactName());
		customerClientUserModel.setCustomerId(customerClientUserDtoReq.getCustomerId());
		customerClientUserModel.setEnable(true);
		customerClientUserModel.setClientUserId(clientUserModel.getId());
		customerClientUserModel.setSource(CustomerSourceEnums.SYSTEM.getValue());
		
		super.save(Arrays.asList(customerClientUserModel));
		
		//建立关系
		UcClientUserRoleModel clientUserRoleModel = new UcClientUserRoleModel();
		clientUserRoleModel.setUserId(clientUserModel.getId());
		clientUserRoleModel.setRoleId(customerClientUserDtoReq.getRoleId());
		clientUserRoleModel.setEnable(true);
		ucClientUserRoleService.save(Arrays.asList(clientUserRoleModel));
		
		return DtoResult.success();
	}
	
	@Transactional
	public DtoResult removeCustomerEmployee(CustomerClientUserUpdateDtoReq customerClientUserDtoReq) {
		ucCustomerClientUserMappingDao.removeCustomerClientUserMapping(customerClientUserDtoReq.getCustomerClientUserId());
		
		UcClientUserModel oldClientUserModel = new UcClientUserModel();
		oldClientUserModel.setId(customerClientUserDtoReq.getClientUserId());
		
		UcClientUserModel newClientUserModel = new UcClientUserModel();
		newClientUserModel.setEnable(false);
		
		ucClientUserService.update(oldClientUserModel, newClientUserModel);
		
		UcClientUserRoleModel oleClientUserRoleModel = new UcClientUserRoleModel();
		oleClientUserRoleModel.setUserId(customerClientUserDtoReq.getClientUserId());
		UcClientUserRoleModel newClientUserRoleModel = new UcClientUserRoleModel();
		newClientUserRoleModel.setEnable(false);
		
		ucClientUserRoleService.update(oleClientUserRoleModel, newClientUserRoleModel);
		
		return DtoResult.success();
	}
	
}