package com.newhope.moneyfeed.user.api.dto.response.client;


import com.newhope.moneyfeed.api.dto.response.PageDtoResult;

import java.util.List;

public class CustomerCarListDtoResult extends PageDtoResult {

    private List<CustomerCarDtoResult> cars;


    public List<CustomerCarDtoResult> getCars() {
        return cars;
    }

    public void setCars(List<CustomerCarDtoResult> cars) {
        this.cars = cars;
    }

}
