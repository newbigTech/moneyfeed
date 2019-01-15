package com.newhope.moneyfeed.feedback.api.bean;

import com.newhope.moneyfeed.api.bean.BaseModel;
import java.util.Date;

public class CsUserFeedbacksModel extends BaseModel {
    private Long id;

    private Date gmtCreated;

    private Date gmtModified;

    private Long creator;

    private Long modifier;

    private Long userId;

    private Date feedbackTime;

    private Long orderId;

    private String feedbackNumber;

    private String feedbackDesc;

    private String feedbackStatus;

    private String channel;

    private Long intentionShopId;

    private Long shopId;

    private String distributedDesc;

    private Long distributedCreator;

    private Date distributedCreated;

    private String officialAccountsMobile;

    private String officialAccountsName;

    private String messageLabel;

    private String messageLabelType;

    private String dataStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.id
     *
     * @return the value of cs_user_feedbacks.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.id
     *
     * @param id the value for cs_user_feedbacks.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.gmt_created
     *
     * @return the value of cs_user_feedbacks.gmt_created
     *
     * @mbggenerated
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.gmt_created
     *
     * @param gmtCreated the value for cs_user_feedbacks.gmt_created
     *
     * @mbggenerated
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.gmt_modified
     *
     * @return the value of cs_user_feedbacks.gmt_modified
     *
     * @mbggenerated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.gmt_modified
     *
     * @param gmtModified the value for cs_user_feedbacks.gmt_modified
     *
     * @mbggenerated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.creator
     *
     * @return the value of cs_user_feedbacks.creator
     *
     * @mbggenerated
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.creator
     *
     * @param creator the value for cs_user_feedbacks.creator
     *
     * @mbggenerated
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.modifier
     *
     * @return the value of cs_user_feedbacks.modifier
     *
     * @mbggenerated
     */
    public Long getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.modifier
     *
     * @param modifier the value for cs_user_feedbacks.modifier
     *
     * @mbggenerated
     */
    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.user_id
     *
     * @return the value of cs_user_feedbacks.user_id
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.user_id
     *
     * @param userId the value for cs_user_feedbacks.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.feedback_time
     *
     * @return the value of cs_user_feedbacks.feedback_time
     *
     * @mbggenerated
     */
    public Date getFeedbackTime() {
        return feedbackTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.feedback_time
     *
     * @param feedbackTime the value for cs_user_feedbacks.feedback_time
     *
     * @mbggenerated
     */
    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.order_id
     *
     * @return the value of cs_user_feedbacks.order_id
     *
     * @mbggenerated
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.order_id
     *
     * @param orderId the value for cs_user_feedbacks.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.feedback_number
     *
     * @return the value of cs_user_feedbacks.feedback_number
     *
     * @mbggenerated
     */
    public String getFeedbackNumber() {
        return feedbackNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.feedback_number
     *
     * @param feedbackNumber the value for cs_user_feedbacks.feedback_number
     *
     * @mbggenerated
     */
    public void setFeedbackNumber(String feedbackNumber) {
        this.feedbackNumber = feedbackNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.feedback_desc
     *
     * @return the value of cs_user_feedbacks.feedback_desc
     *
     * @mbggenerated
     */
    public String getFeedbackDesc() {
        return feedbackDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.feedback_desc
     *
     * @param feedbackDesc the value for cs_user_feedbacks.feedback_desc
     *
     * @mbggenerated
     */
    public void setFeedbackDesc(String feedbackDesc) {
        this.feedbackDesc = feedbackDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.feedback_status
     *
     * @return the value of cs_user_feedbacks.feedback_status
     *
     * @mbggenerated
     */
    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.feedback_status
     *
     * @param feedbackStatus the value for cs_user_feedbacks.feedback_status
     *
     * @mbggenerated
     */
    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.channel
     *
     * @return the value of cs_user_feedbacks.channel
     *
     * @mbggenerated
     */
    public String getChannel() {
        return channel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.channel
     *
     * @param channel the value for cs_user_feedbacks.channel
     *
     * @mbggenerated
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.intention_shop_id
     *
     * @return the value of cs_user_feedbacks.intention_shop_id
     *
     * @mbggenerated
     */
    public Long getIntentionShopId() {
        return intentionShopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.intention_shop_id
     *
     * @param intentionShopId the value for cs_user_feedbacks.intention_shop_id
     *
     * @mbggenerated
     */
    public void setIntentionShopId(Long intentionShopId) {
        this.intentionShopId = intentionShopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.shop_id
     *
     * @return the value of cs_user_feedbacks.shop_id
     *
     * @mbggenerated
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.shop_id
     *
     * @param shopId the value for cs_user_feedbacks.shop_id
     *
     * @mbggenerated
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.distributed_desc
     *
     * @return the value of cs_user_feedbacks.distributed_desc
     *
     * @mbggenerated
     */
    public String getDistributedDesc() {
        return distributedDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.distributed_desc
     *
     * @param distributedDesc the value for cs_user_feedbacks.distributed_desc
     *
     * @mbggenerated
     */
    public void setDistributedDesc(String distributedDesc) {
        this.distributedDesc = distributedDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.distributed_creator
     *
     * @return the value of cs_user_feedbacks.distributed_creator
     *
     * @mbggenerated
     */
    public Long getDistributedCreator() {
        return distributedCreator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.distributed_creator
     *
     * @param distributedCreator the value for cs_user_feedbacks.distributed_creator
     *
     * @mbggenerated
     */
    public void setDistributedCreator(Long distributedCreator) {
        this.distributedCreator = distributedCreator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.distributed_created
     *
     * @return the value of cs_user_feedbacks.distributed_created
     *
     * @mbggenerated
     */
    public Date getDistributedCreated() {
        return distributedCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.distributed_created
     *
     * @param distributedCreated the value for cs_user_feedbacks.distributed_created
     *
     * @mbggenerated
     */
    public void setDistributedCreated(Date distributedCreated) {
        this.distributedCreated = distributedCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.official_accounts_mobile
     *
     * @return the value of cs_user_feedbacks.official_accounts_mobile
     *
     * @mbggenerated
     */
    public String getOfficialAccountsMobile() {
        return officialAccountsMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.official_accounts_mobile
     *
     * @param officialAccountsMobile the value for cs_user_feedbacks.official_accounts_mobile
     *
     * @mbggenerated
     */
    public void setOfficialAccountsMobile(String officialAccountsMobile) {
        this.officialAccountsMobile = officialAccountsMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.official_accounts_name
     *
     * @return the value of cs_user_feedbacks.official_accounts_name
     *
     * @mbggenerated
     */
    public String getOfficialAccountsName() {
        return officialAccountsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.official_accounts_name
     *
     * @param officialAccountsName the value for cs_user_feedbacks.official_accounts_name
     *
     * @mbggenerated
     */
    public void setOfficialAccountsName(String officialAccountsName) {
        this.officialAccountsName = officialAccountsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.message_label
     *
     * @return the value of cs_user_feedbacks.message_label
     *
     * @mbggenerated
     */
    public String getMessageLabel() {
        return messageLabel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.message_label
     *
     * @param messageLabel the value for cs_user_feedbacks.message_label
     *
     * @mbggenerated
     */
    public void setMessageLabel(String messageLabel) {
        this.messageLabel = messageLabel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.message_label_type
     *
     * @return the value of cs_user_feedbacks.message_label_type
     *
     * @mbggenerated
     */
    public String getMessageLabelType() {
        return messageLabelType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.message_label_type
     *
     * @param messageLabelType the value for cs_user_feedbacks.message_label_type
     *
     * @mbggenerated
     */
    public void setMessageLabelType(String messageLabelType) {
        this.messageLabelType = messageLabelType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_user_feedbacks.data_status
     *
     * @return the value of cs_user_feedbacks.data_status
     *
     * @mbggenerated
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_user_feedbacks.data_status
     *
     * @param dataStatus the value for cs_user_feedbacks.data_status
     *
     * @mbggenerated
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }
}