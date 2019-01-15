package com.newhope.openapi.biz.service.customer;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.newhope.moneyfeed.common.constant.CommonConstant;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.common.util.ExportUtil;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderListDtoResult;
import com.newhope.moneyfeed.order.api.enums.OrderStatusTypeEnum;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerPropertyModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerExModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserShopApplyQueryReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerClientUserUpdateDtoReq;
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
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerLabelListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopCustomerPropertyListResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmLabelDtoResult;
import com.newhope.moneyfeed.user.api.enums.client.AllotCustomerStatusEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerPropertyTypeEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerSourceEnums;
import com.newhope.moneyfeed.user.api.enums.client.CustomerUserActivateEnums;
import com.newhope.openapi.api.vo.request.customer.AllotIntentionCustomerReq;
import com.newhope.openapi.api.vo.request.customer.CustomerClientUserUpdateReq;
import com.newhope.openapi.api.vo.request.customer.CustomerContactQueryReq;
import com.newhope.openapi.api.vo.request.customer.CustomerQueryReq;
import com.newhope.openapi.api.vo.request.customer.CustomerUpdateReq;
import com.newhope.openapi.api.vo.request.customer.IntentionCustomerQueryReq;
import com.newhope.openapi.api.vo.request.customer.ModifyIntentionCustomerMsg;
import com.newhope.openapi.api.vo.request.customer.UpdateCustomerPropertyReq;
import com.newhope.openapi.api.vo.response.customer.CustomerContactListResult;
import com.newhope.openapi.api.vo.response.customer.CustomerContactResult;
import com.newhope.openapi.api.vo.response.customer.CustomerLabelListResult;
import com.newhope.openapi.api.vo.response.customer.CustomerListResult;
import com.newhope.openapi.api.vo.response.customer.CustomerPropertyResult;
import com.newhope.openapi.api.vo.response.customer.CustomerResult;
import com.newhope.openapi.api.vo.response.customer.IntentionCustomerListResult;
import com.newhope.openapi.api.vo.response.customer.IntentionCustomerResult;
import com.newhope.openapi.api.vo.response.label.LabelResult;
import com.newhope.openapi.biz.common.BizConstant;
import com.newhope.openapi.biz.rpc.feign.customer.CustomerFeignClient;
import com.newhope.openapi.biz.rpc.feign.customer.EbsCustomerBillFeignClient;
import com.newhope.openapi.biz.rpc.feign.order.OrderFeignClient;
import com.newhope.openapi.biz.rpc.feign.shop.ShopApplyFeignClient;
import com.newhope.openapi.biz.rpc.feign.shop.ShopFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.BaseUserFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.ClientUserFeginClient;
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
	
	@Autowired
	ClientUserFeginClient clientUserFeginClient;
	
	@Autowired
	BaseUserFeignClient baseUserFeignClient;
	
	@Autowired
	OrderFeignClient orderFeignClient;
	
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
		
		CustomerQueryDtoReq customerQueryDtoReq = new CustomerQueryDtoReq();
		BeanUtils.copyProperties(customerQueryReq, customerQueryDtoReq);
		BaseResponse<CustomerDtoListResult> feignResp = customerFeignClient.queryCustomerAllList(customerQueryDtoReq);
		
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
			customerResult.setEnable(dtoResult.getEnable());
			customerResult.setLabels(this.getCustomerLabel(dtoResult.getId()).getLabels());
			/*customerResult.setLabels(this.getCustomerLabel(dtoResult.getId()).getLabels());
			BaseResponse<CustomerDtoResult> lastTimeFeign = customerFeignClient.getCustomerLastLoginTime(customerResult.getId());
			if(lastTimeFeign.isSuccess()) {
				customerResult.setLastLoginTime(lastTimeFeign.getData().getLastLoginTime());
			}
			
			CustomerResult  customer = this.getCustomerInfo(customerResult.getId());
			customerResult.setCustomerAddr(customer.getCustomerAddr());*/
			
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
		customerQueryReq.setPage(null);
		customerQueryReq.setPageSize(null);
		CustomerListResult customerListResult = this.queryCustomerList(customerQueryReq);
		
		List<Map<String, Object>> customerList = new ArrayList<Map<String,Object>>();
		if (CollectionUtils.isNotEmpty(customerListResult.getCustomers())) {
			Map<String, Object> map = null;
			for (CustomerResult customer : customerListResult.getCustomers()) {
				map = new HashMap<String, Object>();
				map.put("customerName", StringUtils.isBlank(customer.getCustomerName()) ? "" : customer.getCustomerName());
				map.put("shortName", StringUtils.isBlank(customer.getShortName()) ? "" : customer.getShortName());
				map.put("customerNum", StringUtils.isBlank(customer.getCustomerNum()) ? "" : customer.getCustomerNum());
				if(CollectionUtils.isNotEmpty(customer.getLabels())) {
					StringBuffer strBuffer = new StringBuffer();
					for (LabelResult label : customer.getLabels()) {
						strBuffer.append(label.getName()).append("，");
					}
					strBuffer.substring(0, strBuffer.lastIndexOf("，") - 1);
					map.put("label", strBuffer.toString());
				}else {
					map.put("label", "");
				}
				if(null == customer.getEnable()) {
					map.put("enable", "");
				}else {
					if(customer.getEnable()) {
						map.put("enable", "启用");
					}else {
						map.put("enable", "禁用");
					}
				}
				map.put("lastLoginTime", customer.getLastLoginTime() == null ? "" : DateUtil.getDateStr(customer.getLastLoginTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
				customerList.add(map);
			}
		}
		
		// map映射key
		String mapKey = "customerName,shortName,customerNum,label,enable,lastLoginTime";
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
	
	
	public CustomerContactListResult queryCustomerEmployeeList(CustomerContactQueryReq customerQueryReq) {
		CustomerContactListResult result = new CustomerContactListResult();
		CustomerContactQueryDtoReq customerContactQueryDtoReq = new CustomerContactQueryDtoReq();
		BeanUtils.copyProperties(customerQueryReq, customerContactQueryDtoReq);
		BaseResponse<CustomerClientUserMappingDtoListResult> feignResp = customerFeignClient.queryCustomerEmployee(customerContactQueryDtoReq);
		
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
	
	public void exportCustomerEmployee(CustomerContactQueryReq customerQueryReq, HttpServletResponse response) {
		CustomerContactQueryDtoReq customerContactQueryDtoReq = new CustomerContactQueryDtoReq();
		BeanUtils.copyProperties(customerQueryReq, customerContactQueryDtoReq);
		customerContactQueryDtoReq.setPage(null);
		customerContactQueryDtoReq.setPageSize(null);
		BaseResponse<CustomerClientUserMappingDtoListResult> feignResp = customerFeignClient.queryCustomerEmployee(customerContactQueryDtoReq);
		
		List<Map<String, Object>> contactList = new ArrayList<Map<String,Object>>();
		if (null != feignResp && CollectionUtils.isNotEmpty(feignResp.getData().getCustomerContacts())) {
			Map<String, Object> map = null;
			for (CustomerClientUserMappingDtoResult dto : feignResp.getData().getCustomerContacts()) {
				map = new HashMap<String, Object>();
				map.put("contactName", StringUtils.isBlank(dto.getContactName()) ? "" : dto.getContactName());
				map.put("customerName", StringUtils.isBlank(dto.getCustomerName()) ? "" : dto.getCustomerName());
				map.put("roleName", StringUtils.isBlank(dto.getRoleName()) ? "" : dto.getRoleName());
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
				if( StringUtils.isBlank(dto.getActivated())) {
					map.put("activated", "");
				}else {
					if(CustomerUserActivateEnums.ACTIVATED == CustomerUserActivateEnums.valueOf(dto.getActivated())) {
						map.put("activated", CustomerUserActivateEnums.ACTIVATED.getDesc());
					}
					if(CustomerUserActivateEnums.NOT_ACTIVE == CustomerUserActivateEnums.valueOf(dto.getActivated())) {
						map.put("activated", CustomerUserActivateEnums.NOT_ACTIVE.getDesc());
					}
				}
				
				contactList.add(map);
			}
		}
		
		// map映射key
		String mapKey = "contactName,customerName,roleName,contactPhone,source,gmtCreated,enable,activated";
		try {
			final OutputStream os = response.getOutputStream();
			ExportUtil.responseSetProperties(BizConstant.TITLE_EXPORT_CUSTOMER_EMPLOYEE, response);
			ExportUtil.doExport(contactList, BizConstant.COLUMNS_EXPORT_CUSTOMER_EMPLOYEE,
					BizConstant.CSV_COLUMN_SEPARATOR, BizConstant.CSV_RN, mapKey, os);
		} catch (Exception e) {
			logger.error("[CustomerService.exportCustomerEmployee]:下载文件异常", e);
		}
		
	}

	public Result updateCustomerEmployee(CustomerClientUserUpdateReq customerClientUserReq) {
		Result result = new Result();
		
		boolean sendMsg = false;
		//获取员工信息
		CustomerContactQueryDtoReq customerContactQueryDtoReq = new CustomerContactQueryDtoReq();
		customerContactQueryDtoReq.setCustomerClientUserId(customerClientUserReq.getCustomerClientUserId());
		BaseResponse<CustomerClientUserMappingDtoListResult> infoFeignResp = customerFeignClient.queryCustomerEmployee(customerContactQueryDtoReq);
		
		if(infoFeignResp.isSuccess() && CollectionUtils.isNotEmpty(infoFeignResp.getData().getCustomerContacts())
				&& null != infoFeignResp.getData().getCustomerContacts().get(0).getRoleId()
				&& infoFeignResp.getData().getCustomerContacts().get(0).getRoleId().intValue() != customerClientUserReq.getRoleId().intValue()) {
			sendMsg = true;
		}
		
		CustomerClientUserUpdateDtoReq queryParam = new CustomerClientUserUpdateDtoReq();
		BeanUtils.copyProperties(customerClientUserReq, queryParam);
		BaseResponse<DtoResult> feignResp = customerFeignClient.updateCustomerEmployee(queryParam);
		
		//如果更改为离职状态，或者角色变更需删除缓存
		if(!customerClientUserReq.getEnable() || sendMsg) {
			
			UserRemoveDtoReq userRemoveDtoReq = new UserRemoveDtoReq();
			userRemoveDtoReq.setUserIds(Arrays.asList(customerClientUserReq.getClientUserId()));
			userRemoveDtoReq.setThirdSource(CommonConstant.HTTP_HEADER_SOURCE_WECHAT);
			baseUserFeignClient.removeLoginInfo(userRemoveDtoReq);
		}
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		return result;
	} 
	
	public CustomerLabelListResult getCustomerLabel(Long customerId) {
		CustomerLabelListResult result = new CustomerLabelListResult();
		
		BaseResponse<CustomerLabelListDtoResult> feignResp = customerFeignClient.getCustomerLabel(customerId);
		if(feignResp.isSuccess() && null != feignResp.getData() && CollectionUtils.isNotEmpty(feignResp.getData().getLabels())) {
			List<LabelResult> labels = new ArrayList<>();
			LabelResult labelResult = null;
			for (UcPmLabelDtoResult cPmLabelDtoResult : feignResp.getData().getLabels()) {
				labelResult = new LabelResult();
				BeanUtils.copyProperties(cPmLabelDtoResult, labelResult);
				labels.add(labelResult);
			}
			result.setLabels(labels);
		}
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		return result;
	}

	public Result updateCustomerLabel(CustomerUpdateReq customerUpdateReq) {
		Result result = new Result();
		if(null != customerUpdateReq.getEnable() && !customerUpdateReq.getEnable()) {
			//禁用客户，验证是否有未完成订单
			OrderInfoQueryDtoReq orderInfoQueryDtoReq = new OrderInfoQueryDtoReq();
			orderInfoQueryDtoReq.setCustomerId(customerUpdateReq.getCustomerId());
			List<String> statusList = new ArrayList<>();
			statusList.add(OrderStatusTypeEnum.TO_BE_PAID.getValue());
			statusList.add(OrderStatusTypeEnum.TO_PULL_MATERIAL.getValue());
			orderInfoQueryDtoReq.setStatusList(statusList);
			BaseResponse<OrderListDtoResult> orderFeignResp = orderFeignClient.queryOrderInfoList(orderInfoQueryDtoReq);
			if(orderFeignResp.isSuccess() && null != orderFeignResp.getData() && CollectionUtils.isNotEmpty(orderFeignResp.getData().getOrderList())) {
				result.setCode(ResultCode.CUSTOMER_ORDER_UNCONFIRMED.getCode());
				result.setMsg(ResultCode.CUSTOMER_ORDER_UNCONFIRMED.getDesc());
				return result;
			}
		}
		ModifyCustomerDtoReq modifyCustomerDtoReq = new ModifyCustomerDtoReq();
		modifyCustomerDtoReq.setComment(customerUpdateReq.getComment());
		modifyCustomerDtoReq.setEnable(customerUpdateReq.getEnable());
		modifyCustomerDtoReq.setId(customerUpdateReq.getCustomerId());
		modifyCustomerDtoReq.setLabelIds(customerUpdateReq.getLabelIds());
		BaseResponse<DtoResult> feignResp = customerFeignClient.modifyCustomer(modifyCustomerDtoReq);
		
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		return result;
	}

	public CustomerResult getCustomerInfo(Long customerId) {
		CustomerResult result = new CustomerResult();
		BaseResponse<CustomerDtoResult> feignResp = customerFeignClient.getCustomerInfo(customerId);
		if(feignResp.isSuccess() && null != feignResp.getData()) {
			BeanUtils.copyProperties(feignResp.getData(), result);
		}
		
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		return result;
	}
	
	
}
