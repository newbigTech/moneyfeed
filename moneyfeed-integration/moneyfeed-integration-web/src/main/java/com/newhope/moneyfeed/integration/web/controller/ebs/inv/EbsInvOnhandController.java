package com.newhope.moneyfeed.integration.web.controller.ebs.inv;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.integration.api.dto.request.ebs.inv.EbsItemOnhandDtoReq;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.inv.EbsItemOnhandDtoResult;
import com.newhope.moneyfeed.integration.api.rest.ebs.inv.EbsInvAPI;
import com.newhope.moneyfeed.integration.api.service.ebs.inv.EbsInvOnhandService;

@RestController
public class EbsInvOnhandController extends AbstractController implements EbsInvAPI {

	private final static Logger logger = LoggerFactory.getLogger(EbsInvOnhandController.class);
	
	@Autowired
	private EbsInvOnhandService invOnhandService;

	@Override
	public BaseResponse<EbsItemOnhandDtoResult> ebsInvItemOnhandQuery(@RequestBody List<EbsItemOnhandDtoReq> reqList) {
		EbsItemOnhandDtoResult result = new EbsItemOnhandDtoResult();

		logger.info("请求参数："+JSON.toJSONString(reqList));
	
		if (reqList==null||reqList.size()<=0) {
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg("请求数据为空");
			return buildJson(result);
		}

		result = invOnhandService.ebsItemOnhandQuery(reqList);

		return buildJson(result);
	}

}
