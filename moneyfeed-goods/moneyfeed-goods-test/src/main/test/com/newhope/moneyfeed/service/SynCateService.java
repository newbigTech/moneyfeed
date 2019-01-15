package com.newhope.moneyfeed.service;

import com.newhope.goods.biz.service.CategoryService;
import com.newhope.goods.biz.service.RegularProductService;
import com.newhope.goods.biz.service.impl.SynCategoryServiceImpl;
import com.newhope.goods.biz.service.impl.SalecategoryServiceImpl;
import com.newhope.moneyfeed.BaseUnitTest;
import com.newhope.moneyfeed.goods.api.dto.request.CategoryQueryDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.RegularProductDtoReq;
import com.newhope.moneyfeed.goods.api.dto.request.SaleCategoryQueryDtoReq;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class SynCateService extends BaseUnitTest {

    @Autowired
    RegularProductService regularProductService;

    @Test
    public  void test1(){
        System.out.println("aaaaaaaaaaaaaaa");
    }



    @Test
    public void cleanRegularProduct(){
        System.out.println("aaaaaaa");
        regularProductService.cleanRegularProduct();
    }


    @Test
    public void  queryRegularProduct(){
        RegularProductDtoReq dtoReq = new RegularProductDtoReq();
        dtoReq.setCustomerNo("657888");
        dtoReq.setShopId(1l);
        regularProductService.queryRegularProductList(dtoReq);
    }



}
