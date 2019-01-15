package com.newhope.moneyfeed.integration.api.service.ebs.order;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs.LockWarehouseReq;

/**
 * EBS请求（反馈结果）商城，订单相关服务
 *
 * Created by yuyanlin on 2018/11/21
 */
public interface EbsToMoneyfeedOrderService {

    /**
     * 取出创建中的订单，请求EBS，获取创建结果，通知商城
     * @return
     */
    DtoResult ebsToMoneyfeedOrdersCreateResult() throws Exception;

    /**
     * 需要重新向订单中心发送上次发送失败的，订单创建失败的消息
     * @return
     * @throws Exception
     */
    DtoResult ebsToMoneyfeedRetrySendFailCreateResult() throws Exception;

    /**
     * 取出支付中的订单，请求EBS，获取支付结果，通知商城
     * @return
     */
    DtoResult ebsToMoneyfeedOrdersPayResult();

    /**
     * 取出取消中的订单，请求EBS，获取取消结果，通知商城
     * @return
     */
    DtoResult ebsToMoneyfeedOrdersCancelResult();

    /**
     * 请求EBS系统，取出当天取货订单锁库的结果，然后通知商城
     *
     * @return
     */
    DtoResult ebsToMoneyfeedOrderLockWarehouseResult(LockWarehouseReq req);

    /**
     * 根据ebs_order表中的order_update_status结果，处理后，返回给订单中心
     *
     * @return
     */
    DtoResult ebsToMoneyfeedSendUpdateInfoResult();
}
