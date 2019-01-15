package com.newhope.pay.biz.service.impl;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.common.context.AppContext;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.common.util.ExcelImportUitls;
import com.newhope.moneyfeed.pay.api.bean.PayBillDataExceptionModel;
import com.newhope.moneyfeed.pay.api.bean.PayBillDataModel;
import com.newhope.moneyfeed.pay.api.bean.PayOrderInfoModel;
import com.newhope.moneyfeed.pay.api.bean.PayOrderLogModel;
import com.newhope.moneyfeed.pay.api.dto.req.PayOrderInfoQueryDtoReq;
import com.newhope.moneyfeed.pay.api.enums.AbnormalBillEnum;
import com.newhope.moneyfeed.pay.api.enums.CheckPayStatusEnum;
import com.newhope.moneyfeed.pay.api.enums.PayStatusEnum;
import com.newhope.pay.biz.service.PayBillDataExceptionService;
import com.newhope.pay.biz.service.PayBillDataService;
import com.newhope.pay.biz.service.PayOrderLogService;
import com.newhope.pay.biz.service.PayOrderService;
import com.newhope.pay.biz.service.PaymentScheduleService;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bena.peng
 * @date 2018/12/19 14:26
 */

@Service
public class PaymentScheduleServiceImpl implements PaymentScheduleService {
    private final Logger logger = LoggerFactory.getLogger(PaymentScheduleServiceImpl.class);

    @Autowired
    PayBillDataService payBillDataService;

    @Autowired
    PayOrderService payOrderService;

    @Autowired
    PayOrderLogService payOrderLogService;

    @Autowired
    PayBillDataExceptionService payBillDataExceptionService;

    @Override

    @Transactional(rollbackFor = Exception.class)
    public DtoResult syncThirdPaymentBill() {

        List<PayBillDataModel> billData = fetchBillData();
        if (billData == null) {
            return DtoResult.success();
        }
        Map<String, PayOrderInfoModel> payOrderData = getPayOrderData();


        //第一步 以第三方支付平台的对账单为驱动数据，进行对账
        checkBillByOther(billData, payOrderData);

        //第二步，对商城未匹配上的数据再次对账
        if (payOrderData.size() > 0) {
            checkBillByLocal(payOrderData);
        }
        return DtoResult.success();
    }


    /**
     * 以第三方平台对账单为依据对账
     * 将对账后的数据从 payOrderData中取出
     *
     * @param billData
     * @param payOrderData
     */
    private void checkBillByOther(List<PayBillDataModel> billData, Map<String, PayOrderInfoModel> payOrderData) {
        List<PayBillDataExceptionModel> exceptionModels = new ArrayList<>();
        List<PayOrderLogModel> orderLogModels = new ArrayList<>();
        //商城当前集合中不存在的支付平台的订单数据
        List<String> exclusiveOrders = new ArrayList<>();
        Map<String, PayBillDataModel> exclusiveBillMap = new HashMap<>();
        for (PayBillDataModel billDataModel : billData) {
            String payOrderNo = billDataModel.getPayOrderNo();
            this.updateBillExceptionFlag(payOrderNo);
            if (payOrderData.containsKey(payOrderNo)) {
                //对过账的数据进行移除
                PayOrderInfoModel localOrderModel = payOrderData.get(payOrderNo);
                processMatchingOrder(localOrderModel, billDataModel, exceptionModels, orderLogModels);
                payOrderData.remove(payOrderNo);
                continue;
            }
            //商城无支付订单数据，支付平台有数据
            exclusiveBillMap.put(payOrderNo, billDataModel);
            //处理商城的订单数据未查询出来的情况
            exclusiveOrders.add(payOrderNo);

        }
        processExclusiveLocalOrders(exclusiveOrders, exclusiveBillMap, exceptionModels, orderLogModels);
        payBillDataExceptionService.save(exceptionModels);
        payOrderLogService.save(orderLogModels);
    }

    /**
     * 以本地支付订单为对账依据
     * 处理本地系统有数据，支付平台无数据的case
     *
     * @param payOrderData
     */
    private void checkBillByLocal(Map<String, PayOrderInfoModel> payOrderData) {
        List<PayBillDataExceptionModel> exceptionModels = new ArrayList<>();
        List<PayOrderLogModel> orderLogModels = new ArrayList<>();
        for (Map.Entry<String, PayOrderInfoModel> e : payOrderData.entrySet()) {
            PayBillDataExceptionModel exceptionModel = new PayBillDataExceptionModel();
            PayOrderInfoModel orderInfoModel = e.getValue();
            buildExceptionModelByLocal(orderInfoModel, exceptionModel);
            AbnormalBillEnum exceptionInstance = AbnormalBillEnum.SHOP_EXISTENCE_OUTSIDE_NON_EXISTENCE;
            exceptionModel.setReason(exceptionInstance.getDesc());
            exceptionModel.setReasonType(exceptionInstance.getValue());
            exceptionModels.add(exceptionModel);
            this.updateBillExceptionFlag(e.getKey());
            this.modifyPayOrderInfo(false, e.getKey(), null, null);
            orderLogModels.add(this.buildPayOrderLogModel(orderInfoModel, false, null));
        }

        payBillDataExceptionService.save(exceptionModels);
        payOrderLogService.save(orderLogModels);
    }


    /**
     * 处理商城无支付数据，支付平台有数据的情况
     * 有可能是当前时间范围内 未查出商城的订单数据，需要再次处理
     *
     * @param exclusiveOrders
     * @param exclusiveBillMap
     * @param exceptionModels
     */
    private void processExclusiveLocalOrders(List<String> exclusiveOrders, Map<String, PayBillDataModel> exclusiveBillMap,
                                             List<PayBillDataExceptionModel> exceptionModels,
                                             List<PayOrderLogModel> orderLogModels) {
        if (exclusiveOrders.size() == 0) {
            return;
        }
        PayOrderInfoQueryDtoReq query = new PayOrderInfoQueryDtoReq();
        query.setPayOrderNoList(exclusiveOrders);
        List<PayOrderInfoModel> exclusiveLocals = payOrderService.queryList(query);
        for (PayOrderInfoModel localOrderModel : exclusiveLocals) {
            String payOrderNo = localOrderModel.getPayOrderNo();
            if (exclusiveBillMap.containsKey(payOrderNo)) {
                processMatchingOrder(localOrderModel, exclusiveBillMap.get(payOrderNo), exceptionModels, orderLogModels);
                exclusiveBillMap.remove(payOrderNo);
            }
        }

        //没有匹配上的 添加到异常信息中
        for (Map.Entry<String, PayBillDataModel> e : exclusiveBillMap.entrySet()) {
            PayBillDataExceptionModel exceptionModel = buildExceptionModelByOther(e.getValue());
            AbnormalBillEnum exceptionInstance = AbnormalBillEnum.SHOP_NON_EXISTENCE_OUTSIDE_EXISTENCE;
            exceptionModel.setReason(exceptionInstance.getDesc());
            exceptionModel.setReasonType(exceptionInstance.getValue());
            exceptionModels.add(exceptionModel);
        }
    }

    /**
     * 更新 PayBillDataExceptionModel 中已存在的记录的fixed 状态
     *
     * @param payOrderNo
     */
    private void updateBillExceptionFlag(String payOrderNo) {
        //更新异常表中存在记录的fixed状态
        PayBillDataExceptionModel oldModel = new PayBillDataExceptionModel();
        oldModel.setPayOrderNo(payOrderNo);
        oldModel.setFixFlag(false);
        PayBillDataExceptionModel newModel = new PayBillDataExceptionModel();
        newModel.setPayOrderNo(payOrderNo);
        newModel.setFixFlag(true);
        payBillDataExceptionService.update(oldModel, newModel);
    }

    /**
     * 处理 商城和支付平台都存在的订单数据
     * 将异常信息放入到list中
     *
     * @param localOrderModel 商城的支付订单
     * @param billDataModel   支付平台的订单
     * @param exceptionModels 异常订单集合
     */
    private void processMatchingOrder(PayOrderInfoModel localOrderModel, PayBillDataModel billDataModel,
                                      List<PayBillDataExceptionModel> exceptionModels,
                                      List<PayOrderLogModel> orderLogModels) {

        String localStatus = localOrderModel.getStatus();
        double localAmount = localOrderModel.getAmount().doubleValue();

        String bankStatus = billDataModel.getStatus();
        PayStatusEnum bankPayStatus = bankStatus.contains("成功") ? PayStatusEnum.PAY_SUCESS : PayStatusEnum.PAY_FAIL;
        bankStatus = bankPayStatus.getValue();
        double bankAmount = billDataModel.getBankAmount().doubleValue();

        AbnormalBillEnum exceptionInstance = null;
        boolean matchFlag = true;
        //支付状态不一致
        if (!localStatus.equals(bankStatus)) {
            matchFlag = false;
            exceptionInstance = verifyPaymentStatus(localStatus, bankPayStatus);
        }
        //支付金额不一致
        if (matchFlag && localAmount != bankAmount) {
            matchFlag = false;
            exceptionInstance = verifyPaymentAmount(localAmount, bankAmount);
        }

        if (!matchFlag) {
            PayBillDataExceptionModel exceptionModel = buildMismatchingDataModel(billDataModel, localOrderModel);
            exceptionModel.setReason(exceptionInstance.getDesc());
            exceptionModel.setReasonType(exceptionInstance.getValue());
            exceptionModels.add(exceptionModel);
        }
        String remark = billDataModel.getRemark();
        this.modifyPayOrderInfo(matchFlag, localOrderModel.getPayOrderNo(), billDataModel.getFee(), remark);
        orderLogModels.add(this.buildPayOrderLogModel(localOrderModel, matchFlag, remark));
    }

    /**
     * 验证支付金额不一致的异常处理
     * @param localAmount
     * @param bankAmount
     * @return
     */
    private AbnormalBillEnum verifyPaymentAmount(double localAmount, double bankAmount) {
        AbnormalBillEnum exceptionInstance;
        if (localAmount > bankAmount) {
            exceptionInstance = AbnormalBillEnum.SHOP_MORE_OUTSIDE_LESS;
        } else {
            exceptionInstance = AbnormalBillEnum.SHOP_LESS_OUTSIDE_MORE;
        }
        return exceptionInstance;
    }

    /**
     * 验证支付状态不一致的异常处理
     * @param localStatus
     * @param bankPayStatus
     * @return
     */
    private AbnormalBillEnum verifyPaymentStatus(String localStatus, PayStatusEnum bankPayStatus) {
        AbnormalBillEnum exceptionInstance = null;
        PayStatusEnum payStatusEnum = PayStatusEnum.valueOf(localStatus);
        switch (payStatusEnum) {
            //商城成功，支付平台失败
            case PAY_SUCESS:
                exceptionInstance = AbnormalBillEnum.SHOP_SUCCESS_OUTSIDE_FAIL;
                break;

            case PAY_WAITTING:
            case PAY_PROGRESS:
                exceptionInstance = bankPayStatus.equals(PayStatusEnum.PAY_SUCESS) ?
                        AbnormalBillEnum.SHOP_PROGRESS_OUTSIDE_SUCCESS : AbnormalBillEnum.SHOP_PROGRESS_OUTSIDE_FAIL;
                break;
            //商城支付失败，支付平台成功
            case PAY_FAIL:
                exceptionInstance = AbnormalBillEnum.SHOP_FAIL_OUTSIDE_SUCCESS;
                break;
            default:
        }
        return exceptionInstance;
    }


    /**
     * 对账结束后 对 PayOrderInfoModel 做更新
     *
     * @param matchFlag
     * @param payOrderNo
     * @param bankFee
     */
    private void modifyPayOrderInfo(boolean matchFlag, String payOrderNo, BigDecimal bankFee, String remark) {
        String checkStatus = matchFlag ? CheckPayStatusEnum.CHECK_PAY_SUCESS.getValue() :
                CheckPayStatusEnum.CHECK_PAY_FAIL.getValue();

        PayOrderInfoModel oldOrderModel = new PayOrderInfoModel();
        oldOrderModel.setPayOrderNo(payOrderNo);
        PayOrderInfoModel newOrderModel = new PayOrderInfoModel();
        newOrderModel.setPayOrderNo(payOrderNo);
        newOrderModel.setFee(bankFee);
        newOrderModel.setCheckStatus(checkStatus);
        newOrderModel.setRemark(remark);
        payOrderService.update(oldOrderModel, newOrderModel);
    }

    /**
     * 根据 PayOrderInfoModel 构建 PayOrderLogModel
     * @param orderInfoModel
     * @param matchFlag 对账状态
     * @param remark
     * @return
     */
    private PayOrderLogModel buildPayOrderLogModel(PayOrderInfoModel orderInfoModel, boolean matchFlag, String remark) {
        PayOrderLogModel payOrderLogModel = new PayOrderLogModel();
        payOrderLogModel.setPayOrderId(orderInfoModel.getId());
        payOrderLogModel.setPayOrderNo(orderInfoModel.getPayOrderNo());
        payOrderLogModel.setBeforeStatus(orderInfoModel.getStatus());
        payOrderLogModel.setAfterStatus(orderInfoModel.getStatus());

        payOrderLogModel.setBeforeEbsStatus(orderInfoModel.getEbsStatus());
        payOrderLogModel.setAfterEbsStatus(orderInfoModel.getEbsStatus());

        payOrderLogModel.setBeforeCheckStatus(orderInfoModel.getCheckStatus());
        payOrderLogModel.setAfterCheckStatus(matchFlag ? CheckPayStatusEnum.CHECK_PAY_SUCESS.getValue()
                : CheckPayStatusEnum.CHECK_PAY_FAIL.getValue());


        payOrderLogModel.setRemark(remark);
        return payOrderLogModel;
    }


    /**
     * 查询系统 的支付订单
     *
     * @return
     */
    private Map<String, PayOrderInfoModel> getPayOrderData() {
//        List<PayOrderInfoModel> list = new ArrayList<>();
//        for (int i = 1; i < 3; i++) {
//            PayOrderInfoModel model = new PayOrderInfoModel();
//            model.setPayOrderNo("payno-" + i);
//            model.setOrderId((long) i);
//            model.setOrderNo("");
//            model.setTradeTime(new Date());
//            model.setAmount(BigDecimal.valueOf(i + 1));
//            model.setStatus(PayStatusEnum.PAY_SUCESS.getValue());
//            list.add(model);
//        }

        PayOrderInfoQueryDtoReq query = buildQueryEntity();
        List<PayOrderInfoModel> list = payOrderService.queryList(query);
        Map<String, PayOrderInfoModel> res = new HashMap<>();
        for (PayOrderInfoModel model : list) {
            res.put(model.getPayOrderNo(), model);
        }
        return res;

    }

    private PayOrderInfoQueryDtoReq buildQueryEntity() {
        PayOrderInfoQueryDtoReq query = new PayOrderInfoQueryDtoReq();
        query.setTradeBeginDate("2018-12-27 22:00:00");
        query.setTradeEndDate("2018-12-28 22:00:00");


        List<String> statusList = new ArrayList<>();
        statusList.add(CheckPayStatusEnum.CHECK_PAY_WAITTING.getValue());
        query.setCheckStatusList(statusList);
        return query;
    }

    /**
     * 获取银行对账单
     *
     * @return
     */
    private List<PayBillDataModel> fetchBillData() {
//        List<PayBillDataModel> list = new ArrayList<>();
//        for (int i = 1; i < 4; i++) {
//            PayBillDataModel model = new PayBillDataModel();
//            model.setPayOrderNo("payno-" + i);
//            model.setBankOrderNo("bankno-" + i);
//            model.setBankAmount(BigDecimal.valueOf(i));
//            model.setFee(BigDecimal.valueOf(i));
//            model.setTradeType("");
//            model.setBankTradeTime(new Date());
//            model.setStatus("成功");
//            list.add(model);
//        }
//        if (list.size() == 0) {
//            return null;
//        }
//        payBillDataService.save(list);
//        return list;

        try {
            FileInputStream fis = new FileInputStream("d:/1.xls");
            final List<PayBillDataModel> list = ExcelImportUitls.excelImportConvert(fis, PayBillDataModel.class, "xls", 3);
            if (list.size() == 0) {
                return null;
            }
            payBillDataService.save(list);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("fetch bill data faild!");
        }
        return null;

    }


    /**
     * 构建支付信息不匹配的 PayBillDataExceptionModel
     * 即对账单和本地都有支付信息的数据
     * @param billDataModel
     * @param localOrderModel
     */
    private PayBillDataExceptionModel buildMismatchingDataModel(PayBillDataModel billDataModel, PayOrderInfoModel localOrderModel) {
        PayBillDataExceptionModel exceptionModel = buildExceptionModelByOther(billDataModel);
        buildExceptionModelByLocal(localOrderModel, exceptionModel);
        return exceptionModel;
    }

    /**
     * 用商城的支付订单设置 PayBillDataExceptionModel 的属性
     *
     * @param localOrderModel
     * @param exceptionModel
     */
    private void buildExceptionModelByLocal(PayOrderInfoModel localOrderModel, PayBillDataExceptionModel exceptionModel) {
        exceptionModel.setPayOrderNo(localOrderModel.getPayOrderNo());
        exceptionModel.setOrderNo(localOrderModel.getOrderNo());
        exceptionModel.setPlatformid(localOrderModel.getPlatformid());
        exceptionModel.setMerchno(localOrderModel.getMerchno());
        exceptionModel.setTradeTime(localOrderModel.getTradeTime());
        exceptionModel.setAmount(localOrderModel.getAmount());
        exceptionModel.setStatus(localOrderModel.getStatus());
        exceptionModel.setFixFlag(false);
    }

    /**
     * 构建 PayBillDataExceptionModel
     * 以第三方支付平台设置属性
     *
     * @param billDataModel
     * @return
     */
    private PayBillDataExceptionModel buildExceptionModelByOther(PayBillDataModel billDataModel) {
        PayBillDataExceptionModel exceptionModel = new PayBillDataExceptionModel();
        exceptionModel.setPayOrderNo(billDataModel.getPayOrderNo());
        exceptionModel.setBankOrderNo(billDataModel.getBankOrderNo());
        exceptionModel.setBankAmount(billDataModel.getBankAmount());
        exceptionModel.setFee(billDataModel.getFee());
        exceptionModel.setRemark(billDataModel.getRemark());
        exceptionModel.setBankTradeTime(billDataModel.getBankTradeTime());
        exceptionModel.setBillStatus(billDataModel.getStatus());
        exceptionModel.setFixFlag(false);
        return exceptionModel;
    }


    @Override
    public DtoResult closeTimeoutPayOrder() {

        PayOrderInfoQueryDtoReq payOrderInfoQueryDtoReq = new PayOrderInfoQueryDtoReq();
        payOrderInfoQueryDtoReq.setPayStatusList(Arrays.asList(PayStatusEnum.PAY_PROGRESS.name()));
        payOrderInfoQueryDtoReq.setCheckStatusList(Arrays.asList(CheckPayStatusEnum.CHECK_PAY_WAITTING.name()));
        //超过十分钟
        Date date = new Date();
        date = DateUtils.add(date, Calendar.MINUTE, -10);
        payOrderInfoQueryDtoReq.setTradeEndDate(DateUtils.date2Str(new Date(), DateUtils.datetimeFormat));
        payOrderInfoQueryDtoReq.setPage(null);

        List<PayOrderInfoModel> payOrderList = payOrderService.queryList(payOrderInfoQueryDtoReq);
        if (CollectionUtils.isNotEmpty(payOrderList)) {
            for (PayOrderInfoModel payOrderInfoModel : payOrderList) {
                try {
                    //调用支付系统查询接口,如果没有查到,关闭
                    AppContext.getSpringContext().getBean(this.getClass()).closeTimeoutPayOrder(payOrderInfoModel);
                } catch (Exception e) {
                    logger.error("PaymentScheduleServiceImpl.closeTimeoutPayOrder关闭支付订单异常:", e);
                }

            }
        }
        return DtoResult.success();
    }

    @Transactional
    public void closeTimeoutPayOrder(PayOrderInfoModel payOrderInfoModel) {
        PayOrderInfoModel oldModel = new PayOrderInfoModel();
        oldModel.setId(payOrderInfoModel.getId());
        oldModel.setStatus(PayStatusEnum.PAY_PROGRESS.name());
        oldModel.setCheckStatus(CheckPayStatusEnum.CHECK_PAY_WAITTING.name());
        PayOrderInfoModel newModel = new PayOrderInfoModel();
        newModel.setStatus(PayStatusEnum.PAY_USELESS.name());
        newModel.setCheckStatus(CheckPayStatusEnum.NOT_CHECK.name());
        payOrderService.update(oldModel, newModel);
    }
}
