package com.newhope.moneyfeed.integration.api.rest.ebs.bill;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDetailPageDtoReq;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.bill.EbsCustomerBillDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDetailPageDtoResult;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.bill.EbsCustomerBillDtoResult;

import io.swagger.annotations.ApiOperation;

public interface EbsCustomerBillAPI {

	@ApiOperation(value = "客户账单汇总查询", notes = "客户账单汇总查询", protocols = "http")
	@RequestMapping(value = "/ebs/customerbill/query", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	BaseResponse<EbsCustomerBillDtoResult> ebsBillQuery(@RequestBody EbsCustomerBillDtoReq billReq);
	
	@ApiOperation(value = "客户账单明细查询", notes = "客户账单明细查询", protocols = "http")
	@RequestMapping(value = "/ebs/customerbilldetail/query", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	BaseResponse<EbsCustomerBillDetailPageDtoResult> ebsBillDetailQuery(@RequestBody EbsCustomerBillDetailPageDtoReq billDetailReq);
	
	@ApiOperation(value = "同步客户账单汇总、账单明细", notes = "同步客户账单汇总、账单明细", protocols = "http")
	@RequestMapping(value = "/ebs/syncbill", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	BaseResponse<DtoResult> syncCustomerBill();
	
}
