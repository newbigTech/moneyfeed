package com.newhope.order.biz.service;

import com.newhope.moneyfeed.order.api.bean.OrderDetailModel;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderDetailDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderDetailDtoResult;

/**
 * Create by yyq on 2018/11/19
 */
public interface OrderDetailService extends BaseService<OrderDetailModel>{
    
    
    /**
     * @Author     ：yyq
     * @Date       ：Created in  2018/11/19 0019 17:02
     * @Param      ：
     */
    OrderDetailDtoResult queryOrderDetail(OrderDetailDtoReq orderDetailDtoReq);

}
