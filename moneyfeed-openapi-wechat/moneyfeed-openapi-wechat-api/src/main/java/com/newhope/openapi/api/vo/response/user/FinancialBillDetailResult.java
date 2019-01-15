package com.newhope.openapi.api.vo.response.user;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class FinancialBillDetailResult {
    @ApiModelProperty(value = "哪一天：例如13号")
    private Integer day;
    @ApiModelProperty(value = "周几")
    private Integer dayOfTheWeek;
    @ApiModelProperty(value = "账单类型：online:向上、offline:线下")
    private String type;
    @ApiModelProperty(value = "车牌")
    private String licensePlate;
    @ApiModelProperty(value = "饲料购买详细信息")
    private List<Item> items;
    @ApiModelProperty(value = "支付方式：合计")
    private BigDecimal totalPay;
    @ApiModelProperty(value = "单据号，订单号")
    private String documentNo;
    @ApiModelProperty(value = "0：应收合计,1：实收合计")
    private Integer totalPayType = 0;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(Integer dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(BigDecimal totalPay) {
        this.totalPay = totalPay;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public Integer getTotalPayType() {
        return totalPayType;
    }

    public void setTotalPayType(Integer totalPayType) {
        this.totalPayType = totalPayType;
    }

    public static class Item {
        @ApiModelProperty(value = "饲料名称")
        private String name;
        @ApiModelProperty(value = "购买数量（吨）")
        private Double quantity;
        @ApiModelProperty(value = "购买金额")
        private BigDecimal pay;
        @ApiModelProperty(value = "规格")
        private String specification;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getQuantity() {
            return quantity;
        }

        public void setQuantity(Double quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getPay() {
            return pay;
        }

        public void setPay(BigDecimal pay) {
            this.pay = pay;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }
    }
}
