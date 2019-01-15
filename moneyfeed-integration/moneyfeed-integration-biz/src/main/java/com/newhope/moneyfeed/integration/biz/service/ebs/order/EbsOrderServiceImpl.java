package com.newhope.moneyfeed.integration.biz.service.ebs.order;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderDetailModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderModel;
import com.newhope.moneyfeed.integration.api.enums.order.MoneyfeedToEbsOperationStatusEnum;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderExModel;
import com.newhope.moneyfeed.integration.api.service.ebs.order.EbsOrderService;
import com.newhope.moneyfeed.integration.biz.service.BaseService;
import com.newhope.moneyfeed.integration.dal.dao.BaseDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.order.EbsOrderDao;
import com.newhope.moneyfeed.integration.dal.dao.ebs.order.EbsOrderDetailDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by yuyanlin on 2018/11/20
 */
@Service
public class EbsOrderServiceImpl extends BaseService<EbsOrderModel> implements EbsOrderService {

    @Autowired
    private EbsOrderDao ebsOrderDao;

    @Autowired
    private EbsOrderDetailDao ebsOrderDetailDao;

    @Override
    protected BaseDao<EbsOrderModel> getDao() {
        return ebsOrderDao;
    }

    @Override
    public EbsOrderExModel getEbsOrderWithDetailByMoneyfeedOrderId(String moneyfeedOrderId) {
        EbsOrderModel ebsOrderSearch = new EbsOrderModel();
        ebsOrderSearch.setSaleOrderId(moneyfeedOrderId);

        List<EbsOrderModel> ebsOrderList = ebsOrderDao.select(ebsOrderSearch);

        if (CollectionUtils.isEmpty(ebsOrderList)) {
            return null;
        }

        EbsOrderModel ebsOrder = ebsOrderList.get(0);

        EbsOrderDetailModel orderDetailSearch = new EbsOrderDetailModel();
        orderDetailSearch.setOrderId(ebsOrder.getId());

        List<EbsOrderDetailModel> ebsOrderDetailList = ebsOrderDetailDao.select(orderDetailSearch);

        if (CollectionUtils.isEmpty(ebsOrderDetailList)) {
            return null;
        }

        EbsOrderDetailModel ebsOrderDetail = ebsOrderDetailList.get(0);

        EbsOrderExModel ebsOrderWithDetail = new EbsOrderExModel();

        BeanUtils.copyProperties(ebsOrder, ebsOrderWithDetail);
        ebsOrderWithDetail.setEbsOrderDetail(ebsOrderDetail);

        return ebsOrderWithDetail;
    }

    @Override
    public EbsOrderExModel getEbsOrderWithDetailByEbsOrderNo(String ebsOrderNo) {
        EbsOrderModel ebsOrderSearch = new EbsOrderModel();
        ebsOrderSearch.setEbsOrderNo(ebsOrderNo);

        List<EbsOrderModel> ebsOrderList = ebsOrderDao.select(ebsOrderSearch);

        if (CollectionUtils.isEmpty(ebsOrderList)) {
            return null;
        }

        EbsOrderModel ebsOrder = ebsOrderList.get(0);

        EbsOrderDetailModel orderDetailSearch = new EbsOrderDetailModel();
        orderDetailSearch.setOrderId(ebsOrder.getId());

        List<EbsOrderDetailModel> ebsOrderDetailList = ebsOrderDetailDao.select(orderDetailSearch);

        if (CollectionUtils.isEmpty(ebsOrderDetailList)) {
            return null;
        }

        EbsOrderDetailModel ebsOrderDetail = ebsOrderDetailList.get(0);

        EbsOrderExModel ebsOrderWithDetail = new EbsOrderExModel();

        BeanUtils.copyProperties(ebsOrder, ebsOrderWithDetail);
        ebsOrderWithDetail.setEbsOrderDetail(ebsOrderDetail);

        return ebsOrderWithDetail;
    }

    @Override
    public List<EbsOrderModel> getCreatingEbsOrderList() {
        EbsOrderModel ebsOrderSearch = new EbsOrderModel();
        ebsOrderSearch.setOrderCreateStatus(MoneyfeedToEbsOperationStatusEnum.OPERATING.name());

        return query(ebsOrderSearch);
    }

    @Override
    public List<EbsOrderModel> getPayingEbsOrderList() {
        EbsOrderModel ebsOrderSearch = new EbsOrderModel();
        ebsOrderSearch.setOrderPayStatus(MoneyfeedToEbsOperationStatusEnum.OPERATING.name());

        return query(ebsOrderSearch);
    }

    @Override
    public List<EbsOrderModel> getCancelingEbsOrderList() {
        EbsOrderModel ebsOrderSearch = new EbsOrderModel();
        ebsOrderSearch.setOrderCancelStatus(MoneyfeedToEbsOperationStatusEnum.OPERATING.name());

        return query(ebsOrderSearch);
    }

    @Override
    public Map<String, List<String>> getOrgIdMoneyfeedIdListMapFromEbsOrderList(List<EbsOrderModel> ebsOrderList) {
        if (org.apache.commons.collections.CollectionUtils.isEmpty(ebsOrderList)) {
            return Maps.newHashMap();
        }

        Map<String, List<String>> orgIdMoneyfeedIdListMap = Maps.newHashMap();

        for (EbsOrderModel ebsOrder : ebsOrderList) {
            if (null != orgIdMoneyfeedIdListMap.get(ebsOrder.getEbsOrgId())) {
                orgIdMoneyfeedIdListMap.get(ebsOrder.getEbsOrgId()).add(ebsOrder.getSaleOrderId());
            } else {
                List<String> moneyfeedIdList = Lists.newArrayList();
                moneyfeedIdList.add(ebsOrder.getSaleOrderId());

                orgIdMoneyfeedIdListMap.put(ebsOrder.getEbsOrgId(), moneyfeedIdList);
            }
        }

        return orgIdMoneyfeedIdListMap;
    }

    @Override
    public Map<String, List<String>> getOrgIdEbsOrderNoListMapFromEbsOrderList(List<EbsOrderModel> ebsOrderList) {
        if (org.apache.commons.collections.CollectionUtils.isEmpty(ebsOrderList)) {
            return Maps.newHashMap();
        }

        Map<String, List<String>> orgIdEbsOrderNoListMap = Maps.newHashMap();

        for (EbsOrderModel ebsOrder : ebsOrderList) {
            if (null != orgIdEbsOrderNoListMap.get(ebsOrder.getEbsOrgId())) {
                orgIdEbsOrderNoListMap.get(ebsOrder.getEbsOrgId()).add(ebsOrder.getEbsOrderNo());
            } else {
                List<String> ebsOrderNoList = Lists.newArrayList();
                ebsOrderNoList.add(ebsOrder.getEbsOrderNo());

                orgIdEbsOrderNoListMap.put(ebsOrder.getEbsOrgId(), ebsOrderNoList);
            }
        }

        return orgIdEbsOrderNoListMap;
    }

    @Override
    public Map<String, List<Long>> getOrgIdEbsOrderIdListMapFromEbsOrderList(List<EbsOrderModel> ebsOrderList) {
        Map<String, List<Long>> orgIdEbsOrderIdListMap = Maps.newHashMap();

        if (CollectionUtils.isEmpty(ebsOrderList)) {
            return orgIdEbsOrderIdListMap;
        }

        for (EbsOrderModel ebsOrder : ebsOrderList) {
            if (null != orgIdEbsOrderIdListMap.get(ebsOrder.getEbsOrgId())) {
                orgIdEbsOrderIdListMap.get(ebsOrder.getEbsOrgId()).add(ebsOrder.getId());
            } else {
                List<Long> ebsOrderIdList = Lists.newArrayList();
                ebsOrderIdList.add(ebsOrder.getId());

                orgIdEbsOrderIdListMap.put(ebsOrder.getEbsOrgId(), ebsOrderIdList);
            }
        }

        return orgIdEbsOrderIdListMap;
    }

    @Override
    public Map<Long, String> getEbsOrderIdSaleIdMapFromEbsOrderList(List<EbsOrderModel> creatingOrderList) {
        Map<Long, String> ebsOrderIdSaleOrderIdMap = Maps.newHashMap();

        if (CollectionUtils.isEmpty(creatingOrderList)) {
            return ebsOrderIdSaleOrderIdMap;
        }

        for (EbsOrderModel ebsOrder : creatingOrderList) {
            ebsOrderIdSaleOrderIdMap.put(ebsOrder.getId(), ebsOrder.getSaleOrderId());
        }

        return ebsOrderIdSaleOrderIdMap;
    }

    @Override
    public List<EbsOrderModel> getEbsOrderListHavedUpdateResult() {
        EbsOrderExModel ebsOrderSearch = new EbsOrderExModel();
        ebsOrderSearch.setExcludeOrderUpdateStatusList(Lists.newArrayList(MoneyfeedToEbsOperationStatusEnum.UNCOMMIT.name()));

        return ebsOrderDao.queryEbsOrderList(ebsOrderSearch);
    }

    /**
     * 查询EBS已经支付的订单切没有关闭的订单
     * @return
     */
    public  List<EbsOrderModel> queryBookOrderInfo(){
        return  ebsOrderDao.queryBookOrderInfo();
    }
    
    
    public List<EbsOrderModel> getEbsOrderListByIdList(List<Long> orderIdList){
    	return ebsOrderDao.getEbsOrderListByIdList(orderIdList);
    }

	@Override
	public EbsOrderModel getEbsOrderModelById(Long id) {
		return ebsOrderDao.getEbsOrderModelById(id);
	}

}
