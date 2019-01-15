package com.newhope.moneyfeed.api.enums;

import org.apache.commons.lang.StringUtils;

public enum ResultCode {
    SUCCESS("成功", 10001),
    FAIL("服务端异常", 10002),
    PARAM_ERROR("参数错误", 10003),
    DATA_ERROR("数据异常", 10004),
    SENSITIVE_WORD("含有敏感词", 10005),
    NO_DATA_AVAILABLE("无可用数据", 10006),
    RESOURCE_AUTH_FAIL("无资源权限", 10007),
    THIRD_AUTH_FAIL("授权失败", 10008),
    THIRD_EXPIRE("timestamp过期", 10009),
    THIRD_API_UNAUTH("接口未授权", 10010),
    THIRD_SIGN_FAIL("数据验签失败", 10011),
    ILLEGAL_ACCESS("非法访问", 10012),
    REPEAT_OPERATION("重复操作", 10013),
    THIRD_ACCT_UNBIND("三方账户未绑定", 10015),
    THIRD_WECHAT_UNSUBSCRIBE("未关注微信公账号", 10016),
    MSG_SMS_CODE_ERROR("短信验证码错误", 10017),
    //用户中心 20100-20199
    USER_NOT_EXIST("用户不存在", 20100),
    USER_LOGIN_FAIL("用户登录失败", 20101),
    USER_LOGIN_REQUIRED("用户未登录", 20103),
    USER_MOBILE_EXIST("手机号码已存在", 20104),
    USER_CUSTOMER_NOT_EXIST("用户无对应客户", 20105),
	USER_SHOP_ID_IS_NULL("店铺Id为空", 20105),
	USER_LOGIN_SMSCODE_ERROR("短信验证码错误", 20106),
	USER_CAR_EXIST("车牌号已经存在", 20107),
	USER_PAYMENT_PASSWORD_EXIST("支付密码已经存在，不能重复创建", 20108),
    USER_CAR_NOT_FOUND("车辆信息不存在", 20109),
    USER_LOGIN_PASS_ERROR("密码错误", 20110),
    USER_RESET_PASS_SUCCESS("重置密码成功", 20111),
    USER_RESET_PASS_FAIL("重置密码失败", 20112),
    USER_CARD_ERROR("证件号码错误", 20114),
    USER_CUSTOMER_OR_SHOP_NOT_EXIST("登录用户无对应客户或未选择对应的商铺", 20115),
    SHOP_COLSE_CUSTOMER_BUSINESS("商户已关闭客户线上交易", 20113),
    USER_PAY_PASS_ERROR("支付密码错误", 20114),
    USER_PAY_RETRY_ERROR("支付密码重试过多", 20115),
    USER_PAY_PASS_UN_EXIST("支付密码未创建", 20116),
    USER_INFO_MODIFY_FAIL("用户个人信息修改失败",20117),
    USER_ADMIN_ROLE_NOT_CHANGE("当前只有一个管理员，不能变更为其他角色",20118),
    USER_ADMIN_DIMISSION_NOT_CHANGE("当前管理员，不能变更为离职状态",20119),
    CUSTOMER_ORDER_UNCONFIRMED("当前客户存在待支付或待拉料订单",20120),
    CUSTOMER_FORBIDDEN("当前客户已被禁用",20120),
    //订单中心 20200-20299
    OC_ORDER_CREATE_ERROR("创建订单异常",20201),
    OC_ORDER_UPDATE_ERROR("更新订单异常",20202),
    OC_SUPPLIER_RULE_ABNORMAL("商户订单规则异常",20203),
    OC_SUPPLIER_RULE_PULLDATE_MISMATCH("拉料日期不满足商户规定",20204),
    OC_ORDER_ID_NOT_EXISTS("订单ID不存在",20205),
    OC_EBS_ORDER_NO_NOT_EXISTS("EBS订单NO不存在",20206),
    OC_ORDER_LOG_CREATE_ERROR("创建订单日志异常",20207),
    OC_ORDER_DETAIL_CREATE_ERROR("创建订单明细异常",20208),
    OC_ORDER_SNAPSHOT_CREATE_ERROR("创建订单明细异常",20209),
    OC_ORDER_TRANSPORT_CREATE_ERROR("创建拉料记录异常",20210),
    OC_ORDER_GOODS_QUERY_ERROR("商品信息异常",20211),
    OC_ORDERNO_INIT_ERROR("初始化订单号异常",20212),
    OC_GEN_ORDERNO_ERROR("生成订单号异常",20213),
    OC_CANCEL_ORDER_FAIL("取消订单失败",20214),
    OC_ORDER_RULE_UPDATE_FAIL("更新订单规则失败",20215),
    OC_ORDER_CREATE_FOBIDDEN("禁止下单",20216),
    OC_ORDER_OPERALOG_ERROR("订单操作日志异常",20217),
    OC_CLOSE_ORDER_FAIL("关闭订单失败",20214),
    OC_EBS_ORDER_NO_DELETE("EBS订单已经删除",20218),
    OC_CARTS_GOODS_OVERDUE("购物车商品已过期",20219),
    OC_ORDER_NOT_EXIST("商城订单不存在",20220),
    OC_ORDER_NOT_WAITTING_PAY("商城订单不是待支付",20221),
    OC_ORDER_NOT_UPDATE_ERROR("商城订单修改失败",20222),
    OC_ORDER_QUERY_REPOSITORY_ERROR("查询库存出错",20223),
    OC_ORDER_REPOSITORY_LOW("库存不足",202234),
    OC_ORDER_STATUS_NONEDITABLE("订单不可再编辑",202235),


    //商品中心 20300-20399
    //售后中心 20400-20499
    LY_NOT_EXIST("留言不存在", 20400),
    
    //支付中心 20500-20599
    PAY_STATUS_CODE_ERROR("支付平台返回状态码不正确",20500),
    PAY_GEN_PAYNO_ERROR("生成支付订单号错误",20501),
    PAY_CREATE_ERROR("创建支付订单错误",20502),
    PAY_LOG_ERROR("生成支付订单日志错误",20503),
    PAY_ORDER_NOT_EXIST("支付订单不存在",20504),
    PAY_ORDER_NOT_PROGRESS("支付订单不是进行中",20505),
    PAY_ORDER_UPDATE_ERROR("支付订单修改不成功",20506),
    PAY_EBS_PAY_ERROR("同步EBS支付信息错误",20507),
    PAY_EBS_RECHARGE_ERROR("同步EBS充值 信息错误",20507),
    PAY_ORDER_NOT_WAITTING_PAY("订单非待支付状态",20508),
    PAY_SHOP_NOT_EXIST("支付订单中的SHOP不存在",20509),

    //基础服务 20600-20699
    BASE_MQ_SEND_FAILED("发送MQ消息失败", 20601),
    BASE_SMS_SEND_FAILED("发送短信失败", 20602),
    BASE_SMS_SEND_FAILED_MORE_MAX_COUNT("发送短信失败, 超过最大发送短信次数", 20603),
    BASE_SMS_CODE_ERROR("短信验证码错误", 20604),
    BASE_SMS_CODE_EXPIRED_ERROR("短信验证码已过期", 20605),
    ;

    private String desc;
    private Integer code;

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    ResultCode(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    public static ResultCode getResultCodeByName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        ResultCode[] enumsArray = ResultCode.values();
        for (ResultCode resultCode : enumsArray) {
            if (resultCode.name().equals(name)) {
                return resultCode;
            }
        }
        return null;
    }
}
