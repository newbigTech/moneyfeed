package com.newhope.moneyfeed.integration.api.bean.ebs.baseData;


import com.newhope.moneyfeed.api.bean.BaseModel;

/**
 *   OA定价人员对照表
 */
public class OaUserModel extends BaseModel {

	private static final long serialVersionUID = 3472827081070196994L;

	/** oa用户ID */
    private String oaId;

    /** 店铺ID */
    private Long shopId;

    /** 区域名称 */
    private String area;

    public String getOaId() {
        return oaId;
    }

    public void setOaId(String oaId) {
        this.oaId = oaId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}