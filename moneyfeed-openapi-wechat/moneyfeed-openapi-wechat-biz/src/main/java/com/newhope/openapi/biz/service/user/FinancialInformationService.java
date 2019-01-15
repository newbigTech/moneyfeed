package com.newhope.openapi.biz.service.user;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillDetailModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillModel;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.balance.EbsBalanceDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDetailPageDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.balance.EbsBalanceDto;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.balance.EbsBalanceDtoResult;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDetailPageDtoResult;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDtoResult;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerAccountStatementListDtoResult;
import com.newhope.openapi.api.vo.response.user.*;
import com.newhope.openapi.biz.rpc.feign.user.CustomerAccountFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.EbsCustomerBalanceFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.EbsCustomerBillFeignClient;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class FinancialInformationService {
    private static final Logger logger = LoggerFactory.getLogger(FinancialInformationService.class);
    @Autowired
    private RSessionCache rSessionCache;
    @Autowired
    private EbsCustomerBalanceFeignClient customerBalanceFeignClient;
    @Autowired
    private EbsCustomerBillFeignClient customerBillFeignClient;
    @Autowired
    private CustomerAccountFeignClient accountFeignClient;

    public FinancialBalanceResult getBalance() {
        ClientUserCacheDtoResult user = getCurrentUser();
        FinancialBalanceResult result = new FinancialBalanceResult();

        EbsBalanceDtoReq params = new EbsBalanceDtoReq();
        params.setCustomerCode(user.getCustomer().getCustomer().getEbsCustomerNum());
        params.setOrgCode(String.valueOf(user.getVisitShop().getShop().getEbsOrgId()));
        logger.info("调用integration获取ebs余额请求参数：{}", JSON.toJSONString(params));
        BaseResponse<EbsBalanceDtoResult> feignResult = customerBalanceFeignClient.ebsBalanceQuery(params);
        logger.info("调用integration获取ebs余额返回结果：{}", JSON.toJSONString(feignResult));
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        if (feignResult.isSuccess()) {
            final List<EbsBalanceDto> dataList = feignResult.getData().getDataList();
            result.setAvailableCredit(dataList.stream().map(EbsBalanceDto::getAvailableCredit).reduce(BigDecimal.ZERO, BigDecimal::add));
            result.setReturnDeposit(dataList.stream().map(EbsBalanceDto::getDeposit).reduce(BigDecimal.ZERO, BigDecimal::add));
            result.setAvailable(dataList.stream().map(EbsBalanceDto::getAvailableBalance).reduce(BigDecimal.ZERO, BigDecimal::add));
            result.setBalance(dataList.stream().map(EbsBalanceDto::getBalance).reduce(BigDecimal.ZERO, BigDecimal::add));
        }
        return result;
    }

    public FinancialBillOverviewResult getBillOverview(Integer year, Integer month) {
        ClientUserCacheDtoResult user = getCurrentUser();
        FinancialBillOverviewResult result = new FinancialBillOverviewResult();
        EbsCustomerBillDtoReq params = wrapBillParams(year, month, user);
        logger.info("调用integration获取ebs账单信息请求参数：{}", JSON.toJSONString(params));
        BaseResponse<EbsCustomerBillDtoResult> feignResult = customerBillFeignClient.ebsBillQuery(params);
        logger.info("调用integration获取ebs账单信息返回结果：{}", JSON.toJSONString(feignResult));
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        if (feignResult.isSuccess()) {
            final List<EbsCustomerBillModel> dataList = feignResult.getData().getDataList();
            result.setBeginRepayment(dataList.stream().map(EbsCustomerBillModel::getBeginPlanPayment).reduce(BigDecimal.ZERO, BigDecimal::add));
            result.setEndRepayment(dataList.stream().map(EbsCustomerBillModel::getEndPlanPayment).reduce(BigDecimal.ZERO, BigDecimal::add));
            result.setTradingVolume(dataList.stream().mapToInt(EbsCustomerBillModel::getPayQuantity).sum());
            result.setPurchaseQuantity(dataList.stream().map(EbsCustomerBillModel::getBuyQuantity).reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue());
            result.setGiftQuantity(dataList.stream().map(EbsCustomerBillModel::getGiveQuantity).reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue());
            result.setPayable(dataList.stream().map(EbsCustomerBillModel::getMustPayable).reduce(BigDecimal.ZERO, BigDecimal::add));
            result.setActual(dataList.stream().map(EbsCustomerBillModel::getRealPayable).reduce(BigDecimal.ZERO, BigDecimal::add));
            result.setPayByBalance(dataList.stream().map(EbsCustomerBillModel::getBalancePay).reduce(BigDecimal.ZERO, BigDecimal::add));
            result.setPayByCard(dataList.stream().map(EbsCustomerBillModel::getCardPay).reduce(BigDecimal.ZERO, BigDecimal::add));
        }
        return result;
    }

    public FinancialBillDetailListResult getBillDetail(Integer page, Integer pageSize, Integer year, Integer month) {
        ClientUserCacheDtoResult user = getCurrentUser();
        final EbsCustomerBillDetailPageDtoReq params = wrapDetailParams(page, pageSize, year, month, user);
        logger.info("调用integration获取ebs账单详细信息请求参数：{}", JSON.toJSONString(params));
        BaseResponse<EbsCustomerBillDetailPageDtoResult> feignResult = customerBillFeignClient.ebsBillDetailQuery(
                params
        );
        logger.info("调用integration获取ebs账单详细信息返回结果：{}", JSON.toJSONString(feignResult));
        FinancialBillDetailListResult result = new FinancialBillDetailListResult();
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        final EbsCustomerBillDetailPageDtoResult data = feignResult.getData();
        if (feignResult.isSuccess() && data != null) {
            result.setTotalCount(data.getTotalCount());
            result.setTotalPage(data.getTotalPage());
            result.setPage(data.getPage());
            List<EbsCustomerBillDetailModel> ebsDetailList = data.getDataList();
            List<FinancialBillDetailResult> detailList = new ArrayList<>();
            Map<String, List<EbsCustomerBillDetailModel>> grouped = ebsDetailList.stream().collect(Collectors.groupingBy(EbsCustomerBillDetailModel::getDocmentNum));
            grouped.forEach((k, v) -> {
                FinancialBillDetailResult detail = new FinancialBillDetailResult();
                EbsCustomerBillDetailModel same = v.get(0);
                detail.setDay(new DateTime(same.getGmtCreated()).getDayOfMonth());
                detail.setDayOfTheWeek(new DateTime(same.getGmtCreated()).getDayOfWeek());
                detail.setType(same.getBillType());
                detail.setDocumentNo(same.getDocmentNum());
                detail.setLicensePlate(same.getCarNum());
                detail.setItems(v.stream().map(d -> {
                    FinancialBillDetailResult.Item item = new FinancialBillDetailResult.Item();
                    item.setName(d.getMaterielName());
                    item.setQuantity(d.getQuantity().doubleValue());
                    if (d.getAccountReceivable() != null) {
                        item.setPay(d.getAccountReceivable());
                        detail.setTotalPayType(0);
                    } else {
                        item.setPay(d.getFundsReceived());
                        detail.setTotalPayType(1);
                    }
                    item.setSpecification(d.getUomCode());
                    return item;
                }).collect(Collectors.toList()));
                detail.setTotalPay(detail.getItems().stream().map(FinancialBillDetailResult.Item::getPay).reduce(BigDecimal.ZERO, BigDecimal::add));
                detailList.add(detail);
            });
            result.setDetail(detailList);
        }
        return result;
    }

    public FinancialPaymentFlowListResult getPaymentFlow(Integer page,
                                                         Integer pageSize,
                                                         Integer year,
                                                         Integer month,
                                                         String bizType) {
        FinancialPaymentFlowListResult result = new FinancialPaymentFlowListResult();
        CustomerAccountStatementQueryDtoReq params = getCustomerAccountStatementQueryDtoParams(page, pageSize, year, month, bizType);
        logger.info("调用user-api获取流水信息请求参数：{}", JSON.toJSONString(params));
        BaseResponse<CustomerAccountStatementListDtoResult> feignResult = accountFeignClient.queryCustomerAccountStatement(params);
        logger.info("调用user-api获取流水信息返回结果：{}", JSON.toJSONString(feignResult));
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        if (feignResult.isSuccess() && feignResult.getData() != null) {
            result.setPage(feignResult.getData().getPage());
            result.setTotalCount(feignResult.getData().getTotalCount());
            result.setTotalPage(feignResult.getData().getTotalPage());
            List<FinancialPaymentFlowResult> flowResults = feignResult.getData().getStatements().stream().map(statement -> {
                FinancialPaymentFlowResult flow = new FinancialPaymentFlowResult();
                DateTime createdAt = new DateTime(statement.getGmtCreated());
                flow.setDay(createdAt.getDayOfMonth());
                flow.setDayOfTheWeek(createdAt.getDayOfWeek());
                flow.setOrderCode(statement.getOrderNo());
                flow.setPayOrderCode(statement.getPayOrderNo());
                flow.setNumber(String.valueOf(statement.getSerialNumber()));
                flow.setPayment(statement.getAmount());
                flow.setCreated(createdAt.toString("yyyy年MM月dd日 HH:mm"));
                flow.setGroupbyDate(createdAt.toString("yyyy年MM月dd日"));
                flow.setType(statement.getBizType());
                flow.setCard(statement.getAccount().getCardNo());
                flow.setIssuingBank(statement.getAccount().getIssuingBank());
                return flow;
            }).collect(Collectors.toList());
            result.setFlow(flowResults.stream().collect(Collectors.groupingBy(FinancialPaymentFlowResult::getGroupbyDate, TreeMap::new, Collectors.toList())));
        }
        return result;

    }

    public FinancialMonthBillOverviewListResult getMonthBillOverview() {
        ClientUserCacheDtoResult user = getCurrentUser();
        FinancialMonthBillOverviewListResult result = new FinancialMonthBillOverviewListResult();
        EbsCustomerBillDtoReq params = wrapBillParams(null, null, user);
        logger.info("getMonthBillOverview调用integration获取ebs账单信息请求参数：{}", JSON.toJSONString(params));
        BaseResponse<EbsCustomerBillDtoResult> feignResult = customerBillFeignClient.ebsBillQuery(params);
        logger.info("getMonthBillOverview调用integration获取ebs账单信息返回结果：{}", JSON.toJSONString(feignResult));
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        if (feignResult.isSuccess()) {
            final List<EbsCustomerBillModel> dataList = feignResult.getData().getDataList();
            for (EbsCustomerBillModel bill : dataList) {
                FinancialMonthBillOverviewResult billResult = new FinancialMonthBillOverviewResult();
                billResult.setMonth(bill.getMonth());
                billResult.setYear(bill.getYear());
                result.getDataList().add(billResult);
            }
        }
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        return result;
    }

    private CustomerAccountStatementQueryDtoReq getCustomerAccountStatementQueryDtoParams(long page, Integer pageSize, Integer year, Integer month, String bizType) {
        CustomerAccountStatementQueryDtoReq params = new CustomerAccountStatementQueryDtoReq();
        params.setBizType(bizType);
        params.setBeginDate(new DateTime().withYear(year).withMonthOfYear(month).dayOfMonth().withMinimumValue().toString("yyyy-MM-dd"));
        params.setEndDate(new DateTime().withYear(year).withMonthOfYear(month).dayOfMonth().withMaximumValue().toString("yyyy-MM-dd"));
        params.setCustomerId(getCurrentUser().getCustomer().getCustomer().getId());
        params.setPage(page);
        params.setPageSize(pageSize);
        return params;
    }

    private ClientUserCacheDtoResult getCurrentUser() {
        ClientUserCacheDtoResult currentUser = (ClientUserCacheDtoResult) rSessionCache.getUserInfo();
        if (currentUser == null) {
            throw new BizException(ResultCode.USER_LOGIN_REQUIRED);
        }
        try {
            // 检查是否非法登录，如果是非法登录，下面两句代码会出现异常
            currentUser.getCustomer().getCustomer().getEbsCustomerNum();
            currentUser.getVisitShop().getShop().getEbsOrgId();
        } catch (Exception e) {
            throw new BizException(ResultCode.USER_CUSTOMER_OR_SHOP_NOT_EXIST.getCode(), "登录用户无对应客户或未选择对应的商铺");
        }
        return currentUser;
    }

    private EbsCustomerBillDtoReq wrapBillParams(Integer year, Integer month, ClientUserCacheDtoResult user) {
        EbsCustomerBillDtoReq params = new EbsCustomerBillDtoReq();
        params.setCustomerNo(user.getCustomer().getCustomer().getEbsCustomerNum());
        params.setOrgId(String.valueOf(user.getVisitShop().getShop().getEbsOrgId()));
        params.setMonth(month);
        params.setYear(year);
        return params;
    }

    private EbsCustomerBillDetailPageDtoReq wrapDetailParams(Integer page,
                                                             Integer pageSize,
                                                             Integer year,
                                                             Integer month, ClientUserCacheDtoResult user) {
        EbsCustomerBillDetailPageDtoReq params = new EbsCustomerBillDetailPageDtoReq();
        params.setCustomerNo(user.getCustomer().getCustomer().getEbsCustomerNum());
        params.setOrgId(String.valueOf(user.getVisitShop().getShop().getEbsOrgId()));
        params.setPage((long) page);
        params.setPageSize(pageSize);
        params.setYear(year);
        params.setMonth(month);
        return params;
    }
}
