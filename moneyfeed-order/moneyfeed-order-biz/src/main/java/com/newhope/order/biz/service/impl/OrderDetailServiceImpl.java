package com.newhope.order.biz.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.order.api.bean.*;
import com.newhope.moneyfeed.order.api.constant.CommonConstant;
import com.newhope.moneyfeed.order.api.dto.response.*;
import com.newhope.moneyfeed.order.api.enums.OrderStatusEnum;
import com.newhope.moneyfeed.order.api.enums.PayLimitTypeEnums;
import com.newhope.moneyfeed.order.dal.dao.*;
import com.newhope.order.biz.service.OrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderDetailDtoReq;
import com.newhope.moneyfeed.order.dal.BaseDao;
import com.newhope.order.biz.service.OrderDetailService;

/**
 * Create by yyq on 2018/11/17
 */
@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetailModel>  implements OrderDetailService {

    private final Logger logger = LoggerFactory.getLogger(OrderDetailServiceImpl.class);
    @Autowired
    OrderInfoDao orderInfoDao;
    @Autowired
    OrderDetailDao orderDetailDao;
    @Autowired
    OrderPresentFeedDao orderPresentFeedDao;
    @Autowired
    OrderFeedTransportDao orderFeedTransportDao;
    @Autowired
    OrderSnapshotDao orderSnapshotDao;
    @Autowired
    OrderRuleDao orderRuleDao;
    @Autowired
    OrderService orderService;


	@Override
	protected BaseDao<OrderDetailModel> getDao() {
		return orderDetailDao;
	}
    
    @Override
    public OrderDetailDtoResult queryOrderDetail(OrderDetailDtoReq orderDetailDtoReq){
        OrderDetailDtoResult result = new OrderDetailDtoResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());

        //订单信息查询
        OrderInfoModel orderInfoModel = new OrderInfoModel();
        if(orderDetailDtoReq.getOrderId()!=null){

            orderInfoModel.setId(orderDetailDtoReq.getOrderId());
        }
        if(StringUtils.isNotEmpty(orderDetailDtoReq.getOrderNo())){
            orderInfoModel.setOrderNo(orderDetailDtoReq.getOrderNo());
        }

        List<OrderInfoModel> select = orderInfoDao.select(orderInfoModel);
        if(CollectionUtils.isEmpty(select)){
            return result;
        }
        OrderDtoResult orderInfoDtoResult = new OrderDtoResult();
        OrderInfoModel orderModel = select.get(0);
        BeanUtils.copyProperties(orderModel,orderInfoDtoResult);
        //赠品信息列表
        OrderPresentFeedModel orderPresentFeedModel = new OrderPresentFeedModel();
        orderPresentFeedModel.setOrderId(select.get(0).getId());
        BigDecimal presentTotal =BigDecimal.ZERO;
        List<OrderPresentFeedModel> presentFeedModelList = orderPresentFeedDao.select(orderPresentFeedModel);
        if(CollectionUtils.isNotEmpty(presentFeedModelList)){
            List<OrderPresentDtoResult> orderPresentDtoResultList =new ArrayList<>();

            for(OrderPresentFeedModel presentFeedModel :presentFeedModelList){
                OrderPresentDtoResult orderPresentDtoResult = new OrderPresentDtoResult();
                BeanUtils.copyProperties(presentFeedModel, orderPresentDtoResult);
                presentTotal = presentTotal.add(BigDecimal.valueOf(presentFeedModel.getCount()));
                orderPresentDtoResultList.add(orderPresentDtoResult);
            }
            result.setOrderPresentDtoResultList(orderPresentDtoResultList);
        }
        orderInfoDtoResult.setTotalPresent(presentTotal);
        //商品信息列表
        OrderSnapshotModel orderSnapshotModel = new OrderSnapshotModel();
        orderSnapshotModel.setOrderId(select.get(0).getId());
        List<OrderSnapshotModel> orderSnapshotModelList = orderSnapshotDao.select(orderSnapshotModel);
        try{
            if(CollectionUtils.isNotEmpty(orderSnapshotModelList)){
                List<OrderGoodsDtoResult> orderGoodsDtoResultList =new ArrayList<>();
                BigDecimal sum = BigDecimal.ZERO;
                BigDecimal sumMoney = BigDecimal.ZERO;
                for(OrderSnapshotModel snapshotModel :orderSnapshotModelList){
                    OrderGoodsDtoResult orderGoodsDtoResult = new OrderGoodsDtoResult();
                    List<OrderSnapshotDtoResult> snapshotDtoResultList = JSONObject.parseArray(snapshotModel.getSkuAttrParams(), OrderSnapshotDtoResult.class);
                    if(CommonConstant.SKU_NAME.equals(snapshotDtoResultList.get(0).getName())){
                        BigDecimal i = snapshotDtoResultList.get(0).getParmValue().multiply(BigDecimal.valueOf(snapshotModel.getQuantity())) ;
                        orderGoodsDtoResult.setTotal(i);
                        sumMoney = sumMoney.add(i.multiply(snapshotModel.getPrice()));
                        if(!CommonConstant.WEIGHT_UNIT_KG.equals(snapshotModel.getPrimaryUom())){
                            i = i.multiply(new BigDecimal(CommonConstant.TON_CONVERSION_KG));
                        }
                        sum = sum.add(i);

                        orderGoodsDtoResult.setSkuParams(snapshotDtoResultList.get(0).getParmValue()+snapshotModel.getPrimaryUom()+"/"+snapshotModel.getSecondaryUom());
                    }
                    orderGoodsDtoResult.setSkuParamsValue(snapshotDtoResultList.get(0).getParmValue().toEngineeringString());
                    BeanUtils.copyProperties(snapshotModel, orderGoodsDtoResult);
                    orderGoodsDtoResultList.add(orderGoodsDtoResult);
                }
                orderInfoDtoResult.setTotalFeedWeight(sum);
                orderInfoDtoResult.setTotalOrginalAmount(sumMoney);
                result.setOrderGoodsDtoResultList(orderGoodsDtoResultList);
            }
        } catch (Exception e){
            logger.error("OrderDetailServiceImpl.queryOrderDetail异常:",e);
            throw new BizException(ResultCode.DATA_ERROR);
        }

        //查询订单规则
        OrderRuleModel orderRuleModel = new OrderRuleModel();
        orderRuleModel.setUcShopId(orderModel.getUcShopId());
        List<OrderRuleModel> orderRuleModels = orderRuleDao.select(orderRuleModel);
        if(CollectionUtils.isNotEmpty(orderRuleModels)){
            OrderRuleModel ruleModel = orderRuleModels.get(0);
            Calendar instance = Calendar.getInstance();
            List<String> beforePayingStatus = Arrays.asList(OrderStatusEnum.CREATING.name()
            		,OrderStatusEnum.WAITING_FOR_PAYMENT.name()
            		,OrderStatusEnum.PAYING.name());
            //支付前状态
            if(beforePayingStatus.contains(orderModel.getStatus())){
            	instance.setTime(orderModel.getGmtCreated());
            }else{
            	instance.setTime(orderModel.getPlanTransportTime());
            }
            //设置开始时间
            instance.add(Calendar.DATE,ruleModel.getTransportStartDay());
            Date now = new Date();
            //设置时间不能小于当前时间
            if(instance.getTime().before(now)){
                instance.setTime(now);
            }
            orderInfoDtoResult.setTransportStart(instance.getTime());
            //设置结束时间
            instance.setTime(orderModel.getGmtCreated());
            instance.add(Calendar.DATE,ruleModel.getTransportEndDay());
            //设置时间不能小于当前时间
            if(instance.getTime().before(now)){
                instance.setTime(now);
            }
            orderInfoDtoResult.setTransportEnd(instance.getTime());

            Date date = orderService.calLimitDate(orderModel.getPlanTransportTime(), ruleModel.getCanModifyDay(), ruleModel.getCanModifyTime());
            orderInfoDtoResult.setCanUpdateTime(date);
        }

        result.setOrderInfoDtoResult(orderInfoDtoResult);
        //司机信息
        OrderFeedTransportModel orderFeedTransportModel = new OrderFeedTransportModel();
        orderFeedTransportModel.setOrderId(select.get(0).getId());
        List<OrderFeedTransportModel> orderFeedTransportModelList = orderFeedTransportDao.select(orderFeedTransportModel);
        if(CollectionUtils.isNotEmpty(orderFeedTransportModelList)){
            OrderTransportDtoResult orderTransportDtoResult =new OrderTransportDtoResult();
            BeanUtils.copyProperties(orderFeedTransportModelList.get(0),orderTransportDtoResult);
            result.setOrderTransportDtoResult(orderTransportDtoResult);
        }

        return  result;
    }



}
