package com.newhope.moneyfeed.integration.api.dto.response.ebs.order;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商城请求EBS创建订单，"订单创建"的结果
 *
 * Created by yuyanlin on 2018/11/20
 */
public class MoneyfeedOrderCreateDtoResult extends DtoResult {

    private static final long serialVersionUID = 5468221983314978859L;

    @ApiModelProperty(name = "companyShortCode", value = "公司编码（EBS编码）")
    private String companyShortCode;

    @ApiModelProperty(name = "moneyfeedOrderId", value = "商城订单ID")
    private String moneyfeedOrderId;

    @ApiModelProperty(name = "ebsOrderNo", value = "EBS订单编号")
    private String ebsOrderNo;

    @ApiModelProperty(name = "totalOrginalAmount", value = "订单总额（厂价）")
    private BigDecimal totalOrginalAmount;

    @ApiModelProperty(name = "totalPayAmount", value = "实付金额（折扣价）")
    private BigDecimal totalPayAmount;

    @ApiModelProperty(name = "totalDiscountAmount", value = "折扣金额")
    private BigDecimal totalDiscountAmount;

    @ApiModelProperty(name = "productList", value = "产品结果列表")
    private List<MoneyfeedOrderCreateProductDtoResult> productList;

    @ApiModelProperty(name = "presentFeedList", value = "赠品结果列表")
    private List<MoneyfeedOrderCreatePresentDtoResult> presentFeedList;

    @ApiModelProperty(name = "result", value = "创建结果（成功、失败）")
    private boolean result;

    @ApiModelProperty(name = "msg", value = "创建结果失败的原因")
    private String msg;

    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }

    public String getMoneyfeedOrderId() {
        return moneyfeedOrderId;
    }

    public void setMoneyfeedOrderId(String moneyfeedOrderId) {
        this.moneyfeedOrderId = moneyfeedOrderId;
    }

    public String getEbsOrderNo() {
        return ebsOrderNo;
    }

    public void setEbsOrderNo(String ebsOrderNo) {
        this.ebsOrderNo = ebsOrderNo;
    }

    public BigDecimal getTotalOrginalAmount() {
        return totalOrginalAmount;
    }

    public void setTotalOrginalAmount(BigDecimal totalOrginalAmount) {
        this.totalOrginalAmount = totalOrginalAmount;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public BigDecimal getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(BigDecimal totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<MoneyfeedOrderCreateProductDtoResult> getProductList() {
        return productList;
    }

    public void setProductList(List<MoneyfeedOrderCreateProductDtoResult> productList) {
        this.productList = productList;
    }

    public List<MoneyfeedOrderCreatePresentDtoResult> getPresentFeedList() {
        return presentFeedList;
    }

    public void setPresentFeedList(List<MoneyfeedOrderCreatePresentDtoResult> presentFeedList) {
        this.presentFeedList = presentFeedList;
    }
}
