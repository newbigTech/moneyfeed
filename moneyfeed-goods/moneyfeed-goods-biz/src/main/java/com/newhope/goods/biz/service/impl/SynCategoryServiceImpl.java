package com.newhope.goods.biz.service.impl;

import com.newhope.goods.biz.service.SynCategoryService;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.goods.api.bean.CategoryModel;
import com.newhope.moneyfeed.goods.api.bean.ebs.EbsCategoryModel;
import com.newhope.moneyfeed.goods.api.enums.CategoryLevelEnums;
import com.newhope.moneyfeed.goods.dal.dao.CategoryDao;
import com.newhope.moneyfeed.goods.dal.dao.ebs.EbsCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SynCategoryServiceImpl implements SynCategoryService {

    private static final String INIT_ROOT_CATEGORY = "15";

    @Autowired
    private EbsCategoryDao ebsCategoryDao;

    @Autowired
    private CategoryDao categoryDao;

    public DtoResult initCategory(){
        DtoResult result = new DtoResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());
        CategoryModel delModel = new CategoryModel();
        categoryDao.delete(delModel);
        List<EbsCategoryModel> ebsCategoryModelList = ebsCategoryDao.selectByOneCat(INIT_ROOT_CATEGORY);
        List<CategoryModel> categoryModelList = new ArrayList<>();

        for (EbsCategoryModel ebsCategoryModel : ebsCategoryModelList) {
            saveTwoCat(ebsCategoryModel.getOneCat());
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setCateId(ebsCategoryModel.getOneCat());
            categoryModel.setCateLevel(CategoryLevelEnums.ONE_LEVEL.getValue());
            categoryModel.setCateName(ebsCategoryModel.getOneCatDesc());
            categoryModelList.add(categoryModel);
        }
        categoryDao.insert(categoryModelList);

        return result;

    }

    private void saveTwoCat(String oneCat){
        List<EbsCategoryModel> ebsCategoryModelList = ebsCategoryDao.selectByTwoCat(oneCat);
        List<CategoryModel> categoryModelList = new ArrayList<>();
        for (EbsCategoryModel ebsCategoryModel : ebsCategoryModelList) {
            saveThreeCat(oneCat,ebsCategoryModel.getTwoCat());
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setCateId(oneCat+ebsCategoryModel.getTwoCat());
            categoryModel.setCateLevel(CategoryLevelEnums.TWO_LEVEL.getValue());
            categoryModel.setParentCateId(oneCat);
            categoryModel.setCateName(ebsCategoryModel.getTwoCatDesc());
            categoryModelList.add(categoryModel);

        }
        if(categoryModelList.size()!=0){
            categoryDao.insert(categoryModelList);
        }
    }

    private void saveThreeCat(String oneCat,String twoCat){
        List<EbsCategoryModel> ebsCategoryModelList = ebsCategoryDao.selectByThreeCat(oneCat,twoCat);
        List<CategoryModel> categoryModelList = new ArrayList<>();
        for (EbsCategoryModel ebsCategoryModel : ebsCategoryModelList) {
            saveFourCat(oneCat,twoCat,ebsCategoryModel.getThreeCat());
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setCateId(oneCat+twoCat+ebsCategoryModel.getThreeCat());
            categoryModel.setCateLevel(CategoryLevelEnums.THREE_LEVEL.getValue());
            categoryModel.setParentCateId(oneCat+twoCat);
            categoryModel.setCateName(ebsCategoryModel.getThreeCatDesc());
            categoryModelList.add(categoryModel);
        }
        if(categoryModelList.size()!=0){
            categoryDao.insert(categoryModelList);
        }
    }

    private void saveFourCat(String oneCat,String twoCat,String threeCat){
        List<EbsCategoryModel> ebsCategoryModelList = ebsCategoryDao.selectByFourCat(oneCat,twoCat,threeCat);
        List<CategoryModel> categoryModelList = new ArrayList<>();
        for (EbsCategoryModel ebsCategoryModel : ebsCategoryModelList) {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setCateId(oneCat+twoCat+threeCat+ebsCategoryModel.getFourCat());
            categoryModel.setCateLevel(CategoryLevelEnums.FOUR_LEVEL.getValue());
            categoryModel.setParentCateId(oneCat+twoCat+threeCat);
            categoryModel.setCateName(ebsCategoryModel.getFourCatDesc());
            categoryModelList.add(categoryModel);
        }
        if (!categoryModelList.isEmpty()) {
            categoryDao.insert(categoryModelList);
        }
    }
}
