package com.newhope.order.biz.service;

/**
 * 订单定时任务服务
 * @author hp
 *
 */
public interface OrderScheduleService{

    /**
     * 关闭超时未支付订单
     */
    void closeOvertimeNotpaid();

    /**
     * 发送支付提醒消息
     */
    void remindDoPay();

    /**
     * 定时发送拉料提醒
     */
    void remindTransport();

    /**
     * 定时发送所库存失败备货提醒
     */
    void remindPreparingMaterial();
}
