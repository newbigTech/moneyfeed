package com.newhope.moneyfeed.integration.api.dto.request.ebs.order;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商城请求EBS创建订单，订单参数
 *
 * Created by yuyanlin on 2018/11/20
 */
public class MoneyfeedToEbsOrderCreateDtoReq implements Serializable {

    private static final long serialVersionUID = 2488935192166964028L;

    @NotBlank(message = "orgId不能为空")
    @ApiModelProperty(name = "orgId", value = "公司ID（EBS公司ou Id）")
    private String orgId;

    @NotBlank(message = "companyShortCode不能为空")
    @ApiModelProperty(name = "companyShortCode", value = "公司编码（EBS编码）")
    private String companyShortCode;

    @NotBlank(message = "customerNum不能为空")
    @ApiModelProperty(name = "customerNum", value = "客户编码")
    private String customerNum;

    @NotBlank(message = "moneyFeedOrderId不能为空")
    @ApiModelProperty(name = "moneyFeedOrderId", value = "商城订单ID")
    private String moneyFeedOrderId;

    @NotBlank(message = "moneyFeedOrderNo不能为空")
    @ApiModelProperty(name = "moneyFeedOrderNo", value = "商城订单code")
    private String moneyFeedOrderNo;

    @ApiModelProperty(name = "channel", value = "线上渠道ID（商城、标记是否来自于商城、默认MONEYFEED）")
    private String channel;

    @NotBlank(message = "shopId不能为空")
    @ApiModelProperty(name = "shopId", value = "商店ID")
    private String shopId;

    @NotBlank(message = "planTransportTime不能为空")
    @ApiModelProperty(name = "planTransportTime", value = "计划拉料日期（YYYY-MM-DD）")
    private String planTransportTime;

    private Date planTransportTimeDate;

    @ApiModelProperty(name = "carNo", value = "车牌号")
    private String carNo;

    @NotBlank(message = "orderType不能为空")
    @ApiModelProperty(name = "orderType", value = "订单类型")
    private String orderType;

    @Valid
    @NotEmpty(message = "productList不能为空")
    @ApiModelProperty(name = "productList", value = "订单产品列表")
    private List<MoneyFeedToEbsOrderCreateProductDtoReq> productList;

    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getMoneyFeedOrderId() {
        return moneyFeedOrderId;
    }

    public void setMoneyFeedOrderId(String moneyFeedOrderId) {
        this.moneyFeedOrderId = moneyFeedOrderId;
    }

    public String getMoneyFeedOrderNo() {
        return moneyFeedOrderNo;
    }

    public void setMoneyFeedOrderNo(String moneyFeedOrderNo) {
        this.moneyFeedOrderNo = moneyFeedOrderNo;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPlanTransportTime() {
        return planTransportTime;
    }

    public void setPlanTransportTime(String planTransportTime) {
        this.planTransportTime = planTransportTime;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public List<MoneyFeedToEbsOrderCreateProductDtoReq> getProductList() {
        return productList;
    }

    public void setProductList(List<MoneyFeedToEbsOrderCreateProductDtoReq> productList) {
        this.productList = productList;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Date getPlanTransportTimeDate() {
        return planTransportTimeDate;
    }

    public void setPlanTransportTimeDate(Date planTransportTimeDate) {
        this.planTransportTimeDate = planTransportTimeDate;
    }
}
