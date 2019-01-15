package com.newhope.moneyfeed.api.enums.wechat;

import java.text.MessageFormat;

/**
 * Created by liming on 2018/11/27.
 */
public enum WechatMsgTypeEnums {

    ORDER_NOT_PAY("订单未支付","您的订单{0}还未支付，请在{1}内前往商城完成支付，若超时未付订单将被自动关闭"),
    ORDER_TIMEOUT("订单超时关闭","您的订单{0}因超时未付已被关闭，请尽快前往商城重新下单"),
    PULL_FODDER("拉料提醒","您的订单{0}已成功修改拉料日期为{1}，请及时安排拉料"),
    ORDER_BILL("司机进场通知"," 您的订单{0} 进厂时间：{1}，开票时间：{2}， 车牌号为{3}"),
    DRIVER_OUT("司机出厂通知","您的订单{0}司机已出发，订单重量{1}，请及时准备卸货。 车牌号为{2}"),
    PAY_SUCCESS("支付成功","您的订单{0}已支付成功，支付方式：{1}，支付金额：{2}元。拉料日期：{3}，请提前安排"),
    WALLET_PASSWORD_RESET("余额账户密码重置成功","您的{0}余额账户重置密码成功。如有疑问，请致电客服{1}"),
    WALLET_RECHARGE("余额充值成功","您的{0}余额账户成功充值{1}元，如有疑问致电{2}"),
    ORDER_STOCK_CHANGE("订单库存变化","您的订单{0}库存发生变化，请重新选择拉料时间，详情致电{1}"),
    ORDER_PRE_PAY("订单待支付提醒","您的订单{0}还未支付，请在{1}小时内前往商城完成支付，若超时未付订单将被自动关闭"),
    ORDER_CLOSE("订单关闭提醒","您的订单{0}已关闭，请前往商城重新下单，如有疑问致电{1}"),
    REIMBURSE_SUCCESS("退款成功通知","您的订单{0}已退款成功，金额{1}元，如有疑问致电{2}"),
    FEEDBACK_ING("反馈处理进度","您在{0}提交的反馈已受理。如有疑问，请重新提交"),
    PAY_OVERTIME_CLOSED("订单超时关闭 ","您的订单{0}因超时未付已被关闭，请尽快前往商城重新下单"),
    TRANSPORT_TIME_MODIFY("拉料日期修改成功","您的订单{0}已成功修改拉料日期为{1}，请及时安排拉料"),
    TRANSPORT_TIME_REMIND("拉料提醒","您的订单{0}待{1}拉料，请在当日提货！详情致电{3}"),
    ORDER_WAITING_PAY("订单待支付提醒","您的订单{0}核价成功，请在{1}小时内前往商城完成支付，若超时未付订单将被自动关闭"),
    MODIFY_PAY_PWD_SUCCESS("支付密码修改成功","您的可用额度账户支付密码修改成功。如有疑问，请致电客服{0}"),
    ORDER_PROXY_CREATE("预约时间修改通知","“{0}”已成功为你在料你富商城下了一笔饲料订单，为避免订单失效，请尽快登录并完成支付。立即登陆>\n订单编号：{1}\n订单状态：{2}"),
    CLIENT_ROLE_CHANGE("角色变更","管理员已修改您的角色为【{0}】，请知晓")
    ;

    private String desc;

    private String templateStr;

    WechatMsgTypeEnums(String desc, String templateStr) {
        this.desc=desc;
        this.templateStr=templateStr;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTemplateStr() {
        return templateStr;
    }

    public void setTemplateStr(String templateStr) {
        this.templateStr = templateStr;
    }

    public  static void main(String[] args){
        String s=MessageFormat.format(WechatMsgTypeEnums.ORDER_NOT_PAY.getTemplateStr(),"12312");
        System.out.println(s);
    }

}