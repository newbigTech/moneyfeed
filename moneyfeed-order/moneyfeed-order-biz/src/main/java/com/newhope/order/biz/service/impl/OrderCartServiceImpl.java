package com.newhope.order.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.order.api.bean.OrderCartModel;
import com.newhope.moneyfeed.order.api.dto.request.carts.CartsQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.carts.CartsSkuDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.carts.CartsUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.carts.OrderCartsSkuDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.carts.OrderCartsSkuListDtoResult;
import com.newhope.moneyfeed.order.dal.BaseDao;
import com.newhope.moneyfeed.order.dal.dao.OrderCartDao;
import com.newhope.order.biz.service.OrderCartService;

@Service("OrderCartService")
public class OrderCartServiceImpl extends BaseServiceImpl<OrderCartModel> implements OrderCartService {
	// private final Logger logger =
	// LoggerFactory.getLogger(OrderCartServiceImpl.class);
	@Autowired
	OrderCartDao orderCartDao;

	protected BaseDao<OrderCartModel> getDao() {
		return orderCartDao;
	}

	@Override
	public OrderCartsSkuListDtoResult getCarts(CartsQueryDtoReq dtoReq) {

		OrderCartsSkuListDtoResult result = new OrderCartsSkuListDtoResult();
		result.setCode(ResultCode.SUCCESS.getCode());
		OrderCartModel queryModel = new OrderCartModel();
		BeanUtils.copyProperties(dtoReq, queryModel);
		List<OrderCartModel> carts = query(queryModel);
		if (CollectionUtils.isEmpty(carts)) {
			return result;
		}
		List<OrderCartsSkuDtoResult> cartsResult = new ArrayList<OrderCartsSkuDtoResult>(carts.size());
		for (OrderCartModel cart : carts) {
			OrderCartsSkuDtoResult cartResult = new OrderCartsSkuDtoResult();
			BeanUtils.copyProperties(cart, cartResult);
			cartsResult.add(cartResult);
		}
		result.setCarts(cartsResult);
		return result;
	}

	@Override
	@Transactional
	public DtoResult updateCarts(CartsUpdateDtoReq dtoReq) {
		
		//防止全表删除
		if( dtoReq.getUcShopId() == null && StringUtils.isBlank(dtoReq.getCustomerNo())){
			DtoResult.fail(ResultCode.PARAM_ERROR);
		}
		// 全量删除
		OrderCartModel delModel = new OrderCartModel();
		delModel.setUcShopId(dtoReq.getUcShopId());
		delModel.setCustomerNo(dtoReq.getCustomerNo());
		remove(delModel);

		// 批量增加
		if (CollectionUtils.isNotEmpty(dtoReq.getItems())) {
			List<OrderCartModel> addList = new ArrayList<OrderCartModel>(dtoReq.getItems().size());
			for (CartsSkuDtoReq sku : dtoReq.getItems()) {
				OrderCartModel cart = new OrderCartModel();
				cart.setCount(sku.getQuantity());
				cart.setPcProcuctId(sku.getSkuId());
				cart.setBuyerId(dtoReq.getBuyerId());
				cart.setCustomerNo(dtoReq.getCustomerNo());
				cart.setUcShopId(dtoReq.getUcShopId());
				addList.add(cart);
			}
			save(addList);
		}

		return DtoResult.success();
	}

}
