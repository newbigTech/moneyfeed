package com.newhope.openapi.web.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.openapi.api.rest.product.CategoryOpenApi;
import com.newhope.openapi.api.vo.request.category.CategoryQueryReq;
import com.newhope.openapi.api.vo.response.category.CategoryResult;
import com.newhope.openapi.biz.service.product.CategoryService;

@RestController
public class CategoryController extends AbstractController implements CategoryOpenApi {

	@Autowired
    CategoryService categorySer;

	@Override
	public BaseResponse<Result> queryCategory(CategoryQueryReq dtoReq) {
		CategoryResult result = categorySer.queryCategory(dtoReq);
		return buildJson(result);
	}

	@Override
	public BaseResponse<Result> initCategory() {
		return buildJson(categorySer.initCategory());
	}

}
