package com.newhope.order.biz.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newhope.moneyfeed.order.api.enums.OrderPayTypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Maps;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.dto.uclog.UserOperationLogDtoReq;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.SmsTemplateEnums;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.api.enums.wechat.WechatMsgTypeEnums;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.goods.api.dto.request.ProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuAttributesDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyFeedToEbsOrderCreateProductDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderCreateDtoReq;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderTypeEnum;
import com.newhope.moneyfeed.order.api.bean.OrderChangeLogModel;
import com.newhope.moneyfeed.order.api.bean.OrderDetailModel;
import com.newhope.moneyfeed.order.api.bean.OrderFeedTransportModel;
import com.newhope.moneyfeed.order.api.bean.OrderInfoModel;
import com.newhope.moneyfeed.order.api.bean.OrderPresentFeedModel;
import com.newhope.moneyfeed.order.api.bean.OrderRuleModel;
import com.newhope.moneyfeed.order.api.bean.OrderSnapshotModel;
import com.newhope.moneyfeed.order.api.dto.request.integration.CancelOrderToEbsDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.integration.TransportInfoToEbsDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderCloseDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoModifyDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoQueryDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.OrderInfoSelectDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.nh.OrderBuilder;
import com.newhope.moneyfeed.order.api.dto.request.order.nh.OrderNHCreateDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.nh.OrderNHUpdateDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.order.nh.rule.FeedTransportDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.product.ProductDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.product.SkuAttrParam;
import com.newhope.moneyfeed.order.api.dto.request.shop.UcShopDtoReq;
import com.newhope.moneyfeed.order.api.dto.request.user.CustomerUserDtoReq;
import com.newhope.moneyfeed.order.api.dto.response.OrderDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderCreateDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderGoodsDetailDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderInfoDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.OrderValidateRepositoryDtoResult;
import com.newhope.moneyfeed.order.api.dto.response.order.PayOrderNoDtoResult;
import com.newhope.moneyfeed.order.api.enums.OrderOperationSourceEnums;
import com.newhope.moneyfeed.order.api.enums.OrderOperationTypeEnums;
import com.newhope.moneyfeed.order.api.enums.OrderStatusEnum;
import com.newhope.moneyfeed.order.api.enums.PayLimitTypeEnums;
import com.newhope.moneyfeed.order.dal.BaseDao;
import com.newhope.moneyfeed.order.dal.dao.OrderInfoDao;
import com.newhope.moneyfeed.user.api.enums.client.OrderCalculateRuleEnums;
import com.newhope.order.biz.hepler.NumberGeneraterHelper;
import com.newhope.order.biz.rpc.feign.SmsFeignClient;
import com.newhope.order.biz.rpc.feign.UserOperationFeignClient;
import com.newhope.order.biz.rpc.feign.pc.ProductFeignClient;
import com.newhope.order.biz.rpc.feign.uc.ClientUserFeignClient;
import com.newhope.order.biz.rpc.feign.uc.ShopFeignClient;
import com.newhope.order.biz.service.OrderChangeLogService;
import com.newhope.order.biz.service.OrderDetailService;
import com.newhope.order.biz.service.OrderFeedTransportService;
import com.newhope.order.biz.service.OrderIntegrationService;
import com.newhope.order.biz.service.OrderMsgService;
import com.newhope.order.biz.service.OrderPresentFeedService;
import com.newhope.order.biz.service.OrderRuleService;
import com.newhope.order.biz.service.OrderService;
import com.newhope.order.biz.service.OrderSnapshotService;
import com.newhope.order.biz.utils.SinaApiUtils;

@Service("OrderService")
public class OrderServiceImpl extends BaseServiceImpl<OrderInfoModel> implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    OrderInfoDao orderInfoDao;
    @Autowired
    OrderRuleService orderRuleService;
    @Autowired
    OrderSnapshotService orderSnapshotService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    OrderFeedTransportService orderFeedTransportService;
    @Autowired
    OrderChangeLogService orderChangeLogService;
    @Autowired
    OrderIntegrationService orderIntegrationService;
    @Autowired
    ProductFeignClient productFeignClient;
    @Autowired
    NumberGeneraterHelper orderNoGeneraterHelper;
    @Autowired
    ClientUserFeignClient clientUserFeignClient;
    @Autowired
    SmsFeignClient smsFeignClient;
    @Autowired
    OrderMsgService orderMsgService;
    @Autowired
    ShopFeignClient shopFeignClient;
    @Autowired
    UserOperationFeignClient userOperationFeignClient;
    @Autowired
    OrderPresentFeedService presentFeedService;

    @Value("${newhope.wechat.hompageurl}")
    private String hompageurl;

    private final static String weight_cn = "重量";
    private final static String BLANK_SAPCE = " ";
    private final static int RETRY_TIMES = 2;

    @Override
    protected BaseDao<OrderInfoModel> getDao() {
        return orderInfoDao;
    }

    /**
     * 执行newhope订单创建
     * creator : pudongliang
     * dateTime : 2018年11月21日下午5:31:32
     */
    @Transactional
    public void excuteCreateNHOrder(OrderBuilder builder) {
    	AppContext.getSpringContext().getBean(this.getClass()).createOrder(builder);
        /**请求集成层创建订单*/
        DtoResult result = AppContext.getSpringContext().getBean(this.getClass()).syncOrderCreateInfo(builder);
        //根据逆向设计，根据result做相应处理
        if (!ResultCode.SUCCESS.getCode().equals(result.getCode())) {
            logger.info("同步失败:" + result.getMsg());
            throw new BizException("同步失败:" + result.getMsg());
        }
    }
    
    /**
     * 执行订单修改-并创建
     * creator : pudongliang
     * dateTime : 2018年11月21日下午5:31:32
     */
    @Transactional
    public void excuteCreateAfterCancel(OrderBuilder builder,String oldOrderNo) {
    	AppContext.getSpringContext().getBean(this.getClass()).orderCreateAfterCancel(builder,oldOrderNo);
        /**请求集成层创建订单*/
        DtoResult result = AppContext.getSpringContext().getBean(this.getClass()).syncOrderCreateInfo(builder);
        //根据逆向设计，根据result做相应处理
        if (!ResultCode.SUCCESS.getCode().equals(result.getCode())) {
            logger.info("同步失败:" + result.getMsg());
            throw new BizException("同步失败:" + result.getMsg());
        }
    }

	protected DtoResult syncOrderCreateInfo(OrderBuilder builder) {
        OrderInfoModel orderInfo = builder.getOrderInfo();
        OrderFeedTransportModel feedTransport = builder.getOrderFeedTransport();
        List<OrderDetailModel> orderDetails = builder.getOrderDetails();

        MoneyfeedToEbsOrderCreateDtoReq moneyfeedToEbsOrderCreateDtoReq = new MoneyfeedToEbsOrderCreateDtoReq();
        moneyfeedToEbsOrderCreateDtoReq.setCompanyShortCode(orderInfo.getCompanyShortCode());
        moneyfeedToEbsOrderCreateDtoReq.setCustomerNum(orderInfo.getCustomerNum());
        moneyfeedToEbsOrderCreateDtoReq.setMoneyFeedOrderId(orderInfo.getId().toString());
        moneyfeedToEbsOrderCreateDtoReq.setMoneyFeedOrderNo(orderInfo.getOrderNo());
        //商城、标记是否来自于商城、默认MONEYFEED
        moneyfeedToEbsOrderCreateDtoReq.setChannel("MONEYFEED");
        moneyfeedToEbsOrderCreateDtoReq.setShopId(orderInfo.getUcShopId().toString());
        moneyfeedToEbsOrderCreateDtoReq.setOrgId(orderInfo.getOrgId());
        //用户与中台定义不一致，转换一下
        if (OrderCalculateRuleEnums.SYETEM_CALCULATE.name().equals(orderInfo.getCusOrderType())) {
            moneyfeedToEbsOrderCreateDtoReq.setOrderType(EbsOrderTypeEnum.AUTOMATIC_TYPE.name());
        } else if (OrderCalculateRuleEnums.PERSON_CALCULATE.name().equals(orderInfo.getCusOrderType())) {
            moneyfeedToEbsOrderCreateDtoReq.setOrderType(EbsOrderTypeEnum.MANUAL_REVIEW_TYPE.name());
        }
        moneyfeedToEbsOrderCreateDtoReq.setPlanTransportTime(DateUtil.getDateStr(orderInfo.getPlanTransportTime(), DateUtil.YYYY_MM_DD));
        moneyfeedToEbsOrderCreateDtoReq.setCarNo(feedTransport.getCarNo());
        List<MoneyFeedToEbsOrderCreateProductDtoReq> productList = new ArrayList<>();
        for (OrderDetailModel orderDetail : orderDetails) {
            MoneyFeedToEbsOrderCreateProductDtoReq moneyFeedToEbsOrderCreateProductDtoReq = new MoneyFeedToEbsOrderCreateProductDtoReq();
            moneyFeedToEbsOrderCreateProductDtoReq.setOrganizationCode(orderDetail.getOrganizationCode());
            moneyFeedToEbsOrderCreateProductDtoReq.setProductCount(orderDetail.getTotalFeedWeight());
            moneyFeedToEbsOrderCreateProductDtoReq.setSuppliesCode(orderDetail.getSuppliesCode());
            moneyFeedToEbsOrderCreateProductDtoReq.setUnit(orderDetail.getUnit());
            productList.add(moneyFeedToEbsOrderCreateProductDtoReq);
        }
        moneyfeedToEbsOrderCreateDtoReq.setProductList(productList);

        return orderIntegrationService.sendOrderCreateInfo(moneyfeedToEbsOrderCreateDtoReq);
    }

    /**
     * @param date  the original date.
     * @param field  the calendar field.
     * @param amount the amount of date or time to be added to the field.
     * @creator : pudongliang
     * @dateTime : 2018年11月24日上午10:49:28
     */
    private Date calculateDate(Date date, int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * 验证拉料时间是否符合规则
     *
     * @param rule 商户拉货规则
     *             transportDate 拉料日期(不能为空)
     *             currentDate 当前日期 (不能为空)
     *             creator : pudongliang
     *             dateTime : 2018年11月22日下午2:47:02
     */
    private boolean validTransportDate(OrderRuleModel rule, Date currentDate, Date transportDate) {
        if (rule == null)
            return true;
        int startDay = rule.getTransportStartDay() == null ? 0 : rule.getTransportStartDay();
        if (transportDate.getTime() < calculateDate(currentDate, Calendar.DAY_OF_YEAR, startDay).getTime()) {
            return false;
        }
        Integer endDay = rule.getTransportEndDay();
        if (endDay != null && endDay - startDay >= 0) {
            if (transportDate.getTime() > calculateDate(currentDate, Calendar.DAY_OF_YEAR, endDay.intValue()).getTime()) {
                return false;
            }
        }
        return true;
    }

    /***
     * 拉料日期 + 限制天数 + 限制时分 = 拉料限制日期
     * creator : pudongliang
     * dateTime : 2018年11月22日下午3:13:10
     */
    @Override
    public Date calLimitDate(Date transportDate, int day, String time) {
        String limitDate = DateUtils.date2Str(calculateDate(transportDate, Calendar.DAY_OF_YEAR, day),
                DateUtils.date_sdf);
        limitDate = limitDate + BLANK_SAPCE + time;
        return DateUtils.str2Date(limitDate, DateUtils.time_sdf);
    }


    /**
     * newhope订单数据构建
     * creator : pudongliang
     * dateTime : 2018年11月21日下午4:55:55
     *
     * @throws ParseException
     */

    private OrderBuilder buildNHOrderData(OrderNHCreateDtoReq dtoReq) throws BizException {

        Date currentDate = new Date();//订单创建时间
        FeedTransportDtoReq feedTransport = dtoReq.getPullFeedRule();
        Date transportDate = feedTransport.getPlanTransportTime();//订单拉料日期 YYYY-MM-DD格式
        CustomerUserDtoReq user = dtoReq.getUser();
        UcShopDtoReq shop = dtoReq.getShop();
        /**商户订单规则验证*/
        OrderRuleModel ruleQueryModel = new OrderRuleModel();
        ruleQueryModel.setUcShopId(shop.getUcShopId());
        List<OrderRuleModel> rules = orderRuleService.query(ruleQueryModel);
        OrderRuleModel rule = null;
        String limitType = null;
        //规则数据验证
        if (CollectionUtils.isNotEmpty(rules)) {
            rule = rules.get(0);
            if (rules.size() > 1) {
                logger.error(String.format(ResultCode.OC_SUPPLIER_RULE_ABNORMAL.getDesc() + ":{%s}", JSON.toJSONString(dtoReq)));
                throw new BizException(ResultCode.OC_SUPPLIER_RULE_ABNORMAL);
            }
        }
        /**新建订单主数据*/
        OrderInfoModel orderInfo = new OrderInfoModel();
        BeanUtils.copyProperties(shop, orderInfo);//商户信息
        BeanUtils.copyProperties(user, orderInfo);//用户客户信息
        if (rule != null) {
            //验证当前时间是否可以下单
           if(!dtoReq.getProxy()){
        	 //拉料日期规则验证
               if (!validTransportDate(rule, DateUtils.trunc(currentDate), transportDate)) {
                   throw new BizException(ResultCode.OC_SUPPLIER_RULE_PULLDATE_MISMATCH);
               }
        	   if (StringUtils.isNotBlank(rule.getTransportTime())) {
                   if (DateUtils.date_sdf.format(currentDate).equals(DateUtils.date_sdf.format(transportDate))) {
                       if (DateUtils.str2Date((DateUtils.date_sdf.format(currentDate)) + BLANK_SAPCE + rule.getTransportTime(),
                               DateUtils.time_sdf).getTime() <= currentDate.getTime()) {
                           throw new BizException(ResultCode.OC_ORDER_CREATE_FOBIDDEN.getCode(), "不能下当日拉料的订单");
                       }
                   }
               }
           }

            limitType = rule.getLimitTimeType();
            //根据类型计算支付最后时间
            try {
                if (PayLimitTypeEnums.LIMIT_TIME_CREATE_END.name().equals(limitType) || 
                		PayLimitTypeEnums.LIMIT_TIME_PRICE.name().equals(limitType)) {//下单后支付时间限制
                    int h = rule.getLimitTimeCreateEnd();
                    orderInfo.setPayLimitTime(calculateDate(currentDate, Calendar.HOUR, h));
                } else if (PayLimitTypeEnums.LIMIT_TIME_TRANSPORT.name().equals(limitType)) {//拉料前支付限制时间设置
                    orderInfo.setPayLimitTime(calLimitDate(transportDate, rule.getLimitTimeTransportDay(), rule.getLimitTimeTransportTime()));
                }
            } catch (Exception e) {
                logger.error(String.format("error:{{OrderServiceImpl.buildNHOrderData:处理商户规则控制支付时间异常}:{bizData:{%s},ruleDate:{%s}}}", JSON.toJSONString(dtoReq), rule));
                throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR);
            }
            //计算最后可以编辑时间
            orderInfo.setCanModifyTime(calLimitDate(transportDate, rule.getCanModifyDay(), rule.getCanModifyTime()));
            orderInfo.setNotifyMobile(rule.getNotifyMobile());
            orderInfo.setUcShopMobile(rule.getUcShopMobile());
        }
        //生成订单号
        int retryTimes = 0;
        String orderNo = orderNoGeneraterHelper.genOrderNo();
        while(StringUtils.isBlank(orderNo)){
        	orderNo = orderNoGeneraterHelper.genOrderNo();
        	if(retryTimes == RETRY_TIMES){
        		throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR.getCode(),"创建订单超时,请稍后再试");
        	}
        	retryTimes ++;
        }
        
        orderInfo.setGmtCreated(currentDate);
        orderInfo.setLimitTimeType(limitType);
        orderInfo.setOrderNo(orderNo);
        orderInfo.setShortNo(orderNo);
        orderInfo.setStatus(OrderStatusEnum.CREATING.name());
        orderInfo.setPlanTransportTime(feedTransport.getPlanTransportTime());
        orderInfo.setOldPlanTransportTime(feedTransport.getPlanTransportTime());
        orderInfo.setProxName(dtoReq.getProxName());
        orderInfo.setProxUserId(dtoReq.getProxUserId());
        orderInfo.setOrderChannel(dtoReq.getOrderChannel());
        orderInfo.setPayFlag(false);
        orderInfo.setPayType(OrderPayTypeEnum.NONE.name());

        List<ProductDtoReq> products = dtoReq.getProducts();
        int psize = products.size();
        /**商品快照数据*/
        List<OrderSnapshotModel> snapshots = new ArrayList<>(psize);
        List<OrderDetailModel> orderDetails = new ArrayList<>(psize);
        List<Long> skuids = new ArrayList<>(psize);
        Map<Long, Integer> skuCountMap = Maps.newHashMap();
        for (ProductDtoReq productDto : products) {
            skuids.add(productDto.getSkuid());
            skuCountMap.put(productDto.getSkuid(), productDto.getCount());
        }
        ProductQueryDtoReq productQueryDtoReq = new ProductQueryDtoReq();
        productQueryDtoReq.setProductSkuIds(skuids);
        productQueryDtoReq.setShopId(shop.getUcShopId());
        productQueryDtoReq.setVerify(Boolean.TRUE);
        productQueryDtoReq.setCustomerNum(user.getCustomerNum());
        BaseResponse<ProductSkuListDtoResult> skuResponse = productFeignClient.getProduct(productQueryDtoReq);
        ProductSkuListDtoResult skuDtoResult = skuResponse.getData();
        if (!ResultCode.SUCCESS.getCode().equals(skuResponse.getCode()) || skuDtoResult == null) {
            throw new BizException(ResultCode.OC_ORDER_GOODS_QUERY_ERROR);
        }
        if (CollectionUtils.isEmpty(skuDtoResult.getProductSkuList())) {
            logger.error(String.format( "查询商品:skuids:{%s},shopid:{%s},resp:{%s}", JSON.toJSONString(skuids)),shop.getUcShopId(),JSON.toJSONString(skuResponse));
            throw new BizException(ResultCode.OC_ORDER_GOODS_QUERY_ERROR);

        }
        
        BigDecimal totalOrginalAmount = BigDecimal.ZERO;//原始总金额
        BigDecimal totalFeedWeight = BigDecimal.ZERO;//原始总重量
        for (ProductSkuDtoResult skuDto : skuDtoResult.getProductSkuList()) {
            /**构建商品详情数据*/
            OrderDetailModel orderDetail = new OrderDetailModel();
            BeanUtils.copyProperties(skuDto, orderDetail);
            orderDetail.setOrderNo(orderNo);
            orderDetail.setCount(skuCountMap.get(skuDto.getProductSkuId()));
            orderDetail.setUnit(skuDto.getPrimaryUom());
            /**构建商品快照数据*/
            OrderSnapshotModel snapshot = new OrderSnapshotModel();
            BeanUtils.copyProperties(skuDto, snapshot);
            snapshot.setSkuId(skuDto.getProductSkuId());
            snapshot.setOrderNo(orderNo);
            snapshot.setQuantity(skuCountMap.get(snapshot.getSkuId()));
            snapshot.setShopId(shop.getUcShopId());

            List<ProductSkuAttributesDtoResult> skuAttrParamsDto = skuDto.getProductSkuAttributesExModels();
            BigDecimal qualityDecimal = skuCountMap.get(snapshot.getSkuId()) == null ?
                    BigDecimal.ZERO : BigDecimal.valueOf(skuCountMap.get(snapshot.getSkuId()));
            if (CollectionUtils.isNotEmpty(skuAttrParamsDto)) {
                List<SkuAttrParam> skuAttrParams = new ArrayList<>(skuAttrParamsDto.size());
                for (ProductSkuAttributesDtoResult skuAttrParamDto : skuAttrParamsDto) {
                    SkuAttrParam skuAttrParam = new SkuAttrParam();
                    BeanUtils.copyProperties(skuAttrParamDto, skuAttrParam);
                    skuAttrParams.add(skuAttrParam);
                    if (weight_cn.equals(skuAttrParamDto.getName())) {
                        orderDetail.setWeightParam(skuAttrParamDto.getParmValue());
                        if (StringUtils.isNotBlank(orderDetail.getWeightParam())) {
                            BigDecimal spuTotalWeight = BigDecimal.valueOf(Double.valueOf(orderDetail.getWeightParam())).multiply(qualityDecimal);
                            orderDetail.setTotalFeedWeight(spuTotalWeight);
                            totalFeedWeight = totalFeedWeight.add(spuTotalWeight);
                            BigDecimal spuAmount = spuTotalWeight.multiply(skuDto.getPrice() == null ? BigDecimal.ZERO : skuDto.getPrice());
                            orderDetail.setTotalOrginalAmount(spuAmount);
                            totalOrginalAmount = totalOrginalAmount.add(spuAmount);
                        }
                    }
                }
                snapshot.setSkuAttrParams(JSON.toJSONString(skuAttrParams));
            }

            snapshots.add(snapshot);
            orderDetails.add(orderDetail);
        }
        orderInfo.setCompanyShortCode(skuDtoResult.getProductSkuList().get(0).getCompanyShortCode());
        orderInfo.setTotalFeedWeight(totalFeedWeight);//设置订单商品总重
        orderInfo.setTotalOrginalAmount(totalOrginalAmount);//设置订单厂价总金额
        /**构建拉料计划数据*/
        OrderFeedTransportModel orderFeedTransport = new OrderFeedTransportModel();
        BeanUtils.copyProperties(feedTransport, orderFeedTransport);
        orderFeedTransport.setOrderNo(orderNo);
        orderFeedTransport.setSource(OrderOperationSourceEnums.MONEYFEED.name());

        /**构建操作日志数据*/
        OrderChangeLogModel changeLog = new OrderChangeLogModel();
        changeLog.setOrderId(orderInfo.getId());
        changeLog.setOrderNo(orderNo);
        changeLog.setBizData(JSON.toJSONString(dtoReq));
        changeLog.setOperType(OrderOperationTypeEnums.CREATE_ORDER.name());
        changeLog.setOrderStatus(OrderStatusEnum.CREATING.name());
        if (dtoReq.getProxy()) {
            changeLog.setOperSource(OrderOperationSourceEnums.BM.name());
            changeLog.setOperUserId(dtoReq.getProxUserId());
            changeLog.setOperUserName(dtoReq.getProxName());
        } else {
            changeLog.setOperSource(OrderOperationSourceEnums.MONEYFEED.name());
            changeLog.setOperUserId(user.getBuyerId());
            changeLog.setOperUserName(user.getBuyerName());
        }
        
        
        /**构建用户操作日志数据*/
        UserOperationLogDtoReq userOperationLogDtoReq = new UserOperationLogDtoReq();
        userOperationLogDtoReq.setAfterEventStatus(orderInfo.getStatus());
        userOperationLogDtoReq.setAfterEventAmount(BigDecimal.ZERO);
        userOperationLogDtoReq.setBeforeEventAmount(BigDecimal.ZERO);
        userOperationLogDtoReq.setEbsOrderId(orderInfo.getEbsOrderNo());
        userOperationLogDtoReq.setUserOperEventType(UserOperEventType.ORDER);
        userOperationLogDtoReq.setSource(UserOperSourceType.ORDER_CENTER);
        userOperationLogDtoReq.setUserOperationEnums(UserOperationEnums.OD_CREATE_SHOP_ORDER);

        return OrderBuilder.Builder.newInstance()
                .orderInfo(orderInfo)
                .orderDetails(orderDetails)
                .orderFeedTransport(orderFeedTransport)
                .orderSnapshot(snapshots)
                .orderChangeLog(changeLog)
                .opreaLog(userOperationLogDtoReq)
                .build();
    }
    
    @Transactional
    public void orderCreateAfterCancel(OrderBuilder builder,String oldOrderNo) {
    	AppContext.getSpringContext().getBean(this.getClass()).createOrder(builder);
    	OrderCloseDtoReq orderCloseDtoReq = new OrderCloseDtoReq();
    	orderCloseDtoReq.setOrderNo(oldOrderNo);
    	if(!ResultCode.SUCCESS.getCode().equals(AppContext.getSpringContext().getBean(this.getClass()).closeOrder(orderCloseDtoReq).getCode())){
    		throw new BizException(ResultCode.OC_ORDER_UPDATE_ERROR);
    	}
    }
    
    /**
     * 订单聚合模型创建订单
     * creator : pudongliang
     * dateTime : 2018年11月22日下午3:25:08
     */
    @Transactional
    public void createOrder(OrderBuilder builder) {

        /**插入订单主表数据*/
        OrderInfoModel order = builder.getOrderInfo();
        Long orderId = null;
        if (order != null) {
            if (!save(Arrays.asList(order))) {
                logger.error(String.format(ResultCode.OC_ORDER_CREATE_ERROR.getDesc() + ":{%s}", builder));
                throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR);
            }
            orderId = order.getId();
        }
        /**插入订单详情数据*/
        List<OrderDetailModel> orderDetails = builder.getOrderDetails();
        if (CollectionUtils.isNotEmpty(orderDetails)) {
            for (OrderDetailModel detail : orderDetails) {
                detail.setOrderId(orderId);
            }
            if (!orderDetailService.save(orderDetails)) {
                logger.error(String.format(ResultCode.OC_ORDER_DETAIL_CREATE_ERROR.getDesc() + ":{%s}", builder));
                throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR);
            }
        }
        /**插入商品快照数据*/
        List<OrderSnapshotModel> snapshots = builder.getOrderSnapshots();
        if (CollectionUtils.isNotEmpty(snapshots)) {
            for (OrderSnapshotModel snapshot : snapshots) {
                snapshot.setOrderId(orderId);
            }
            if (!orderSnapshotService.save(snapshots)) {
                logger.error(String.format(ResultCode.OC_ORDER_SNAPSHOT_CREATE_ERROR.getDesc() + ":{%s}", builder));
                throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR);
            }
        }

        /**插入拉料记录数据*/
        OrderFeedTransportModel feedTransport = builder.getOrderFeedTransport();
        if (feedTransport != null) {
            feedTransport.setOrderId(orderId);
            if (!orderFeedTransportService.save(Arrays.asList(feedTransport))) {
                logger.error(String.format(ResultCode.OC_ORDER_TRANSPORT_CREATE_ERROR.getDesc() + ":{%s}", builder));
                throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR);
            }
        }

        /**插入订单日志数据*/
        OrderChangeLogModel changeLog = builder.getOrderChangeLog();
        if (changeLog != null) {
            changeLog.setOrderId(orderId);
            if (!orderChangeLogService.save(Arrays.asList(changeLog))) {
                logger.error(String.format(ResultCode.OC_ORDER_LOG_CREATE_ERROR.getDesc() + ":{%s}", builder));
                throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR);
            }
        }
        /**插入用户操作日志数据*/
        UserOperationLogDtoReq operationLog = builder.getOperaLog();
        if (operationLog != null) {
            operationLog.setAfterEventModel(JSON.toJSONString(order));
            operationLog.setEventDate(order.getGmtCreated());
            operationLog.setEventId(orderId);
            operationLog.setOrderId(orderId);
			BaseResponse<DtoResult> operaResp = userOperationFeignClient.recordingUserOperation(operationLog);
			if (!operaResp.isSuccess()) {
				logger.error(String.format(ResultCode.OC_ORDER_OPERALOG_ERROR.getDesc() + "[error:{%s-%s}," + "data:{%s}]",
								operaResp.getCode(), operaResp.getMsg(), builder));
				throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR);
			}
        }
    }


    @Override
    public OrderCreateDtoResult createNHOrder(OrderNHCreateDtoReq dtoReq) {
        OrderCreateDtoResult orderDtoResult = new OrderCreateDtoResult();
        orderDtoResult.setCode(ResultCode.SUCCESS.getCode());
        OrderBuilder builder = null;
        try {
            builder = buildNHOrderData(dtoReq);
        } catch (BizException e) {
            orderDtoResult.setCode(e.getCode());
            orderDtoResult.setMsg(e.getMessage());
            return orderDtoResult;
        }
        AppContext.getSpringContext().getBean(this.getClass()).excuteCreateNHOrder(builder);
        orderDtoResult.setOrderNo(builder.getOrderInfo().getOrderNo());
		if(OrderCalculateRuleEnums.PERSON_CALCULATE.name().equals(builder.getOrderInfo().getCusOrderType())){
			// 【料你富】今日订单${orderNo}待核计应付金额，请登录EBS查看详情并及时核价。
			// 发送短信给商户配置联系人
			try {
				if(StringUtils.isNotBlank(builder.getOrderInfo().getNotifyMobile())){
					Map<String, String> paramMap = new HashMap<>(1);// sms参数集合
					paramMap.put("orderNo", builder.getOrderInfo().getOrderNo());
					orderMsgService.sendSmsMsg(builder.getOrderInfo().getNotifyMobile(),SmsTemplateEnums.SMS_151772902.name(), paramMap);
				}
			} catch (Exception e) {
				logger.error("OrderServiceImpl.createOrderNH发送sms消息异常:{}", e);
			}
		}
        //代用户下单需发送消息
        if (dtoReq.getProxy()) {
            String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + builder.getOrderInfo().getId() + "&pre_path=_static_html_order_orderList";
            try {
            	url = SinaApiUtils.CompressUrl.compressUrl(url, null);	
			} catch (Exception e) {
				logger.error("OrderServiceImpl.createOrderNH generate short url exception");
			}
            // 发送短信：［料你富］“XXX”已成功为你在料你富商城下了一笔饲料订单，为避免订单失效，请尽快微信搜索关注“料你富商城”，登录并完成支付。XXX链接XXX
            Map<String, String> paramMap = new HashMap<>(2);//sms参数集合
            paramMap.put("shopName", dtoReq.getShop().getUcShopName());
            paramMap.put("order", url);
            orderMsgService.sendSmsMsg(dtoReq.getUser().getBuyerMobile(), SmsTemplateEnums.SMS_152210484.name(), paramMap);

            // 通知：
            // 任务名称：预约时间修改通知
            // 内容：“XXX”已成功为你在料你富商城下了一笔饲料订单，为避免订单失效，请尽快登录并完成支付。立即登陆>
            // 订单编号：672367247347
            // 订单状态：待支付
            List<String> params = new ArrayList<>();
            params.add(dtoReq.getProxName());
            params.add(builder.getOrderInfo().getOrderNo());
            params.add(OrderStatusEnum.getEnumsMap().get(builder.getOrderInfo().getStatus()));
            orderMsgService.sendWechatMsg(dtoReq.getUser().getBuyerId(), WechatMsgTypeEnums.ORDER_PROXY_CREATE, params, url);
        }
        return orderDtoResult;
    }

    @Override
    public PageList<OrderDtoResult> queryOrderInfoList(OrderInfoQueryDtoReq queryReq) {
        PageBounds pageBounds;
        if (queryReq.getPage() == null || queryReq.getPageSize() == null) {
            pageBounds = new PageBounds();
        } else {
            pageBounds = new PageBounds(queryReq.getPage().intValue(), queryReq.getPageSize());
        }
        return orderInfoDao.queryOrderInfoList(queryReq, pageBounds);
    }

    @Override
    @Transactional
    public DtoResult updateOrderInfo(OrderInfoModifyDtoReq requestBody) {
    	DtoResult dtoResult = new DtoResult();
        OrderInfoModel queryModel = new OrderInfoModel();
        queryModel.setId(requestBody.getOrderId());
        queryModel.setOrderNo(requestBody.getOrderNo());
        queryModel.setStatus(requestBody.getValidStatus());
        //queryModel.setStatus(requestBody.getOldStatus());
        List<OrderInfoModel> modelList = query(queryModel);
        if (CollectionUtils.isEmpty(modelList)) {
            throw new BizException(ResultCode.PARAM_ERROR.getCode(),"订单不存在或订单状态不可修改");
        }
        OrderInfoModel oldModel = modelList.get(0);
        OrderInfoModel newModel = new OrderInfoModel();
        
        //获取店铺下单规则
        OrderRuleModel ruleQueryModel = new OrderRuleModel();
        ruleQueryModel.setUcShopId(oldModel.getUcShopId());
        List<OrderRuleModel> rules = orderRuleService.query(ruleQueryModel);
        OrderRuleModel rule = null;
		// 规则数据验证
		if (CollectionUtils.isNotEmpty(rules)) {
			rule = rules.get(0);
			if (rules.size() > 1) {
				logger.error(String.format(ResultCode.OC_SUPPLIER_RULE_ABNORMAL.getDesc() + ":ucshopid={%s}",oldModel.getUcShopId()));
				throw new BizException(ResultCode.OC_SUPPLIER_RULE_ABNORMAL);
			}
		}
		if (rule != null) {
			Date currentDate = new Date();//订单创建时间
			if(StringUtils.isNotBlank(rule.getTransportTime())) {
				if(requestBody.getPlanTransportTime() != null ){
					String transportDate = DateUtils.formatDate(requestBody.getPlanTransportTime());//订单拉料日期 YYYY-MM-DD格式
					if (DateUtils.date_sdf.format(currentDate).equals(transportDate)) {
						if (DateUtils.str2Date(transportDate + BLANK_SAPCE + rule.getTransportTime(),DateUtils.time_sdf)
								.getTime() <= currentDate.getTime()) {
							throw new BizException(ResultCode.OC_ORDER_CREATE_FOBIDDEN.getCode(), "不能下当日拉料的订单");
						}
					}
				}
			}
		}
        //是否锁库标志
        Boolean whetherLock = false;
        //如果拉料日期改成今天，且拉料时间变化，验证库存是否足够
        if(requestBody.getPlanTransportTime() != null && 
        		DateUtils.formatDate(new Date()).equals(DateUtils.formatDate(requestBody.getPlanTransportTime()))){
        	if(!DateUtils.formatDate(oldModel.getPlanTransportTime()).equals(DateUtils.formatDate(requestBody.getPlanTransportTime()))){
	        	OrderValidateRepositoryDtoResult orderValidateRepositoryDtoResult = orderIntegrationService.validateRepository(oldModel.getId());
	        	if(orderValidateRepositoryDtoResult.getRepositoryLowFlag()){
	        		dtoResult.setCode(ResultCode.OC_ORDER_REPOSITORY_LOW.getCode());
	        		dtoResult.setMsg("抱歉,修改拉料日期失败,"+ DateUtils.formatDate(requestBody.getPlanTransportTime()) + "," +JSON.toJSONString(orderValidateRepositoryDtoResult.getMaterialName())+",库存不足，建议你选择其它拉料时间!");
	        		return dtoResult;
	        	}
	        	whetherLock = true;
        	}
        }
        //设置需要更新的字段值
        BeanUtils.copyProperties(requestBody, newModel);
        if (OrderStatusEnum.CUSTOMER_REPEALING.getValue().equals(requestBody.getStatus()) ||
                OrderStatusEnum.SHOP_REPEAL.getValue().equals(requestBody.getStatus()) ||
                OrderStatusEnum.CUSTOMER_REPEAL.getValue().equals(requestBody.getStatus())) {
            newModel.setCloseTime(new Date());
        }
        newModel.setOldPlanTransportTime(oldModel.getTransportTime());
        boolean updateOrder = AppContext.getSpringContext().getBean(this.getClass()).update(queryModel, newModel);
        if (!updateOrder) {
            logger.error(ResultCode.OC_ORDER_UPDATE_ERROR.getDesc() + ":{%s}", JSON.toJSONString(requestBody));
            throw new BizException(ResultCode.OC_ORDER_UPDATE_ERROR);
        }
        OrderInfoModel newQueryModel = new OrderInfoModel();
        newQueryModel.setId(requestBody.getOrderId());
        newQueryModel.setOrderNo(requestBody.getOrderNo());
        newModel = query(newQueryModel).get(0);
        //如果改了拉料时间，调用接口往EBS同步信息
        if (requestBody.getPlanTransportTime() != null) {
        	if(!DateUtils.formatDate(oldModel.getPlanTransportTime()).equals(DateUtils.formatDate(requestBody.getPlanTransportTime()))){
        		TransportInfoToEbsDtoReq transportInfoToEbsDtoReq = new TransportInfoToEbsDtoReq();
                transportInfoToEbsDtoReq.setOrderId(requestBody.getOrderId());
                transportInfoToEbsDtoReq.setPlanTransportTime(requestBody.getPlanTransportTime());
                transportInfoToEbsDtoReq.setWhetherLock(whetherLock);
                orderIntegrationService.sendTransportInfoToEbs(transportInfoToEbsDtoReq);
        	}
        }

        // 如果修改了拉料时间则发消息
        if (requestBody.getPlanTransportTime() != null && requestBody.getPlanTransportTime().getTime() != oldModel.getPlanTransportTime().getTime()) {
            BigDecimal presentWeight = calculatePresentWeight(requestBody.getOrderId());
            newModel.setTotalFeedWeight(newModel.getTotalFeedWeight().add(presentWeight));
            if(newModel.getStatus().equals(OrderStatusEnum.PAID.name())
            		|| newModel.getStatus().equals(OrderStatusEnum.WAITING_PULL_MATERIAL.name())
            		|| newModel.getStatus().equals(OrderStatusEnum.MATERIAL_PREPARING.name())){
            	sendSms(requestBody, newModel);
                sendWechatMsg(requestBody, newModel);
            }
            //添加用户操作日志
            recordLog(oldModel, newModel, UserOperationEnums.OD_USER_MODIFY_ORDER);
        }

        return DtoResult.success();
    }

    @Override
    @Transactional
	public DtoResult closeOrder(OrderCloseDtoReq requestBody) {
    	if(requestBody.getOrderId() == null && StringUtils.isBlank(requestBody.getOrderNo())){
    		throw new BizException(ResultCode.PARAM_ERROR.getCode(),"订单参数错误");
    	}
    	DtoResult dtoResult = new DtoResult();
        OrderInfoModel queryModel = new OrderInfoModel();
        queryModel.setId(requestBody.getOrderId());
        queryModel.setOrderNo(requestBody.getOrderNo());
        List<OrderInfoModel> modelList = query(queryModel);
        if (CollectionUtils.isEmpty(modelList)) {
            throw new BizException(ResultCode.OC_ORDER_ID_NOT_EXISTS);
        }
        OrderInfoModel orderModel = modelList.get(0);
        //如果已关闭 直接返回 处理多次取消的情况
    	if (OrderStatusEnum.CUSTOMER_REPEALING.name().equals(orderModel.getStatus())
    			|| OrderStatusEnum.CUSTOMER_REPEAL.name().equals(orderModel.getStatus())
    			|| OrderStatusEnum.SHOP_REPEAL.name().equals(orderModel.getStatus())
    			|| OrderStatusEnum.CLOSED.name().equals(orderModel.getStatus())) {
    		return DtoResult.success();
		}
    	if (!orderEnableModify(orderModel)) {
    		dtoResult.setCode(ResultCode.OC_ORDER_STATUS_NONEDITABLE.getCode());
    		dtoResult.setMsg(ResultCode.OC_ORDER_STATUS_NONEDITABLE.getDesc());
			return dtoResult;
		}
    	
    	OrderInfoModel oldModel = new OrderInfoModel();
        OrderInfoModel newModel = new OrderInfoModel();
        oldModel.setId(orderModel.getId());
        oldModel.setStatus(orderModel.getStatus());
        newModel.setStatus(OrderStatusEnum.CUSTOMER_REPEALING.name());
        newModel.setCloseTime(new Date());
        boolean flag = update(oldModel, newModel);
        if (flag) {
        	//调接口传输
			CancelOrderToEbsDtoReq cancelOrderToEbsDtoReq = new CancelOrderToEbsDtoReq();
			cancelOrderToEbsDtoReq.setEbsOrderNo(orderModel.getEbsOrderNo());
			cancelOrderToEbsDtoReq.setOrgId(orderModel.getOrgId());
			cancelOrderToEbsDtoReq.setOrderId(orderModel.getId());
			dtoResult = orderIntegrationService.sendCancelOrderToEbs(cancelOrderToEbsDtoReq);
			//如果ebs已删除，直接修改状态
			if (ResultCode.OC_EBS_ORDER_NO_DELETE.getCode().equals(dtoResult.getCode())) {
				newModel = new OrderInfoModel();
				newModel.setStatus(OrderStatusEnum.SHOP_REPEAL.name());
				boolean updateOrder = update(queryModel, newModel);
		        if (!updateOrder) {
		            logger.error(ResultCode.OC_ORDER_UPDATE_ERROR.getDesc() + ":{%s}", JSON.toJSONString(requestBody));
		            throw new BizException(ResultCode.OC_ORDER_UPDATE_ERROR);
		        }
			}else if(!ResultCode.SUCCESS.getCode().equals(dtoResult.getCode())){
				logger.error("客户取消订单失败"+JSON.toJSONString(dtoResult));
				throw new BizException(ResultCode.OC_CANCEL_ORDER_FAIL.getCode(), dtoResult.getMsg());
			}
			//记录日志
			newModel = query(queryModel).get(0);
			AppContext.getSpringContext().getBean(this.getClass()).recordLog(orderModel, newModel, UserOperationEnums.OD_CUSTOMER_CLOSE_ORDER);
		}
    	
		return DtoResult.success();
	}
    
    /**    
     *  计算赠包重量
     *
     * @author: dongql  
     * @date: 2018/12/12 11:54
     * @param orderId  
     * @return: java.math.BigDecimal  
     */
    @Override
    public BigDecimal calculatePresentWeight(Long orderId){
        //赠品信息列表
        OrderPresentFeedModel orderPresentFeedModel = new OrderPresentFeedModel();
        orderPresentFeedModel.setOrderId(orderId);
        BigDecimal presentTotal =BigDecimal.ZERO;
        List<OrderPresentFeedModel> presentFeedModelList = presentFeedService.query(orderPresentFeedModel);
        if(CollectionUtils.isNotEmpty(presentFeedModelList)){
            for(OrderPresentFeedModel presentFeedModel :presentFeedModelList){
                presentTotal = presentTotal.add(BigDecimal.valueOf(presentFeedModel.getCount()));
            }
        }
        return presentTotal;
    }
  /**
     *  记录日志
     *
     * @author: dongql  
     * @date: 2018/12/3 17:38
     * @param
     * @param newModel  
     * @return: void  
     */  
        void recordLog(OrderInfoModel oldModel, OrderInfoModel newModel, UserOperationEnums userOperationEnums) {
        UserOperationLogDtoReq userOperationLogDtoReq = new UserOperationLogDtoReq();
        userOperationLogDtoReq.setAfterEventAmount(newModel.getTotalPayAmount());
        userOperationLogDtoReq.setAfterEventModel(JSON.toJSONString(newModel));
        userOperationLogDtoReq.setAfterEventStatus(newModel.getStatus());
        userOperationLogDtoReq.setBeforeEventAmount(oldModel.getTotalPayAmount());
        userOperationLogDtoReq.setBeforeEventModel(JSON.toJSONString(oldModel));
        userOperationLogDtoReq.setBeforeEventStatus(oldModel.getStatus());
        userOperationLogDtoReq.setComment(userOperationEnums.getDesc());
        userOperationLogDtoReq.setOrderId(oldModel.getId());
        userOperationLogDtoReq.setEbsOrderId(newModel.getEbsOrderNo());
        userOperationLogDtoReq.setEventDate(new Date());
        userOperationLogDtoReq.setEventId(newModel.getId());
        userOperationLogDtoReq.setUserOperEventType(UserOperEventType.ORDER);
        userOperationLogDtoReq.setSource(UserOperSourceType.ORDER_CENTER);
        userOperationLogDtoReq.setUserOperationEnums(userOperationEnums);
        BaseResponse<DtoResult> operaResp = userOperationFeignClient.recordingUserOperation(userOperationLogDtoReq);
        if (!operaResp.isSuccess()) {
            logger.error(String.format(ResultCode.OC_ORDER_OPERALOG_ERROR.getDesc() + "[error:{%s}" + "data:{%s}]", operaResp.getCode(), userOperationLogDtoReq));
        }
    }

    @Override
    public List<OrderInfoDtoResult> getOrdersInfo(OrderInfoSelectDtoReq dtoReq) {
        return orderInfoDao.queryOrdersInfo(dtoReq);

    }

    /**
     * 发微信消息
     *
     * @param requestBody
     * @param newModel
     * @author: dongql
     * @date: 2018/11/28 15:53
     * @return: void
     */
    private void sendWechatMsg(OrderInfoModifyDtoReq requestBody, OrderInfoModel newModel) {
        //项目名称：拉料日期修改成功
        //项目进展：通知
        //您的订单1788已成功修改拉料日期为11月17日，请及时安排拉料
        List<String> params = new ArrayList<>();
        params.add(newModel.getOrderNo());
        params.add(DateUtils.date_sdf_wz.format(requestBody.getPlanTransportTime()));
        String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + newModel.getId() + "&pre_path=_static_html_order_orderList";
        orderMsgService.sendWechatMsg(newModel.getBuyerId(), WechatMsgTypeEnums.TRANSPORT_TIME_MODIFY, params, url);
    }

    /**
     * 发短信
     *
     * @param requestBody
     * @param newModel
     * @author: dongql
     * @date: 2018/11/28 15:53
     * @return: void
     */
    private void sendSms(OrderInfoModifyDtoReq requestBody, OrderInfoModel newModel) {
        OrderFeedTransportModel orderFeedTransportModel = new OrderFeedTransportModel();
        orderFeedTransportModel.setOrderId(newModel.getId());
        List<OrderFeedTransportModel> listOrderFeedTransportModel = orderFeedTransportService.query(orderFeedTransportModel);
        orderFeedTransportModel = listOrderFeedTransportModel.get(0);
        //【料你富】${userName}的${shopName}饲料订单装运时间已修改为${transportTime}, 订单重量：${weight}吨，货主电话${tel}
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userName", newModel.getBuyerName());
        paramMap.put("shopName", newModel.getUcShopName());
        paramMap.put("transportTime", DateUtils.date_sdf_wz.format(requestBody.getPlanTransportTime()));
        paramMap.put("weight", newModel.getTotalFeedWeight().divide(new BigDecimal(1000), 2, RoundingMode.FLOOR).toString());
        paramMap.put("tel", newModel.getBuyerMobile());
        orderMsgService.sendSmsMsg(orderFeedTransportModel.getDriverMobile(), SmsTemplateEnums.SMS_151767099.getTemplateId(), paramMap);
    }


    @Override
    public PageList<OrderGoodsDetailDtoResult> queryOrderGoodsDetailList(OrderInfoQueryDtoReq queryReq) {
        PageBounds pageBounds;
        if (queryReq.getPage() == null || queryReq.getPageSize() == null) {
            pageBounds = new PageBounds();
        } else {
            pageBounds = new PageBounds(queryReq.getPage().intValue(), queryReq.getPageSize());
        }
        return orderInfoDao.queryOrderGoodsDetailList(queryReq, pageBounds);
    }

	@Override
	public OrderCreateDtoResult createNHOrderAfterCancel(OrderNHUpdateDtoReq req) {
		OrderCreateDtoResult orderDtoResult = new OrderCreateDtoResult();
        orderDtoResult.setCode(ResultCode.SUCCESS.getCode());
        OrderBuilder builder = null;
        try {
            builder = buildNHOrderData(req.getCreateBody());
        } catch (BizException e) {
            orderDtoResult.setCode(e.getCode());
            orderDtoResult.setMsg(e.getMessage());
            return orderDtoResult;
        }
        AppContext.getSpringContext().getBean(this.getClass()).excuteCreateAfterCancel(builder,req.getOrderNo());
        orderDtoResult.setOrderNo(builder.getOrderInfo().getOrderNo());
		if(OrderCalculateRuleEnums.PERSON_CALCULATE.name().equals(builder.getOrderInfo().getCusOrderType())){
			// 【料你富】今日订单${orderNo}待核计应付金额，请登录EBS查看详情并及时核价。
			// 发送短信给商户配置联系人
			try {
				if(StringUtils.isNotBlank(builder.getOrderInfo().getNotifyMobile())){
					Map<String, String> paramMap = new HashMap<>(1);// sms参数集合
					paramMap.put("orderNo", builder.getOrderInfo().getOrderNo());
					orderMsgService.sendSmsMsg(builder.getOrderInfo().getNotifyMobile(),SmsTemplateEnums.SMS_151772902.name(), paramMap);
				}
			} catch (Exception e) {
				logger.error("OrderServiceImpl.createOrderNH发送sms消息异常:{}", e);
			}
		}
        //代用户下单需发送消息
        if (req.getCreateBody().getProxy()) {
            String url = hompageurl + "/static/html/order/order_temp.html?orderId=" + builder.getOrderInfo().getId() + "&pre_path=_static_html_order_orderList";
            // 发送短信：［料你富］“XXX”已成功为你在料你富商城下了一笔饲料订单，为避免订单失效，请尽快微信搜索关注“料你富商城”，登录并完成支付。XXX链接XXX
            Map<String, String> paramMap = new HashMap<>(2);//sms参数集合
            paramMap.put("shopName", req.getCreateBody().getShop().getUcShopName());
            paramMap.put("order", url);
            orderMsgService.sendSmsMsg(req.getCreateBody().getUser().getBuyerMobile(), SmsTemplateEnums.SMS_152210484.name(), paramMap);

            // 通知：
            // 任务名称：预约时间修改通知
            // 内容：“XXX”已成功为你在料你富商城下了一笔饲料订单，为避免订单失效，请尽快登录并完成支付。立即登陆>
            // 订单编号：672367247347
            // 订单状态：待支付
            List<String> params = new ArrayList<>();
            params.add(req.getCreateBody().getProxName());
            params.add(builder.getOrderInfo().getOrderNo());
            params.add(OrderStatusEnum.getEnumsMap().get(builder.getOrderInfo().getStatus()));
            orderMsgService.sendWechatMsg(req.getCreateBody().getUser().getBuyerId(), WechatMsgTypeEnums.ORDER_PROXY_CREATE, params, url);
        }
        return orderDtoResult;
	}
	@Override
	public PayOrderNoDtoResult genPayNo() {
		PayOrderNoDtoResult payOrderNoDtoResult = new PayOrderNoDtoResult();
		//生成订单号
        int retryTimes = 0;
        String payOrderNo = orderNoGeneraterHelper.genPayNo();
        while(StringUtils.isBlank(payOrderNo)){
        	payOrderNo = orderNoGeneraterHelper.genPayNo();
        	if(retryTimes == RETRY_TIMES){
        		throw new BizException(ResultCode.OC_ORDER_CREATE_ERROR.getCode(),"创建支付订单号超时,请稍后再试");
        	}
        	retryTimes ++;
        }
        payOrderNoDtoResult.setPayOrderNo(payOrderNo);
        payOrderNoDtoResult.setCode(ResultCode.SUCCESS.getCode());
        payOrderNoDtoResult.setMsg(ResultCode.SUCCESS.getDesc());
        return payOrderNoDtoResult;
	}

    @Override
    public DtoResult modifyOrderByOfflinePayment(String orderNo) {
        OrderInfoModel oldModel = new OrderInfoModel();
        oldModel.setOrderNo(orderNo);
        OrderInfoModel newModel = new OrderInfoModel();
        newModel.setOrderNo(orderNo);
        newModel.setStatus(OrderStatusEnum.WAITING_OFF_LINE_PAY.getValue());
        newModel.setPayType(OrderPayTypeEnum.OFF_LINE.getValue());

        long update = orderInfoDao.update(oldModel, newModel);
        if (update <= 0) {
            return DtoResult.fail(ResultCode.FAIL);
        }
        return DtoResult.success();
    }
    
    /**
	 * 订单状态是否能被修改
	 * 待支付 
	 * @param orderInfo
	 * @return
	 */
	private boolean orderEnableModify(OrderInfoModel orderInfo) {
		String status = orderInfo.getStatus();
		return OrderStatusEnum.WAITING_FOR_PAYMENT.getValue().equals(status);
	}

}
