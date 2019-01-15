package com.newhope.moneyfeed.integration.dal.dao.ebs.order;

import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderDetailModel;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderDetailExModel;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EbsOrderDetailDao extends BaseDao<EbsOrderDetailModel> {

    /**
     * 根据
     * @param saleIds 根据商场ID集合查询订单详情
     * @return
     */
    List<EbsOrderDetailExModel>  queryEbsOrderDetails(List<Long> saleIds);

    List<EbsOrderDetailExModel> queryEbsOrderDetailExModelList(EbsOrderDetailExModel ebsOrderDetailExModel);
    
    //查询需要判断是否锁库成功的订单
    List<EbsOrderDetailExModel> queryLockWarehouseDetailList(@Param("orgId")String orgId,@Param("planDate")Date planDate);
}