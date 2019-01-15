package com.newhope.openapi.web.controller.order;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.openapi.api.rest.order.OrderRuleOpenAPI;
import com.newhope.openapi.api.vo.request.order.OrderRuleQueryReq;
import com.newhope.openapi.api.vo.response.order.OrderRuleDetailResult;
import com.newhope.openapi.api.vo.response.order.rule.TransportDateRuleResult;
import com.newhope.openapi.biz.service.order.OrderRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: dongql
 * @date: 2018/11/19 16:41
 */
@RestController
public class OrderRuleOpenController extends AbstractController implements OrderRuleOpenAPI {
    @Autowired
    OrderRuleService orderRuleService;


    @Override
    public BaseResponse<OrderRuleDetailResult> queryOrderRuleDetail(OrderRuleQueryReq req) {

        OrderRuleDetailResult result = orderRuleService.queryOrderRuleDetail(req);
        return buildJson(result);
    }


	@Override
	public BaseResponse<TransportDateRuleResult> getRuleOfTransportDate() {
			TransportDateRuleResult result = orderRuleService.getRuleOfTransportDate();
	        return buildJson(result);
	}
    
    
}
