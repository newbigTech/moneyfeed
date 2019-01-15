package com.newhope.moneyfeed.user.api.bean.platform;

import com.newhope.moneyfeed.api.bean.BaseModel;

public class UcPmLabelModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9013420846448349064L;

	/** 标签名称 */
    private String name;

    /** 标签目标类别 */
    private String targetType;

    /** 生成方式 */
    private String createType;

    /** 备注信息 */
    private String comment;

    /** 是否可用 */
    private Boolean enable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}