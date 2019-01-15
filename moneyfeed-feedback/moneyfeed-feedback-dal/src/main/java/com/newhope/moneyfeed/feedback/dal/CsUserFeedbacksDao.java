package com.newhope.moneyfeed.feedback.dal;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.newhope.moneyfeed.feedback.api.bean.CsUserFeedbacksModel;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackPageDtoReq;
import com.newhope.moneyfeed.feedback.api.exbean.CsUserFeedbacksExModel;

public interface CsUserFeedbacksDao extends BaseDao<CsUserFeedbacksModel> {

	List<CsUserFeedbacksExModel> queryFeedbackList(FeedbackPageDtoReq req, PageBounds pageBounds);

	Integer queryFeedbackCount(FeedbackPageDtoReq req);
	
	CsUserFeedbacksExModel queryFeedbackById(Long id);
}