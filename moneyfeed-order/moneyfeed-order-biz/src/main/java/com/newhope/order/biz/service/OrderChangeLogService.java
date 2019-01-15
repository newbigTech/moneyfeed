package com.newhope.order.biz.service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.order.api.bean.OrderChangeLogModel;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderLogDtoReq;

/**
 * Create by yyq on 2018/11/19
 */
public interface OrderChangeLogService extends BaseService<OrderChangeLogModel>{

    /**
     * @Author     ：yyq
     * @Date       ：Created in  2018/11/19 0019 17:06
     * @Param      ：
     */
    DtoResult addOrderChangeLog(OrderLogDtoReq orderLogDtoReq);
}
