package com.newhope.moneyfeed.order.web.controller;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderRuleUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderRuleDetailDtoResult;
import com.newhope.moneyfeed.order.api.rest.OrderRuleAPI;
import com.newhope.order.biz.service.OrderRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 16:41
 */
@RestController
public class OrderRuleController extends AbstractController implements OrderRuleAPI{
    @Autowired
    OrderRuleService orderRuleService;

    @Override
    public BaseResponse<OrderRuleDetailDtoResult> queryOrderRuleDetail(@RequestBody OrderRuleQueryDtoReq requestBody) {
        OrderRuleDetailDtoResult result = orderRuleService.queryOrderRuleDetail(requestBody);

        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> editOrderRule(@RequestBody OrderRuleUpdateDtoReq dto) {
        DtoResult result = orderRuleService.editOrderRule(dto);
        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> saveOrderRule(@RequestBody OrderRuleDtoReq dto) {
        DtoResult result = orderRuleService.saveOrderRule(dto);
        return buildJson(result);
    }
}
