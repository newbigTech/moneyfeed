package com.newhope.user.user.biz.service.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserModel;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserRoleModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerAccountModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerClientUserMappingModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerModel;
import com.newhope.moneyfeed.user.api.bean.client.UcEbsCustomerModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerMappingModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerPropertyModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.dto.request.client.EbsCustomerListPostDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.EbsCustomerPostDtoReq;
import com.newhope.moneyfeed.user.api.enums.client.AllowOnlineBusinessEnums;
import com.newhope.moneyfeed.user.api.enums.client.BillShowRuleEnums;
import com.newhope.moneyfeed.user.api.enums.client.CreditBalanceShowRuleEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerAccountTypeEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerPropertyTypeEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerSourceEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerTypeEnums;
import com.newhope.moneyfeed.user.api.enums.client.DaybookOnlineShowRuleEnums;
import com.newhope.moneyfeed.user.api.enums.client.MonthBillShowRuleEnums;
import com.newhope.moneyfeed.user.api.enums.client.OrderCalculateRuleEnums;
import com.newhope.moneyfeed.user.api.enums.platform.ManagerRoleCodeEnums;
import com.newhope.moneyfeed.user.api.enums.platform.SysSourceTypeEnums;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcEbsCustomerDao;
import com.newhope.user.user.biz.service.BaseService;
import com.newhope.user.user.biz.service.platform.UcPmRoleService;

@Service
public class UcEbsCustomerService extends BaseService<UcEbsCustomerModel> {

	@Autowired
	UcEbsCustomerDao ucEbsCustomerDao;

	@Autowired
	UcCustomerService ucCustomerService;

	@Autowired
	UcCustomerClientUserMappingService customerUserMappingService;

	@Autowired
	UcCustomerAccountService ucCustomerAccountService;
	
	@Autowired
	UcShopCustomerMappingService shopCustomerMappingService;
	
	@Autowired
	UcShopCustomerPropertyService shopCustomerPropertyService;
	
	@Autowired
	UcPmRoleService ucPmRoleService;
	
	@Autowired
	UcClientUserRoleService ucClientUserRoleService;
	
	@Autowired
	UcClientUserService userService;
	
	@Autowired
	UcClientShopService shopService;
	
	@Override
	protected BaseDao<UcEbsCustomerModel> getDao() {
		return ucEbsCustomerDao;
	}

	public DtoResult modifyEbsCustomerList(EbsCustomerListPostDtoReq dtoReq) {
		System.out.println(System.currentTimeMillis());
		DtoResult result = new DtoResult();
		List<UcEbsCustomerModel> cutomerList = new ArrayList<UcEbsCustomerModel>();
		//查询client管理员权限
		UcPmRoleModel roleParam = new UcPmRoleModel();
		roleParam.setCode(ManagerRoleCodeEnums.CLIENT.getValue());
		roleParam.setSourceType(SysSourceTypeEnums.CLIENT.name());
		roleParam.setEnable(true);
		List<UcPmRoleModel> roleList = ucPmRoleService.query(roleParam);
		for (EbsCustomerPostDtoReq dto : dtoReq.getDataList()) {
			UcEbsCustomerModel cutomer = new UcEbsCustomerModel();
			cutomer.setCustomerCardNum(dto.getCustomerCardNum());
			cutomer.setCustomerType(dto.getCustomerType());
			cutomer.setContactName(dto.getContactName());
			cutomer.setContactPhone(dto.getContactPhone());
			cutomer.setCustomerAddr(dto.getCustomerAddr());
			cutomer.setCustomerAsName(dto.getCustomerAsName());
			cutomer.setCustomerName(dto.getCustomerName());
			cutomer.setCustomerNum(dto.getCustomerNum());
			cutomer.setOrgId(dto.getOrgId());
			cutomer.setOrgName(dto.getOrgName());
			cutomer.setContactId(dto.getContactId());
			cutomer.setEnable(dto.getEnable());
			UcEbsCustomerModel oldModel = new UcEbsCustomerModel();
			oldModel.setCustomerNum(dto.getCustomerNum());
			oldModel.setOrgId(dto.getOrgId());
			oldModel.setContactId(dto.getContactId());
			if (!update(oldModel, cutomer)) {
				cutomerList.add(cutomer);
			}
		}
		List<UcCustomerClientUserMappingModel> mappingList = new ArrayList<UcCustomerClientUserMappingModel>();
		if (CollectionUtils.isNotEmpty(cutomerList)) {
			// 新增用户 创建用户客户关系
			Map<String, UcShopModel> shopMap = new HashMap<String, UcShopModel>();
			for (UcEbsCustomerModel customer : cutomerList) {
				//先判断对应ebsorg是否已经建成商户
				if(!shopMap.containsKey(customer.getOrgId())){
					UcShopModel shopParam = new UcShopModel();
					shopParam.setEbsOrgId(customer.getOrgId());
					List<UcShopModel> shopList = shopService.query(shopParam);
					if(CollectionUtils.isEmpty(shopList)){
						continue;
					}
					shopMap.put(customer.getOrgId(), shopList.get(0));
				}
				//生成客户
				UcCustomerModel ucCustomer = new UcCustomerModel();
				ucCustomer.setCardNum(customer.getCustomerCardNum());
				if(CustomerTypeEnums.PERSON.getDesc().equals(customer.getCustomerType())){
					ucCustomer.setType(CustomerTypeEnums.PERSON.getValue());
				}
				if(CustomerTypeEnums.ENTERPRISE.getDesc().equals(customer.getCustomerType())){
					ucCustomer.setType(CustomerTypeEnums.ENTERPRISE.getValue());
				}
				ucCustomer.setEbsCustomerNum(customer.getCustomerNum());
				ucCustomer.setShortName(customer.getCustomerAsName());
				ucCustomer.setName(customer.getCustomerName());
				ucCustomer.setEnable(true);
				UcCustomerModel oldUcCustomer = new UcCustomerModel();
				oldUcCustomer.setEbsCustomerNum(customer.getCustomerNum());
				List<UcCustomerModel> oldUcCustomerList = ucCustomerService.query(oldUcCustomer);
				if(!CollectionUtils.isEmpty(oldUcCustomerList)){
					ucCustomer.setId(oldUcCustomerList.get(0).getId());
					ucCustomerService.update(oldUcCustomer, ucCustomer);
					handleShopCustomer(shopMap.get(customer.getOrgId()).getId(), oldUcCustomerList.get(0).getId());
				}else{
					ucCustomerService.save(Arrays.asList(ucCustomer));
					handleShopCustomer(shopMap.get(customer.getOrgId()).getId(), ucCustomer.getId());
					//创建默认账户
					createCustomerAccount(ucCustomer.getId());
					
				}
				// 创建客户用户关系
				if (StringUtils.isNotBlank(customer.getContactPhone())) {
					UcCustomerClientUserMappingModel mapping = new UcCustomerClientUserMappingModel();
					mapping.setCustomerId(ucCustomer.getId());
					mapping.setClientUserMobile(customer.getContactPhone());
					mapping.setClientUserName(customer.getContactName());
					mapping.setEnable(true);
					mapping.setSource(CustomerSourceEnums.EBS.getValue());
					//查询客户是否存在 如果存在反填数据
					UcClientUserModel userParam = new UcClientUserModel();
					userParam.setMobile(customer.getContactPhone());
					List<UcClientUserModel> userList = userService.query(userParam);
					if(CollectionUtils.isNotEmpty(userList)){
						mapping.setClientUserId(userList.get(0).getId());
						UcClientUserModel oldUser = new UcClientUserModel();
						oldUser.setId(userList.get(0).getId());
						UcClientUserModel newUser = new UcClientUserModel();
						newUser.setName(customer.getContactName());
						userService.update(oldUser, newUser);
					}else{
						//创建用户
						UcClientUserModel newUser = new UcClientUserModel();
						newUser.setName(customer.getContactName());
						newUser.setEnable(true);
						newUser.setMobile(customer.getContactPhone());
						newUser.setSource(CustomerSourceEnums.EBS.getValue());
						userService.save(Arrays.asList(newUser));
						mapping.setClientUserId(newUser.getId());
						//创建角色
						if(CollectionUtils.isNotEmpty(roleList)){
							UcClientUserRoleModel newUserRole = new UcClientUserRoleModel();
							newUserRole.setEnable(true);
							newUserRole.setRoleId(roleList.get(0).getId());
							newUserRole.setUserId(newUser.getId());
							ucClientUserRoleService.save(Arrays.asList(newUserRole));
						}
					}
					UcCustomerClientUserMappingModel oldmapping = new UcCustomerClientUserMappingModel();
					oldmapping.setCustomerId(ucCustomer.getId());
					oldmapping.setClientUserMobile(customer.getContactPhone());
					oldmapping.setSource(CustomerSourceEnums.EBS.getValue());
					if (!customerUserMappingService.update(oldmapping, mapping)) {
						mappingList.add(mapping);
					}
				}
			}
			if(CollectionUtils.isNotEmpty(mappingList)){
				customerUserMappingService.save(mappingList);
			}
			save(cutomerList);
		}
		System.out.println(System.currentTimeMillis());
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return result;
	}
	
	/**
	 * //创建客户额度，银行卡账户
	 * @param customerId
	 */
	public void createCustomerAccount(Long customerId){
		UcCustomerAccountModel bankAccount = new UcCustomerAccountModel();
		bankAccount.setAccountType(CustomerAccountTypeEnums.BANK.name());
		bankAccount.setCustomerId(customerId);
		bankAccount.setEnable(true);
		UcCustomerAccountModel creditAccount = new UcCustomerAccountModel();
		creditAccount.setAccountType(CustomerAccountTypeEnums.CREDIT.name());
		creditAccount.setCustomerId(customerId);
		creditAccount.setEnable(true);
		ucCustomerAccountService.save(Arrays.asList(bankAccount,creditAccount));
	}
	
	/**
	 * 操作商户客户关系 如果没有建立关系 初始化建立规则
	 * @param shopId
	 * @param customerId
	 */
	public void handleShopCustomer(Long shopId,Long customerId){
		UcShopCustomerMappingModel mapping = new UcShopCustomerMappingModel();
		mapping.setShopId(shopId);
		mapping.setCustomerId(customerId);
		mapping.setEnable(true);
		UcShopCustomerMappingModel oldmapping = new UcShopCustomerMappingModel();
		oldmapping.setShopId(shopId);
		oldmapping.setCustomerId(customerId);
		if(!shopCustomerMappingService.update(oldmapping, mapping)){
			shopCustomerMappingService.save(Arrays.asList(mapping));
			CustomerPropertyTypeEnums[] propertyArry = CustomerPropertyTypeEnums.values();
			List<UcShopCustomerPropertyModel> propertyList = new ArrayList<UcShopCustomerPropertyModel>();
			for(CustomerPropertyTypeEnums enums:propertyArry){
				UcShopCustomerPropertyModel property = new UcShopCustomerPropertyModel();
				property.setMappingId(mapping.getId());
				property.setEnable(true);
				property.setName(enums.name());
				property.setDetail(enums.getDesc());
				if(CustomerPropertyTypeEnums.ALLOW_ONLINE_BUSINESS.name().equals(enums.name())){
					property.setValue(AllowOnlineBusinessEnums.UN_ALLOW.getValue());
				}
				if(CustomerPropertyTypeEnums.CREDIT_BALANCE_SHOW_RULE.name().equals(enums.name())){
					property.setValue(CreditBalanceShowRuleEnums.SHOW_GROOS_DETAIL.getValue());
				}
				if(CustomerPropertyTypeEnums.DAYBOOK_ONLINE_SHOW_RULE.name().equals(enums.name())){
					property.setValue(DaybookOnlineShowRuleEnums.SHOW.getValue());
				}
				if(CustomerPropertyTypeEnums.ORDER_CALCULATE_RULE.name().equals(enums.name())){
					property.setValue(OrderCalculateRuleEnums.SYETEM_CALCULATE.getValue());
				}
				if(CustomerPropertyTypeEnums.BILL_SHOW_RULE.name().equals(enums.name())){
					property.setValue(BillShowRuleEnums.SHOW_PANDECT_DETAIL.getValue());
				}
				if(CustomerPropertyTypeEnums.MONTH_BILL_SHOW_RULE.name().equals(enums.name())){
					property.setValue(MonthBillShowRuleEnums.SHOW.getValue());
				}
				propertyList.add(property);
			}
			shopCustomerPropertyService.save(propertyList);
		}
	}
}