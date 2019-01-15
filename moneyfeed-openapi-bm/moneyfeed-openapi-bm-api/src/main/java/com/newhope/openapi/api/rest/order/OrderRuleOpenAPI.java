package com.newhope.openapi.api.rest.order;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.order.OrderRuleQueryReq;
import com.newhope.openapi.api.vo.request.order.OrderRuleReq;
import com.newhope.openapi.api.vo.request.order.OrderRuleUpdateReq;
import com.newhope.openapi.api.vo.response.order.OrderRuleDetailResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ApiOperation(value="编辑订单规则", notes="编辑订单规则", protocols="http")
    @RequestMapping(value = "/order/rule", method = RequestMethod.PUT, consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<Result> editOrderRule(@RequestBody OrderRuleUpdateReq dto);

    @ApiOperation(value="新增订单规则", notes="新增订单规则", protocols="http")
    @RequestMapping(value = "/order/rule", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<Result> saveOrderRule(@RequestBody OrderRuleReq dto);
}
