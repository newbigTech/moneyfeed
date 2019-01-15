package com.newhope.moneyfeed.integration.api.exbean.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderDetailModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderModel;

import java.util.List;

/**
 * Created by yuyanlin on 2018/11/20
 */
public class EbsOrderExModel extends EbsOrderModel {

    private static final long serialVersionUID = -3787611621767177455L;

    private EbsOrderDetailModel ebsOrderDetail;

    private List<String> excludeOrderUpdateStatusList;

    public EbsOrderDetailModel getEbsOrderDetail() {
        return ebsOrderDetail;
    }

    public void setEbsOrderDetail(EbsOrderDetailModel ebsOrderDetail) {
        this.ebsOrderDetail = ebsOrderDetail;
    }

    public List<String> getExcludeOrderUpdateStatusList() {
        return excludeOrderUpdateStatusList;
    }

    public void setExcludeOrderUpdateStatusList(List<String> excludeOrderUpdateStatusList) {
        this.excludeOrderUpdateStatusList = excludeOrderUpdateStatusList;
    }
}
