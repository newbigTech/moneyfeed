package com.newhope.moneyfeed.integration.api.service.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderModel;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderExModel;

import java.util.List;
import java.util.Map;

/**
 * ebs order 表对应的基础服务
 *
 * Created by yuyanlin on 2018/11/20
 */
public interface EbsOrderService {

    EbsOrderExModel getEbsOrderWithDetailByMoneyfeedOrderId(String moneyfeedOrderId);

    EbsOrderExModel getEbsOrderWithDetailByEbsOrderNo(String ebsOrderNo);

    List<EbsOrderModel> getCreatingEbsOrderList();

    List<EbsOrderModel> getPayingEbsOrderList();

    List<EbsOrderModel> getCancelingEbsOrderList();

    Map<String, List<String>> getOrgIdMoneyfeedIdListMapFromEbsOrderList(List<EbsOrderModel> ebsOrderList);

    Map<String, List<String>> getOrgIdEbsOrderNoListMapFromEbsOrderList(List<EbsOrderModel> ebsOrderList);

    Map<String, List<Long>> getOrgIdEbsOrderIdListMapFromEbsOrderList(List<EbsOrderModel> ebsOrderList);

    Map<Long,String> getEbsOrderIdSaleIdMapFromEbsOrderList(List<EbsOrderModel> creatingOrderList);

    /**
     * 获取已经有更新结果的订单
     *
     * @return
     */
    List<EbsOrderModel> getEbsOrderListHavedUpdateResult();
    
    List<EbsOrderModel> getEbsOrderListByIdList(List<Long> orderIdList);
    
    EbsOrderModel getEbsOrderModelById(Long id);

}
