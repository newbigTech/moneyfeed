package com.newhope.moneyfeed.integration.biz.service.ebs.order;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.newhope.commons.lang.DateUtils;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.UserOperEventType;
import com.newhope.moneyfeed.api.enums.UserOperSourceType;
import com.newhope.moneyfeed.api.enums.UserOperationEnums;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.common.cache.CacheData;
import com.newhope.moneyfeed.common.concurrent.ManagedThreadPool;
import com.newhope.moneyfeed.common.config.HttpMessageSender;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.common.util.EBSInterfaceUtil;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderDetailModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromEbsItemsModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderFromEbsModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsOrderModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.order.EbsRechargeModel;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderCancelDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderCreateDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderPayDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderPayInBalanceTypeDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderPayInBankCardTypeDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderReCreateDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderRechargeDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.MoneyfeedToEbsOrderUpdateInfoDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.order.QueryEbsOrderDetailInfoDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.order.QueryEbsOrderDetailInfoDtoResult;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.order.QueryEbsOrderDetailInfoItemDtoResult;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.order.QueryEbsOrderMaterialInfoDtoResult;
import com.newhope.moneyfeed.integration.api.enums.common.EBSInterfaceIfcodeEnum;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderProductTypeEnum;
import com.newhope.moneyfeed.integration.api.enums.order.EbsOrderStatusEnum;
import com.newhope.moneyfeed.integration.api.enums.order.MoneyfeedToEbsOperationStatusEnum;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderDetailExModel;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderExModel;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderModelBuilder;
import com.newhope.moneyfeed.integration.api.exbean.ebs.order.EbsOrderRechargeModelBuilder;
import com.newhope.moneyfeed.integration.api.service.ebs.order.MoneyfeedToEbsOrderService;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.order.sendRequestToEbs.QueryEbsOrderInfoReq;
import com.newhope.moneyfeed.integration.biz.service.common.CommonService;
import com.newhope.moneyfeed.integration.biz.service.common.MoneyFeedOrderCenterService;
import com.newhope.moneyfeed.integration.biz.service.common.SendMQService;
import com.newhope.moneyfeed.integration.biz.thread.MoneyfeedToEbsCancelOrderThread;
import com.newhope.moneyfeed.integration.biz.thread.MoneyfeedToEbsCreateOrderThread;
import com.newhope.moneyfeed.integration.biz.thread.MoneyfeedToEbsOrderAccountRechargeThread;
import com.newhope.moneyfeed.integration.biz.thread.MoneyfeedToEbsOrderBalanceRechargeThread;
import com.newhope.moneyfeed.integration.biz.thread.MoneyfeedToEbsOrderPayInBalanceThread;
import com.newhope.moneyfeed.integration.biz.thread.MoneyfeedToEbsOrderPayInBankCardThread;
import com.newhope.moneyfeed.integration.biz.thread.MoneyfeedToEbsUpdateOrderInfoThread;
import com.newhope.moneyfeed.integration.biz.thread.UpdateOrderDetailThread;
import com.newhope.moneyfeed.integration.dal.dao.ebs.order.EbsOrderFromEbsItemsDao;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;

/**
 * Created by yuyanlin on 2018/11/20
 */
@Service
public class MoneyfeedToEbsOrderServiceImpl implements MoneyfeedToEbsOrderService {

    @Autowired
    private EbsOrderServiceImpl ebsOrderService;

    @Autowired
    private EbsOrderDetailServiceImpl ebsOrderDetailService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private HttpMessageSender httpMessageSender;

    @Autowired
    private CacheData cacheData;

    @Autowired
    private ManagedThreadPool managedThreadPool;

    @Autowired
    private  EbsOrderFromEbsItemsServiceImpl ebsOrderFromEbsItemsService;

    @Autowired
    private EbsOrderFromEbsItemsDao ebsOrderFromEbsItemsDao;

    @Autowired
    private  EbsOrderFromEbsServiceImpl ebsOrderFromEbsService;

    @Autowired
    private MoneyFeedOrderCenterService moneyFeedOrderCenterService;
    
    @Autowired
    private SendMQService sendMQService;
    
    @Autowired
    private EbsRechargeServiceImpl ebsRechargeService;

    @Value("${ebs.integration.url}")
    private String ebsInterfaceUrl;

    private  Integer BOOKED_ORDER_LEGNTH =50;

    private  String QUERY_ORDER_INFO_CACHE_KEY="QUERY_ORDER_INFO_CACHE_KEY";

    @Autowired
    private  EbsOrderLogServiceImpl orderLogService;

    private Logger logger = LoggerFactory.getLogger(MoneyfeedToEbsOrderServiceImpl.class);

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DtoResult moneyfeedToEbsOrderCreate(MoneyfeedToEbsOrderCreateDtoReq dtoReq) throws Exception {
        DtoResult dtoResult = commonService.getSuccessDtoResult();

        // 0、判断订单是否存在 1、判断订单曾经是否已经提交过创建
        EbsOrderExModel ebsOrderWithDetail = ebsOrderService.getEbsOrderWithDetailByMoneyfeedOrderId(dtoReq.getMoneyFeedOrderId());
        if (ebsOrderWithDetail != null && !MoneyfeedToEbsOperationStatusEnum.UNCOMMIT.name().equals(ebsOrderWithDetail.getOrderCreateStatus())) {
            return dtoResult;
        }

        Date planTransportDate;
        try {
            planTransportDate = DateUtils.date_sdf.parse(dtoReq.getPlanTransportTime());
            dtoReq.setPlanTransportTimeDate(planTransportDate);
        } catch (ParseException e) {
            logger.error("解析计划拉料日期异常");

            dtoResult.setCode(ResultCode.FAIL.getCode());
            dtoResult.setMsg("解析计划拉料日期异常");
        }

        managedThreadPool.execute(new MoneyfeedToEbsCreateOrderThread(dtoReq));

        return dtoResult;
    }


    @Override
    public DtoResult moneyfeedToEbsOrderPay(MoneyfeedToEbsOrderPayDtoReq dtoReq) {
        DtoResult dtoResult = commonService.getSuccessDtoResult();

        // 0、判断订单是否存在 1、判断订单是否创建成功 2、判断订单曾经是否已经提交过支付
        EbsOrderExModel ebsOrderWithDetail = ebsOrderService.getEbsOrderWithDetailByEbsOrderNo(dtoReq.getEbsOrderNo());

        if (null == ebsOrderWithDetail) {
            dtoResult.setCode(ResultCode.FAIL.getCode());
            dtoResult.setMsg("订单不存在");

            return dtoResult;
        }

        if (!MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_SUCCESS.name().equals(ebsOrderWithDetail.getOrderCreateStatus())) {
            dtoResult.setCode(ResultCode.FAIL.getCode());
            dtoResult.setMsg("订单没有创建成功");

            return dtoResult;
        }

        if (!MoneyfeedToEbsOperationStatusEnum.UNCOMMIT.name().equals(ebsOrderWithDetail.getOrderPayStatus())) {
            return dtoResult;
        }

        // ebs order
        EbsOrderModelBuilder ebsOrderBuilder = EbsOrderModelBuilder.anEbsOrderModel();
        ebsOrderBuilder.payType(dtoReq.getPayType()).totalAmount(dtoReq.getTotalPayAmount()).ebsPayAmount(dtoReq.getEbsPayAmount())
                .cardPayAmount(dtoReq.getBankPayAmount()).bankPayNo(dtoReq.getAccNo()).orderPayStatus(MoneyfeedToEbsOperationStatusEnum.OPERATING.name());

        ebsOrderService.update(ebsOrderWithDetail, ebsOrderBuilder.build());

        // TODO 发送EBS，通知EBS支付订单
       
        
        
        return dtoResult;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DtoResult moneyfeedToEbsOrderCancel(MoneyfeedToEbsOrderCancelDtoReq dtoReq) {
    	logger.info("取消订单请求, dtoReq: " + JSON.toJSONString(dtoReq));
        DtoResult dtoResult = commonService.getSuccessDtoResult();

        // 0、判断订单是否存在 1、判断订单是否创建成功
        EbsOrderExModel ebsOrderWithDetail = ebsOrderService.getEbsOrderWithDetailByEbsOrderNo(dtoReq.getEbsOrderNo());

        if (null == ebsOrderWithDetail) {
            dtoResult.setCode(ResultCode.FAIL.getCode());
            dtoResult.setMsg("订单不存在");

            return dtoResult;
        }
        EbsOrderDetailModel ebsOrderDetail = ebsOrderWithDetail.getEbsOrderDetail();
        if (EbsOrderStatusEnum.CANCELLED.name().equals(ebsOrderDetail.getEbsOrderStatus())
        		|| EbsOrderStatusEnum.DELETE.name().equals(ebsOrderDetail.getEbsOrderStatus())
        		|| EbsOrderStatusEnum.CLOSED.name().equals(ebsOrderDetail.getEbsOrderStatus())) {
            return dtoResult;
        }else if(EbsOrderStatusEnum.BOOKED.name().equals(ebsOrderDetail.getEbsOrderStatus())){
        	 dtoResult.setCode(ResultCode.FAIL.getCode());
             dtoResult.setMsg("订单" + EbsOrderStatusEnum.BOOKED.getDesc() + "不能取消");
        	 return dtoResult;
        }

        if (!MoneyfeedToEbsOperationStatusEnum.UNCOMMIT.name().equals(ebsOrderWithDetail.getOrderCancelStatus())) {
            return dtoResult;
        }

        // TODO 发送EBS，通知EBS取消订单 (线程控制)
        managedThreadPool.submit(new MoneyfeedToEbsCancelOrderThread(dtoReq));

        return dtoResult;
    }

    /**
     * 查询EBS订单信息
     * @param search
     * @return
     */
    @Override
    public QueryEbsOrderDetailInfoDtoResult queryEbsOrderDetailInfo(QueryEbsOrderDetailInfoDtoReq search) {

        if(search==null ||  search.getMoneyfeedOrderIds()==null || search.getMoneyfeedOrderIds().isEmpty()){
            throw new BizException("需要查询的订单ID集合不能为空");
        }

        QueryEbsOrderDetailInfoDtoResult result =new QueryEbsOrderDetailInfoDtoResult();

        EbsOrderDetailExModel ebsOrderDetailExSearch = new EbsOrderDetailExModel();
        ebsOrderDetailExSearch.setSaleOrderIdList(search.getMoneyfeedOrderIds());

        List<EbsOrderDetailExModel> items = ebsOrderDetailService.queryEbsOrderDetailExModelList(ebsOrderDetailExSearch);

        if(items!=null && !items.isEmpty()){

            result.setOrderInfos(new ArrayList<QueryEbsOrderDetailInfoItemDtoResult>(items.size()));

            for (EbsOrderDetailExModel item : items) {

                QueryEbsOrderDetailInfoItemDtoResult order =new QueryEbsOrderDetailInfoItemDtoResult();

                result.getOrderInfos().add(order);

                order.setMoneyfeedOrderId(item.getSaleOrderId());
                order.setCancleTime(item.getCancelOrderTime());
                order.setCarNo(item.getCarNo());
                order.setCheckinTime(item.getInvoiceOrderTime());
                order.setCompanyShortCode(item.getCompanyCode());
                order.setCompanyOrgId(item.getComanyId());
                order.setDealTime(item.getCarOutTime());
                order.setEbsOrderStatus(item.getEbsOrderStatus());
                order.setCloseTime(item.getCloseOrderTime());
                order.setPlanTransportTime(item.getPlanTransportTime());
                order.setTransportTime(item.getCarInTime());
                order.setCarInWeight(item.getInCarWeight());
                order.setCarOutWeight(item.getOutCarWeight());
                order.setWeightNo(item.getWeightNum());
                order.setEbsOrderNo(item.getEbsOrderNo());
                order.setLastUpdateTime(item.getGmtCreated());
                if(item.getMaterialChanged()!=null) {
                    order.setMaterialChanged(item.getMaterialChanged());
                }else {
                    order.setMaterialChanged(false);
                }



                EbsOrderFromEbsItemsModel query =new EbsOrderFromEbsItemsModel();
                query.setOrderId(item.getOrderId());
               List<EbsOrderFromEbsItemsModel> mitems =    ebsOrderFromEbsItemsDao.exSelect(query);

                order.setMaterials(new ArrayList<QueryEbsOrderMaterialInfoDtoResult>());

                for (EbsOrderFromEbsItemsModel mitem : mitems) {
                    QueryEbsOrderMaterialInfoDtoResult material =new QueryEbsOrderMaterialInfoDtoResult();
                    order.getMaterials().add(material);
                    BeanUtils.copyProperties(mitem,material);
                }
            }

        }

        result.setCode(ResultCode.SUCCESS.getCode());
        return result;
    }

    @Override
    public DtoResult moneyfeedToEbsOrderUpdateInfo(MoneyfeedToEbsOrderUpdateInfoDtoReq dtoReq) {
        DtoResult dtoResult = commonService.getSuccessDtoResult();

        // 0、判断订单是否存在 1、判断订单是否创建成功 2、正在操作中的订单不能修改
        EbsOrderExModel ebsOrderWithDetail = ebsOrderService.getEbsOrderWithDetailByEbsOrderNo(dtoReq.getEbsOrderNo());

        if (null == ebsOrderWithDetail) {
            dtoResult.setCode(ResultCode.FAIL.getCode());
            dtoResult.setMsg("订单不存在");

            return dtoResult;
        }

        if (!MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_SUCCESS.name().equals(ebsOrderWithDetail.getOrderCreateStatus())) {
            dtoResult.setCode(ResultCode.FAIL.getCode());
            dtoResult.setMsg("订单没有创建成功");

            return dtoResult;
        }

        if (MoneyfeedToEbsOperationStatusEnum.OPERATING.name().equals(ebsOrderWithDetail.getOrderCreateStatus())
                || MoneyfeedToEbsOperationStatusEnum.OPERATING.name().equals(ebsOrderWithDetail.getOrderPayStatus())
                || MoneyfeedToEbsOperationStatusEnum.OPERATING.name().equals(ebsOrderWithDetail.getOrderCancelStatus())
                || MoneyfeedToEbsOperationStatusEnum.OPERATING.name().equals(ebsOrderWithDetail.getOrderUpdateStatus())) {
            dtoResult.setCode(ResultCode.FAIL.getCode());
            dtoResult.setMsg("订单正在操作中");

            return dtoResult;
        }

        Date planPickUpDate;
        try {
            planPickUpDate = DateUtils.date_sdf.parse(dtoReq.getPlanTransportTime());
        } catch (ParseException e) {
            e.printStackTrace();

            dtoResult.setCode(ResultCode.FAIL.getCode());
            dtoResult.setMsg("解析计划拉料日期异常");

            return dtoResult;
        }
        dtoReq.setPlanTransportTimeDate(planPickUpDate);

        managedThreadPool.execute(new MoneyfeedToEbsUpdateOrderInfoThread(dtoReq));

        return dtoResult;
    }

    /**
     *  将EBS的订单信息同步到中台
     */
    public  void queryEbsOrderDetailInfoJob(){

        Object computering = null;
        try {
            computering = cacheData.getDataCache().get(QUERY_ORDER_INFO_CACHE_KEY);
        } catch (Exception e) {
            CommonService.formatExceptionMsg( this.getClass(),e);
           return;
        }

        if (computering != null) {
            logger.info("--------------当前有正在处理更新信息的订单--------------");
            return;
        }

        try {
            //设置20分钟超时
            cacheData.getDataCache().set(QUERY_ORDER_INFO_CACHE_KEY, "true",1200);
        } catch (Exception e) {
            CommonService.formatExceptionMsg( this.getClass(),e);
            return;
        }
        managedThreadPool.submit(new UpdateOrderDetailThread());

    }

    @Override
    public void resendMQ() {
        sendMQService.ReSendMQMsg();
    }

    public  void queryEbsOrderDetailInfoJobThread(){

        try {
            List<EbsOrderModel> orders = ebsOrderService.queryBookOrderInfo();

            if (orders == null || orders.isEmpty()) {
                return;
            }

            logger.info("本次需要更新的订单信息总数量为：" + orders.size());

            if (orders.size() <= BOOKED_ORDER_LEGNTH) {
                //直接调用发送EBS
                operationOrderDetailInfo(orders);
            } else {
                Integer total = orders.size();
                int page = total / BOOKED_ORDER_LEGNTH;
                if (total % BOOKED_ORDER_LEGNTH > 0) {
                    page += 1;
                }

                for (Integer i = 0; i < page; i++) {

                    if (i != page - 1) {
                        List<EbsOrderModel> temp = orders.subList(i * BOOKED_ORDER_LEGNTH, i * BOOKED_ORDER_LEGNTH + BOOKED_ORDER_LEGNTH);
                        operationOrderDetailInfo(temp);
                    } else {
                        List<EbsOrderModel> temp = orders.subList(i * BOOKED_ORDER_LEGNTH, orders.size());

                        operationOrderDetailInfo(temp);
                    }
                }

            }

            logger.info("本次需要更新的订单信息总数量为：" + orders.size() + "已处理完成");
        }
        finally {
            try {
                cacheData.getDataCache().remove(QUERY_ORDER_INFO_CACHE_KEY);
            } catch (Exception e) {
                CommonService.formatExceptionMsg( this.getClass(),e);
            }
        }
    }

    /**
     * 将查询到的EBS订单更新到中台表
     * @param orders
     */
    private  void operationOrderDetailInfo(List<EbsOrderModel>  orders) {

        if(orders==null || orders.isEmpty())
            return;

        logger.info("当前更新数量："+orders.size());

        List<QueryEbsOrderInfoReq> reqs = new ArrayList<QueryEbsOrderInfoReq>(orders.size());
        for (EbsOrderModel order : orders) {
            QueryEbsOrderInfoReq req=new QueryEbsOrderInfoReq();
            req.setCompanyId(order.getEbsOrgId());
            req.setEbsOrderNo(order.getEbsOrderNo());
            reqs.add(req);
        }

        // 获取ebs请求头报文
         String ebsRequestXmlString = EBSInterfaceUtil.
                 getEbsRequestXmlString(EBSInterfaceIfcodeEnum.QUERY_ORDER_INFO_IFCODE.getIfcode(), reqs);

        try {
            // 发送请求
            String ebsResponseMsg = httpMessageSender.postRequestXml(ebsInterfaceUrl, ebsRequestXmlString);

            // 解析请求
            final QueryEbsOrderDetailInfoDtoResult queryEbsOrderDetailInfoDtoResult = EBSInterfaceUtil
                    .convertEBSResponseXMLtoObject(ebsResponseMsg, QueryEbsOrderDetailInfoDtoResult.class);
            // 处理业务
            if(queryEbsOrderDetailInfoDtoResult==null || queryEbsOrderDetailInfoDtoResult.getOrderInfos()==null ||
                    queryEbsOrderDetailInfoDtoResult.getOrderInfos().isEmpty()) {
                return;
            }

            for (QueryEbsOrderDetailInfoItemDtoResult order : queryEbsOrderDetailInfoDtoResult.getOrderInfos()) {
                UpdateEbsOrderDetail(order);
            }
        }
        catch (Exception e){
            CommonService.formatExceptionMsg( this.getClass(),e);
        }

    }

    /**
     * 更新中间订单详情接口
     * @param ebsOrderInfo
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void UpdateEbsOrderDetail(QueryEbsOrderDetailInfoItemDtoResult ebsOrderInfo) {

        try {

            EbsOrderModel search = new EbsOrderModel();
            search.setEbsOrderNo(ebsOrderInfo.getEbsOrderNo());
            search.setSaleOrderId(ebsOrderInfo.getMoneyfeedOrderId());
            List<EbsOrderModel> tmps = ebsOrderService.query(search);

            if (tmps == null || tmps.isEmpty()) {
                logger.info("在处理EBS订单：" + ebsOrderInfo.getEbsOrderNo() + "时，没有找到中台的对应关系");
                return;
            }

            EbsOrderModel order = tmps.get(0);
            EbsOrderDetailModel detailSearch = new EbsOrderDetailModel();
            detailSearch.setOrderId(order.getId());
            List<EbsOrderDetailModel> tmps1 = ebsOrderDetailService.query(detailSearch);

            if (tmps1 == null || tmps1.isEmpty()) {
                logger.info("在处理EBS订单：" + ebsOrderInfo.getEbsOrderNo() + "时，没有找到中台的对应关系");
                return;
            }

            EbsOrderDetailModel orderDetailModel = tmps1.get(0);

            EbsOrderDetailModel old = new EbsOrderDetailModel();
            old.setId(orderDetailModel.getId());


            EbsOrderDetailModel update = new EbsOrderDetailModel();
            if(org.apache.commons.lang.StringUtils.isNotEmpty(ebsOrderInfo.getCancleTimeStr())) {
                update.setCancelOrderTime(DateUtil.getDate(ebsOrderInfo.getCancleTimeStr() ,DateUtil.YYYY_MM_DD_HH_MM_SS));
            }

            if(org.apache.commons.lang.StringUtils.isNotEmpty(ebsOrderInfo.getTransportTimeStr())) {
                update.setCarInTime(DateUtil.getDate(ebsOrderInfo.getTransportTimeStr() ,DateUtil.YYYY_MM_DD_HH_MM_SS));
            }
            update.setCarNo(ebsOrderInfo.getCarNo());

            if(org.apache.commons.lang.StringUtils.isNotEmpty(ebsOrderInfo.getDealTimeStr())) {
                update.setCarOutTime(DateUtil.getDate(ebsOrderInfo.getDealTimeStr() ,DateUtil.YYYY_MM_DD_HH_MM_SS));
            }

            if(org.apache.commons.lang.StringUtils.isNotEmpty(ebsOrderInfo.getCloseTimeStr())) {
                update.setCloseOrderTime(DateUtil.getDate(ebsOrderInfo.getCloseTimeStr() ,DateUtil.YYYY_MM_DD_HH_MM_SS));
            }
            update.setInCarWeight(ebsOrderInfo.getCarInWeight());
            update.setOutCarWeight(ebsOrderInfo.getCarOutWeight());
            update.setGmtModified(new Date());

            if(org.apache.commons.lang.StringUtils.isNotEmpty(ebsOrderInfo.getCheckinTimeStr())) {
                update.setInvoiceOrderTime(DateUtil.getDate(ebsOrderInfo.getCheckinTimeStr() ,DateUtil.YYYY_MM_DD_HH_MM_SS));
            }

            if(org.apache.commons.lang.StringUtils.isNotEmpty(ebsOrderInfo.getPlanTransportTimeStr())) {
                update.setPlanTransportTime(DateUtil.getDate(ebsOrderInfo.getPlanTransportTimeStr() ,DateUtil.YYYY_MM_DD));
            }

            update.setWeightNum(ebsOrderInfo.getWeightNo());
            update.setEbsOrderStatus(ebsOrderInfo.getEbsOrderStatus());


            EbsOrderFromEbsItemsModel searchMaterial = new EbsOrderFromEbsItemsModel();
            searchMaterial.setOrderId(order.getId());

            List<EbsOrderFromEbsItemsModel> oldMaterials= ebsOrderFromEbsItemsService.getDao().select(searchMaterial);
            double totaloldAmount = 0;
            for (EbsOrderFromEbsItemsModel oldMaterial : oldMaterials) {
                if(oldMaterial.getRealPayAmount()!=null) {
                    totaloldAmount += oldMaterial.getRealPayAmount().doubleValue();
                }
            }

            if(ebsOrderInfo.getMaterials()!=null && !ebsOrderInfo.getMaterials().isEmpty()) {

                ebsOrderFromEbsItemsService.getDao().delete(searchMaterial);
                double totalnewAmount = 0;
                double totalPlanAmount=0;
                List<EbsOrderFromEbsItemsModel> tmpList = new ArrayList<EbsOrderFromEbsItemsModel>();
                for (QueryEbsOrderMaterialInfoDtoResult material : ebsOrderInfo.getMaterials()) {
                    EbsOrderFromEbsItemsModel tmp = new EbsOrderFromEbsItemsModel();
                    BeanUtils.copyProperties(material,tmp);
                    tmp.setEbsOrderItemJson(JSON.toJSONString(material));
                    if(tmp.getRealPayAmount().doubleValue()==0 || tmp.getRealPayAmount()==null) {
                        tmp.setItemType(EbsOrderProductTypeEnum.PRESENT_PRODUCT.name());
                    }
                    else{
                        tmp.setItemType(EbsOrderProductTypeEnum.NORMAL_PRODUCT.name());
                    }

                    if(material.getRealPayAmount()!=null){
                        totalnewAmount+=material.getRealPayAmount().doubleValue();
                    }

                    if(material.getPlanAmount()!=null && material.getQuantity()!=null){
                        totalPlanAmount+=material.getPlanAmount().doubleValue() * material.getQuantity().doubleValue();
                    }

                    tmp.setOrderId(order.getId());
                    //tmp.setOrderFromEbsId(or0der.getEbsOrderId());
                    tmp.setOrgId(order.getEbsOrgId());
                    tmp.setGmtCreated(new Date());
                    tmp.setGmtModified(new Date());
                    tmp.setVersion(0L);

                    tmpList.add(tmp);
                }

                boolean materialChanged=changedMaterial(oldMaterials,tmpList,totaloldAmount,totalnewAmount);

                update.setMaterialChanged(materialChanged);

                ebsOrderDetailService.update(old, update);

                ebsOrderFromEbsItemsService.getDao().insert(tmpList);

                //更新EBS订单信息
                EbsOrderFromEbsModel oldEbsModel = new EbsOrderFromEbsModel();
                oldEbsModel.setOrderId(order.getId());

                EbsOrderFromEbsModel updateEbsModel = new EbsOrderFromEbsModel();
                updateEbsModel.setRealPayAmount(BigDecimal.valueOf(totalnewAmount));
                updateEbsModel.setPlanTotalAmount(BigDecimal.valueOf(totalPlanAmount));
                updateEbsModel.setDiscountAmount(BigDecimal.valueOf(totalPlanAmount-totalnewAmount));
                updateEbsModel.setGmtModified(new Date());

                ebsOrderFromEbsService.getDao().update(oldEbsModel,updateEbsModel);

                //调用商场接口，主动更新商场物料信息
                if(materialChanged && org.apache.commons.lang.StringUtils.isNotEmpty(ebsOrderInfo.getMoneyfeedOrderId())){
                     moneyFeedOrderCenterService.updateSaleOrderItem(ebsOrderInfo.getMoneyfeedOrderId());
                }

                //记录日志
                if(computerOrderDetail(orderDetailModel,update) ||materialChanged){

                    commonService.saveFulllog(BigDecimal.valueOf(totaloldAmount),
                            BigDecimal.valueOf(totalnewAmount),
                            orderDetailModel.getEbsOrderStatus(),
                            update.getEbsOrderStatus(),
                            orderDetailModel,
                            update,
                            order.getEbsOrderNo(),
                            new Date(),order.getId(),
                            null,
                            Long.valueOf( order.getSaleOrderId()),
                            UserOperEventType.ORDER,
                            UserOperationEnums.INT_UPDATE_EBS_ORDER_INFO,
                            "获取EBS订单信息，更新中台订单信息",
                            UserOperSourceType.MEDIUM_CENTER);
                }
            }



        }
        catch (Exception e){
            CommonService.formatExceptionMsg( this.getClass(),e);
        }
    }

    private  boolean computerOrderDetail(EbsOrderDetailModel old,EbsOrderDetailModel newsest){


        if( !org.apache.commons.lang.StringUtils.equals(old.getEbsOrderStatus(),newsest.getEbsOrderStatus()))
            return true;
        if(!CommonService.sameDate(old.getCancelOrderTime(),newsest.getCancelOrderTime())){
            return true;
        }

        if(!CommonService.sameDate(old.getCarInTime(),newsest.getCarInTime())){
            return true;
        }

        if(!CommonService.sameDate(old.getCarOutTime(),newsest.getCarOutTime())){
            return true;
        }

        if(!CommonService.sameDate(old.getCloseOrderTime(),newsest.getCloseOrderTime())){
            return true;
        }

        if(!CommonService.sameDate(old.getInvoiceOrderTime(),newsest.getInvoiceOrderTime())){
            return true;
        }

        if(!CommonService.sameDate(old.getPlanTransportTime(),newsest.getPlanTransportTime())){
            return true;
        }

        if( !org.apache.commons.lang.StringUtils.equals(old.getCarNo(),newsest.getCarNo())){
            return true;
        }

        if( !org.apache.commons.lang.StringUtils.equals(old.getWeightNum(),newsest.getWeightNum())){
            return true;
        }

        return  false;
    }

    /**
     * 物料是否发生过变更
     * @param oldMaterials
     * @param newMaterials
     * @param oldAmount
     * @param newAmount
     * @return
     */
    public boolean changedMaterial(List<EbsOrderFromEbsItemsModel> oldMaterials,
                                    List<EbsOrderFromEbsItemsModel> newMaterials,double oldAmount,double newAmount ){
        if(oldMaterials==null || newMaterials==null){
            return true;
        }

        if(oldMaterials.size()!=newMaterials.size()){
            return  true;
        }

        if(oldAmount!=newAmount){
            return true;
        }

        for (EbsOrderFromEbsItemsModel oldMaterial : oldMaterials) {

            boolean extis=false;

            for (EbsOrderFromEbsItemsModel newMaterial : newMaterials) {
                 if(newMaterial.getMaterielNo().equals(oldMaterial.getMaterielNo()) &&
                         newMaterial.getRealPayAmount().compareTo( oldMaterial.getRealPayAmount())==0 &&
                         newMaterial.getQuantity().compareTo(oldMaterial.getQuantity())==0
                        ){
                     extis=true;
                 }
            }

            if(!extis){
                return true;
            }
        }


        return  false;

    }

    @Override
    public DtoResult moneyfeedToEbsOrderPayInBalanceType(MoneyfeedToEbsOrderPayInBalanceTypeDtoReq balancePayDtoReq) throws Exception {
        DtoResult dtoResult = commonService.getSuccessDtoResult();

        // 0、判断订单是否存在 1、判断订单正在支付中
        EbsOrderExModel ebsOrderWithDetail = ebsOrderService.getEbsOrderWithDetailByEbsOrderNo(balancePayDtoReq.getEbsOrderNo());

        if (null == ebsOrderWithDetail) {
            throw new BizException("该订单还没创建，创建后再支付");
        }

        if (ebsOrderWithDetail != null && MoneyfeedToEbsOperationStatusEnum.OPERATING.name().equals(ebsOrderWithDetail.getOrderPayStatus())) {
            return dtoResult;
        }

        if (ebsOrderWithDetail != null && MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_SUCCESS.name().equals(ebsOrderWithDetail.getOrderPayStatus())) {
            throw new BizException("该订单已经支付成功，不能再次支付");
        }

        managedThreadPool.execute(new MoneyfeedToEbsOrderPayInBalanceThread(balancePayDtoReq));

        return dtoResult;
    }

    @Override
    public DtoResult moneyfeedToEbsOrderPayInBankCardType(MoneyfeedToEbsOrderPayInBankCardTypeDtoReq bankCardPayDtoReq) throws Exception {
        DtoResult dtoResult = commonService.getSuccessDtoResult();

        // 0、判断订单是否存在 1、判断订单正在支付中
        EbsOrderExModel ebsOrderWithDetail = ebsOrderService.getEbsOrderWithDetailByEbsOrderNo(bankCardPayDtoReq.getEbsOrderNo());

        if (null == ebsOrderWithDetail) {
            throw new BizException("该订单还没创建，创建后再支付");
        }

        if (ebsOrderWithDetail != null && MoneyfeedToEbsOperationStatusEnum.OPERATING.name().equals(ebsOrderWithDetail.getOrderPayStatus())) {
            return dtoResult;
        }

        managedThreadPool.execute(new MoneyfeedToEbsOrderPayInBankCardThread(bankCardPayDtoReq));

        return dtoResult;
    }


	@Override
	public DtoResult moneyfeedToEbsOrderRecharge(MoneyfeedToEbsOrderRechargeDtoReq orderRechargeDtoReq) {
		DtoResult dtoResult = commonService.getSuccessDtoResult();
		if(orderRechargeDtoReq.getRechargeDate() == null ){
			dtoResult.setCode(ResultCode.FAIL.getCode());
            dtoResult.setMsg("充值日期为空");
            return dtoResult;
		}
		if(orderRechargeDtoReq.getRechargeAmount() == null ){
			dtoResult.setCode(ResultCode.FAIL.getCode());
            dtoResult.setMsg("充值金额为空");
            return dtoResult;
		}
        List<EbsRechargeModel> ebsRechargeModelByMoneyfeedPayNoList =
        		ebsRechargeService.queryEbsRechargeModelByMoneyfeedPayNo(orderRechargeDtoReq.getMoneyfeedPayNo());
        EbsRechargeModel ebsRechargeModel = null;
        if(!CollectionUtils.isEmpty(ebsRechargeModelByMoneyfeedPayNoList)){
        	int size = ebsRechargeModelByMoneyfeedPayNoList.size();
        	if(size > 1){
        		dtoResult.setCode(ResultCode.FAIL.getCode());
                dtoResult.setMsg("订单充值数据异常");
                return dtoResult;
        	}else{
        		ebsRechargeModel = ebsRechargeModelByMoneyfeedPayNoList.get(0);
        		if(MoneyfeedToEbsOperationStatusEnum.OPERATING.name().equals(ebsRechargeModel.getPayStatus())){
        			dtoResult.setCode(ResultCode.SUCCESS.getCode());
                    dtoResult.setMsg("充值中，商城充值流水号：" + ebsRechargeModel.getMoneyfeedPayNo());
                    return dtoResult;
        		}else if(MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_SUCCESS.name().equals(ebsRechargeModel.getPayStatus())){
        			dtoResult.setCode(ResultCode.SUCCESS.getCode());
                    dtoResult.setMsg("充值成功，商城充值流水号：" + ebsRechargeModel.getMoneyfeedPayNo());
                    return dtoResult;
        		}
        	}
        }
        
        String key = "moneyfeedtoEbs:recharge:" + orderRechargeDtoReq.getOrgId() + orderRechargeDtoReq.getCusNo() + orderRechargeDtoReq.getMoneyfeedPayNo();
   	 	//根据支付流水号加锁
        commonService.addLock(key);
        
        // 判断哪种充值方式
        if(PayTypeEnums.SO_PAY.name().equals(orderRechargeDtoReq.getPayType())){ //订单充值
        	if(StringUtils.isEmpty(orderRechargeDtoReq.getEbsOrderNo())){
        		dtoResult.setCode(ResultCode.FAIL.getCode());
                dtoResult.setMsg("订单充值EBS订单编码为空");

                return dtoResult;
        	}
        	// 0、判断订单是否存在 1、判断订单正在支付中
        	EbsOrderExModel ebsOrderWithDetail = ebsOrderService.getEbsOrderWithDetailByEbsOrderNo(orderRechargeDtoReq.getEbsOrderNo());
        	
        	if (null == ebsOrderWithDetail) {
        		throw new BizException("该订单还没创建，创建后再充值");
        	}
        	// 需要确认订单未创建成功是否可以充值
        	if (!MoneyfeedToEbsOperationStatusEnum.BIZ_OPERATION_SUCCESS.name().equals(ebsOrderWithDetail.getOrderCreateStatus())) {
                dtoResult.setCode(ResultCode.FAIL.getCode());
                dtoResult.setMsg("订单没有创建成功");

                return dtoResult;
            }
        	
        	if(ebsRechargeModel == null){
        		saveEbsRechargeModel(orderRechargeDtoReq , ebsOrderWithDetail.getId() , PayTypeEnums.SO_PAY);
        	}
        	managedThreadPool.execute(new MoneyfeedToEbsOrderBalanceRechargeThread(orderRechargeDtoReq , key));
        }else if (PayTypeEnums.ACC_PAY.name().equals(orderRechargeDtoReq.getPayType())){ //账户充值
        	if(ebsRechargeModel == null){
        		saveEbsRechargeModel(orderRechargeDtoReq , null , PayTypeEnums.ACC_PAY);
        	}
        	managedThreadPool.execute(new MoneyfeedToEbsOrderAccountRechargeThread(orderRechargeDtoReq , key));
        }
        
        return dtoResult;
    }
	
	public void saveEbsRechargeModel(MoneyfeedToEbsOrderRechargeDtoReq orderRechargeDtoReq , Long orderId , PayTypeEnums payTypeEnum){
		EbsOrderRechargeModelBuilder ebsOrderRechargeModelBuilder = EbsOrderRechargeModelBuilder.anEbsOrderRechargeModel();
    	ebsOrderRechargeModelBuilder.totalPayAmount(orderRechargeDtoReq.getRechargeAmount())
    	.currency(orderRechargeDtoReq.getCurrency())
    	.orderId(orderId)
    	.payDate(orderRechargeDtoReq.getRechargeDate())
    	.payType(payTypeEnum.name())
    	.tempOwe(orderRechargeDtoReq.getTempOwe())
    	.payStatus(MoneyfeedToEbsOperationStatusEnum.UNCOMMIT.name())
    	.moneyfeedPayNo(orderRechargeDtoReq.getMoneyfeedPayNo())
    	.accNo(orderRechargeDtoReq.getAccNo())
    	.customerNo(orderRechargeDtoReq.getCusNo())
    	.orgId(orderRechargeDtoReq.getOrgId())
    	.ebsRealAccount(orderRechargeDtoReq.getEbsRealAccount());
    	
    	EbsRechargeModel ebsRechargeModel = ebsOrderRechargeModelBuilder.build();
    	ebsRechargeService.save(Lists.newArrayList(ebsRechargeModel));
	}


	@Override
	public DtoResult moneyfeedToEbsOrderCreateAfterCancel(MoneyfeedToEbsOrderReCreateDtoReq orderReCreateDtoReq) throws Exception {
		//先调用取消方法
		MoneyfeedToEbsOrderCancelDtoReq cancelDtoReq = new MoneyfeedToEbsOrderCancelDtoReq();
		cancelDtoReq.setEbsOrderNo(orderReCreateDtoReq.getEbsOrderNo());
		cancelDtoReq.setOrg_id(orderReCreateDtoReq.getOrgId());
		DtoResult cancelDtoResult = moneyfeedToEbsOrderCancel(cancelDtoReq);
		
		//调用创建方法(如果成功)
		if(cancelDtoResult.getCode() == ResultCode.SUCCESS.getCode()){
			MoneyfeedToEbsOrderCreateDtoReq createDtoReq = new MoneyfeedToEbsOrderCreateDtoReq();
			BeanUtils.copyProperties(orderReCreateDtoReq, createDtoReq);
			createDtoReq.setProductList(orderReCreateDtoReq.getProductList()); 
			DtoResult createDtoResult = moneyfeedToEbsOrderCreate(createDtoReq);
			return createDtoResult;
		}
		
		return cancelDtoResult;
	}
	
}
