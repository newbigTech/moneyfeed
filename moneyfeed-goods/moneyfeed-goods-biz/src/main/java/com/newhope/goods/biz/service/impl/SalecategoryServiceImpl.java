package com.newhope.goods.biz.service.impl;

import com.newhope.goods.biz.service.BaseService;
import com.newhope.goods.biz.service.SalecategoryService;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.goods.api.bean.SalecategoryModel;
import com.newhope.moneyfeed.goods.api.dto.request.SaleCategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.SalecateProductQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.SaleCategoryDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.SaleCategoryListDtoResult;
import com.newhope.moneyfeed.goods.dal.dao.BaseDao;
import com.newhope.moneyfeed.goods.dal.dao.SalecategoryDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/23 10:05
 */
@Service
public class SalecategoryServiceImpl extends BaseService<SalecategoryModel> implements SalecategoryService {
    @Autowired
    private SalecategoryDao salecategoryDao;


    @Override
    protected BaseDao<SalecategoryModel> getDao() {
        return salecategoryDao;
    }

    @Autowired
    RSessionCache rSessionCache;
    @Override
    public SaleCategoryListDtoResult querySaleCategory(SaleCategoryQueryDtoReq dtoReq) {
        SalecategoryModel saleCategoryModel = new SalecategoryModel();
        SaleCategoryListDtoResult saleCategoryListDtoResult = new SaleCategoryListDtoResult();

        BeanUtils.copyProperties(dtoReq, saleCategoryModel);
        List<SalecategoryModel> saleCategoryModelList = query(saleCategoryModel);
        for (SalecategoryModel model : saleCategoryModelList) {
            SaleCategoryDtoResult result = new SaleCategoryDtoResult();
            BeanUtils.copyProperties(model, result);
            saleCategoryListDtoResult.getSaleCategoryDtoResultList().add(result);
        }
        saleCategoryListDtoResult.setCode(ResultCode.SUCCESS.getCode());
        saleCategoryListDtoResult.setMsg(ResultCode.SUCCESS.getDesc());
        return saleCategoryListDtoResult;
    }

    /**
     * 同步前端对应的商品
     *
     * @param dtoReq
     * @return
     */
    @Override
    public List<SalecategoryModel> queryAggregationSalecategory(SalecateProductQueryDtoReq dtoReq) {
        List<SalecategoryModel> categoryModelList = salecategoryDao.queryAggregationSalecategory(dtoReq);
        return categoryModelList;
    }

    @Override
    public SaleCategoryListDtoResult queryCustomerSaleCategory(SaleCategoryQueryDtoReq dtoReq)  {
        SaleCategoryListDtoResult saleCategoryListDtoResult = new SaleCategoryListDtoResult();
        List<SalecategoryModel> saleCategoryModelList = salecategoryDao.queryCustomerSaleCategory(dtoReq);
        for (SalecategoryModel model : saleCategoryModelList) {
            SaleCategoryDtoResult result = new SaleCategoryDtoResult();
            BeanUtils.copyProperties(model, result);
            saleCategoryListDtoResult.getSaleCategoryDtoResultList().add(result);
        }
        saleCategoryListDtoResult.setCode(ResultCode.SUCCESS.getCode());
        saleCategoryListDtoResult.setMsg(ResultCode.SUCCESS.getDesc());
        return saleCategoryListDtoResult;
    }


}
