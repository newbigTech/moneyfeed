package com.newhope.moneyfeed.user.api.dto.response.client;


import java.util.List;

import com.newhope.moneyfeed.api.dto.response.PageDtoResult;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;


public class ShopDtoListResult extends PageDtoResult {
	
    private static final long serialVersionUID = 4204268611222051507L;
    
    private List<UcShopModel> shops;

    public List<UcShopModel> getShops() {
        return shops;
    }

    public void setShops(List<UcShopModel> shops) {
        this.shops = shops;
    }

}