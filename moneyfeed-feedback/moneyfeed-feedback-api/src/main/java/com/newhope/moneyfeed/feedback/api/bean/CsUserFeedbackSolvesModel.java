package com.newhope.moneyfeed.feedback.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.util.Date;

public class CsUserFeedbackSolvesModel extends BaseModel {
    private Long id;

    private Date gmtCreated;

    private Date gmtModified;

    private Long creator;

    private Long modifier;

    private Long feedbackId;

    private Long solveUserId;

    private Date solveTime;

    private String solveDesc;

    private String solveType;

    private Long shopId;

    private String dataStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedback_solves.id
     *
     * @return the value of cs_user_feedback_solves.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedback_solves.id
     *
     * @param id the value for cs_user_feedback_solves.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedback_solves.gmt_created
     *
     * @return the value of cs_user_feedback_solves.gmt_created
     *
     * @mbggenerated
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedback_solves.gmt_created
     *
     * @param gmtCreated the value for cs_user_feedback_solves.gmt_created
     *
     * @mbggenerated
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedback_solves.gmt_modified
     *
     * @return the value of cs_user_feedback_solves.gmt_modified
     *
     * @mbggenerated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedback_solves.gmt_modified
     *
     * @param gmtModified the value for cs_user_feedback_solves.gmt_modified
     *
     * @mbggenerated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedback_solves.creator
     *
     * @return the value of cs_user_feedback_solves.creator
     *
     * @mbggenerated
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedback_solves.creator
     *
     * @param creator the value for cs_user_feedback_solves.creator
     *
     * @mbggenerated
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedback_solves.modifier
     *
     * @return the value of cs_user_feedback_solves.modifier
     *
     * @mbggenerated
     */
    public Long getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedback_solves.modifier
     *
     * @param modifier the value for cs_user_feedback_solves.modifier
     *
     * @mbggenerated
     */
    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedback_solves.feedback_id
     *
     * @return the value of cs_user_feedback_solves.feedback_id
     *
     * @mbggenerated
     */
    public Long getFeedbackId() {
        return feedbackId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedback_solves.feedback_id
     *
     * @param feedbackId the value for cs_user_feedback_solves.feedback_id
     *
     * @mbggenerated
     */
    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedback_solves.solve_user_id
     *
     * @return the value of cs_user_feedback_solves.solve_user_id
     *
     * @mbggenerated
     */
    public Long getSolveUserId() {
        return solveUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedback_solves.solve_user_id
     *
     * @param solveUserId the value for cs_user_feedback_solves.solve_user_id
     *
     * @mbggenerated
     */
    public void setSolveUserId(Long solveUserId) {
        this.solveUserId = solveUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedback_solves.solve_time
     *
     * @return the value of cs_user_feedback_solves.solve_time
     *
     * @mbggenerated
     */
    public Date getSolveTime() {
        return solveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedback_solves.solve_time
     *
     * @param solveTime the value for cs_user_feedback_solves.solve_time
     *
     * @mbggenerated
     */
    public void setSolveTime(Date solveTime) {
        this.solveTime = solveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedback_solves.solve_desc
     *
     * @return the value of cs_user_feedback_solves.solve_desc
     *
     * @mbggenerated
     */
    public String getSolveDesc() {
        return solveDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedback_solves.solve_desc
     *
     * @param solveDesc the value for cs_user_feedback_solves.solve_desc
     *
     * @mbggenerated
     */
    public void setSolveDesc(String solveDesc) {
        this.solveDesc = solveDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedback_solves.solve_type
     *
     * @return the value of cs_user_feedback_solves.solve_type
     *
     * @mbggenerated
     */
    public String getSolveType() {
        return solveType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedback_solves.solve_type
     *
     * @param solveType the value for cs_user_feedback_solves.solve_type
     *
     * @mbggenerated
     */
    public void setSolveType(String solveType) {
        this.solveType = solveType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedback_solves.shop_id
     *
     * @return the value of cs_user_feedback_solves.shop_id
     *
     * @mbggenerated
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedback_solves.shop_id
     *
     * @param shopId the value for cs_user_feedback_solves.shop_id
     *
     * @mbggenerated
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedback_solves.data_status
     *
     * @return the value of cs_user_feedback_solves.data_status
     *
     * @mbggenerated
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedback_solves.data_status
     *
     * @param dataStatus the value for cs_user_feedback_solves.data_status
     *
     * @mbggenerated
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }
}