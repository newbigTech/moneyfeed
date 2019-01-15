package com.newhope.moneyfeed.biz.common;


public class BizConstant {

	//定价买猪模式的ID
	public static final Long TRADEMODEL000001 = 1L;
	//竞价买猪模式的ID
	public static final Long TRADEMODEL000002 = 2L;
	//求购模式
	public static final Long TRADEMODEL000003 = 3L;
	//店铺可用模式IDS
	public static final Long[] SHOP_TRADE_MODEL_IDS = {TRADEMODEL000001, TRADEMODEL000002};

	//规则模板1，定价卖猪
	public static final Long TRADERULE1 = 1L;
	//规则模板2，竞价卖猪
	public static final Long TRADERULE3 = 3L;
	
	//类目-肥猪
	public static final Long CATEGORYPIG = 2L;
	//类目-仔猪
	public static final Long CATEGORYPIGLET = 4L;
	//类目-母猪
	public static final Long CATEGORYSOW = 5L;
	
	//产品模板1
	public static final Long PRDUCTTEMPLATE1 = 1L;
	//产品模板2
	public static final Long PRDUCTTEMPLATE2 = 2L;

	//默认短信最大发送次数
    public static final Integer DEFAULT_SMS_SEND_MAX_COUNT = 1000;
    //短信验证码时间间隔,单位秒
    public static final Integer DEFAULT_SMS_SEND_TIME_INTERVAL = 60;
    //发布求购单短信最大发送次数
    public static final Integer BUYOFFER_SMS_SEND_MAX_COUNT = 10;
    //发布求购单短信有效时长3分钟-单位为毫秒
    public static final Long BUYOFFER_SMS_AVAILABLE_MILLISECOND= 3*60*1000L;

    //每头猪的红包额度
    public static final double PIG_HONGBAO_AMOUNT = 20;
    
    public static final String DEFAULT_GOODS_UNIT = "元/头";
    
    public static final boolean TRUE = Boolean.TRUE ;
    
    public static final boolean FASLE = Boolean.FALSE;
    
    public static final Long ZERO_LONG = 0L;

}
