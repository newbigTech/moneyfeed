package com.newhope.goods.biz.service.impl;

import com.newhope.goods.biz.service.RegularProductService;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.goods.api.bean.RegularProductModel;
import com.newhope.moneyfeed.goods.api.dto.request.RegularProductDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.ProductSkuListDtoResult;

import com.newhope.moneyfeed.goods.api.exbean.ProductSkuExModel;
import com.newhope.moneyfeed.goods.dal.dao.BaseDao;
import com.newhope.moneyfeed.goods.dal.dao.RegularProductDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class RegularProductServerImpl implements RegularProductService {

    @Autowired
    private RegularProductDao regularProductDao;

    protected BaseDao<RegularProductModel> getDao() {
        return regularProductDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DtoResult cleanRegularProduct() {
        DtoResult dtoResult = new DtoResult();
        List<RegularProductModel> regularProductModelList = regularProductDao.selectCustomerProductByOrder();
        regularProductDao.delete(null);
        regularProductDao.insert(regularProductModelList);
        dtoResult.setCode(ResultCode.SUCCESS.getCode());
        dtoResult.setMsg(ResultCode.SUCCESS.getDesc());
        return dtoResult;
    }


    @Override
    public ProductSkuListDtoResult queryRegularProductList(RegularProductDtoReq dtoReq) {

        ProductSkuListDtoResult productSkuListDtoResult = new ProductSkuListDtoResult();
        productSkuListDtoResult.setCode(ResultCode.SUCCESS.getCode());
        productSkuListDtoResult.setMsg(ResultCode.SUCCESS.getDesc());
        List<ProductSkuExModel> productSkuExModels = regularProductDao.queryRegularProductList(dtoReq);
        List<ProductSkuDtoResult> productSkuDtoResults = new ArrayList<>();
        for (ProductSkuExModel productSkuExModel : productSkuExModels) {
            ProductSkuDtoResult productSkuDtoResult = new ProductSkuDtoResult();
            BeanUtils.copyProperties(productSkuExModel, productSkuDtoResult);
            productSkuDtoResults.add(productSkuDtoResult);
        }
        productSkuListDtoResult.setProductSkuList(productSkuDtoResults);
        return productSkuListDtoResult;
    }
}
