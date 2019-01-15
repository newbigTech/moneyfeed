package com.newhope.moneyfeed.integration.api.enums.order;

/**
 * Created by yuyanlin on 2018/11/22
 */
public enum EbsOrderCanUseStockEnum {
    CAN_USE_STOCK(Byte.valueOf("1")),
    CAN_NOT_USE_STOCK(Byte.valueOf("0"));

    private byte mark;

    EbsOrderCanUseStockEnum(byte mark) {
        this.mark = mark;
    }

    public byte getMark() {
        return mark;
    }

    public void setMark(byte mark) {
        this.mark = mark;
    }
}
