package com.newhope.moneyfeed.common.constant;

public class StatisticsConstant {
	
	// 上架明细表下载文件名
	public static final String FILE_PUTAWAT_DETAIL = "上架每日明细表";
	// 上架明细表列名
	public static final String COLUMNS_PUTAWAT_DETAIL = "商品序号,上架发布时间,养户姓名,上架发布数量,是否为放养推送,交易头数,交割头数,交割金额,销售员姓名";
	// 
	public static final String FILE_STATISTICS_DETAIL = "统计表";
	// 上架明细表列名
	public static final String COLUMNS_STATISTICS_DETAIL = "统计日期,用户注册数,接口接受条数,放养发布订单数,自定义发布订单数,慧养猪发布订单数,放养发布头数,自定义发布头数,慧养猪发布头数,放养发布交易数,自定义发布交易数,慧养猪发布交易数,交割数,交割金额";
	// CSV文件列分隔符 
	public static final String CSV_COLUMN_SEPARATOR = ",";
	// CSV文件行分隔符
	public static final String CSV_RN = "\r\n";
	
	public static final String ADMIN_EXPORT_DELIVERY = "运营系统交割单报表导出";
	
	public static final String ADMIN_EXPORT_GOODS = "运营系统商品管理报表导出";
	
	public static final String ADMIN_EXPORT_ORDERINFO = "运营系统订单报表导出";
	
	public static final String ADMIN_EXPORT_USER_STATISTICS = "运营系统用户管理统计报表导出";
	
	public static final String ADMIN_EXPORT_SHOP_STATISTICS = "运营系统店铺管理统计报表导出";

	public static final String ADMIN_EXPORT_BUYOFFER = "运营系统求购单报表导出";

	public static final String ADMIN_EXPORT_SHELVES_APPLY = "运营系统上架单申请报表导出";

	public static final String ADMIN_EXPORT_CUSTOMER_HONGBAO = "运营系统顾客红包详情报表导出";
	
	public static final String ADMIN_EXPORT_CUSTOMER_HONGBAO_USED = "运营系统顾客红包核销详情报表导出";
	
	public static final String ADMIN_EXPORT_CUSTOMER_DELIVERY = "运营系统客户交割记录报表导出";

	public static final String ADMIN_EXPORT_POTENTIAL_USER = "运营系统潜在用户报表导出";
	public static final String ADMIN_EXPORT_PIG_PRICE = "运营系统猪价报表导出";

	public static final String COLUMNS_ADMIN_EXPORT_DELIVERY = "交割单ID,交割时间,商品名称,交割数量,交割重量,交割金额,交割均价,买家名称,买家ID,买家电话,店铺名称,店铺ID,猪场名称,关联订单ID,关联上架单ID,猪源类型,订单号,交割状态,交割方式,卖家评价,买家评价";
	
	public static final String COLUMNS_ADMIN_EXPORT_ORDERINFO = "订单ID,下单时间,买家名称,买家ID,买家电话,下单数量,下单价格,商品名称,店铺名称,店铺ID,猪场名称,交割单ID,关联上架单,猪源类型,订单状态";
	
	public static final String COLUMNS_ADMIN_EXPORT_USER_STATISTICS = "用户ID,用户名称,联系电话,注册时间,所属店铺,所属店铺类型,店铺身份,会员店铺,申请店铺,被拒绝店铺,用户状态,交易地址,详细地址,用户角色,用户身份,单次交易量,所属公司,被推荐次数,历史上架头数,历史求购头数,最近一次上架／求购时间,最近一次交易时间";

	public static final String COLUMNS_ADMIN_EXPORT_SHOP_STATISTICS = "店铺名称,店铺ID,注册日期,店主,联系人,联系电话,店铺地址,猪场数,子账号数,会员数,申请会员数,拒绝会员数,店铺状态,店铺类型,是否活动店铺,云端放养ID,慧养猪ID";

	public static final String COLUMNS_ADMIN_EXPORT_GOODS = "上架单ID,开拍时间,发布时间,商品名,店铺名称,猪场名称,店主,店主电话,发布人,发布人电话,上架数量,价格,预估均重,预订数量,交割数量,交割金额,交割地址,推送渠道,商品发布来源,商品描述标签,竞价次数,竞价成功用户名,推荐次数,推荐成功次数,是否活动商品," +
			"猪源类型,模式,状态,关联订单ID";

	public static final String COLUMNS_ADMIN_EXPORT_SHEVLES_APPLY = "联系人电话,申请人姓名,店主电话,店主,申请时间,店铺名";
	public static final String COLUMNS_ADMIN_EXPORT_BUYOFFER = "求购单ID,品种,求购地区,联系人,联系电话,发布时间,求购结束时间,报价次数,状态,求购标签,价格,推荐次数,补充说明";

	public static final String COLUMNS_ADMIN_EXPORT_CUSTOMER_HONGBAO = "用户ID,认证客户ID,姓名,手机号,身份证号,可用红包余额,待激活红包余额,可用红包数量";
	
	public static final String COLUMNS_ADMIN_EXPORT_CUSTOMER_HONGBAO_USED = "用户ID,姓名,手机号,购买吨数,折扣金额,核销时间";
	
	public static final String COLUMNS_ADMIN_EXPORT_CUSTOMER_DELIVERY = "交割单号,用户ID,姓名,手机号,线上交割头数,线上交割金额,线上交割单价,线上交割卖家姓名,激活红包总额,剩余可用红包金额,交割日期,审核转态";

	public static final String ADMIN_EXPORT_BUYER_STATISTICS = "运营系统用户管理-买家统计报表导出";

	public static final String COLUMNS_ADMIN_EXPORT_BUYER_STATISTICS = "用户ID,用户名称,联系电话,用户身份,单次交易量,历史交易品类,最新交易地址,最新详细地址,买猪偏好,历史求购头数,历史求购单数,历史交易头数,历史交易单数,历史交割头数,历史交割单数,交易合作人数,会员店铺,注册时间,最近交易时间,被推荐次数";

	public static final String ADMIN_EXPORT_SALER_STATISTICS = "运营系统用户管理-卖家统计报表导出";

	public static final String COLUMNS_ADMIN_EXPORT_SALER_STATISTICS = "用户ID,用户名称,联系电话,店铺名称,店铺类型,店铺角色,单次交易量,历史交易品类,最新交易地址,最新详细地址,历史上架头数,历史上架单数,历史交易头数,历史交易单数,历史交割单数,交易合作人数,注册时间,最近上架时间,最近交易时间,最近交割时间,被推荐次数";
	public static final String COLUMNS_ADMIN_EXPORT_POTENTIAL_USER = "潜在用户ID,用户名称,联系电话,用户角色,用户身份,单次交易量,交易品类,最近交易地址,详细地址,买猪偏好,录入数据时间,邀请/录入数据人,数据来源,被推荐求购次数,被推荐买猪次数,用户意向度,跟进备注";

	public static final String COLUMNS_ADMIN_EXPORT_PIG_PRICE = "日期,地区,品类,猪易通最低价,猪易通最高价,猪易通建议价格,卓创最高价,卓创最低价,卓创建议价格,运营配置最低价,运营配置最高价,运营建议价格,用户评价“高”,用户评价“低”";

	public static final String BM_EXPORT_ORDER = "bm系统订单报表导出";
	public static final String COLUMNS_BM_EXPORT_ORDER = "订单号,EBS订单号,客户名,客户ID,购买总量,购买总金额,应付总额,资金订单号," +
			"银行流水号,下单时间,完成时间,进场时间,开票时间,出厂时间,订单状态,EBS退款订单号,银行退款流水号,预计拉料日期,车牌号,司机姓名,手机号码,下单渠道,下单人";

	public static final String BM_EXPORT_ORDER_GOODS_DETAIL = "bm系统订单商品明细报表导出";
	public static final String COLUMNS_BM_EXPORT_ORDER_GOODS_DETAIL = "订单号,EBS订单号,客户名,客户ID,商品EBS编码,商品名称,商品数量,商品总吨数," +
			"下单时间,完成时间,订单状态,EBS退款订单号,预计拉料日期,车牌号,下单渠道,下单人";

	public static final String PM_EXPORT_LABEL = "pm系统标签报表导出";
	public static final String COLUMNS_PM_EXPORT_LABEL = "标签名称,标签类别,生成方式,客户数,备注,创建时间";

	public static final String PM_EXPORT_ROLE = "平台角色报表导出";
	public static final String COLUMNS_PM_EXPORT_ROLE = "id,分类,角色名称,角色说明";

	public static final String PM_EXPORT_SHOP = "pm系统店铺报表导出";
	public static final String COLUMNS_PM_EXPORT_SHOP = "店铺名称,公司名称,编码,状态,负责人姓名,负责人账号,创建时间";

	public static final String BM_EXPORT_SHOP = "BM系统店铺员工报表导出";
	public static final String COLUMNS_BM_EXPORT_SHOP = "ID,姓名,手机号码,状态,角色";

	public static final String PM_BM_EXPORT_SHOP = "PM系统BM店铺员工报表导出";
	public static final String PM_COLUMNS_BM_EXPORT_SHOP = "ID,姓名,手机号码,状态,店铺,角色";

	public static final String BM_EXPORT_PAY_STATMENT = "支付流水报表导出";
	public static final String BM_COLUMNS_EXPORT_PAY_STATMENT = "创建时间,客户名称,单据号,支付流水号,金额,支付方式";

	public static final String PM_EXPORT_PAY_STATMENT = "支付流水报表导出";
	public static final String PM_COLUMNS_EXPORT_PAY_STATMENT = "创建时间,商户名称,客户名称,单据号,支付流水号,金额,支付方式";

}