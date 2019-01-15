package com.newhope.moneyfeed.integration.api.service.ebs.bill;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDetailPageDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDetailPageDtoResult;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDtoResult;
import com.newhope.moneyfeed.integration.api.vo.request.ebs.bill.CustomerBillVoReq;

public interface EBSCustomerBillService {

	/*
	 * 查询客户对账单
	 * */
	EbsCustomerBillDtoResult ebsBillQuery(EbsCustomerBillDtoReq billReq);
	
	/*
	 * 查询客户对账单明细
	 * */
	EbsCustomerBillDetailPageDtoResult ebsBillDetailQuery(EbsCustomerBillDetailPageDtoReq billDetailReq);
	
	//同步EBS对账单
	DtoResult syncCustomerBill(CustomerBillVoReq req);
}
