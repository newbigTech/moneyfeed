package com.newhope.openapi.api.rest.order;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.vo.request.order.OrderRuleQueryReq;
import com.newhope.openapi.api.vo.response.order.OrderRuleDetailResult;
import com.newhope.openapi.api.vo.response.order.rule.TransportDateRuleResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 19:14
 */
@Api(value = "OrderRuleOpenAPI", description = "订单规则服务open-api", protocols = "http")
public interface OrderRuleOpenAPI {

    @ApiOperation(value="查询订单规则详情", notes="查询订单规则详情", protocols="http")
    @RequestMapping(value = "/order/rule/detail", method = RequestMethod.GET, produces={"application/json"})
    BaseResponse<OrderRuleDetailResult> queryOrderRuleDetail(OrderRuleQueryReq req);
    
    @ApiOperation(value="查询拉料规则日期", notes="查询拉料规则日期", protocols="http")
    @RequestMapping(value = "/order/rule/transportdate", method = RequestMethod.GET, produces={"application/json"})
    BaseResponse<TransportDateRuleResult> getRuleOfTransportDate();
}
