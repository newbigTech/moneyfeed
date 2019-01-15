package com.newhope.user.user.biz.service.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerMappingModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.enums.platform.ShopStatusEnums;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcShopDao;
import com.newhope.user.user.biz.service.BaseService;


@Service
public class UcClientShopService extends BaseService<UcShopModel> {

    @Autowired
    private UcShopDao shopDao;

    @Autowired
   	UcShopCustomerMappingService shopCustomerMappingService;
    
    @Override
    protected BaseDao<UcShopModel> getDao() {
        return shopDao;
    }

    public List<UcShopModel> searchShop(ShopQueryDtoReq queryParam) {
		Long start = null;
		if (queryParam.getPage() != null && queryParam.getPageSize() != null) {
			start = (queryParam.getPage() - 1) * queryParam.getPageSize();
		}
		return shopDao.searchShop(queryParam, start, queryParam.getPageSize());
	}

	public Long searchShopCount(ShopQueryDtoReq queryParam) {
		return shopDao.searchShopCount(queryParam);
	}
	
	public List<UcShopModel> queryCustomerMappingShop(Long customerId,String status) {
		UcShopCustomerMappingModel mappingParam = new UcShopCustomerMappingModel();
		mappingParam.setCustomerId(customerId);
		mappingParam.setEnable(true);
		List<UcShopCustomerMappingModel> mappingList = shopCustomerMappingService.query(mappingParam);
		if (CollectionUtils.isNotEmpty(mappingList)) {
			List<Long> shopIds = new ArrayList<Long>();
			for (UcShopCustomerMappingModel mapping : mappingList) {
				shopIds.add(mapping.getShopId());
			}
			ShopQueryDtoReq shopDtoReq = new ShopQueryDtoReq();
			shopDtoReq.setIds(shopIds);
			shopDtoReq.setStatus(status);
			shopDtoReq.setEnable(true);
			List<UcShopModel> dataList = searchShop(shopDtoReq);
			return dataList;
		}
		return null;
	}
}