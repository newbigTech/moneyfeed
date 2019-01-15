package com.newhope.openapi.web.controller.feedback;

import javax.servlet.http.HttpServletResponse;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.rest.feedback.FeedbacksOpenApi;
import com.newhope.openapi.api.vo.request.feedback.FeedbackDistributedReq;
import com.newhope.openapi.api.vo.request.feedback.FeedbackPageReq;
import com.newhope.openapi.api.vo.request.feedback.FeedbackStatusReq;
import com.newhope.openapi.api.vo.request.feedback.ShopPageReq;
import com.newhope.openapi.api.vo.request.feedback.UserPageReq;
import com.newhope.openapi.api.vo.response.feedback.FeedbackDetailResult;
import com.newhope.openapi.api.vo.response.feedback.FeedbackResult;
import com.newhope.openapi.api.vo.response.feedback.FeedbackShopListResult;
import com.newhope.openapi.api.vo.response.feedback.FeedbackUserListResult;
import com.newhope.openapi.biz.service.feedback.FeedbacksService;

@RestController
public class FeedbacksOpenController extends AbstractController implements FeedbacksOpenApi{
	
	@Autowired
	FeedbacksService feedbacksService;
	

	@Override
	public BaseResponse<FeedbackResult> feedbackList(@RequestBody FeedbackPageReq req) {
		FeedbackResult result = feedbacksService.feedbackList(req);
		return buildJson(result);
	}


	@Override
	public BaseResponse<Result> feedbackExport(FeedbackPageReq req, HttpServletResponse response) {
		Result result = feedbacksService.feedbackExport(req , response);
		return buildJson(result);
	}


	@Override
	public BaseResponse<Result> feedbackStatusModify(@RequestBody FeedbackStatusReq req) {
		Result result = new Result();
		result = feedbacksService.feedbackStatusModify(req);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> feedbackDistributedModify(@RequestBody FeedbackDistributedReq req) {
		Result result = new Result();
		result = feedbacksService.feedbackDistributedModify(req);
		return buildJson(result);
	}

	@Override
	public BaseResponse<FeedbackDetailResult> feedbackDetail(@PathVariable("id")  Long id) {
		FeedbackDetailResult result = new FeedbackDetailResult();
		result = feedbacksService.feedbackDetail(id);
		return buildJson(result);
	}


	@Override
	public BaseResponse<FeedbackUserListResult> queryUserList(@RequestBody UserPageReq req) {
		FeedbackUserListResult result = new FeedbackUserListResult();
		result = feedbacksService.queryUserList(req);
		return buildJson(result);
	}


	@Override
	public BaseResponse<FeedbackShopListResult> queryShopList(@RequestBody ShopPageReq req) {
		FeedbackShopListResult result = new FeedbackShopListResult();
		result = feedbacksService.queryShopList(req);
		return buildJson(result);
	}


}
