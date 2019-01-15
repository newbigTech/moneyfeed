package com.newhope.moneyfeed.feedback.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.feedback.biz.service.FeedbacksService;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackDistributedDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackPageDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackStatusDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.ShopPageDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.UserPageDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackDetailDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackShopListDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackUserListDtoResult;
import com.newhope.moneyfeed.feedback.api.enums.ChannelEnum;
import com.newhope.moneyfeed.feedback.api.enums.FeedbackStatusEnum;
import com.newhope.moneyfeed.feedback.api.enums.MessageLabelTypeEnum;
import com.newhope.moneyfeed.feedback.api.rest.FeedbacksApi;

@RestController
public class FeedbacksController extends AbstractController implements FeedbacksApi{
	private static final Logger logger = LoggerFactory.getLogger(FeedbacksController.class);
	
	@Autowired
	FeedbacksService feedbacksService;
	
	@Override
	public BaseResponse<DtoResult> feedbackReqAdd(@RequestBody FeedbackDtoReq dtoReq) {
		DtoResult result = new DtoResult();
		if(dtoReq.getFeedbackTime() == null 
				|| (StringUtils.isEmpty(dtoReq.getOfficialAccountsMobile()) 
						&& ChannelEnum.OFFICIAL_ACCOUNTS.getCode().equals(dtoReq.getChannel()))
				|| !(MessageLabelTypeEnum.SHOP_TYPE.getCode().equals(dtoReq.getMessageLabelType()) ||
						MessageLabelTypeEnum.SYSTEM_TYPE.getCode().equals(dtoReq.getMessageLabelType()))) { 
			result.setCode(ResultCode.PARAM_ERROR.getCode());
			result.setMsg(ResultCode.PARAM_ERROR.getDesc());
		}else {
			result = feedbacksService.feedbackReqAdd(dtoReq);
		}
		return buildJson(result);
	}



	@Override
	public BaseResponse<FeedbackDtoResult> feedbackList(@RequestBody FeedbackPageDtoReq dtoReq) {
		FeedbackDtoResult result = feedbacksService.feedbackList(dtoReq);
		return buildJson(result);
	}


//	@Override
//	public BaseResponse<DtoResult> feedbackExport(FeedbackPageDtoReq dtoReq, HttpServletResponse response) {
//		DtoResult result = feedbacksService.feedbackExport(dtoReq , response);
//		return buildJson(result.getCode(), result.getMsg(), null);
//	}


	@Override
	public BaseResponse<DtoResult> feedbackStatusModify(@RequestBody FeedbackStatusDtoReq dtoReq) {
		DtoResult result = new DtoResult();
		if(!FeedbackStatusEnum.SHOP_CLOSED.getCode().equals(dtoReq.getFeedbackStatus()) || dtoReq.getFeedbackStatus() == null) { 
			result.setCode(ResultCode.PARAM_ERROR.getCode());
			result.setMsg(ResultCode.PARAM_ERROR.getDesc());
		}else {
			result = feedbacksService.feedbackStatusModify(dtoReq);
		}
		return buildJson(result);
	}
	

	@Override
	public BaseResponse<DtoResult> feedbackDistributedModify(@RequestBody FeedbackDistributedDtoReq dtoReq) {
		DtoResult result = new DtoResult();
		if((dtoReq.getShopId() == null && FeedbackStatusEnum.DISTRIBUTED.getCode().equals(dtoReq.getFeedbackStatus()))
				|| (FeedbackStatusEnum.PLATFORM_CLOSED.getCode().equals(dtoReq.getFeedbackStatus()) && dtoReq.getSendWechatMsg() == null)
				|| !(FeedbackStatusEnum.PLATFORM_CLOSED.getCode().equals(dtoReq.getFeedbackStatus()) ||
						 FeedbackStatusEnum.DISTRIBUTED.getCode().equals(dtoReq.getFeedbackStatus()))) { 
			result.setCode(ResultCode.PARAM_ERROR.getCode());
			result.setMsg(ResultCode.PARAM_ERROR.getDesc());
		}else {
			result = feedbacksService.feedbackDistributedModify(dtoReq);
		}
		return buildJson(result);
	}


	@Override
	public BaseResponse<FeedbackDetailDtoResult> feedbackDetail(@PathVariable("id")  Long id) {
		FeedbackDetailDtoResult result = new FeedbackDetailDtoResult();
		if(id == null) { 
			result.setCode(ResultCode.PARAM_ERROR.getCode());
			result.setMsg(ResultCode.PARAM_ERROR.getDesc());
		}else {
			result = feedbacksService.feedbackDetail(id);
		}
		return buildJson(result);
	}


	@Override
	public BaseResponse<FeedbackUserListDtoResult> queryUserList(@RequestBody UserPageDtoReq dtoReq) {
		FeedbackUserListDtoResult result = feedbacksService.queryUserList(dtoReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<FeedbackShopListDtoResult> queryShopList(@RequestBody ShopPageDtoReq dtoReq) {
		FeedbackShopListDtoResult result = feedbacksService.queryShopList(dtoReq);
		return buildJson(result);
	}

}
