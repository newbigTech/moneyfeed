package com.newhope.moneyfeed.feedback.api.dto.response;

import java.io.Serializable;
import java.util.List;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;

public class FeedbackDtoResult extends PageDtoResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<FeedbackItemDtoResult>  feedbackItemList;

	public List<FeedbackItemDtoResult> getFeedbackItemList() {
		return feedbackItemList;
	}

	public void setFeedbackItemList(List<FeedbackItemDtoResult> feedbackItemList) {
		this.feedbackItemList = feedbackItemList;
	}
	
	
}
