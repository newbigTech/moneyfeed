package com.newhope.user.user.biz.service.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerClientUserMappingModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerModel;
import com.newhope.moneyfeed.user.api.bean.client.UcEbsCustomerModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerMappingModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerExModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmLabelCustomerMappingModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmLabelModel;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ModifyCustomerDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerLabelListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmLabelDtoResult;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcCustomerDao;
import com.newhope.user.user.biz.service.BaseService;
import com.newhope.user.user.biz.service.platform.PmLabelService;


@Service
public class UcCustomerService extends BaseService<UcCustomerModel> {
	
	
	@Autowired
	UcCustomerDao ucCustomerDao;
	
	@Autowired
	UcShopCustomerMappingService shopCustomerMappingService;
	
	@Autowired
	UcCustomerClientUserMappingService customerUserMappingService;
	
	@Autowired
	UcLabelCustomerService ucLabelCustomerService;
	
	@Autowired
	PmLabelService pmLabelService;
	
	@Autowired
	UcShopCustomerMappingService ucShopCustomerMappingService;
	
	@Autowired
	UcClientShopService ucClientShopService;
	
	@Autowired
	UcEbsCustomerService ucEbsCustomerService;
	
	@Override
	protected BaseDao<UcCustomerModel> getDao() {
		return ucCustomerDao;
	}
	
	public CustomerDtoListResult searchCustomer(CustomerQueryDtoReq queryParam) {
		CustomerDtoListResult result = new CustomerDtoListResult();
		
		String tradingStatus = queryParam.getStatus();
		queryParam.setStatus(tradingStatus);//交易状态传值为枚举字符串，包含_，查询时不需要转义
		
		PageList<UcCustomerExModel> pageList = null;
		PageBounds pageBounds = null;
		
		if(null != queryParam.getPage() && null != queryParam.getPageSize()) {
			pageBounds = new PageBounds(queryParam.getPage().intValue(), queryParam.getPageSize());
			pageList = ucCustomerDao.searchCustomer(queryParam, pageBounds);
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
			pageList = ucCustomerDao.searchCustomer(queryParam);
		}
		
		result.setCustomers(pageList);
		
		return result;
	}

	public List<UcCustomerExModel> queryUserCustomerMapping(Long userId) {
		UcCustomerClientUserMappingModel mappingParam = new UcCustomerClientUserMappingModel();
		mappingParam.setClientUserId(userId);
		mappingParam.setEnable(true);
		List<UcCustomerClientUserMappingModel> mappingList = customerUserMappingService.query(mappingParam);
		if (CollectionUtils.isNotEmpty(mappingList)) {
			List<Long> customerIds = new ArrayList<Long>();
			for (UcCustomerClientUserMappingModel mapping : mappingList) {
				customerIds.add(mapping.getCustomerId());
			}
			CustomerQueryDtoReq userDtoReq = new CustomerQueryDtoReq();
			userDtoReq.setIds(customerIds);
			userDtoReq.setEnable(true);
			CustomerDtoListResult customerDtoListResult = searchCustomer(userDtoReq);
			return customerDtoListResult.getCustomers();
		}
		return null;
	}
	
	public List<UcCustomerExModel> queryShopCustomerMapping(Long shopId) {
		UcShopCustomerMappingModel mappingParam = new UcShopCustomerMappingModel();
		mappingParam.setShopId(shopId);
		mappingParam.setEnable(true);
		List<UcShopCustomerMappingModel> mappingList = shopCustomerMappingService.query(mappingParam);
		if (CollectionUtils.isNotEmpty(mappingList)) {
			List<Long> customerIds = new ArrayList<Long>();
			for (UcShopCustomerMappingModel mapping : mappingList) {
				customerIds.add(mapping.getCustomerId());
			}
			CustomerQueryDtoReq userDtoReq = new CustomerQueryDtoReq();
			userDtoReq.setIds(customerIds);
			CustomerDtoListResult customerDtoListResult = searchCustomer(userDtoReq);
			return customerDtoListResult.getCustomers();
		}
		return null;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public DtoResult modifyCustomer(ModifyCustomerDtoReq modifyCustomerDtoReq) {
		DtoResult result = new DtoResult();
		
		UcCustomerModel oldModel = new UcCustomerModel();
		oldModel.setId(modifyCustomerDtoReq.getId());
		UcCustomerModel newModel = new UcCustomerModel();
		newModel.setComment(modifyCustomerDtoReq.getComment());
		newModel.setEnable(modifyCustomerDtoReq.getEnable());
		
		boolean r = this.update(oldModel, newModel);
		if(r) {
			/**
			 * 更改标签信息：先删除原来的标签管理关系，再重新添加
			 */
			UcPmLabelCustomerMappingModel oldMappingModel = new UcPmLabelCustomerMappingModel();
			oldMappingModel.setCustomerId(modifyCustomerDtoReq.getId());
			UcPmLabelCustomerMappingModel newMappingModel = new UcPmLabelCustomerMappingModel();
			newMappingModel.setEnable(false);
			ucLabelCustomerService.update(oldMappingModel, newMappingModel);
			
			if(CollectionUtils.isNotEmpty(modifyCustomerDtoReq.getLabelIds())) {
				List<UcPmLabelCustomerMappingModel> list = new ArrayList<>();
				UcPmLabelCustomerMappingModel ucPmLabelCustomerMappingModel = null;
				for (Long labelId : modifyCustomerDtoReq.getLabelIds()) {
					ucPmLabelCustomerMappingModel = new UcPmLabelCustomerMappingModel();
					ucPmLabelCustomerMappingModel.setCustomerId(modifyCustomerDtoReq.getId());
					ucPmLabelCustomerMappingModel.setLabelId(labelId);
					ucPmLabelCustomerMappingModel.setEnable(true);
					
					list.add(ucPmLabelCustomerMappingModel);
				}
				ucLabelCustomerService.save(list);
			}
			
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		}else {
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}
		return result;
	}
	
	public CustomerLabelListDtoResult getCustomerLabel(Long customerId) {
		CustomerLabelListDtoResult result = new CustomerLabelListDtoResult();
		List<UcPmLabelModel> labelList = pmLabelService.queryLabelByCusomerId(customerId);
		if(CollectionUtils.isNotEmpty(labelList)) {
			List<UcPmLabelDtoResult> labels = new ArrayList<>();
			UcPmLabelDtoResult ucPmLabelDtoResult = null;
			for (UcPmLabelModel ucPmLabelModel : labelList) {
				ucPmLabelDtoResult = new UcPmLabelDtoResult();
				BeanUtils.copyProperties(ucPmLabelModel, ucPmLabelDtoResult);
				
				labels.add(ucPmLabelDtoResult);
			}
			result.setLabels(labels);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return result;
	}
	
	public CustomerDtoResult getCustomerInfo(Long customerId) {
		CustomerDtoResult result = new CustomerDtoResult();
		
		CustomerQueryDtoReq queryParam = new CustomerQueryDtoReq();
		queryParam.setId(customerId);
		PageList<UcCustomerExModel> pageList = ucCustomerDao.queryCustomerList(queryParam);
		if(CollectionUtils.isNotEmpty(pageList)) {
			BeanUtils.copyProperties(pageList.get(0), result);
			result.setCustomerName(pageList.get(0).getName());
			result.setCustomerNum(pageList.get(0).getEbsCustomerNum());
			UcEbsCustomerModel ucEbsCustomerModel = new UcEbsCustomerModel();
			ucEbsCustomerModel.setCustomerNum(result.getCustomerNum());
			ucEbsCustomerModel.setEnable(true);
			List<UcEbsCustomerModel> list = ucEbsCustomerService.query(ucEbsCustomerModel);
			if(CollectionUtils.isNotEmpty(list)) {
				result.setCustomerAddr(list.get(0).getCustomerAddr());
			}
		}
		
		result.setLabels(this.getCustomerLabel(customerId).getLabels());
		
		List<UcShopModel> shopList = ucClientShopService.queryCustomerMappingShop(customerId, null);
		if(CollectionUtils.isNotEmpty(shopList)) {
			List<ShopDtoResult> shops = new ArrayList<>();
			ShopDtoResult shopDtoResult = null;
			for (UcShopModel ucShopModel : shopList) {
				shopDtoResult = new ShopDtoResult();
				shopDtoResult.setShop(ucShopModel);
				shops.add(shopDtoResult);
			}
			result.setShops(shops);
		}
		result.setLabels(this.getCustomerLabel(customerId).getLabels());
		
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return result;
	}
	
	
	public CustomerDtoListResult queryCustomerList(CustomerQueryDtoReq queryParam) {
		CustomerDtoListResult result = new CustomerDtoListResult();
		
		PageList<UcCustomerExModel> pageList = null;
		PageBounds pageBounds = null;
		
		if(null != queryParam.getPage() && null != queryParam.getPageSize()) {
			pageBounds = new PageBounds(queryParam.getPage().intValue(), queryParam.getPageSize());
			pageList = ucCustomerDao.queryCustomerList(queryParam, pageBounds);
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
			pageList = ucCustomerDao.queryCustomerList(queryParam);
		}
		
		result.setCustomers(pageList);
		
		return result;
	}
	
	/**
	 * 获取客户所有员工中最后一次登录时间
	 * @param customerId
	 * @return
	 */
	public CustomerDtoResult getCustomerLastLoginTime(Long customerId) {
		CustomerDtoResult result = new CustomerDtoResult();
		UcCustomerExModel ucCustomerExModel = ucCustomerDao.getCustomerLastLoginTime(customerId);
		if(null != ucCustomerExModel && null != ucCustomerExModel.getLastLoginTime()) {
			result.setLastLoginTime(ucCustomerExModel.getLastLoginTime());
		}
		
		return result;
	}
	
	public CustomerDtoListResult queryCustomerAllList(CustomerQueryDtoReq queryParam) {
		CustomerDtoListResult result = new CustomerDtoListResult();
		queryParam.setEnable(true);
		
		List<UcCustomerExModel> list = ucCustomerDao.queryCustomerAllList(queryParam);
		if(!list.isEmpty()) {
			result.setCustomers(list);
		}
		
		return result;
	}
	
	public CustomerDtoResult getCustomerInfoByUser(Long userId) {
		CustomerDtoResult result = new CustomerDtoResult();
		
		UcCustomerClientUserMappingModel customerClientUserMappingModel = new UcCustomerClientUserMappingModel();
		customerClientUserMappingModel.setClientUserId(userId);
		customerClientUserMappingModel.setEnable(true);
		List<UcCustomerClientUserMappingModel> mappingList =  customerUserMappingService.query(customerClientUserMappingModel);
		if(CollectionUtils.isNotEmpty(mappingList)) {
			UcCustomerModel ucCustomerModel = new UcCustomerModel();
			ucCustomerModel.setId(mappingList.get(0).getCustomerId());
			ucCustomerModel.setEnable(true);
			List<UcCustomerModel> customerList = super.query(ucCustomerModel);
			if(CollectionUtils.isNotEmpty(customerList)) {
				BeanUtils.copyProperties(customerList.get(0), result);
			}
		}
		
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return result;
	}
	
}