package com.newhope.moneyfeed.integration.api.rest.ebs.inv;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.inv.EbsItemOnhandDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.inv.EbsItemOnhandDtoResult;

import io.swagger.annotations.ApiOperation;

public interface EbsInvAPI {

	@ApiOperation(value = "物料现有量查询", notes = "物料现有量查询", protocols = "http")
	@RequestMapping(value = "/ebs/inv/itemonhand/query", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	BaseResponse<EbsItemOnhandDtoResult> ebsInvItemOnhandQuery(@RequestBody List<EbsItemOnhandDtoReq> reqList);
}
