package com.newhope.openapi.api.vo.response.feedback;

import java.io.Serializable;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;

public class FeedbackResult extends PageDtoResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<FeedbackItemResult>  feedbackItemList;

	public List<FeedbackItemResult> getFeedbackItemList() {
		return feedbackItemList;
	}

	public void setFeedbackItemList(List<FeedbackItemResult> feedbackItemList) {
		this.feedbackItemList = feedbackItemList;
	}
	
	
}
