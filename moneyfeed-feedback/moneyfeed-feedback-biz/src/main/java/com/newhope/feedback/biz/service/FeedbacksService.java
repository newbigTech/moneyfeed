package com.newhope.feedback.biz.service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
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

public interface FeedbacksService {
	
	DtoResult feedbackReqAdd(FeedbackDtoReq dtoReq);
    
	FeedbackDtoResult feedbackList(FeedbackPageDtoReq dtoReq);
	
//	DtoResult feedbackExport(FeedbackPageDtoReq dtoReq , HttpServletResponse response);
     
	DtoResult feedbackStatusModify(FeedbackStatusDtoReq dtoReq);
    
	FeedbackDetailDtoResult feedbackDetail(Long id);

	DtoResult feedbackDistributedModify(FeedbackDistributedDtoReq dtoReq);

	FeedbackUserListDtoResult queryUserList(UserPageDtoReq dtoReq);

	FeedbackShopListDtoResult queryShopList(ShopPageDtoReq dtoReq);

}
