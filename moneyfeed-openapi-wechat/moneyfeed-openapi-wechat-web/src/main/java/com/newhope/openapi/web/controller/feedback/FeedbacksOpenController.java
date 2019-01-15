package com.newhope.openapi.web.controller.feedback;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.rest.feedback.FeedbacksOpenApi;
import com.newhope.openapi.api.vo.request.feedback.FeedbackReq;
import com.newhope.openapi.api.vo.request.feedback.ShopPageReq;
import com.newhope.openapi.api.vo.response.feedback.FeedbackShopListResult;
import com.newhope.openapi.biz.service.feedback.FeedbacksService;

@RestController
public class FeedbacksOpenController extends AbstractController implements FeedbacksOpenApi{
	
	@Autowired
	FeedbacksService feedbacksService;
	
	@Override
	public BaseResponse<Result> feedbackReqAdd(@RequestBody FeedbackReq req) {
		Result result = new Result();
		result = feedbacksService.feedbackReqAdd(req);
		return buildJson(result);
	}
	
	@Override
	public BaseResponse<FeedbackShopListResult> queryShopList(@RequestBody ShopPageReq req) {
		FeedbackShopListResult result = new FeedbackShopListResult();
		result = feedbacksService.queryShopList(req);
		return buildJson(result);
	}

}
