package com.newhope.openapi.biz.service.customer;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.constant.StatisticsConstant;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.common.util.DateUtil;
import com.newhope.moneyfeed.common.util.ExportUtil;
import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillDetailModel;
import com.newhope.moneyfeed.integration.api.bean.ebs.bill.EbsCustomerBillModel;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDetailPageDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDetailPageDtoResult;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDtoResult;
import com.newhope.moneyfeed.order.api.enums.PayTypeEnums;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerAccountStatementExModel;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerExModel;
import com.newhope.moneyfeed.user.api.constant.CommonConstant;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerAccountStatementListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopDtoListResult;
import com.newhope.openapi.api.enums.BillTypeEnums;
import com.newhope.openapi.api.vo.request.customer.FinancialBillQueryReq;
import com.newhope.openapi.api.vo.request.customer.FinancialPaymentFlowQueryReq;
import com.newhope.openapi.api.vo.request.customer.MonthBillOverviewReq;
import com.newhope.openapi.api.vo.response.customer.FinancialBillDetailListResult;
import com.newhope.openapi.api.vo.response.customer.FinancialBillDetailResult;
import com.newhope.openapi.api.vo.response.customer.FinancialBillOverviewResult;
import com.newhope.openapi.api.vo.response.customer.FinancialMonthBillOverviewListResult;
import com.newhope.openapi.api.vo.response.customer.FinancialMonthBillOverviewResult;
import com.newhope.openapi.api.vo.response.customer.FinancialPaymentFlowListResult;
import com.newhope.openapi.api.vo.response.customer.FinancialPaymentFlowResult;
import com.newhope.openapi.biz.common.BizConstant;
import com.newhope.openapi.biz.rpc.feign.customer.CustomerAccountFeignClient;
import com.newhope.openapi.biz.rpc.feign.customer.CustomerFeignClient;
import com.newhope.openapi.biz.rpc.feign.customer.EbsCustomerBillFeignClient;
import com.newhope.openapi.biz.rpc.feign.shop.ShopFeignClient;

@Service
public class FinancialInformationService {
	
	private static final Logger logger = LoggerFactory.getLogger(FinancialInformationService.class);
	
	@Autowired
    private EbsCustomerBillFeignClient customerBillFeignClient;
	
	@Autowired
	private ShopFeignClient shopFeignClient;
	
	@Autowired
	CustomerFeignClient customerFeignClient;
	
	@Autowired
	CustomerAccountFeignClient accountFeignClient;
	@Value("${preview.url}")
	String previewUrl;
	
	public FinancialBillOverviewResult getBillOverview(FinancialBillQueryReq financialBillQueryReq) {
        FinancialBillOverviewResult result = new FinancialBillOverviewResult();
        EbsCustomerBillDtoReq params = new EbsCustomerBillDtoReq();
        params.setCustomerNo(financialBillQueryReq.getCustomerNum());
        params.setMonth(financialBillQueryReq.getMonth());
        params.setYear(financialBillQueryReq.getYear());
        //根据店铺id获取orgId
        ShopQueryDtoReq queryDtoReq = new ShopQueryDtoReq();
        queryDtoReq.setId(financialBillQueryReq.getShopId());
        BaseResponse<ShopDtoListResult> shopFeign = shopFeignClient.queryShop(queryDtoReq);
        if(shopFeign.isSuccess() && null!= shopFeign.getData() 
        		&& CollectionUtils.isNotEmpty(shopFeign.getData().getShops())) {
        	params.setOrgId(shopFeign.getData().getShops().get(0).getEbsOrgId());
        }
        
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
	
	public FinancialBillDetailListResult getBillDetail(FinancialBillQueryReq financialBillQueryReq) {
		String shopName = "";
        EbsCustomerBillDetailPageDtoReq params = new EbsCustomerBillDetailPageDtoReq();
        //根据店铺id获取orgId
        ShopQueryDtoReq queryDtoReq = new ShopQueryDtoReq();
        queryDtoReq.setId(financialBillQueryReq.getShopId());
        BaseResponse<ShopDtoListResult> shopFeign = shopFeignClient.queryShop(queryDtoReq);
        if(shopFeign.isSuccess() && null!= shopFeign.getData() 
        		&& CollectionUtils.isNotEmpty(shopFeign.getData().getShops())) {
        	params.setOrgId(shopFeign.getData().getShops().get(0).getEbsOrgId());
        	shopName = shopFeign.getData().getShops().get(0).getName();
        }
        params.setCustomerNo(financialBillQueryReq.getCustomerNum());
        params.setMonth(financialBillQueryReq.getMonth());
        params.setYear(financialBillQueryReq.getYear());
        params.setPage(financialBillQueryReq.getPage());
        params.setPageSize(financialBillQueryReq.getPageSize());
        
        logger.info("调用integration获取ebs账单详细信息请求参数：{}", JSON.toJSONString(params));
        BaseResponse<EbsCustomerBillDetailPageDtoResult> feignResult = customerBillFeignClient.ebsBillDetailQuery(params);
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
            if(CollectionUtils.isNotEmpty(ebsDetailList)) {
            	List<FinancialBillDetailResult> detailList = new ArrayList<>();
            	for (EbsCustomerBillDetailModel same : ebsDetailList) {
                    FinancialBillDetailResult detail = new FinancialBillDetailResult();
                    detail.setGmtCreated(same.getGmtCreated());
                    detail.setDayOfTheWeek(new DateTime(same.getGmtCreated()).getDayOfWeek());
                    if(StringUtils.isNotBlank(same.getBillType())) {
                    	if("1".equals(same.getBillType())) {
                    		detail.setType(BillTypeEnums.ONLINE.getDesc());
                    	}
                    	if("2".equals(same.getBillType())) {
                    		detail.setType(BillTypeEnums.OFFLINE.getDesc());
                    	}
                    }else {
                    	detail.setType("");
                    }
                    detail.setDocumentNo(same.getDocmentNum());
                    detail.setLicensePlate(same.getCarNum());
                    detail.setName(same.getMaterielName());
                    detail.setQuantity(same.getQuantity().doubleValue());
                	detail.setAccountReceivable(same.getAccountReceivable());
                	detail.setFundsReceived(same.getFundsReceived());
                    detail.setSpecification(same.getUomCode());
                    detail.setCustomerName(financialBillQueryReq.getCustomerName());
                    detail.setShopName(shopName);
                    
                    detailList.add(detail);
            	}
            	result.setDetail(detailList);
            }
        }
        return result;
    }
	
	public FinancialMonthBillOverviewListResult getMonthBillOverview(MonthBillOverviewReq mzonthBillOverviewReq) {
        FinancialMonthBillOverviewListResult result = new FinancialMonthBillOverviewListResult();
        EbsCustomerBillDtoReq params = new EbsCustomerBillDtoReq();
        params.setCustomerNo(mzonthBillOverviewReq.getEbsCustomerNum());
        ShopQueryDtoReq queryDtoReq = new ShopQueryDtoReq();
        queryDtoReq.setId(mzonthBillOverviewReq.getShopId());
        queryDtoReq.setEnable(true);
        BaseResponse<ShopDtoListResult> shopFeignResult = shopFeignClient.queryShop(queryDtoReq);
        if(shopFeignResult.isSuccess() && null != shopFeignResult.getData() 
        		&& CollectionUtils.isNotEmpty(shopFeignResult.getData().getShops())) {
        	params.setOrgId(shopFeignResult.getData().getShops().get(0).getEbsOrgId());
        }
        params.setMonth(mzonthBillOverviewReq.getMonth());
        params.setYear(mzonthBillOverviewReq.getYear());
        BaseResponse<EbsCustomerBillDtoResult> feignResult = customerBillFeignClient.ebsBillQuery(params);
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        if (feignResult.isSuccess()) {
            final List<EbsCustomerBillModel> dataList = feignResult.getData().getDataList();
            for(EbsCustomerBillModel bill : dataList){
            	FinancialMonthBillOverviewResult billResult= new FinancialMonthBillOverviewResult();
            	billResult.setMonth(bill.getMonth());
            	billResult.setYear(bill.getYear());
            	billResult.setCustomerName(mzonthBillOverviewReq.getCustomerName());
            	billResult.setPreviewUrl(previewUrl + "?year="+ bill.getYear() + "&month=" + bill.getMonth() + "&orgId=" + bill.getOrgId() + "&customerNo=" + bill.getCustomerNo() );
            	billResult.setShopName(shopFeignResult.getData().getShops().get(0).getName());
            	result.getDataList().add(billResult);
            }
        }
        result.setCode(feignResult.getCode());
        result.setMsg(feignResult.getMsg());
        return result;
    }
	
	public void exportBillDetail(FinancialBillQueryReq financialBillQueryReq, HttpServletResponse response) {
		String shopName = "";
		
		EbsCustomerBillDetailPageDtoReq params = new EbsCustomerBillDetailPageDtoReq();
        //根据店铺id获取orgId
        ShopQueryDtoReq queryDtoReq = new ShopQueryDtoReq();
        queryDtoReq.setId(financialBillQueryReq.getShopId());
        BaseResponse<ShopDtoListResult> shopFeign = shopFeignClient.queryShop(queryDtoReq);
        if(shopFeign.isSuccess() && null!= shopFeign.getData() 
        		&& CollectionUtils.isNotEmpty(shopFeign.getData().getShops())) {
        	params.setOrgId(shopFeign.getData().getShops().get(0).getEbsOrgId());
        	shopName = shopFeign.getData().getShops().get(0).getName();
        }
        params.setCustomerNo(financialBillQueryReq.getCustomerNum());
        params.setMonth(financialBillQueryReq.getMonth());
        params.setYear(financialBillQueryReq.getYear());
        params.setPage(1L);
        params.setPageSize(9999);
        
        logger.info("调用integration获取ebs账单详细信息请求参数：{}", JSON.toJSONString(params));
        BaseResponse<EbsCustomerBillDetailPageDtoResult> feignResult = customerBillFeignClient.ebsBillDetailQuery(params);
        logger.info("调用integration获取ebs账单详细信息返回结果：{}", JSON.toJSONString(feignResult));
        
        List<Map<String, Object>> billDetailList = new ArrayList<Map<String,Object>>();
        final EbsCustomerBillDetailPageDtoResult data = feignResult.getData();
        if (feignResult.isSuccess() && data != null) {
        	Map<String, Object> map = null;
            List<EbsCustomerBillDetailModel> ebsDetailList = data.getDataList();
            if(CollectionUtils.isNotEmpty(ebsDetailList)) {
            	for (EbsCustomerBillDetailModel same : ebsDetailList) {
            		map = new HashMap<String, Object>();
            		if(null == same.getGmtCreated()) {
                    	map.put("day", "");
                    }else {
                    	map.put("day", DateUtil.getDateStr(same.getGmtCreated(), DateUtil.YYYY_MM_DD_HH_MM_SS));
                    }
                    map.put("shopName", shopName);
                    map.put("customerName", financialBillQueryReq.getCustomerName());
                    String type = "";
                    if(StringUtils.isNotBlank(same.getBillType())) {
                    	if("1".equals(same.getBillType())) {
                    		type = BillTypeEnums.ONLINE.getDesc();
                    	}
                    	if("2".equals(same.getBillType())) {
                    		type = BillTypeEnums.OFFLINE.getDesc();
                    	}
                    }
                    map.put("type", type);
                    map.put("documentNo", StringUtils.isBlank(same.getDocmentNum()) ? "" : same.getDocmentNum());
                    map.put("materielName", StringUtils.isBlank(same.getMaterielName()) ? "" : same.getMaterielName());
                    map.put("licensePlate", StringUtils.isBlank(same.getCarNum()) ? "" : same.getCarNum());
                    map.put("name", StringUtils.isBlank(same.getMaterielName()) ? "" : same.getMaterielName());
                    if(null == same.getQuantity() ) {
                    	map.put("quantity",0);
                    }else {
                    	BigDecimal num = BigDecimal.ZERO;
//                    	if(StringUtils.isNotBlank(same.getUomCode()) && "kg".equals(same.getUomCode())) {
                    		num = same.getQuantity().divide(BigDecimal.valueOf(1000), 2, BigDecimal.ROUND_FLOOR);
//                    	}
                    	map.put("quantity", num);
                    }
                    
                    if (same.getAccountReceivable() != null) {
                    	map.put("accountReceivable", same.getAccountReceivable());
                    } else {
                    	map.put("accountReceivable", "");
                    }
                    if(same.getFundsReceived() != null) {
                    	map.put("fundsReceived", same.getFundsReceived());
                    }else {
                    	map.put("fundsReceived", "");
                    }
                    
                    billDetailList.add(map);
            	}
            }
        }
        	// map映射key
     		String mapKey = "day,shopName,customerName,type,documentNo,materielName,licensePlate,quantity,accountReceivable,fundsReceived";
     		try {
     			final OutputStream os = response.getOutputStream();
     			ExportUtil.responseSetProperties(BizConstant.TITLE_EXPORT_BILL_DETAIL, response);
     			ExportUtil.doExport(billDetailList, BizConstant.COLUMNS_EXPORT_BILL_DETAIL,
     					BizConstant.CSV_COLUMN_SEPARATOR, BizConstant.CSV_RN, mapKey, os);
     		} catch (Exception e) {
     			logger.error("[FinancialInformationService.exportBillDetail]:下载文件异常", e);
     		}
	}

	public FinancialPaymentFlowListResult paymentFlow(FinancialPaymentFlowQueryReq queryReq){
		FinancialPaymentFlowListResult result = new FinancialPaymentFlowListResult();
		CustomerAccountStatementQueryDtoReq statementParam = new CustomerAccountStatementQueryDtoReq();
		statementParam.setBeginDate(queryReq.getStartDate());
		statementParam.setEndDate(queryReq.getEndDate());
		statementParam.setPage(queryReq.getPage());
		statementParam.setPageSize(queryReq.getPageSize());
		statementParam.setBizType(queryReq.getBizType());
		statementParam.setOrderNo(queryReq.getOrderNo());
		statementParam.setPayOrderNo(queryReq.getPayOrderNo());
		if(StringUtils.isNotBlank(queryReq.getShopName())){
			ShopQueryDtoReq queryDtoReq = new ShopQueryDtoReq();
	        queryDtoReq.setLikeName(queryReq.getShopName());
	        BaseResponse<ShopDtoListResult> shopFeign = shopFeignClient.queryShop(queryDtoReq);
	        if(shopFeign.isSuccess()&&shopFeign.getData()!=null&&
					CollectionUtils.isNotEmpty(shopFeign.getData().getShops())){
	        	List<Long> shopIds = new ArrayList<Long>();
				for(UcShopModel shop: shopFeign.getData().getShops()){
					shopIds.add(shop.getId());
				}
				statementParam.setShopIds(shopIds);
	        }else{
	        	result.setCode(ResultCode.SUCCESS.getCode());
				result.setMsg(ResultCode.SUCCESS.getDesc());
				return result;
	        }
		}
		if(StringUtils.isNotBlank(queryReq.getCustomerName())){
			CustomerQueryDtoReq customerParam = new CustomerQueryDtoReq();
			customerParam.setCustomerName(queryReq.getCustomerName());
			BaseResponse<CustomerDtoListResult> feignResp = customerFeignClient.queryCustomer(customerParam);
			if(feignResp.isSuccess()&&feignResp.getData()!=null&&
					CollectionUtils.isNotEmpty(feignResp.getData().getCustomers())){
				List<Long> customerIds = new ArrayList<Long>();
				for(UcCustomerExModel customer: feignResp.getData().getCustomers()){
					customerIds.add(customer.getId());
				}
				statementParam.setCustomerIds(customerIds);
			}else{
				result.setCode(ResultCode.SUCCESS.getCode());
				result.setMsg(ResultCode.SUCCESS.getDesc());
				return result;
			}
		}
		BaseResponse<CustomerAccountStatementListDtoResult> feignResp = accountFeignClient.queryCustomerAccountStatement(statementParam);
		if(feignResp.isSuccess()&&feignResp.getData()!=null&&
				CollectionUtils.isNotEmpty(feignResp.getData().getStatements())){
			Map<Long, UcCustomerExModel> customerMap = new HashMap<Long, UcCustomerExModel>();
			Map<Long, UcShopModel> shopMap = new HashMap<Long, UcShopModel>();
			for(UcCustomerAccountStatementExModel statement : feignResp.getData().getStatements()){
				FinancialPaymentFlowResult statementResult = new FinancialPaymentFlowResult();
				statementResult.setAmount(statement.getAmount());
				statementResult.setBizType(statement.getBizType());
				statementResult.setCreateDate(statement.getGmtCreated());
				statementResult.setOrderNo(statement.getOrderNo());
				statementResult.setPayOrderNo(statement.getPayOrderNo());
				//查询客户名称
				if(customerMap.containsKey(statement.getCustomerId())){
					statementResult.setCustomerName(customerMap.get(statement.getCustomerId()).getName());
				}else if(statement.getCustomerId()!=null){
					CustomerQueryDtoReq customerParam = new CustomerQueryDtoReq();
					customerParam.setId(statement.getCustomerId());
					BaseResponse<CustomerDtoListResult> customerResp = customerFeignClient.queryCustomer(customerParam);
					if(customerResp.isSuccess()&&customerResp.getData()!=null&&
							CollectionUtils.isNotEmpty(customerResp.getData().getCustomers())){
						UcCustomerExModel customer = customerResp.getData().getCustomers().get(0);
						customerMap.put(statement.getCustomerId(), customer);
						statementResult.setCustomerName(customer.getName());
					}
				}
				//查询商户名称
				if(shopMap.containsKey(statement.getShopId())){
					statementResult.setShopName(shopMap.get(statement.getShopId()).getName());
				}else if(statement.getShopId()!=null){
					ShopQueryDtoReq queryDtoReq = new ShopQueryDtoReq();
			        queryDtoReq.setId(statement.getShopId());
			        BaseResponse<ShopDtoListResult> shopFeign = shopFeignClient.queryShop(queryDtoReq);
			        if(shopFeign.isSuccess()&&shopFeign.getData()!=null&&
							CollectionUtils.isNotEmpty(shopFeign.getData().getShops())){
			        	UcShopModel shop = shopFeign.getData().getShops().get(0);
			        	shopMap.put(statement.getShopId(), shop);
			        	statementResult.setShopName(shop.getName());
			        }
				}
				result.getDataList().add(statementResult);
				result.setPage(feignResp.getData().getPage());
				result.setTotalCount(feignResp.getData().getTotalCount());
				result.setTotalPage(feignResp.getData().getTotalPage());
			}
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return result;
	}
	
	public void exportPaymentFlow(FinancialPaymentFlowQueryReq queryReq, HttpServletResponse response){
		CustomerAccountStatementQueryDtoReq statementParam = new CustomerAccountStatementQueryDtoReq();
		statementParam.setBeginDate(queryReq.getStartDate());
		statementParam.setEndDate(queryReq.getEndDate());
		statementParam.setPage(null);
		statementParam.setPageSize(null);
		statementParam.setBizType(queryReq.getBizType());
		statementParam.setOrderNo(queryReq.getOrderNo());
		statementParam.setPayOrderNo(queryReq.getPayOrderNo());
		if(StringUtils.isNotBlank(queryReq.getShopName())){
			ShopQueryDtoReq queryDtoReq = new ShopQueryDtoReq();
	        queryDtoReq.setLikeName(queryReq.getShopName());
	        BaseResponse<ShopDtoListResult> shopFeign = shopFeignClient.queryShop(queryDtoReq);
	        if(shopFeign.isSuccess()&&shopFeign.getData()!=null&&
					CollectionUtils.isNotEmpty(shopFeign.getData().getShops())){
	        	List<Long> shopIds = new ArrayList<Long>();
				for(UcShopModel shop: shopFeign.getData().getShops()){
					shopIds.add(shop.getId());
				}
				statementParam.setShopIds(shopIds);
	        }else{
	        	return;
	        }
		}
		if(StringUtils.isNotBlank(queryReq.getCustomerName())){
			CustomerQueryDtoReq customerParam = new CustomerQueryDtoReq();
			customerParam.setCustomerName(queryReq.getCustomerName());
			BaseResponse<CustomerDtoListResult> feignResp = customerFeignClient.queryCustomer(customerParam);
			if(feignResp.isSuccess()&&feignResp.getData()!=null&&
					CollectionUtils.isNotEmpty(feignResp.getData().getCustomers())){
				List<Long> customerIds = new ArrayList<Long>();
				for(UcCustomerExModel customer: feignResp.getData().getCustomers()){
					customerIds.add(customer.getId());
				}
				statementParam.setCustomerIds(customerIds);
			}else{
				return;
			}
		}
		BaseResponse<CustomerAccountStatementListDtoResult> feignResp = accountFeignClient.queryCustomerAccountStatement(statementParam);
		if(feignResp.isSuccess()&&feignResp.getData()!=null&&
				CollectionUtils.isNotEmpty(feignResp.getData().getStatements())){
			List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
			Map<Long, UcCustomerExModel> customerMap = new HashMap<Long, UcCustomerExModel>();
			Map<Long, UcShopModel> shopMap = new HashMap<Long, UcShopModel>();
			Map<String, Object> map = null;
			for(UcCustomerAccountStatementExModel statement : feignResp.getData().getStatements()){
				map = new HashMap<String, Object>();
				map.put("amount",statement.getAmount() == null ? CommonConstant.BLANK_STRING : "\"" + statement.getAmount() + "\"");
				map.put("bizType",statement.getBizType()== null ? CommonConstant.BLANK_STRING : "\"" + PayTypeEnums.valueOf(statement.getBizType()).getDesc() + "\"");
				map.put("orderNo",statement.getOrderNo() == null ? CommonConstant.BLANK_STRING : "\"" + statement.getOrderNo() + "\"");
				map.put("payOrderNo",statement.getPayOrderNo() == null ? CommonConstant.BLANK_STRING : "\"" + statement.getPayOrderNo() + "\"");
				map.put("createDate",statement.getGmtCreated() == null ? 
						CommonConstant.BLANK_STRING : "\"" +  
						DateUtils.date2Str(statement.getGmtCreated(), DateUtils.date_sdf)+ "\"");
				//查询客户名称
				if(customerMap.containsKey(statement.getCustomerId())){
					map.put("customerName",customerMap.get(statement.getCustomerId()).getName() == null ? CommonConstant.BLANK_STRING : "\"" + customerMap.get(statement.getCustomerId()).getName() + "\"");
				}else if(statement.getCustomerId()!=null){
					CustomerQueryDtoReq customerParam = new CustomerQueryDtoReq();
					customerParam.setId(statement.getCustomerId());
					BaseResponse<CustomerDtoListResult> customerResp = customerFeignClient.queryCustomer(customerParam);
					if(customerResp.isSuccess()&&customerResp.getData()!=null&&
							CollectionUtils.isNotEmpty(customerResp.getData().getCustomers())){
						UcCustomerExModel customer = customerResp.getData().getCustomers().get(0);
						customerMap.put(statement.getCustomerId(), customer);
						map.put("customerName",customer.getName() == null ? CommonConstant.BLANK_STRING : "\"" + customer.getName() + "\"");
					}
				}
				//查询商户名称
				if(shopMap.containsKey(statement.getShopId())){
					map.put("shopName",shopMap.get(statement.getShopId()).getName() == null ? CommonConstant.BLANK_STRING : "\"" + shopMap.get(statement.getShopId()).getName() + "\"");
				}else if(statement.getShopId()!=null){
					ShopQueryDtoReq queryDtoReq = new ShopQueryDtoReq();
			        queryDtoReq.setId(statement.getShopId());
			        BaseResponse<ShopDtoListResult> shopFeign = shopFeignClient.queryShop(queryDtoReq);
			        if(shopFeign.isSuccess()&&shopFeign.getData()!=null&&
							CollectionUtils.isNotEmpty(shopFeign.getData().getShops())){
			        	UcShopModel shop = shopFeign.getData().getShops().get(0);
			        	shopMap.put(statement.getShopId(), shop);
			        	map.put("shopName",shop.getName() == null ? CommonConstant.BLANK_STRING : "\"" + shop.getName() + "\"");
			        }
				}
				dataList.add(map);
			}
			String mapKey = "createDate,shopName,customerName,orderNo,payOrderNo,amount,bizType";
			try {
				final OutputStream os = response.getOutputStream();
				ExportUtil.responseSetProperties(StatisticsConstant.PM_EXPORT_PAY_STATMENT, response);
				ExportUtil.doExport(dataList, StatisticsConstant.PM_COLUMNS_EXPORT_PAY_STATMENT,
	                    StatisticsConstant.CSV_COLUMN_SEPARATOR, StatisticsConstant.CSV_RN, mapKey, os);
			} catch (Exception e) {
				logger.error("[FinancialInformationService.exportPaymentFlow]:下载文件异常", e);
			}
		}
	}
}
