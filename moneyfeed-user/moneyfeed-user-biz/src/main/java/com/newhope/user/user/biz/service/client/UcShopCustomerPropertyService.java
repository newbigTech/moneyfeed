package com.newhope.user.user.biz.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerPropertyModel;
import com.newhope.moneyfeed.user.api.dto.request.client.UpdateShopCustomerPropertyReq;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcShopCustomerPropertyDao;
import com.newhope.user.user.biz.service.BaseService;


@Service
public class UcShopCustomerPropertyService extends BaseService<UcShopCustomerPropertyModel> {
	
	
	@Autowired
	UcShopCustomerPropertyDao ucShopCustomerPropertyDao;
	
	@Override
	protected BaseDao<UcShopCustomerPropertyModel> getDao() {
		return ucShopCustomerPropertyDao;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public DtoResult modifyShopCustomerProperty(UpdateShopCustomerPropertyReq updateReq) {
		DtoResult result = new DtoResult();
		
		UcShopCustomerPropertyModel oldModel = new UcShopCustomerPropertyModel();
		oldModel.setName(updateReq.getName());
		oldModel.setMappingId(updateReq.getMappingId());
		
		UcShopCustomerPropertyModel newModel = new UcShopCustomerPropertyModel();
		newModel.setValue(updateReq.getValue());
		
		boolean r = this.update(oldModel, newModel);
		if(r) {
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg(ResultCode.SUCCESS.getDesc());
		}else {
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg(ResultCode.FAIL.getDesc());
		}
        return result;
	}
	
}