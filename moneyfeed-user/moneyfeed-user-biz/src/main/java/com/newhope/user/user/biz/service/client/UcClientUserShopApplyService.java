package com.newhope.user.user.biz.service.client;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserShopApplyModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcClientUserShopApplyExModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerExModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserShopApplyQueryReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserShopApplyDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserShopApplyDtoResult;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcClientUserShopApplyDao;
import com.newhope.user.user.biz.service.BaseService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 客户申请
 * @author guoxueliang
 *
 */
@Service
public class UcClientUserShopApplyService extends BaseService<UcClientUserShopApplyModel> {

    @Autowired
    private UcClientUserShopApplyDao clientUserShopApplyDao;
    
    @Autowired
    private UcCustomerService ucCustomerService;
    
    @Autowired
    private UcClientShopService ucClientShopService;

    @Override
    protected BaseDao<UcClientUserShopApplyModel> getDao() {
        return clientUserShopApplyDao;
    }

	public ClientUserShopApplyDtoListResult queryUserShopApply(ClientUserShopApplyQueryReq userShopApplyQueryReq) {
		ClientUserShopApplyDtoListResult result = new ClientUserShopApplyDtoListResult();
		
		//对String类型的属性值做特殊字符转义
//		ReflectUtil.resetAttributeValue(userShopApplyQueryReq);
		PageList<UcClientUserShopApplyExModel> pageList = null;
		PageBounds pageBounds = null;
		if(null != userShopApplyQueryReq.getPage() && null != userShopApplyQueryReq.getPageSize()) {
			pageBounds = new PageBounds(userShopApplyQueryReq.getPage().intValue(), userShopApplyQueryReq.getPageSize());
			pageList = clientUserShopApplyDao.queryUserShopApplyList(userShopApplyQueryReq, pageBounds);
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
			pageList = clientUserShopApplyDao.queryUserShopApplyList(userShopApplyQueryReq);
		}
		
		if(CollectionUtils.isNotEmpty(pageList)) {
			ClientUserShopApplyDtoResult dtoResult = null;
			for (UcClientUserShopApplyExModel ucClientUserShopApplyExModel : pageList) {
				dtoResult = new ClientUserShopApplyDtoResult();
				BeanUtils.copyProperties(ucClientUserShopApplyExModel, dtoResult);
				dtoResult.setAuditStatus(ucClientUserShopApplyExModel.getStatus());
				dtoResult.setApplyId(ucClientUserShopApplyExModel.getId());
				if(null != dtoResult.getClientUserId()) {
					List<String> shopNameList = new ArrayList<>();
					List<String> shopCodeList = new ArrayList<>();
					List<UcCustomerExModel> customerList = ucCustomerService.queryUserCustomerMapping(dtoResult.getClientUserId());
					if(CollectionUtils.isNotEmpty(customerList)) {
						for (UcCustomerExModel ucCustomerExModel : customerList) {
							List<UcShopModel> shopList = ucClientShopService.queryCustomerMappingShop(ucCustomerExModel.getId(), null); 
							if(CollectionUtils.isNotEmpty(shopList)) {
								for (UcShopModel ucShopModel : shopList) {
									if(!shopCodeList.contains(ucShopModel.getCode())) {
										shopNameList.add(ucShopModel.getName());
										shopCodeList.add(ucShopModel.getCode());
									}
								}
							}
						}
						
					}
					dtoResult.setShopNameList(shopNameList);
				}
				
				result.getUserShopApplyList().add(dtoResult);
			}
		}
        	
		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
	}
}