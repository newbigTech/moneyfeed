package com.newhope.moneyfeed.user.api.dto.response.client;


import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;

import java.io.Serializable;

public class ShopDtoResult implements Serializable {

    private static final long serialVersionUID = 6871372709105873508L;
    private UcShopModel shop;

    public ShopDtoResult(UcShopModel shop) {
        this.shop = shop;
    }

    public ShopDtoResult() {
    }

    public UcShopModel getShop() {
        return shop;
    }

    public void setShop(UcShopModel shop) {
        this.shop = shop;
    }

}