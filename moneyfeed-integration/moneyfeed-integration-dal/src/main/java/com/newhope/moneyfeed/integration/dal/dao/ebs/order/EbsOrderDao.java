package com.newhope.moneyfeed.integration.dal.dao.ebs.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderModel;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderExModel;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;

public interface EbsOrderDao extends BaseDao<EbsOrderModel> {

    /**
     * 查询已支付但是还没有关闭的订单
     * @return
     */
    List<EbsOrderModel> queryBookOrderInfo();

    List<EbsOrderModel> queryEbsOrderList(EbsOrderExModel ebsOrderExModel);

	List<EbsOrderModel> getEbsOrderListByIdList(@Param("orderIdList") List<Long> orderIdList);

	EbsOrderModel getEbsOrderModelById(Long id);
}