package com.newhope.goods.biz.service.impl;

import com.newhope.goods.biz.service.BaseService;
import com.newhope.goods.biz.service.BrandService;
import com.newhope.goods.biz.service.ProductService;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.goods.api.bean.BrandModel;
import com.newhope.moneyfeed.goods.api.dto.request.BrandQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.BrandUpdateDtoReq;
import com.newhope.moneyfeed.goods.api.dto.response.BrandDtoResult;
import com.newhope.moneyfeed.goods.api.dto.response.BrandListDtoResult;
import com.newhope.moneyfeed.goods.api.exbean.BrandExModel;
import com.newhope.moneyfeed.goods.dal.dao.BaseDao;
import com.newhope.moneyfeed.goods.dal.dao.BrandDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/12/19 14:39
 */
@Service
public class BrandServiceImpl extends BaseService<BrandModel> implements BrandService {

    @Autowired
    private BrandDao brandDao;
    @Autowired
    private ProductService productService;

    @Override
    protected BaseDao<BrandModel> getDao() {
        return brandDao;
    }

    @Override
    public BrandListDtoResult queryBrandList(BrandQueryDtoReq dtoReq) {
        BrandListDtoResult result = new BrandListDtoResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());

        Long count = brandDao.queryBrandListCount(dtoReq);
        result.setPage(dtoReq.getPage());
        if (count <= 0) {
            result.setTotalPage(0L);
            result.setTotalCount(0L);
            return result;
        }
        result.setTotalCount(count);
        result.setTotalPage(pages(count, dtoReq.getPageSize()));
        Long start = (dtoReq.getPage() - 1) * dtoReq.getPageSize();
        List<BrandExModel> brandExModels = brandDao.queryBrandList(dtoReq, start, dtoReq.getPageSize());

        List<BrandDtoResult> brandDtoResults = new ArrayList<>();
        for (BrandExModel brandExModel : brandExModels) {
            BrandDtoResult brandDtoResult = new BrandDtoResult();
            BeanUtils.copyProperties(brandExModel, brandDtoResult);
            brandDtoResults.add(brandDtoResult);
        }
        result.setBrandDtoResults(brandDtoResults);
        return result;
    }

    @Override
    @Transactional
    public DtoResult addBrand(BrandUpdateDtoReq dtoReq) {
        DtoResult result = new DtoResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());

        BrandModel brandModel = new BrandModel();
        brandModel.setBrandName(dtoReq.getBrandName());
        brandModel.setEnabled(Boolean.TRUE);
        List<BrandModel> brandModels = brandDao.select(brandModel);
        if (CollectionUtils.isNotEmpty(brandModels)) {
            result.setCode(ResultCode.DATA_ERROR.getCode());
            result.setMsg("品牌名称已存在!");
            return result;
        }
        long count = brandDao.insert(Arrays.asList(brandModel));
        if (count <= 0) {
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(ResultCode.FAIL.getDesc());
        }
        return result;
    }

    @Override
    @Transactional
    public DtoResult updateBrand(BrandUpdateDtoReq dtoReq) {
        DtoResult result = new DtoResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());

        BrandModel oldModel = new BrandModel();
        BrandModel newModel = new BrandModel();
        oldModel.setId(dtoReq.getBrandId());
        oldModel.setEnabled(Boolean.TRUE);
        newModel.setBrandName(dtoReq.getBrandName());
        long count = brandDao.update(oldModel, newModel);
        if (count <= 0) {
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(ResultCode.FAIL.getDesc());
            return result;
        }
        productService.updateBrand(dtoReq);
        return result;
    }

    @Override
    @Transactional
    public DtoResult deleteBrand(Long id) {
        DtoResult result = new DtoResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());

        BrandModel oldModel = new BrandModel();
        BrandModel newModel = new BrandModel();
        oldModel.setId(id);
        newModel.setEnabled(Boolean.FALSE);
        long count = brandDao.update(oldModel, newModel);
        if (count <= 0) {
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(ResultCode.FAIL.getDesc());
            return result;
        }
        BrandUpdateDtoReq dtoReq = new BrandUpdateDtoReq();
        dtoReq.setBrandId(id);
        productService.deleteBrand(dtoReq);
        return result;
    }

    @Override
    public BrandListDtoResult queryProductBrandList(BrandQueryDtoReq dtoReq) {
        BrandListDtoResult result = new BrandListDtoResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());
        List<BrandExModel> brandExModels = brandDao.queryProductBrandList(dtoReq);

        List<BrandDtoResult> brandDtoResults = new ArrayList<>();
        for (BrandExModel brandExModel : brandExModels) {
            BrandDtoResult brandDtoResult = new BrandDtoResult();
            BeanUtils.copyProperties(brandExModel, brandDtoResult);
            brandDtoResults.add(brandDtoResult);
        }
        result.setBrandDtoResults(brandDtoResults);
        return result;
    }

    @Override
    public BrandListDtoResult queryBrandList() {
        BrandListDtoResult result=new BrandListDtoResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());

        BrandModel brandModel = new BrandModel();
        brandModel.setEnabled(Boolean.TRUE);
        List<BrandModel> brandModels = brandDao.select(brandModel);
        List<BrandDtoResult> brandDtoResults = new ArrayList<>();
        for (BrandModel brand : brandModels) {
            BrandDtoResult brandDtoResult = new BrandDtoResult();
            brandDtoResult.setBrandId(brand.getId());
            brandDtoResult.setBrandName(brand.getBrandName());
            brandDtoResults.add(brandDtoResult);
        }
        result.setBrandDtoResults(brandDtoResults);
        return result;
    }

}
