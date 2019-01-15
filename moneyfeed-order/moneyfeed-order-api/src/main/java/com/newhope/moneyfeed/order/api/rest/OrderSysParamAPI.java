package com.newhope.moneyfeed.order.api.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.dto.request.base.OrderSysParamQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.base.OrderSysParamListDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderRuleDetailDtoResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "OrderSysParamAPI", description = "OrderSysParam", protocols = "http")
@RequestMapping(value="/oc")
public interface OrderSysParamAPI {
    @ApiOperation(value="查询订单系统参数", notes="查询订单系统参数", protocols="http")
    @RequestMapping(value = "/order/sys/params", method = RequestMethod.POST, consumes = {"application/json"}, produces={"application/json"})
    BaseResponse<OrderSysParamListDtoResult> getSysParam(@RequestBody OrderSysParamQueryDtoReq requestBody);

}
