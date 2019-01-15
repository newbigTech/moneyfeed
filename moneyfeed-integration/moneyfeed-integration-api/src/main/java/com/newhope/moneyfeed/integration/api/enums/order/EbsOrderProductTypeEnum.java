package com.newhope.moneyfeed.integration.api.enums.order;

/**
 * Created by yuyanlin on 2018/11/29
 */
public enum EbsOrderProductTypeEnum {

    NORMAL_PRODUCT("正常商品"),
    PRESENT_PRODUCT("赠品");

    private String desc;

    EbsOrderProductTypeEnum(String desc) {
        this.desc = desc;
    }

}
