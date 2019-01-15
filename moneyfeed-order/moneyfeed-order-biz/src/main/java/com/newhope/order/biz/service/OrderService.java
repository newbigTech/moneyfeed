package com.newhope.order.biz.service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.order.api.bean.OrderInfoModel;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderCloseDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoModifyDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoSelectDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.nh.OrderNHCreateDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.nh.OrderNHUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderCreateDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderGoodsDetailDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderInfoDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.PayOrderNoDtoResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderService extends BaseService<OrderInfoModel>{
	
	/**
	 * 创建订单
	 * creator : pudongliang  
	 * dateTime : 2018年11月19日下午1:06:46
	 */
	 OrderCreateDtoResult createNHOrder(OrderNHCreateDtoReq req);
	 
	 /**
	  * 修改订单
	  * creator : pudongliang  
	  * dateTime : 2018年11月19日下午1:06:46
	  */
	 OrderCreateDtoResult createNHOrderAfterCancel(OrderNHUpdateDtoReq req);
	 
	/**
	 *  查询订单列表
	 *
	 * @author: dongql
	 * @date: 2018/11/19 15:18
	 * @param req
	 */
	PageList<OrderDtoResult> queryOrderInfoList(OrderInfoQueryDtoReq req);
	/**
	 * 修改订单
	 * @Author     ：yyq
	 * @Date       ：Created in  2018/11/19 0019 19:56
	 * @Param      ：
	 */
	DtoResult updateOrderInfo(OrderInfoModifyDtoReq requestBody);
	
	/**
	 * 关闭订单
	 * @author heping  
	 * @date 2019年1月11日 
	 * @param requestBody
	 * @return
	 */
	DtoResult closeOrder(OrderCloseDtoReq requestBody);
	
	/**
	 * 订单中心聚合查询
	 * @creator : pudongliang  
	 * @dateTime : 2018年11月28日下午5:21:20
	 */
	List<OrderInfoDtoResult>  getOrdersInfo(OrderInfoSelectDtoReq dtoReq);

	/**
	 * 计算拉料可修改时间
	 * @param transportDate
	 * @param day
	 * @param time
	 * @return
	 */
	Date calLimitDate(Date transportDate, int day , String time);

	/**    
	 * 查询订单商品明细列表
	 *
	 * @author: dongql  
	 * @date: 2018/12/5 14:55
	 * @param req  
	 */
	PageList<OrderGoodsDetailDtoResult> queryOrderGoodsDetailList(OrderInfoQueryDtoReq req);
	/**
	 * 计算赠包总重
	 * @Author: yyq
	 * @Date  :Created in  2018/12/13 10:23
	 * @Param :
	 */
	BigDecimal calculatePresentWeight(Long orderId);
	
	/**  
	* @Title: genPayNo  
	* @Description: 生成支付订单号
	*/
	PayOrderNoDtoResult genPayNo();


	/**
	 * 更新该订单为线下支付订单
	 * @param orderNo
	 * @return
	 */
	DtoResult modifyOrderByOfflinePayment(String orderNo);
}
