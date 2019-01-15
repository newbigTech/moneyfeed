package com.newhope.moneyfeed.order.api.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Create by yyq on 2018/11/24
 */
public class OrderSnapshotDtoResult implements Serializable {

    private static final long serialVersionUID = -167961802198214245L;
    private String name;

    private BigDecimal parmValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getParmValue() {
        return parmValue;
    }

    public void setParmValue(BigDecimal parmValue) {
        this.parmValue = parmValue;
    }
}
