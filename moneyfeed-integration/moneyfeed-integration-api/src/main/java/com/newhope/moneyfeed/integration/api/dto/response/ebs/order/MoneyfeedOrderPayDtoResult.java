package com.newhope.moneyfeed.integration.api.dto.response.ebs.order;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 商城请求EBS支付订单，"订单支付"的结果
 *
 * Created by yuyanlin on 2018/11/20
 */
public class MoneyfeedOrderPayDtoResult extends DtoResult {

    private static final long serialVersionUID = 8144298528848945958L;

    @ApiModelProperty(name = "companyShortCode", value = "公司编码（EBS编码）")
    private String companyShortCode;

    @ApiModelProperty(name = "ebsOrderNo", value = "EBS订单编码")
    private String ebsOrderNo;

    @ApiModelProperty(name = "result", value = "支付处理是否成功")
    private boolean result;

    @ApiModelProperty(name = "msg", value = "失败原因")
    private String msg;
}
