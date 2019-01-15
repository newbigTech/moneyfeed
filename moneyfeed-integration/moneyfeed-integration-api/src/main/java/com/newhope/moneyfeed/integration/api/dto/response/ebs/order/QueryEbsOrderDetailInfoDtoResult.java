package com.newhope.moneyfeed.integration.api.dto.response.ebs.order;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCategoryModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Dave Chen on 2018/11/22.
 */
@XmlRootElement(name = "DATA")
public class QueryEbsOrderDetailInfoDtoResult extends DtoResult {

    @ApiModelProperty(name = "orderInfos", value = "订单列表")
    private List<QueryEbsOrderDetailInfoItemDtoResult>  orderInfos;

    @XmlElement(name = "HEADER",type = QueryEbsOrderDetailInfoItemDtoResult.class)
    public List<QueryEbsOrderDetailInfoItemDtoResult> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<QueryEbsOrderDetailInfoItemDtoResult> orderInfos) {
        this.orderInfos = orderInfos;
    }
}
