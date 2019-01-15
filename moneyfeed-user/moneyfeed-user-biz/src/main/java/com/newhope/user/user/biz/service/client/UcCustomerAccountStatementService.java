package com.newhope.user.user.biz.service.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerAccountModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerAccountStatementModel;
import com.newhope.moneyfeed.user.api.bean.client.UcCustomerClientUserMappingModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerAccountStatementExModel;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementPostDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementPostListDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementQueryDtoReq;
import com.newhope.moneyfeed.user.api.enums.client.CustomerAccountTypeEnums;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcCustomerAccountStatementDao;
import com.newhope.user.user.biz.service.BaseService;


@Service
public class UcCustomerAccountStatementService extends BaseService<UcCustomerAccountStatementModel> {
	
	
	@Autowired
	UcCustomerAccountStatementDao ucCustomerAccountStatementDao;
	
	@Autowired
	UcCustomerAccountService customerAccountService;
	
	@Autowired
	UcCustomerClientUserMappingService customerMappingService;
	@Override
	protected BaseDao<UcCustomerAccountStatementModel> getDao() {
		return ucCustomerAccountStatementDao;
	}
	
	public PageList<UcCustomerAccountStatementExModel> queryStatementList(CustomerAccountStatementQueryDtoReq queryParam) {
		PageBounds pageBounds = null;
		if(null != queryParam.getPage() && null != queryParam.getPageSize()) {
			pageBounds = new PageBounds(queryParam.getPage().intValue(), queryParam.getPageSize());
			PageList<UcCustomerAccountStatementExModel> pageList = ucCustomerAccountStatementDao.queryStatementList(queryParam, pageBounds);
			return pageList;
		}else{
			PageList<UcCustomerAccountStatementExModel> pageList = ucCustomerAccountStatementDao.queryStatementList(queryParam);
			return pageList;
		}
	}
	
	public void saveStatementList(CustomerAccountStatementPostListDtoReq data){
		if(CollectionUtils.isNotEmpty(data.getDataList())){
			List<UcCustomerAccountStatementModel> saveList = new ArrayList<UcCustomerAccountStatementModel>();
			for(CustomerAccountStatementPostDtoReq dto:data.getDataList()){
				UcCustomerClientUserMappingModel mappingParam = new UcCustomerClientUserMappingModel();
				mappingParam.setClientUserId(dto.getClientUserId());
				mappingParam.setEnable(true);
				List<UcCustomerClientUserMappingModel> mappingList = customerMappingService.query(mappingParam);
				if(CollectionUtils.isEmpty(mappingList)){
					throw new BizException(ResultCode.USER_CUSTOMER_NOT_EXIST.getCode(), ResultCode.USER_CUSTOMER_NOT_EXIST.getDesc());
				}
				UcCustomerAccountStatementModel model = new UcCustomerAccountStatementModel();
				model.setAmount(dto.getAmount());
				model.setBizType(dto.getBizType());
				model.setShopId(dto.getShopId());
				model.setClientUserId(dto.getClientUserId());
				model.setCustomerId(mappingList.get(0).getCustomerId());
				model.setOrderNo(dto.getOrderNo());
				model.setPayOrderNo(dto.getPayOrderNo());
				model.setSerialNumber(dto.getSerialNumber());
				if(PayTypeEnums.CARD_PAY.name().equals(dto.getBizType())){
					UcCustomerAccountModel queryParam = new UcCustomerAccountModel();
					queryParam.setCustomerId(mappingList.get(0).getCustomerId());
					queryParam.setAccountType(CustomerAccountTypeEnums.BANK.name());
					List<UcCustomerAccountModel> queryList = customerAccountService.query(queryParam);
					model.setCustomerAccountId(queryList.get(0).getId());
				}
				if(PayTypeEnums.BAL_PAY.name().equals(dto.getBizType())||
						PayTypeEnums.SO_PAY.name().equals(dto.getBizType())||
						PayTypeEnums.ACC_PAY.name().equals(dto.getBizType())){
					UcCustomerAccountModel queryParam = new UcCustomerAccountModel();
					queryParam.setCustomerId(mappingList.get(0).getCustomerId());
					queryParam.setAccountType(CustomerAccountTypeEnums.CREDIT.name());
					List<UcCustomerAccountModel> queryList = customerAccountService.query(queryParam);
					model.setCustomerAccountId(queryList.get(0).getId());
				}
				saveList.add(model);
			}
			save(saveList);
		}
	}
}