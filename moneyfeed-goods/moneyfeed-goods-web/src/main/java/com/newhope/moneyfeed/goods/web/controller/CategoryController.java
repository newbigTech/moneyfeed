package com.newhope.moneyfeed.goods.web.controller;


import com.newhope.goods.biz.service.CategoryService;
import com.newhope.goods.biz.service.SynCategoryService;
import com.newhope.moneyfeed.api.abscontroller.AbstractController;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.goods.api.dto.request.CategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.CategoryListDtoResult;
import com.newhope.moneyfeed.goods.api.rest.CategoryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController extends AbstractController implements CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SynCategoryService synCategoryService;


    @Override
    public BaseResponse<CategoryListDtoResult> queryCategory(@RequestBody CategoryQueryDtoReq dtoReq) {
        CategoryListDtoResult result= categoryService.queryCategory(dtoReq);
        return buildJson(result);
    }

    @Override
    public BaseResponse<DtoResult> initCategory() {
        DtoResult result = synCategoryService.initCategory();
        return buildJson(result);
    }

}
