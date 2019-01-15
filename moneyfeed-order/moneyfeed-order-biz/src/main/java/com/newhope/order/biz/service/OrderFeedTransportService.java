package com.newhope.order.biz.service;

import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.order.api.bean.OrderFeedTransportModel;
import com.newhope.moneyfeed.order.api.dto.request.feedtransport.OrderFeedTransportModifyDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.feedtransport.OrderFeedTransportQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderTransportListDtoResult;

/**
 * 拉料信息服务
 * @author hp
 *
 */
@Service
public interface OrderFeedTransportService extends BaseService<OrderFeedTransportModel>{


	/**
	 * 修改拉料信息
	 * @param dtoReq
	 * @return
	 */
	DtoResult modifyFeedTransport(OrderFeedTransportModifyDtoReq dtoReq);
	
	/**  
	* @Title: queryFeedTransport  
	* @Description: 查询拉料信息
	*/
	public OrderTransportListDtoResult queryFeedTransport(OrderFeedTransportQueryDtoReq dtoReq);

}
