package com.newhope.openapi.api.rest.product;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.vo.request.product.ProductQueryReq;
import com.newhope.openapi.api.vo.request.product.ProductSkusQueryReq;
import com.newhope.openapi.api.vo.response.product.ProductSyncHeartbeatResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/20 15:21
 */
@Api(value = "商品sku中心", description = "商品sku中心 API", protocols = "http")
public interface ProductSkuOpenAPI {

	@ApiOperation(value = "查询商品sku列表", notes = "查询商品sku列表", protocols = "http")
	@RequestMapping(value = "/pc/product/sku/list", method = RequestMethod.GET, produces = { "application/json" })
	BaseResponse<?> getProductSku(@RequestBody ProductSkusQueryReq dtoReq);

	@ApiOperation(value = "导出商品sku列表", notes = "导出商品sku列表", protocols = "http")
	@RequestMapping(value = "/pc/product/sku/export", method = RequestMethod.GET)
	BaseResponse<Result> productSkuExport(ProductSkusQueryReq dtoReq, HttpServletResponse response);

	@ApiOperation(value = "商品sku初始化", notes = "商品sku初始化", protocols = "http")
	@RequestMapping(value = "/pc/product/allinit", method = RequestMethod.POST, produces = { "application/json" })
	BaseResponse<?> initProductSku();

	@ApiOperation(value = "查询商品信息", notes = "查询商品信息", protocols = "http")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productName", value = "商品名称", dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "saleCateId", value = "目录id", dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "saleCateLevel", value = "目录层级", dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "shopId", value = "店铺id", dataType = "Long", paramType = "query"), })
	@RequestMapping(value = "/pc/product/list", method = RequestMethod.GET, produces = { "application/json" })
	BaseResponse getProductSkuByCustomerId(ProductSkusQueryReq productQueryReq);

	@ApiOperation(value = "商品sku初始化状态监测", notes = "商品sku初始化状态监测", protocols = "http")
	@RequestMapping(value = "/pc/product/allinit/heartbeat", method = RequestMethod.GET)
	BaseResponse<ProductSyncHeartbeatResult> heartbeat();
	
    @ApiOperation(value = "根据productCode更新商品信息", notes = "根据productCode更新商品信息", protocols = "http")
    @RequestMapping(value = "/pc/product/update", method = RequestMethod.GET, produces = {"application/json"})   
    BaseResponse<?> updateProduct(ProductQueryReq updateReq);
}
