package com.newhope.moneyfeed.goods.api.bean;

public class SalecateProductModel extends BaseModel {
    /** 销售目录id */
    private String oneSalecateId;

    /** 销售目录名称 */
    private String oneSalecateName;

    /** 2级销售目录id */
    private String twoSalecateId;

    /** 2级销售目录名称 */
    private String twoSalecateName;

    /** 三级销售目录id */
    private String threeSalecateId;

    /** 三级销售目录名称 */
    private String threeSalecateName;

    /** 四级目录id */
    private String fourSalecateId;

    /** 四级目录名称 */
    private String fourSalecateName;

    /** 商品id */
    private String productCode;

    /** 销售目录组 */
    private String salecateId;

    private Integer salecateLevel;

    public String getOneSalecateId() {
        return oneSalecateId;
    }

    public void setOneSalecateId(String oneSalecateId) {
        this.oneSalecateId = oneSalecateId;
    }

    public String getOneSalecateName() {
        return oneSalecateName;
    }

    public void setOneSalecateName(String oneSalecateName) {
        this.oneSalecateName = oneSalecateName;
    }

    public String getTwoSalecateId() {
        return twoSalecateId;
    }

    public void setTwoSalecateId(String twoSalecateId) {
        this.twoSalecateId = twoSalecateId;
    }

    public String getTwoSalecateName() {
        return twoSalecateName;
    }

    public void setTwoSalecateName(String twoSalecateName) {
        this.twoSalecateName = twoSalecateName;
    }

    public String getThreeSalecateId() {
        return threeSalecateId;
    }

    public void setThreeSalecateId(String threeSalecateId) {
        this.threeSalecateId = threeSalecateId;
    }

    public String getThreeSalecateName() {
        return threeSalecateName;
    }

    public void setThreeSalecateName(String threeSalecateName) {
        this.threeSalecateName = threeSalecateName;
    }

    public String getFourSalecateId() {
        return fourSalecateId;
    }

    public void setFourSalecateId(String fourSalecateId) {
        this.fourSalecateId = fourSalecateId;
    }

    public String getFourSalecateName() {
        return fourSalecateName;
    }

    public void setFourSalecateName(String fourSalecateName) {
        this.fourSalecateName = fourSalecateName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSalecateId() {
        return salecateId;
    }

    public void setSalecateId(String salecateId) {
        this.salecateId = salecateId;
    }

    public Integer getSalecateLevel() {
        return salecateLevel;
    }

    public void setSalecateLevel(Integer salecateLevel) {
        this.salecateLevel = salecateLevel;
    }
}