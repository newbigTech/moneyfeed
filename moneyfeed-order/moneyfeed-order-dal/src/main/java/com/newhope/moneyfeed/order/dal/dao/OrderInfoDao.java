package com.newhope.moneyfeed.order.dal.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.order.api.bean.OrderInfoModel;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryBaseDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoSelectDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderGoodsDetailDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderInfoDtoResult;
import com.newhope.moneyfeed.order.dal.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfoDao extends BaseDao<OrderInfoModel> {
    /**    
     * 查询订单列表
     *
     * @author: dongql  
     * @date: 2018/11/19 15:21  
     * @param queryReq
     * @param pageBounds  
     */
    PageList<OrderDtoResult> queryOrderInfoList(@Param("param") OrderInfoQueryDtoReq queryReq, PageBounds pageBounds);

    /**
     * 查询订单中心表聚合信息
     * @creator : pudongliang  
     * @dateTime : 2018年11月28日下午4:06:11
     */
    List<OrderInfoDtoResult> queryOrdersInfo(@Param("param") OrderInfoSelectDtoReq dtoReq);

    /**    
     * 查询订单商品明细列表
     *
     * @author: dongql  
     * @date: 2018/12/5 14:59
     * @param queryReq  
     */
    PageList<OrderGoodsDetailDtoResult> queryOrderGoodsDetailList(@Param("param") OrderInfoQueryDtoReq queryReq, PageBounds pageBounds);
    
    /**
     * 
     * @author heping  
     * @date 2019年1月10日 
     * @param queryReq
     * @param pageBounds
     * @return
     */
    List<OrderInfoModel> queryOrderBaseInfoList(@Param("param") OrderInfoQueryBaseDtoReq queryReq);
}