package com.newhope.moneyfeed.user.web.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.user.api.bean.client.extend.UcCustomerAccountStatementExModel;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementPostListDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.CustomerAccountStatementQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerAccountListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerAccountStatementListDtoResult;
import com.newhope.moneyfeed.user.api.rest.client.CustomerAccountAPI;
import com.newhope.moneyfeed.user.web.controller.AbstractController;
import com.newhope.user.user.biz.service.client.UcCustomerAccountService;
import com.newhope.user.user.biz.service.client.UcCustomerAccountStatementService;

@RestController
public class ClientCustomerAccountController extends AbstractController implements CustomerAccountAPI {

	@Autowired
	UcCustomerAccountStatementService statementService;
	
	@Autowired
	UcCustomerAccountService customerAccountService;
	
	@Override
	public BaseResponse<CustomerAccountListDtoResult> queryCustomerAccount(@RequestBody CustomerAccountQueryDtoReq requestBody) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse<CustomerAccountStatementListDtoResult> queryCustomerAccountStatement(
			@RequestBody CustomerAccountStatementQueryDtoReq requestBody) {
		CustomerAccountStatementListDtoResult result = new CustomerAccountStatementListDtoResult();
		PageList<UcCustomerAccountStatementExModel> statementList = statementService.queryStatementList(requestBody);
		Paginator paginator = statementList.getPaginator();
		if(paginator!=null){
			result.setStatements(statementList);
			result.setPage((long) paginator.getPage());
            result.setTotalCount((long) paginator.getTotalCount());
            result.setTotalPage((long) paginator.getTotalPages());
		}else{
			result.setStatements(statementList);
			result.setPage(1L);
			result.setTotalCount(0L);
			result.setTotalPage(0L);
		}
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
	}

	@Override
	public BaseResponse<DtoResult> saveCustomerAccountStatement(
			@RequestBody CustomerAccountStatementPostListDtoReq requestBody) {
		DtoResult result = new DtoResult();
		statementService.saveStatementList(requestBody);
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return buildJson(result.getCode(), result.getMsg(), result);
	}
	
	
}
