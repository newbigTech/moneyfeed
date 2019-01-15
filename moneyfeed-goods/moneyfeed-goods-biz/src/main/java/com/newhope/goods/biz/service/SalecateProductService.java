package com.newhope.goods.biz.service;

import com.newhope.moneyfeed.goods.api.bean.SalecateProductModel;

import java.util.List;

/**
 * 销售目录与商品关系表
 * @author : tom
 * @project: moneyfeed-goods
 * @date : 2018/11/22 10:21
 */
public interface SalecateProductService {


    /**
     * 同步销售目录和商品
     *
     * @param salecateProductModelList
     */
    void syncSalecateProduct(List<SalecateProductModel> salecateProductModelList);
}
