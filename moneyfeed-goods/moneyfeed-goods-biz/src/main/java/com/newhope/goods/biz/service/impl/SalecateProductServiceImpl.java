package com.newhope.goods.biz.service.impl;

import com.newhope.goods.biz.service.BaseService;
import com.newhope.goods.biz.service.SalecateProductService;
import com.newhope.moneyfeed.goods.api.bean.SalecateProductModel;
import com.newhope.moneyfeed.goods.dal.dao.BaseDao;
import com.newhope.moneyfeed.goods.dal.dao.SalecateProductDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/22 10:22
 */
@Service
public class SalecateProductServiceImpl extends BaseService<SalecateProductModel> implements SalecateProductService {

    @Autowired
    private SalecateProductDao salecateProductDao;

    @Override
    protected BaseDao<SalecateProductModel> getDao() {
        return salecateProductDao;
    }


    /**
     * 同步销售目录和商品
     *
     * @param salecateProductModelList
     */
    @Override
    public void syncSalecateProduct(List<SalecateProductModel> salecateProductModelList) {
        List<SalecateProductModel> addSalecateProduct = new ArrayList<>();
        List<SalecateProductModel> updateSalecateProduct = new ArrayList<>();
        for (SalecateProductModel salecateProductModel : salecateProductModelList) {
            SalecateProductModel salecate = new SalecateProductModel();
            salecate.setProductCode(salecateProductModel.getProductCode());
            List<SalecateProductModel> salecateProductModels = query(salecate);
            if (CollectionUtils.isEmpty(salecateProductModels)) {
                addSalecateProduct.add(salecateProductModel);
                continue;
            }

            salecateProductModel.setId(salecateProductModels.get(0).getId());
            salecateProductModel.setGmtModified(new Date());
            updateSalecateProduct.add(salecateProductModel);
        }

        if (CollectionUtils.isNotEmpty(addSalecateProduct)) {
            salecateProductDao.insert(addSalecateProduct);
        }
        if (CollectionUtils.isNotEmpty(updateSalecateProduct)) {
            salecateProductDao.batchUpdate(updateSalecateProduct);
        }
    }

}
