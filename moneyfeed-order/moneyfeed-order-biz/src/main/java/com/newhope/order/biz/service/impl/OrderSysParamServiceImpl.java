package com.newhope.order.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.order.api.bean.OrderSysParamModel;
import com.newhope.moneyfeed.order.api.dto.request.base.OrderSysParamQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.base.OrderSysParamDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.base.OrderSysParamListDtoResult;
import com.newhope.moneyfeed.order.dal.BaseDao;
import com.newhope.moneyfeed.order.dal.dao.OrderSysParamDao;
import com.newhope.order.biz.service.OrderSysParamService;

@Service("OrderSysParamService")
public class OrderSysParamServiceImpl extends BaseServiceImpl<OrderSysParamModel> implements OrderSysParamService{

	@Autowired
	OrderSysParamDao orderSysParamDao;
	@Override
	protected BaseDao<OrderSysParamModel> getDao() {
		return orderSysParamDao;
	}
	@Override
	public OrderSysParamListDtoResult getSysParam(OrderSysParamQueryDtoReq dtoReq) {
		OrderSysParamListDtoResult result = new OrderSysParamListDtoResult();
		OrderSysParamModel queryModel = new OrderSysParamModel();
		BeanUtils.copyProperties(dtoReq, queryModel);
		List<OrderSysParamModel> params = query(queryModel);
		if(CollectionUtils.isNotEmpty(params)){
			List<OrderSysParamDtoResult> paramsDto = new ArrayList<OrderSysParamDtoResult>(params.size());
			for(OrderSysParamModel param :params){
				OrderSysParamDtoResult paramDto = new OrderSysParamDtoResult();
				BeanUtils.copyProperties(param, paramDto);
				paramsDto.add(paramDto);
			}
			result.setParams(paramsDto);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		return result;
	}

}
