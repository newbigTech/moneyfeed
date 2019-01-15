package com.newhope.moneyfeed.goods.web.controller;

import com.newhope.goods.biz.service.ProductService;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.constant.CommonConstant;
import com.newhope.moneyfeed.goods.api.dto.request.ProductDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductEbsQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductUpdateDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;
import com.newhope.moneyfeed.goods.api.rest.ProductApI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : tom
 * @project: moneyfeedMapper-goods
 * @date : 2018/11/17 09:50
 */
@RestController
public class ProductController extends AbstractController implements ProductApI {

    @Autowired
    private ProductService productService;

    @Override
    public BaseResponse<DtoResult> syncProduct() {
        DtoResult result=productService.syncProduct(CommonConstant.ALL_ORG);
        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> productCompany(@RequestBody ProductUpdateDtoReq dtoReq) {
        DtoResult result=productService.productCompany(dtoReq);
        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> productSingle(@RequestBody ProductUpdateDtoReq dtoReq) {
        DtoResult result= productService.productSingle(dtoReq);
        return buildJson(result);
    }

    @Override
    public BaseResponse<ProductSkuListDtoResult> getProduct(@RequestBody ProductQueryDtoReq dtoReq) {
        ProductSkuListDtoResult result= productService.getProduct(dtoReq);
        return buildJson(result);
    }

    @Override
    public BaseResponse<ProductSkuListDtoResult> getEbsProduct(@RequestBody ProductEbsQueryDtoReq dtoReq) {
        ProductSkuListDtoResult result= productService.getEbsProduct(dtoReq);
        return buildJson(result);
    }

	@Override
	public BaseResponse<?> updateProduct(@RequestBody ProductDtoReq updateReq) {
		boolean flag=productService.updateProduct(updateReq);
		if(flag) {
			return buildJson(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc());
		}else {
			return buildJson(ResultCode.DATA_ERROR.getCode(), ResultCode.DATA_ERROR.getDesc());
		}
		
	}

}
