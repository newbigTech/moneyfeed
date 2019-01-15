package com.newhope.moneyfeed.common.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by liming on 2019/1/8.
 */
public class AliyunLogDataBean {


    @JSONField(name="user_id")
    private String userId;


    @JSONField(name = "open_id")
    private String openId;


    @JSONField(name = "env_type")
    private String eventType;


    @JSONField(name = "create_time")
    private Long createTime;


    @JSONField(name = "client_ip")
    private String clientIp;


    @JSONField(name = "success_flag")
    private int successFlag;


    @JSONField(name = "err_desc")
    private String errDesc;


    @JSONField(name = "attr1")
    private String attr1;

    @JSONField(name = "in_param")
    private String inParam;

    private AliyunLogDataBean(Builder builder) {
        setUserId(builder.userId);
        setOpenId(builder.openId);
        setEventType(builder.eventType);
        setCreateTime(builder.createTime);
        setClientIp(builder.clientIp);
        setSuccessFlag(builder.successFlag);
        setErrDesc(builder.errDesc);
        setAttr1(builder.attr1);
        setInParam(builder.inParam);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public int getSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(int successFlag) {
        this.successFlag = successFlag;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getInParam() {
        return inParam;
    }

    public void setInParam(String inParam) {
        this.inParam = inParam;
    }


    public static final class Builder {
        private String userId;
        private String openId;
        private String eventType;
        private Long createTime;
        private String clientIp;
        private int successFlag;
        private String errDesc;
        private String attr1;
        private String inParam;

        public Builder() {
        }

        /**
         * 用戶ID
         * @param val
         * @return
         */
        public Builder userId(String val) {
            userId = val;
            return this;
        }

        /**
         * 微信用戶ID
         * @param val
         * @return
         */
        public Builder openId(String val) {
            openId = val;
            return this;
        }

        /**
         * 事件类型
         * @param val
         * @return
         */
        public Builder eventType(String val) {
            eventType = val;
            return this;
        }

        /**
         * 创建时间戳
         * @param val
         * @return
         */
        public Builder createTime(Long val) {
            createTime = val;
            return this;
        }

        /**
         * 客户端IP地址
         * @param val
         * @return
         */
        public Builder clientIp(String val) {
            clientIp = val;
            return this;
        }

        /**
         * 成功标识
         * @param val
         * @return
         */
        public Builder successFlag(int val) {
            successFlag = val;
            return this;
        }

        /**
         * 错误信息
         * @param val
         * @return
         */
        public Builder errDesc(String val) {
            errDesc = val;
            return this;
        }

        /**
         * 扩展字段
         * @param val
         * @return
         */
        public Builder attr1(String val) {
            attr1 = val;
            return this;
        }

        /**
         * 业务数据 json字符串
         * @param val
         * @return
         */
        public Builder inParam(String val) {
            inParam = val;
            return this;
        }

        public AliyunLogDataBean build() {
            return new AliyunLogDataBean(this);
        }
    }
}
