package com.newhope.moneyfeed.user.web.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.dto.request.client.EbsCustomerListPostDtoReq;
import com.newhope.moneyfeed.user.api.rest.client.EbsCustomerAPI;
import com.newhope.moneyfeed.user.web.controller.AbstractController;
import com.newhope.user.user.biz.service.client.UcEbsCustomerService;

@RestController
public class EbsCustomerController extends AbstractController implements EbsCustomerAPI {

	@Autowired
	UcEbsCustomerService ucEbsCustomerService;
	
	@Override
	public BaseResponse<DtoResult> modifyEbsCustomerList(@RequestBody EbsCustomerListPostDtoReq dtoReq) {
		DtoResult result = ucEbsCustomerService.modifyEbsCustomerList(dtoReq);
		return buildJson(result.getCode(),result.getMsg(),result);
	}
	
}
