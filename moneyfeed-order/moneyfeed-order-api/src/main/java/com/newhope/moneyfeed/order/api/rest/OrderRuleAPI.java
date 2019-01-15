package com.newhope.moneyfeed.order.api.rest;

import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleDtoReq;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderRuleDetailDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 16:35
 */
@Api(value = "OrderRuleAPI", description = "orderRule", protocols = "http")
@RequestMapping(value="/oc")
public interface OrderRuleAPI {
    @ApiOperation(value="查询订单规则详情", notes="查询订单规则详情", protocols="http")
    @RequestMapping(value = "/order/rule/detail", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<OrderRuleDetailDtoResult> queryOrderRuleDetail(@RequestBody OrderRuleQueryDtoReq requestBody);

    @ApiOperation(value="编辑订单规则", notes="编辑订单规则", protocols="http")
    @RequestMapping(value = "/order/rule", method = RequestMethod.PUT, consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<DtoResult> editOrderRule(@RequestBody OrderRuleUpdateDtoReq dto);

    @ApiOperation(value="新增订单规则", notes="新增订单规则", protocols="http")
    @RequestMapping(value = "/order/rule", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<DtoResult> saveOrderRule(@RequestBody OrderRuleDtoReq dto);
}
