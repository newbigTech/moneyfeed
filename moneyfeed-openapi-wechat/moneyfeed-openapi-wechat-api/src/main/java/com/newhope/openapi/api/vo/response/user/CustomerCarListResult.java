package com.newhope.openapi.api.vo.response.user;


import com.newhope.moneyfeed.api.vo.response.PageResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerCarDtoResult;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerCarListResult extends PageResult {

    private List<CustomerCarResult> cars;


    public List<CustomerCarResult> getCars() {
        return cars;
    }

    public void setCars(List<CustomerCarResult> cars) {
        this.cars = cars;
    }

    public void translate(List<CustomerCarDtoResult> cars) {
        this.cars = cars.stream().map(car -> new CustomerCarResult().translate(car)).collect(Collectors.toList());
    }
}
