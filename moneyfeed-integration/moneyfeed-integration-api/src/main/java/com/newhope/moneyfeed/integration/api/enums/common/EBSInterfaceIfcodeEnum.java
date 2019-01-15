package com.newhope.moneyfeed.integration.api.enums.common;

/**
 * EBS系统提供的接口ifcode枚举
 *
 * Created by yuyanlin on 2018/12/4
 */
public enum EBSInterfaceIfcodeEnum {
    // 创建订单
    EBS_ORDER_CREATE_IFCODE("MF01001CREATEORDER001"),

    // 查询订单创建结果
    EBS_ORDER_QUERY_CREATE_RESULT_IFCODE("MF01001QUERYORDER001"),

    // 取消订单
    EBS_ORDER_CANCEL_IFCODE("MF01001CANCELORDER001"),

    // 同步订单信息（信息流 EBS to integration）
    QUERY_ORDER_INFO_IFCODE("MF01001ORDERSTATUS001"),

    // 更新订单信息（信息流 integration to EBS）
    EBS_ORDER_UPDATE_IFCODE("MF01001UPDATEORDER001"),
	
	//同步EBS物料类别   add by bo.wang 20181204
	EBS_CATEGORY_IFCODE("JBZ005ITEMKIND001"),
	
	//同步EBS物料  add by bo.wang 20181204
	EBS_MATERIAL_IFCODE("JBZ003ITEM001"),
	
	//同步EBS客户  add by bo.wang 20181204
	EBS_CUSTOMER_IFCODE("JBZ004CUSTOMER001"),
	
	//获取EBS锁库成功订单接口  add by bo.wang 20181210
	EBS_QUERY_LOCK_SUCCESS_IFCODE("MF01003ORDERINV001"),

	//EBS客户余额接口  add by bo.wang 20181217
	EBS_BALANCE_IFACE_CODE("MF01002CUSTCREDITE001"),

    //请求支付或者充值接口
    EBS_ORDER_PAY_OR_CHARGE_IFCODE("MF01001MALLPAYMENT001"),

    //EBS返回支付或者充值结果接口
    EBS_RETURN_ORDER_PAY_OR_CHARGE_RESULTS_IFCODE("MF01002MALLPAYMENTQRY001"),
    
    //获取EBS物料现有量接口
    EBS_ITEM_ONHAND_IFCODE("MF01004QUERYINV001"),
    
    //同步EBS客户对账单汇总接口
    EBS_BILL_IFCODE("MF01BILL"),
    
  //同步EBS客户对账单明细接口
    EBS_BILL_DETAIL_IFCODE("MF01BILLDETAIL")
    ;

    private String ifcode;

    EBSInterfaceIfcodeEnum(String ifcode) {
        this.ifcode = ifcode;
    }

    public String getIfcode() {
        return ifcode;
    }

}
