package com.newhope.moneyfeed.api.enums;

/**
 * Created by liming on 2018/11/29.
 */
public enum UserOperationEnums {

    /**
     * 订单中心操作类型
     */
    OD_CREATE_SHOP_ORDER("创建订单(商城新建)订单"),
    OD_ITG_RETURN_ORDER("中台返回ebs创建ebs订单结果"),
    OD_INT_CLOSE_ORDER("接受中台返回关闭商城订单结果"),
    OD_CUSTOMER_CLOSE_ORDER("客户关闭订单"),
    OD_INT_RETURN_PAYINFO("中台返回支付结果"),
    OD_SHOP_CREATE_PAY_TO_BANK("商城向银行发起支付更新订单"),
    OD_BANK_RETURN_PAY_RESULT("银行返回支付结果更新订单"),
    OD_EBS_PAY_ORDER("ebs支付订单(拉取中台订单信息时候更新订单信息)"),
    OD_EBS_CANCEL_ORDER("ebs撤销订单(拉取中台订单信息时候更新订单信息)"),
    OD_EBS_CLOSE_ORDER("ebs关闭订单(拉取中台订单信息时候更新订单信息)"),
    OD_EBS_UPDATE_PRICE("ebs更新折扣价格(拉取中台订单信息时候更新订单信息)"),
    OD_CAR_ENTER_FACTORY("车辆进厂(拉取中台订单信息时候更新订单信息)"),
    OD_CAR_OUT_FACTORY("车辆出厂(拉取中台订单信息时候更新订单信息)"),
    OD_RETURN_INVENTORY("返回锁库存结果更新订单"),
    OD_EBS_RETURN_PULL_TIME("ebs返回拉料时间结果"),
    OD_USER_MODIFY_ORDER("用户修改拉料时间，饲料"),
    OD_TIMEOUT_CLOSE_ORDER("超时自动关闭订单"),

    /**
     * 支付中心操作类型
     */
    PA_SHOP_CREATE_PAY_TO_EBS("商城向EBS发起支付"),
    PA_EBS_RETURN_PAY_RESULT("EBS返回支付结果"),
    PA_SHOP_CREATE_PAY_TO_BANK("商城向银行发起支付"),
    PA_RECHARGE_TO_BANK("商城向银行充值"),
    PA_BANK_RETURN_RECHARGE_RESULT("银行返回充值结果"),
    PA_BANK_RETURN_PAY_RESULT("银行返回支付结果"),

    /**
     * 中台操作类型
     */
    INT_CALL_EBS_CREATE_ORDER("收到商城订单后调用ebs创建订单前"),
    INT_GET_EBS_CREATE_ORDER("调用ebs创建订单成功"),
    INT_GET_EBS_CREATE_ORDER_FAIL("调用ebs创建订单失败"),
    INT_FIND_EBS_CREATE_RESULT("查询ebs创建订单结果"),
    INT_UPDATE_EBS_ORDER_INFO("更新ebs订单信息"),
    INT_SHOP_CANCEL_ORDER("收到商城取消订单后调用ebs取消订单前"),
    INT_CALL_EBS_TO_CANCAL_ORDER("调用ebs取消订单(成功，失败)"),
    INT_FIND_EBS_CANCAL_ORDER_RESULT("查询ebs取消订单结果"),
    INT_SHOP_UPDATE_CAR_OR_DATE("收到商城更新车牌号或计划日期消息"),
    INT_CALL_EBS_UPDATE_CAR_OR_DATE("调用ebs更新车牌号或计划日期"),
    INT_FIND_EBS_UPDATE_CAR_OR_DATE_RESULT("查詢ebs更新车牌和计划日期的结果"),
    INT_UPDATE_INVENTORY_SUCCESS("更新订单是否锁库成功"),
    INT_RECEIVE_PAY_REQ_OF_SHOP("中台收到商场提交的支付请求"),
    INT_SEND_PAY_INFO_TO_EBS("中台调用EBS提供支付信息"),
    INT_RECEIVE_PAY_RESULT_OF_EBS("中台获取EBS处理订单支付结果"),
    INT_RECEIVE_RECHARGE_OF_SHOP("中台收到商场提供充值请求"),
    INT_SEND_RECHARGE_INFO_TO_EBS("中台调用EBS接口提供充值信息"),
    INT_RECEIVE_RECHARGE_RESULT_OF_EBS("中台获取EBS处理充值信息结果");



    private String desc;

    UserOperationEnums(String desc) {
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
