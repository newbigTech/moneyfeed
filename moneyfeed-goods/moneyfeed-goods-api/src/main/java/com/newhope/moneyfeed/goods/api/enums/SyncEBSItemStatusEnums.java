package com.newhope.moneyfeed.goods.api.enums;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/12/5 15:20
 */
public enum SyncEBSItemStatusEnums {
    RUNNING("运行中", "RUNNING"),
    COMPLETE("已完成", "COMPLETE");

    private String desc;

    private String value;

    private SyncEBSItemStatusEnums(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public String getValue() {
        return value;
    }
}
