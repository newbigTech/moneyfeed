package com.newhope.moneyfeed.goods.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.goods.biz.service.SkuService;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.SkuInitReq;
import com.newhope.moneyfeed.goods.api.rest.SkuApi;

/**
 * @author : tom
 * @project: moneyfeedMapper-goods
 * @date : 2018/11/17 09:50
 */
@RestController
public class SkuController extends AbstractController implements SkuApi {

	@Autowired
	private SkuService skuSer;

	@Override
	@ResponseBody
	public BaseResponse<DtoResult> skuInit(@RequestBody SkuInitReq req) {
		DtoResult result = new DtoResult();
		try {
			skuSer.allSkuInit(req.getFromDate());
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMsg("初始化sku成功!");
		} catch (BizException e) {
			result.setCode(ResultCode.FAIL.getCode());
			result.setMsg("初始化sku出错!");
		}
		return buildJson(result);
	}

}
