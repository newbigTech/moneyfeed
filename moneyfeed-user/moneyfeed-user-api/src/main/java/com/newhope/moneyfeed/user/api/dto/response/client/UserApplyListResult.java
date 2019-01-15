package com.newhope.moneyfeed.user.api.dto.response.client;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserShopApplyModel;

import java.util.List;

public class UserApplyListResult extends DtoResult {
    private List<UcClientUserShopApplyModel> list;

    public List<UcClientUserShopApplyModel> getList() {
        return list;
    }

    public void setList(List<UcClientUserShopApplyModel> list) {
        this.list = list;
    }
}
