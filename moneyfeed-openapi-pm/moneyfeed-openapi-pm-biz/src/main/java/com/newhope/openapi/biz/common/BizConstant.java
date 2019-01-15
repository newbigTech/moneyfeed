package com.newhope.openapi.biz.common;

public class BizConstant {
	
	// CSV文件列分隔符 
	public static final String CSV_COLUMN_SEPARATOR = ",";
	
	// CSV文件行分隔符 
	public static final String CSV_RN = "\r\n";
	
	public static final String TITLE_EXPORT_INTENTION_CUSTOMER = "意向客户人员信息导出";
	
	public static final String COLUMNS_EXPORT_INTENTION_CUSTOMER = "姓名,手机,状态,所属商户,意向商户,分配商户,提交时间";
	
	public static final String TITLE_EXPORT_CUSTOMER = "客户信息导出";
	
	public static final String COLUMNS_EXPORT_CUSTOMER = "客户名称,简称,编码,标签,客户状态,最后上线时间";
	
	public static final String TITLE_EXPORT_CUSTOMER_CONTACT = "客户联系人信息导出";
	
	public static final String COLUMNS_EXPORT_CUSTOMER_CONTACT = "用户姓名,所属客户,帐号,来源,创建时间,状态,所属商户";
	
	public static final String TITLE_EXPORT_CUSTOMER_EMPLOYEE = "客户所属员工信息导出";
	
	public static final String COLUMNS_EXPORT_CUSTOMER_EMPLOYEE = "员工姓名,所属客户,角色,手机号,来源,创建时间,状态,激活状态";
	
	public static final String TITLE_EXPORT_BILL_DETAIL = "交易账单信息导出";
	
	public static final String COLUMNS_EXPORT_BILL_DETAIL = "日期,商户姓名,客户名称,订单渠道,单据号,摘要,车牌号,数量(吨),应付金额,实收金额";


}
