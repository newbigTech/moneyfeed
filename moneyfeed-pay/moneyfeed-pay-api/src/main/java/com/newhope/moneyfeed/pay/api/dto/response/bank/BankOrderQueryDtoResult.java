package com.newhope.moneyfeed.pay.api.dto.response.bank;

import java.io.Serializable;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.pay.api.bank.QueryResponseEntity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查询银行订单
 * @author heping  
 * @date 2019年1月11日
 */
public class BankOrderQueryDtoResult extends DtoResult implements Serializable {
	
	private static final long serialVersionUID = -8162775185604318637L;
	@ApiModelProperty(name = "queryResponseEntity", notes = "银行返回对象")
	private QueryResponseEntity queryResponseEntity;
	public QueryResponseEntity getQueryResponseEntity() {
		return queryResponseEntity;
	}
	public void setQueryResponseEntity(QueryResponseEntity queryResponseEntity) {
		this.queryResponseEntity = queryResponseEntity;
	}
	
}
