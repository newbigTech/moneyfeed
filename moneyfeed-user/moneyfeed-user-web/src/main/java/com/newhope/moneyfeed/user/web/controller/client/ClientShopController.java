package com.newhope.moneyfeed.user.web.controller.client;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerMappingModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopCustomerPropertyModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.dto.request.client.HandleEbsCustomerVisitDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopCustomerPropertyQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.UpdateShopCustomerPropertyReq;
import com.newhope.moneyfeed.user.api.dto.request.client.UserShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopCustomerPropertyListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopDtoListResult;
import com.newhope.moneyfeed.user.api.rest.client.ShopAPI;
import com.newhope.moneyfeed.user.web.controller.AbstractController;
import com.newhope.user.user.biz.service.businessmanage.BmUserService;
import com.newhope.user.user.biz.service.client.UcClientShopService;
import com.newhope.user.user.biz.service.client.UcShopCustomerMappingService;
import com.newhope.user.user.biz.service.client.UcShopCustomerPropertyService;

@RestController
public class ClientShopController extends AbstractController implements ShopAPI {

    @Autowired
    private UcClientShopService shopService;

    @Autowired
	UcShopCustomerMappingService shopCustomerMappingService;
    
    @Autowired
    UcShopCustomerPropertyService shopCustomerPropertyService;
    
    @Autowired
    BmUserService bmUserService;
    
    @Override
    public BaseResponse<DtoResult> handleEbsCustomerVisit(@RequestBody HandleEbsCustomerVisitDtoReq userDtoReq) {
        return null;
    }

    @Override
    public BaseResponse<ShopDtoListResult> queryShop(@RequestBody ShopQueryDtoReq queryDtoReq) {
    	ShopDtoListResult result = new ShopDtoListResult();
		List<UcShopModel> dataList = shopService.searchShop(queryDtoReq);
		if (CollectionUtils.isNotEmpty(dataList)) {
			result.setShops(dataList);
			result.setPage(queryDtoReq.getPage());
			result.setTotalCount(shopService.searchShopCount(queryDtoReq));
			result.setTotalPage(shopService.pages(result.getTotalCount()));
		} else {
			result.setTotalCount(0L);
			result.setTotalPage(0L);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
    
    }

	@Override
	public BaseResponse<ShopDtoListResult> queryCustomerMappingShop(@RequestParam(value = "customerId", required = true) Long customerId, @RequestParam(value = "status", required = false) String status) {
		ShopDtoListResult result = new ShopDtoListResult();
		result.setShops(shopService.queryCustomerMappingShop(customerId, status));
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<ShopCustomerPropertyListResult> queryShopCustomerProperty(
			@RequestBody ShopCustomerPropertyQueryDtoReq queryDto) {
		ShopCustomerPropertyListResult result = new ShopCustomerPropertyListResult();
		UcShopCustomerPropertyModel queryParam = new UcShopCustomerPropertyModel();
		queryParam.setName(queryDto.getName());
		queryParam.setMappingId(queryDto.getMappingId());
		if(queryDto.getCustomerId()!=null&&queryDto.getShopId()!=null){
			UcShopCustomerMappingModel mappingParam = new UcShopCustomerMappingModel();
			mappingParam.setCustomerId(queryDto.getCustomerId());
			mappingParam.setShopId(queryDto.getShopId());
			mappingParam.setEnable(true);
			List<UcShopCustomerMappingModel> mapping = shopCustomerMappingService.query(mappingParam);
			if(CollectionUtils.isNotEmpty(mapping)){
				queryParam.setMappingId(mapping.get(0).getId());
			}else{
				result.setCode(ResultCode.SUCCESS.getCode());
				result.setMsg(ResultCode.SUCCESS.getDesc());
				return buildJson(result.getCode(), result.getMsg(), result);
			}
		}
		result.setPropertys(shopCustomerPropertyService.query(queryParam));
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> modifyShopCustomerProperty(@RequestBody UpdateShopCustomerPropertyReq updateReq) {
		DtoResult result = shopCustomerPropertyService.modifyShopCustomerProperty(updateReq);
		return buildJson(result.getCode(), result.getMsg(), null);
	}

	@Override
	public BaseResponse<UcBmUserListDtoResult> queryBmUserByShopId(@RequestBody UserShopQueryDtoReq userShopQueryDtoReq) {
		UcBmUserListDtoResult result = bmUserService.queryBmUserByShopId(userShopQueryDtoReq);
		return buildJson(result.getCode(), result.getMsg(), result);
	}

}
