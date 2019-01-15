package com.newhope.openapi.biz.service.customer;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.dto.request.user.UserRemoveDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.AppSourceEnums;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.common.util.ExportUtil;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerPropertyModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerExModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserShopApplyQueryReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerContactQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ModifyCustomerDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ModifyUserShopApplyDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopCustomerPropertyQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.UpdateShopCustomerPropertyReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserShopApplyDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserShopApplyDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerClientUserMappingDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerClientUserMappingDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopCustomerPropertyListResult;
import com.newhope.moneyfeed.user.api.enums.client.AllotCustomerStatusEnums;
import com.newhope.moneyfeed.user.api.enums.client.AllowOnlineBusinessEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerPropertyTypeEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerSourceEnums;
import com.newhope.openapi.api.vo.request.customer.AllotIntentionCustomerReq;
import com.newhope.openapi.api.vo.request.customer.CustomerContactQueryReq;
import com.newhope.openapi.api.vo.request.customer.CustomerQueryReq;
import com.newhope.openapi.api.vo.request.customer.IntentionCustomerQueryReq;
import com.newhope.openapi.api.vo.request.customer.ModifyIntentionCustomerMsg;
import com.newhope.openapi.api.vo.request.customer.UpdateCustomerPropertyReq;
import com.newhope.openapi.api.vo.response.customer.CustomerContactListResult;
import com.newhope.openapi.api.vo.response.customer.CustomerContactResult;
import com.newhope.openapi.api.vo.response.customer.CustomerListResult;
import com.newhope.openapi.api.vo.response.customer.CustomerPropertyResult;
import com.newhope.openapi.api.vo.response.customer.CustomerResult;
import com.newhope.openapi.api.vo.response.customer.IntentionCustomerListResult;
import com.newhope.openapi.api.vo.response.customer.IntentionCustomerResult;
import com.newhope.openapi.biz.common.BizConstant;
import com.newhope.openapi.biz.rpc.feign.customer.CustomerFeignClient;
import com.newhope.openapi.biz.rpc.feign.customer.EbsCustomerBillFeignClient;
import com.newhope.openapi.biz.rpc.feign.shop.ShopApplyFeignClient;
import com.newhope.openapi.biz.rpc.feign.shop.ShopFeignClient;
import com.newhope.openapi.biz.rpc.feign.wechat.WechatUserFeignClient;

@Service
public class CustomerService {
	
	private Logger logger = Logger.getLogger(CustomerService.class);
	
	@Autowired
	CustomerFeignClient customerFeignClient;
	
	@Autowired
	ShopApplyFeignClient shopApplyFeignClient;
	
	@Autowired
	ShopFeignClient shopFeignClient;
	
	@Autowired
    EbsCustomerBillFeignClient customerBillFeignClient;
	
	@Autowired
	WechatUserFeignClient wechatUserFeignClient;
	
	public CustomerContactListResult queryCustomerContactList(CustomerContactQueryReq customerQueryReq) {
		CustomerContactListResult result = new CustomerContactListResult();
		CustomerContactQueryDtoReq customerContactQueryDtoReq = new CustomerContactQueryDtoReq();
		BeanUtils.copyProperties(customerQueryReq, customerContactQueryDtoReq);
		BaseResponse<CustomerClientUserMappingDtoListResult> feignResp = customerFeignClient.queryCustomerContact(customerContactQueryDtoReq);
		
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		if(!feignResp.isSuccess() || null == feignResp.getData() 
				|| CollectionUtils.isEmpty(feignResp.getData().getCustomerContacts())) {
			return result;
		}
		
		List<CustomerContactResult> customerContacts = new ArrayList<CustomerContactResult>();
		CustomerContactResult customerContactResult = null;
		for (CustomerClientUserMappingDtoResult dtoResult : feignResp.getData().getCustomerContacts()) {
			customerContactResult = new CustomerContactResult();
			BeanUtils.copyProperties(dtoResult, customerContactResult);
			customerContacts.add(customerContactResult);
		}
		result.setCustomerContacts(customerContacts);
		
		result.setPage(customerQueryReq.getPage());
        result.setTotalCount(feignResp.getData().getTotalCount());
        result.setTotalPage(feignResp.getData().getTotalPage());
        result.setCode(feignResp.getCode());
        result.setMsg(feignResp.getMsg());
		
		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
	}
	
	public void exportCustomerContact(CustomerContactQueryReq customerQueryReq, HttpServletResponse response) {
		CustomerContactQueryDtoReq customerContactQueryDtoReq = new CustomerContactQueryDtoReq();
		BeanUtils.copyProperties(customerQueryReq, customerContactQueryDtoReq);
		customerContactQueryDtoReq.setPage(null);
		customerContactQueryDtoReq.setPageSize(null);
		BaseResponse<CustomerClientUserMappingDtoListResult> feignResp = customerFeignClient.queryCustomerContact(customerContactQueryDtoReq);
		
		List<Map<String, Object>> contactList = new ArrayList<Map<String,Object>>();
		if (null != feignResp && CollectionUtils.isNotEmpty(feignResp.getData().getCustomerContacts())) {
			Map<String, Object> map = null;
			for (CustomerClientUserMappingDtoResult dto : feignResp.getData().getCustomerContacts()) {
				map = new HashMap<String, Object>();
				map.put("contactName", StringUtils.isBlank(dto.getContactName()) ? "" : dto.getContactName());
				map.put("customerName", StringUtils.isBlank(dto.getCustomerName()) ? "" : dto.getCustomerName());
				map.put("contactPhone", StringUtils.isBlank(dto.getContactPhone()) ? "" : dto.getContactPhone());
				if(StringUtils.isBlank(dto.getSource())) {
					map.put("source", "");
				}else {
					if(CustomerSourceEnums.EBS == CustomerSourceEnums.valueOf(dto.getSource())) {
						map.put("source", CustomerSourceEnums.EBS.getDesc());
					}
					if(CustomerSourceEnums.SYSTEM == CustomerSourceEnums.valueOf(dto.getSource())) {
						map.put("source", CustomerSourceEnums.SYSTEM.getDesc());
					}
				}
				map.put("gmtCreated", null == dto.getGmtCreated() ? "" : DateUtil.getDateStr(dto.getGmtCreated(), DateUtil.YYYY_MM_DD_HH_MM_SS));
				map.put("enable", dto.getEnable() == true ? "正常" : "禁用");
				map.put("shopName", StringUtils.isBlank(dto.getShopName()) ? "" : dto.getShopName());
				
				contactList.add(map);
			}
		}
		
		// map映射key
		String mapKey = "contactName,customerName,contactPhone,source,gmtCreated,enable,shopName";
		try {
			final OutputStream os = response.getOutputStream();
			ExportUtil.responseSetProperties(BizConstant.TITLE_EXPORT_CUSTOMER_CONTACT, response);
			ExportUtil.doExport(contactList, BizConstant.COLUMNS_EXPORT_CUSTOMER_CONTACT,
					BizConstant.CSV_COLUMN_SEPARATOR, BizConstant.CSV_RN, mapKey, os);
		} catch (Exception e) {
			logger.error("[CustomerService.exportCustomerContact]:下载文件异常", e);
		}
		
	}

	public IntentionCustomerListResult queryIntentionCustomerList(
			IntentionCustomerQueryReq intentionCustomerQueryReq) {
		IntentionCustomerListResult result = new IntentionCustomerListResult();
		
		ClientUserShopApplyQueryReq queryParam = new ClientUserShopApplyQueryReq();
		BeanUtils.copyProperties(intentionCustomerQueryReq, queryParam);
		BaseResponse<ClientUserShopApplyDtoListResult> feignResp = customerFeignClient.queryUserShopApply(queryParam);
		
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		if(!feignResp.isSuccess() || null == feignResp.getData() 
				|| CollectionUtils.isEmpty(feignResp.getData().getUserShopApplyList())) {
			return result;
		}
		
		List<IntentionCustomerResult> intentionCustomers = new ArrayList<>();
		IntentionCustomerResult intentionCustomerResult = null;
		for (ClientUserShopApplyDtoResult dtoResult : feignResp.getData().getUserShopApplyList()) {
			intentionCustomerResult = new IntentionCustomerResult(); 
			BeanUtils.copyProperties(dtoResult, intentionCustomerResult);
			intentionCustomers.add(intentionCustomerResult);
		}
		result.setIntentionCustomers(intentionCustomers);
		
		result.setPage(intentionCustomerQueryReq.getPage());
        result.setTotalCount(feignResp.getData().getTotalCount());
        result.setTotalPage(feignResp.getData().getTotalPage());
        result.setCode(feignResp.getCode());
        result.setMsg(feignResp.getMsg());
		
		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
	}
	
	public void exportIntentionCustomer(IntentionCustomerQueryReq intentionCustomerQueryReq, HttpServletResponse response) {
		ClientUserShopApplyQueryReq queryParam = new ClientUserShopApplyQueryReq();
		BeanUtils.copyProperties(intentionCustomerQueryReq, queryParam);
		queryParam.setPage(null);
		queryParam.setPageSize(null);
		BaseResponse<ClientUserShopApplyDtoListResult> feignResp = customerFeignClient.queryUserShopApply(queryParam);
		
		List<Map<String, Object>> customerList = new ArrayList<Map<String,Object>>();
		if (null != feignResp && CollectionUtils.isNotEmpty(feignResp.getData().getUserShopApplyList())) {
			Map<String, Object> map = null;
			for (ClientUserShopApplyDtoResult shopApply : feignResp.getData().getUserShopApplyList()) {
				map = new HashMap<String, Object>();
				map.put("mobile", StringUtils.isBlank(shopApply.getMobile()) ? "" : shopApply.getMobile());
				map.put("name", StringUtils.isBlank(shopApply.getName()) ? "" : shopApply.getName());
				if(StringUtils.isBlank(shopApply.getAuditStatus())) {
					map.put("auditStatus", "");
				}else {
					if(AllotCustomerStatusEnums.ALLOTTED == AllotCustomerStatusEnums.valueOf(shopApply.getAuditStatus())) {
						map.put("auditStatus", AllotCustomerStatusEnums.ALLOTTED.getDesc());
					}
					if(AllotCustomerStatusEnums.ALLOTTING == AllotCustomerStatusEnums.valueOf(shopApply.getAuditStatus())) {
						map.put("auditStatus", AllotCustomerStatusEnums.ALLOTTING.getDesc());
					}
				}
				if(CollectionUtils.isEmpty(shopApply.getShopNameList())) {
					map.put("shopName", "");
				}else {
					StringBuffer bufferStr = new StringBuffer();
					for (String shopName : shopApply.getShopNameList()) {
						bufferStr.append(shopName).append("/");
					}
					bufferStr.deleteCharAt(bufferStr.length() -1);
					map.put("shopName", bufferStr.toString());
				}
				map.put("intentionShopName", StringUtils.isBlank(shopApply.getIntentionShopName()) ? "" : shopApply.getIntentionShopName());
				map.put("allotShopName", StringUtils.isBlank(shopApply.getAllotShopName()) ? "" : shopApply.getAllotShopName());
				map.put("gmtCreated", null == shopApply.getGmtCreated() ? "" : DateUtil.getDateStr(shopApply.getGmtCreated(), DateUtil.YYYY_MM_DD_HH_MM_SS));
				
				
				customerList.add(map);
			}
		}
		
		// map映射key
		String mapKey = "mobile,name,auditStatus,shopName,intentionShopName,allotShopName,gmtCreated";
		try {
			final OutputStream os = response.getOutputStream();
			ExportUtil.responseSetProperties(BizConstant.TITLE_EXPORT_INTENTION_CUSTOMER, response);
			ExportUtil.doExport(customerList, BizConstant.COLUMNS_EXPORT_INTENTION_CUSTOMER,
					BizConstant.CSV_COLUMN_SEPARATOR, BizConstant.CSV_RN, mapKey, os);
		} catch (Exception e) {
			logger.error("[CustomerService.exportIntentionCustomer]:下载文件异常", e);
		}
		
	}
	
	public IntentionCustomerResult queryIntentionCustomerInfo(Long applyId) {
		IntentionCustomerResult result = new IntentionCustomerResult();
		
		ClientUserShopApplyQueryReq queryParam = new ClientUserShopApplyQueryReq();
		queryParam.setUserShopApplyId(applyId);
		queryParam.setPage(null);
		queryParam.setPageSize(null);
		BaseResponse<ClientUserShopApplyDtoListResult> feignResp = customerFeignClient.queryUserShopApply(queryParam);
		
		if(feignResp.isSuccess() && null != feignResp.getData() 
				&& CollectionUtils.isNotEmpty(feignResp.getData().getUserShopApplyList())) {
			BeanUtils.copyProperties(feignResp.getData().getUserShopApplyList().get(0), result);
		}
		
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		return result;
	}
	
	public Result allotIntentionCustomer(
			AllotIntentionCustomerReq allotIntentionCustomerReq) {
		Result result = new Result();
		ModifyUserShopApplyDtoReq modifyUserShopApplyDtoReq = new ModifyUserShopApplyDtoReq();
		BeanUtils.copyProperties(allotIntentionCustomerReq, modifyUserShopApplyDtoReq);
		BaseResponse<DtoResult> feignResp = shopApplyFeignClient.allotIntentionCustomer(modifyUserShopApplyDtoReq);
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		return result;
	}
	
	public Result modifyIntentionCustomerShopDealMsg(
			ModifyIntentionCustomerMsg modifyIntentionCustomerMsg) {
		Result result = new Result();
		ModifyUserShopApplyDtoReq modifyUserShopApplyDtoReq = new ModifyUserShopApplyDtoReq();
		BeanUtils.copyProperties(modifyIntentionCustomerMsg, modifyUserShopApplyDtoReq);
		BaseResponse<DtoResult> feignResp = shopApplyFeignClient.modifyIntentionCustomerShopDealMsg(modifyUserShopApplyDtoReq);
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		return result;
	}
	
	public CustomerListResult queryCustomerList(CustomerQueryReq customerQueryReq){
		CustomerListResult result = new CustomerListResult();
		
		CustomerQueryDtoReq userDtoReq = new CustomerQueryDtoReq();
		BeanUtils.copyProperties(customerQueryReq, userDtoReq);
		BaseResponse<CustomerDtoListResult> feignResp = customerFeignClient.queryCustomerAllList(userDtoReq);
		
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		if(!feignResp.isSuccess() || null == feignResp.getData() 
				|| CollectionUtils.isEmpty(feignResp.getData().getCustomers())) {
			return result;
		}
		
		List<CustomerResult> customers = new ArrayList<>();
		CustomerResult customerResult = null;
		for (UcCustomerExModel dtoResult : feignResp.getData().getCustomers()) {
			customerResult = new CustomerResult(); 
			customerResult.setCustomerName(dtoResult.getName());
			customerResult.setCustomerNum(dtoResult.getEbsCustomerNum());
			customerResult.setGmtCreated(dtoResult.getGmtCreated());
			customerResult.setId(dtoResult.getId());
			customerResult.setShortName(dtoResult.getShortName());
			customerResult.setCardNum(dtoResult.getCardNum());
			customerResult.setComment(dtoResult.getComment());
			customerResult.setShopName(dtoResult.getShopName());
			customerResult.setTradingStatus(dtoResult.getTradingStatus());
			customerResult.setShopId(dtoResult.getShopId());
			
			customers.add(customerResult);
		}
		result.setCustomers(customers);
		
		result.setPage(customerQueryReq.getPage());
        result.setTotalCount(feignResp.getData().getTotalCount());
        result.setTotalPage(feignResp.getData().getTotalPage());
        result.setCode(feignResp.getCode());
        result.setMsg(feignResp.getMsg());
		
		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
	}
	
	public void exportCustomer(CustomerQueryReq customerQueryReq, HttpServletResponse response) {
		CustomerQueryDtoReq userDtoReq = new CustomerQueryDtoReq();
		BeanUtils.copyProperties(customerQueryReq, userDtoReq);
		userDtoReq.setPage(null);
		userDtoReq.setPageSize(null);
		BaseResponse<CustomerDtoListResult> feignResp = customerFeignClient.queryCustomer(userDtoReq);
		
		List<Map<String, Object>> customerList = new ArrayList<Map<String,Object>>();
		if (null != feignResp && CollectionUtils.isNotEmpty(feignResp.getData().getCustomers())) {
			Map<String, Object> map = null;
			for (UcCustomerExModel customerDto : feignResp.getData().getCustomers()) {
				map = new HashMap<String, Object>();
				map.put("shopName", StringUtils.isBlank(customerDto.getShopName()) ? "" : customerDto.getShopName());
				map.put("customerNum", StringUtils.isBlank(customerDto.getEbsCustomerNum()) ? "" : customerDto.getEbsCustomerNum());
				map.put("customerName", StringUtils.isBlank(customerDto.getName()) ? "" : customerDto.getName());
				map.put("shortName", StringUtils.isBlank(customerDto.getShortName()) ? "" : customerDto.getShortName());
				if(StringUtils.isBlank(customerDto.getTradingStatus())) {
					map.put("tradingStatus", "");
				}else {
					if(AllowOnlineBusinessEnums.ALLOW == AllowOnlineBusinessEnums.valueOf(customerDto.getTradingStatus())) {
						map.put("tradingStatus", AllowOnlineBusinessEnums.ALLOW.getDesc());
					}
					if(AllowOnlineBusinessEnums.UN_ALLOW == AllowOnlineBusinessEnums.valueOf(customerDto.getTradingStatus())) {
						map.put("tradingStatus", AllowOnlineBusinessEnums.UN_ALLOW.getDesc());
					}
				}
				customerList.add(map);
			}
		}
		
		// map映射key
		String mapKey = "shopName,customerNum,customerName,shortName,tradingStatus";
		try {
			final OutputStream os = response.getOutputStream();
			ExportUtil.responseSetProperties(BizConstant.TITLE_EXPORT_CUSTOMER, response);
			ExportUtil.doExport(customerList, BizConstant.COLUMNS_EXPORT_CUSTOMER,
					BizConstant.CSV_COLUMN_SEPARATOR, BizConstant.CSV_RN, mapKey, os);
		} catch (Exception e) {
			logger.error("[CustomerService.exportIntentionCustomer]:下载文件异常", e);
		}
	}
	
	public CustomerPropertyResult queryCustomerPropertyInfo(Long customerId, Long shopId) {
		CustomerPropertyResult result = new CustomerPropertyResult();
		
		ShopCustomerPropertyQueryDtoReq queryDto = new ShopCustomerPropertyQueryDtoReq();
		queryDto.setShopId(shopId);
		queryDto.setCustomerId(customerId);
		BaseResponse<ShopCustomerPropertyListResult> feignResp = shopFeignClient.queryShopCustomerProperty(queryDto);
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		if(!feignResp.isSuccess() || null == feignResp.getData() 
				|| CollectionUtils.isEmpty(feignResp.getData().getPropertys())) {
			return result;
		}
		
		for (UcShopCustomerPropertyModel customerPropertyModel : feignResp.getData().getPropertys()) {
			if(CustomerPropertyTypeEnums.ALLOW_ONLINE_BUSINESS == CustomerPropertyTypeEnums.valueOf(customerPropertyModel.getName())) {
				result.setAllowOnlineBusiness(customerPropertyModel.getValue());
			}
			if(CustomerPropertyTypeEnums.ALLOW_OFFLINE_BUSINESS == CustomerPropertyTypeEnums.valueOf(customerPropertyModel.getName())) {
				result.setAllowOfflineBusiness(customerPropertyModel.getValue());
			}
			if(CustomerPropertyTypeEnums.BILL_SHOW_RULE == CustomerPropertyTypeEnums.valueOf(customerPropertyModel.getName())) {
				result.setBillShowRule(customerPropertyModel.getValue());
			}
			if(CustomerPropertyTypeEnums.CREDIT_BALANCE_SHOW_RULE == CustomerPropertyTypeEnums.valueOf(customerPropertyModel.getName())) {
				result.setCreditBalanceShowRule(customerPropertyModel.getValue());
			}
			if(CustomerPropertyTypeEnums.DAYBOOK_ONLINE_SHOW_RULE == CustomerPropertyTypeEnums.valueOf(customerPropertyModel.getName())) {
				result.setDaybookOnlineShowRule(customerPropertyModel.getValue());
			}
			if(CustomerPropertyTypeEnums.MONTH_BILL_SHOW_RULE == CustomerPropertyTypeEnums.valueOf(customerPropertyModel.getName())) {
				result.setMonthBillShowRule(customerPropertyModel.getValue());
			}
			if(CustomerPropertyTypeEnums.ORDER_CALCULATE_RULE == CustomerPropertyTypeEnums.valueOf(customerPropertyModel.getName())) {
				result.setOrdeCalculateRule(customerPropertyModel.getValue());
			}
			result.setMappingId(customerPropertyModel.getMappingId());
		}
		
		return result;
	}
	
	public Result modifyCustomerPropertyInfo(UpdateCustomerPropertyReq updateCustomerPropertyReq) {
		Result result = new Result();
		CustomerPropertyResult customerPropertyResult = this.queryCustomerPropertyInfo(
				updateCustomerPropertyReq.getCustomerId(),updateCustomerPropertyReq.getShopId()); 
		
		if(null != updateCustomerPropertyReq.getMappingId()) {
			
			//用于判断是否对微信端用户缓存更改
			boolean isRemove = false;
			
			Long mappingId = updateCustomerPropertyReq.getMappingId();
			UpdateShopCustomerPropertyReq updateReq = null;
			if(StringUtils.isNotBlank(updateCustomerPropertyReq.getAllowOnlineBusiness())
					&& !customerPropertyResult.getAllowOnlineBusiness().equals(updateCustomerPropertyReq.getAllowOnlineBusiness())) {
				updateReq = new UpdateShopCustomerPropertyReq();
				updateReq.setMappingId(mappingId);
				updateReq.setName(CustomerPropertyTypeEnums.ALLOW_ONLINE_BUSINESS.getValue());
				updateReq.setValue(updateCustomerPropertyReq.getAllowOnlineBusiness());
				
				shopFeignClient.modifyShopCustomerProperty(updateReq);
				isRemove = true;
			}
			if(StringUtils.isNotBlank(updateCustomerPropertyReq.getAllowOfflineBusiness())
					&& !customerPropertyResult.getAllowOfflineBusiness().equals(updateCustomerPropertyReq.getAllowOfflineBusiness())) {
				updateReq = new UpdateShopCustomerPropertyReq();
				updateReq.setMappingId(mappingId);
				updateReq.setName(CustomerPropertyTypeEnums.ALLOW_OFFLINE_BUSINESS.getValue());
				updateReq.setValue(updateCustomerPropertyReq.getAllowOfflineBusiness());
				
				shopFeignClient.modifyShopCustomerProperty(updateReq);
				isRemove = true;
			}
			if(StringUtils.isNotBlank(updateCustomerPropertyReq.getBillShowRule())
					&& !customerPropertyResult.getBillShowRule().equals(updateCustomerPropertyReq.getBillShowRule())) {
				updateReq = new UpdateShopCustomerPropertyReq();
				updateReq.setMappingId(mappingId);
				updateReq.setName(CustomerPropertyTypeEnums.BILL_SHOW_RULE.getValue());
				updateReq.setValue(updateCustomerPropertyReq.getBillShowRule());
				
				shopFeignClient.modifyShopCustomerProperty(updateReq);
				isRemove = true;
			}
			if(StringUtils.isNotBlank(updateCustomerPropertyReq.getCreditBalanceShowRule())
					&& !customerPropertyResult.getCreditBalanceShowRule().equals(updateCustomerPropertyReq.getCreditBalanceShowRule())) {
				updateReq = new UpdateShopCustomerPropertyReq();
				updateReq.setMappingId(mappingId);
				updateReq.setName(CustomerPropertyTypeEnums.CREDIT_BALANCE_SHOW_RULE.getValue());
				updateReq.setValue(updateCustomerPropertyReq.getCreditBalanceShowRule());
				
				shopFeignClient.modifyShopCustomerProperty(updateReq);
				isRemove = true;
			}
			if(StringUtils.isNotBlank(updateCustomerPropertyReq.getDaybookOnlineShowRule())
					&& !customerPropertyResult.getDaybookOnlineShowRule().equals(updateCustomerPropertyReq.getDaybookOnlineShowRule())) {
				updateReq = new UpdateShopCustomerPropertyReq();
				updateReq.setMappingId(mappingId);
				updateReq.setName(CustomerPropertyTypeEnums.DAYBOOK_ONLINE_SHOW_RULE.getValue());
				updateReq.setValue(updateCustomerPropertyReq.getDaybookOnlineShowRule());
				
				shopFeignClient.modifyShopCustomerProperty(updateReq);
				isRemove = true;
			}
			if(StringUtils.isNotBlank(updateCustomerPropertyReq.getMonthBillShowRule())
					&& !customerPropertyResult.getMonthBillShowRule().equals(updateCustomerPropertyReq.getMonthBillShowRule())) {
				updateReq = new UpdateShopCustomerPropertyReq();
				updateReq.setMappingId(mappingId);
				updateReq.setName(CustomerPropertyTypeEnums.MONTH_BILL_SHOW_RULE.getValue());
				updateReq.setValue(updateCustomerPropertyReq.getMonthBillShowRule());
				
				shopFeignClient.modifyShopCustomerProperty(updateReq);
				isRemove = true;
			}
			if(StringUtils.isNotBlank(updateCustomerPropertyReq.getOrdeCalculateRule())
					&& !customerPropertyResult.getOrdeCalculateRule().equals(updateCustomerPropertyReq.getOrdeCalculateRule())) {
				updateReq = new UpdateShopCustomerPropertyReq();
				updateReq.setMappingId(mappingId);
				updateReq.setName(CustomerPropertyTypeEnums.ORDER_CALCULATE_RULE.getValue());
				updateReq.setValue(updateCustomerPropertyReq.getOrdeCalculateRule());
				
				shopFeignClient.modifyShopCustomerProperty(updateReq);
				isRemove = true;
			}
			
			if(isRemove) {//只要有一个配置变更，则对微信端登录用户进行踢出
				List<Long> userIds = new ArrayList<>();//存客户对应的所有联系人Id
				CustomerContactQueryDtoReq customerContactQueryDtoReq = new CustomerContactQueryDtoReq();
				customerContactQueryDtoReq.setShopId(updateCustomerPropertyReq.getShopId());
				customerContactQueryDtoReq.setCustomerId(updateCustomerPropertyReq.getCustomerId());
				BaseResponse<CustomerClientUserMappingDtoListResult> feignResp = customerFeignClient.queryCustomerClientUser(customerContactQueryDtoReq);
				
				if(feignResp.isSuccess() && null != feignResp.getData() 
						&& CollectionUtils.isNotEmpty(feignResp.getData().getCustomerContacts())) {
					for (CustomerClientUserMappingDtoResult userDto : feignResp.getData().getCustomerContacts()) {
						userIds.add(userDto.getClientUserId());
					}
				}
				
				UserRemoveDtoReq userRemoveDtoReq = new UserRemoveDtoReq();
				userRemoveDtoReq.setThirdSource(AppSourceEnums.WECHAT.getValue());
				userRemoveDtoReq.setUserIds(userIds);
				wechatUserFeignClient.removeLoginInfo(userRemoveDtoReq);
			}
			
			//更改客户备注信息
			if(StringUtils.isNotBlank(updateCustomerPropertyReq.getComment())) {
				ModifyCustomerDtoReq modifyCustomerDtoReq = new ModifyCustomerDtoReq();
				modifyCustomerDtoReq.setId(updateCustomerPropertyReq.getCustomerId());
				modifyCustomerDtoReq.setComment(updateCustomerPropertyReq.getComment());
				
				customerFeignClient.modifyCustomer(modifyCustomerDtoReq);
			}
			
		}
		
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return result;
	}
	
	public CustomerContactListResult queryCustomerClientUser(CustomerContactQueryReq customerQueryReq) {
		CustomerContactListResult result = new CustomerContactListResult();
		CustomerContactQueryDtoReq customerContactQueryDtoReq = new CustomerContactQueryDtoReq();
		BeanUtils.copyProperties(customerQueryReq, customerContactQueryDtoReq);
		BaseResponse<CustomerClientUserMappingDtoListResult> feignResp = customerFeignClient.queryCustomerClientUser(customerContactQueryDtoReq);
		
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		if(!feignResp.isSuccess() || null == feignResp.getData() 
				|| CollectionUtils.isEmpty(feignResp.getData().getCustomerContacts())) {
			return result;
		}
		
		List<CustomerContactResult> customerContacts = new ArrayList<CustomerContactResult>();
		CustomerContactResult customerContactResult = null;
		for (CustomerClientUserMappingDtoResult dtoResult : feignResp.getData().getCustomerContacts()) {
			customerContactResult = new CustomerContactResult();
			BeanUtils.copyProperties(dtoResult, customerContactResult);
			customerContacts.add(customerContactResult);
		}
		result.setCustomerContacts(customerContacts);
		
		result.setPage(customerQueryReq.getPage());
		result.setTotalCount(feignResp.getData().getTotalCount());
		result.setTotalPage(feignResp.getData().getTotalPage());
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
	}

	
	
}
