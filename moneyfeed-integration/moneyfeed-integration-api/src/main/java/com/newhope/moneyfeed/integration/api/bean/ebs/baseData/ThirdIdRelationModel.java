package com.newhope.moneyfeed.integration.api.bean.ebs.baseData;


import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   第三方ID关联表
 */
public class ThirdIdRelationModel extends BaseModel {
	private static final long serialVersionUID = -1722358035860740549L;

	/** 第三方ID */
    private String thirdId;

    /** 聚宝猪对应的业务表ID */
    private Long zxeId;

    /** 来源系统 */
    private String source;

    /** 调用聚宝猪服务code:MEMBER会员，PIG_FARM猪场 */
    private String code;

    /** 是否可用 */
    private Boolean enable;

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public Long getZxeId() {
        return zxeId;
    }

    public void setZxeId(Long zxeId) {
        this.zxeId = zxeId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}