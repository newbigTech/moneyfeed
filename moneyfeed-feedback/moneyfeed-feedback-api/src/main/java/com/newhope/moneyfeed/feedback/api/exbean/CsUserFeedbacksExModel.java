package com.newhope.moneyfeed.feedback.api.exbean;

import com.newhope.moneyfeed.feedback.api.bean.CsUserFeedbackSolvesModel;
import com.newhope.moneyfeed.feedback.api.bean.CsUserFeedbacksModel;

public class CsUserFeedbacksExModel extends CsUserFeedbacksModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CsUserFeedbackSolvesModel csUserFeedbackSolvesModel;

	public CsUserFeedbackSolvesModel getCsUserFeedbackSolvesModel() {
		return csUserFeedbackSolvesModel;
	}

	public void setCsUserFeedbackSolvesModel(CsUserFeedbackSolvesModel csUserFeedbackSolvesModel) {
		this.csUserFeedbackSolvesModel = csUserFeedbackSolvesModel;
	}
	
}