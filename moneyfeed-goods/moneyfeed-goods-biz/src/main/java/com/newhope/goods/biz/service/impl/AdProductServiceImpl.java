package com.newhope.goods.biz.service.impl;

import com.newhope.goods.biz.service.AdProductService;
import com.newhope.goods.biz.service.BaseService;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.goods.api.bean.AdProductModel;
import com.newhope.moneyfeed.goods.api.dto.request.AdProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.AdCategoryDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.AdCategoryListDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.AdProductDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.AdProductListDtoResult;
import com.newhope.moneyfeed.goods.api.exbean.AdCategoryExModel;
import com.newhope.moneyfeed.goods.dal.dao.AdProductDao;
import com.newhope.moneyfeed.goods.dal.dao.BaseDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdProductServiceImpl extends BaseService<AdProductModel> implements AdProductService {

    @Autowired
    private AdProductDao adProductDao;

    @Override
    public AdProductListDtoResult queryAdProduct(AdProductQueryDtoReq dtoReq) {
        AdProductListDtoResult adProductListDtoResult = new AdProductListDtoResult();
        AdProductModel adProductModel = new AdProductModel();
        BeanUtils.copyProperties(dtoReq,adProductModel);

        List<AdProductModel> adProductModelList = query(adProductModel);

        for(AdProductModel model:adProductModelList){
            AdProductDtoResult adProductDtoResult = new AdProductDtoResult();
            BeanUtils.copyProperties(model,adProductDtoResult);
            adProductListDtoResult.getAdProductDtoResultList().add(adProductDtoResult);
        }
        adProductListDtoResult.setCode(ResultCode.SUCCESS.getCode());
        adProductListDtoResult.setMsg(ResultCode.SUCCESS.getDesc());
        return adProductListDtoResult;
    }

    @Override
    public AdCategoryListDtoResult queryAdCategory(AdProductQueryDtoReq dtoReq) {
        AdCategoryListDtoResult adCategoryListDtoResult = new AdCategoryListDtoResult();
        List<AdCategoryExModel> adCategoryDtoResultList = adProductDao.queryAdCategory(dtoReq);
        for(AdCategoryExModel model:adCategoryDtoResultList){
            AdCategoryDtoResult adCategoryDtoResult = new AdCategoryDtoResult();
            BeanUtils.copyProperties(model,adCategoryDtoResult);
            adCategoryListDtoResult.getAdCategoryDtoResultList().add(adCategoryDtoResult);

        }
        adCategoryListDtoResult.setCode(ResultCode.SUCCESS.getCode());
        adCategoryListDtoResult.setMsg(ResultCode.SUCCESS.getDesc());
        return adCategoryListDtoResult;
    }

    @Override
    protected BaseDao<AdProductModel> getDao() {
        return adProductDao;
    }
}
