package com.newhope.order.biz.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.SmsTemplateEnums;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.order.api.bean.OrderFeedTransportModel;
import com.newhope.moneyfeed.order.api.bean.OrderInfoModel;
import com.newhope.moneyfeed.order.api.constant.CommonConstant;
import com.newhope.moneyfeed.order.api.dto.request.feedtransport.OrderFeedTransportModifyDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.feedtransport.OrderFeedTransportQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.TransportInfoToEbsDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderTransportListDtoResult;
import com.newhope.moneyfeed.order.api.enums.OrderStatusEnum;
import com.newhope.moneyfeed.order.dal.BaseDao;
import com.newhope.moneyfeed.order.dal.dao.OrderFeedTransportDao;
import com.newhope.moneyfeed.order.dal.dao.OrderInfoDao;
import com.newhope.order.biz.rpc.feign.SmsFeignClient;
import com.newhope.order.biz.service.OrderFeedTransportService;
import com.newhope.order.biz.service.OrderIntegrationService;
import com.newhope.order.biz.service.OrderMsgService;
import com.newhope.order.biz.service.OrderService;

@Service
public class OrderFeedTransportServiceImpl extends BaseServiceImpl<OrderFeedTransportModel> implements OrderFeedTransportService {

    @Autowired
    OrderFeedTransportDao orderFeedTransportDao;
    @Autowired
    OrderInfoDao orderInfoDao;
    @Autowired
    SmsFeignClient smsFeignClient;
    @Autowired
    OrderIntegrationService orderIntegrationService;

    @Autowired
    OrderMsgService orderMsgService;
    @Autowired
    OrderService orderService;



    @Override
    protected BaseDao<OrderFeedTransportModel> getDao() {

        return orderFeedTransportDao;
    }

    @Override
    @Transactional
    public DtoResult modifyFeedTransport(OrderFeedTransportModifyDtoReq dtoReq) {
        OrderFeedTransportModel orderFeedTransportModel = new OrderFeedTransportModel();
        orderFeedTransportModel.setId(dtoReq.getId());
        List<OrderFeedTransportModel> listOrderFeedTransportModel = query(orderFeedTransportModel);
        if (CollectionUtils.isEmpty(listOrderFeedTransportModel)) {
            return DtoResult.fail(ResultCode.FAIL);
        }
        orderFeedTransportModel = listOrderFeedTransportModel.get(0);

        OrderFeedTransportModel oldModel = new OrderFeedTransportModel();
        oldModel.setId(dtoReq.getId());
        oldModel.setOrderId(dtoReq.getOrderId());

        OrderFeedTransportModel newModel = new OrderFeedTransportModel();
        newModel.setCarNo(dtoReq.getCarNo());
        newModel.setDriverMobile(dtoReq.getDriverMobile());
        newModel.setDriverName(dtoReq.getDriverName());
        newModel.setIdCard(dtoReq.getIdCard());
        newModel.setSource(dtoReq.getSource().getValue());

        long count = orderFeedTransportDao.update(oldModel, newModel);
        if (count <= 0) {
            return DtoResult.fail(ResultCode.FAIL);
        }
        //调用EBS同步车牌号
        if (StringUtils.isNotEmpty(dtoReq.getCarNo()) && StringUtils.isEmpty(orderFeedTransportModel.getEbsCarNo())) {
            TransportInfoToEbsDtoReq transportInfoToEbsDtoReq = new TransportInfoToEbsDtoReq();
            transportInfoToEbsDtoReq.setOrderFeedTransportId(dtoReq.getId());
            transportInfoToEbsDtoReq.setCarNo(dtoReq.getCarNo());
            orderIntegrationService.sendTransportInfoToEbs(transportInfoToEbsDtoReq);
        }

        //发送短信
        //获取订单
        OrderInfoModel orderInfoModel = new OrderInfoModel();
        orderInfoModel.setId(orderFeedTransportModel.getOrderId());
        List<OrderInfoModel> orderInfoModels = orderInfoDao.select(orderInfoModel);
        if (CollectionUtils.isNotEmpty(orderInfoModels) &&
                //待拉料or备货中
                (OrderStatusEnum.WAITING_PULL_MATERIAL.getValue().equals(orderInfoModels.get(0).getStatus())||
                OrderStatusEnum.MATERIAL_PREPARING.getValue().equals(orderInfoModels.get(0).getStatus())
                )) {
            if (StringUtils.isNotEmpty(dtoReq.getDriverMobile()) &&
                    (StringUtils.isEmpty(orderFeedTransportModel.getDriverMobile())
                            || !dtoReq.getDriverMobile().equals(orderFeedTransportModel.getDriverMobile())
                    )) {
                //发送短信
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("userName",orderInfoModels.get(0).getBuyerName());
                paramMap.put("shopName", orderInfoModels.get(0).getUcShopName());
                paramMap.put("transportTime", DateUtils.date_sdf_wz.format(orderInfoModels.get(0).getPlanTransportTime()));
                paramMap.put("weight", (orderInfoModels.get(0).getTotalFeedWeight().add(orderService.calculatePresentWeight(orderFeedTransportModel.getOrderId()))).divide(new BigDecimal(CommonConstant.TON_CONVERSION_KG)).toString());
                paramMap.put("tel", orderInfoModels.get(0).getBuyerMobile());
                orderMsgService.sendSmsMsg(dtoReq.getDriverMobile(), SmsTemplateEnums.SMS_151767097.getTemplateId(), paramMap);
            }
        }
        return DtoResult.success();
    }

	@Override
	public OrderTransportListDtoResult queryFeedTransport(OrderFeedTransportQueryDtoReq dtoReq) {
		OrderTransportListDtoResult orderTransportListDtoResult = new OrderTransportListDtoResult();
		OrderFeedTransportModel orderFeedTransportModel = new OrderFeedTransportModel();
		orderFeedTransportModel.setOrderId(dtoReq.getOrderId());
		List<OrderFeedTransportModel> list = query(orderFeedTransportModel);
		orderTransportListDtoResult.setOrderFeedTransports(list);
		orderTransportListDtoResult.setCode(ResultCode.SUCCESS.getCode());
		return orderTransportListDtoResult;
	}

}
