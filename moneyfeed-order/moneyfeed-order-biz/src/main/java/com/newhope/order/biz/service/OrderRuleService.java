package com.newhope.order.biz.service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.order.api.bean.OrderRuleModel;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderRuleDetailDtoResult;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 16:43
 */
public interface OrderRuleService extends BaseService<OrderRuleModel>{

    /**
     * 查询订单规则详情
     *
     * @author: dongql
     * @date: 2018/11/19 16:51
     * @param req
     * @return: com.newhope.moneyfeed.order.api.dto.response.order.OrderRuleDetailDtoResult
     */
    OrderRuleDetailDtoResult queryOrderRuleDetail(OrderRuleQueryDtoReq req);

    /**
     * 修改订单规则
     *
     * @author: dongql
     * @date: 2018/11/19 17:02
     * @param dto
     * @return: void
     */
    DtoResult editOrderRule(OrderRuleUpdateDtoReq dto) throws BizException;

    /**
     * 新增订单规则
     * @param dto
     * @return
     * @throws BizException
     */
    DtoResult saveOrderRule(OrderRuleDtoReq dto) ;
}
