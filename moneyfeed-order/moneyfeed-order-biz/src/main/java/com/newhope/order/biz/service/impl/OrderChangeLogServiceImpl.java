package com.newhope.order.biz.service.impl;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.order.dal.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.order.api.bean.OrderChangeLogModel;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderLogDtoReq;
import com.newhope.moneyfeed.order.dal.dao.OrderChangeLogDao;
import com.newhope.order.biz.service.OrderChangeLogService;

/**
 * Create by yyq on 2018/11/19
 */
@Service
public class OrderChangeLogServiceImpl extends BaseServiceImpl<OrderChangeLogModel>  implements OrderChangeLogService {

    private final Logger logger = LoggerFactory.getLogger(OrderChangeLogServiceImpl.class);
    @Autowired
    OrderChangeLogDao orderChangeLogDao;

    @Override
    protected BaseDao<OrderChangeLogModel> getDao() {
        return orderChangeLogDao;
    }
    @Override
    public DtoResult addOrderChangeLog(OrderLogDtoReq orderLogDtoReq) {

        OrderChangeLogModel orderChangeLogModel = new OrderChangeLogModel();
        BeanUtils.copyProperties(orderLogDtoReq,orderChangeLogModel);
        if(!save(Arrays.asList(orderChangeLogModel))){
            logger.error(ResultCode.OC_ORDER_LOG_CREATE_ERROR.getDesc()+":{%s}",JSON.toJSONString(orderLogDtoReq));
            return  DtoResult.fail(ResultCode.OC_ORDER_LOG_CREATE_ERROR);
        }
        return  DtoResult.success();
    }


}
