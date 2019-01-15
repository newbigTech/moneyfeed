package com.newhope.openapi.web.controller.product;

import javax.servlet.http.HttpServletResponse;

import com.newhope.openapi.api.vo.response.product.ProductSyncHeartbeatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.rest.product.ProductSkuOpenAPI;
import com.newhope.openapi.api.vo.request.product.ProductQueryReq;
import com.newhope.openapi.api.vo.request.product.ProductSkusQueryReq;
import com.newhope.openapi.api.vo.response.product.ProductSkuResult;
import com.newhope.openapi.biz.service.product.ProductSkuService;

@RestController
public class ProductSkuController extends AbstractController implements ProductSkuOpenAPI {

	@Autowired
	ProductSkuService productSkuSer;

	@Override
	public BaseResponse<ProductSkuResult> getProductSku(ProductSkusQueryReq dtoReq) {
		ProductSkuResult result = productSkuSer.getProductSku(dtoReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> productSkuExport(ProductSkusQueryReq dtoReq, HttpServletResponse response) {
		Result result = productSkuSer.productSkuExport(dtoReq, response);
		return buildJson(result);
	}

	@Override
	public BaseResponse<?> initProductSku() {
		return productSkuSer.initProductSku();
	}

	@Override
	public BaseResponse<ProductSkuResult> getProductSkuByCustomerId(ProductSkusQueryReq productQueryReq) {
		ProductSkuResult result = productSkuSer.getProduct(productQueryReq, false);
		return buildJson(result);
	}

	@Override
	public BaseResponse<ProductSyncHeartbeatResult> heartbeat() {
		ProductSyncHeartbeatResult result = productSkuSer.heartbeat();
		return buildJson(result);
	}

	@Override
	public BaseResponse<?> updateProduct(ProductQueryReq updateReq) {
		return productSkuSer.updateProductByCode(updateReq);
	}
}
