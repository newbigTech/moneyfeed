package com.newhope.goods.biz.service.impl;

import java.util.List;

import com.newhope.moneyfeed.api.enums.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.goods.biz.service.BaseService;
import com.newhope.goods.biz.service.CategoryService;
import com.newhope.goods.biz.service.SynCategoryService;
import com.newhope.moneyfeed.goods.api.bean.CategoryModel;
import com.newhope.moneyfeed.goods.api.dto.request.CategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.ProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.CategoryDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.CategoryListDtoResult;
import com.newhope.moneyfeed.goods.dal.dao.BaseDao;
import com.newhope.moneyfeed.goods.dal.dao.CategoryDao;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/20 10:33
 */
@Service
public class CategoryServiceImpl extends BaseService<CategoryModel>  implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;


    public List<CategoryModel> queryAggregationCategory(ProductQueryDtoReq dtoReq) {
        return categoryDao.queryAggregationCategory(dtoReq);
    }


    @Override
    public CategoryListDtoResult queryCategory(CategoryQueryDtoReq dtoReq) {
        CategoryModel categoryModel = new CategoryModel();
        CategoryListDtoResult categoryListDtoResult = new CategoryListDtoResult();

        BeanUtils.copyProperties(dtoReq,categoryModel);
        List<CategoryModel> categoryModelList = query(categoryModel);

        for(CategoryModel model:categoryModelList){
            CategoryDtoResult  categoryDtoResult = new CategoryDtoResult();
            BeanUtils.copyProperties(model,categoryDtoResult);
            categoryListDtoResult.getCategoryDtoResultList().add(categoryDtoResult);
        }
        categoryListDtoResult.setCode(ResultCode.SUCCESS.getCode());
        categoryListDtoResult.setMsg(ResultCode.SUCCESS.getDesc());
        return categoryListDtoResult;
    }


    @Override
    protected BaseDao<CategoryModel> getDao() {
        return categoryDao;
    }
}
