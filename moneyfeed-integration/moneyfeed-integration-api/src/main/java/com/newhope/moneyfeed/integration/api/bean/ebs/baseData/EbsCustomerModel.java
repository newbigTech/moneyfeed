package com.newhope.moneyfeed.integration.api.bean.ebs.baseData;

import com.newhope.moneyfeed.api.bean.BaseModel;

import javax.xml.bind.annotation.XmlElement;


public class EbsCustomerModel extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2186557267292770003L;

	/** 公司名称 */
    private String companyName;

    /** 公司ID */
    private String orgId;

    /** 公司短码 */
    private String companyShortCode;

    /** 客户编码 */
    private String customerNum;

    /** 客户名称 */
    private String customerName;

    /** 客户别名 */
    private String customerKnowAs;

    /** 纳税登记编号或个人身份证 */
    private String taxReference;

    private String province;

    private String city;

    private String county;

    /** 收货地址 */
    private String address;

    /** 联系人姓名 */
    private String contactPerson;


    /** 联系人电话 */
    private String phoneNumber;

    /** 客户-性质 */
    private String category1;

    /** 客户分类-渠道 */
    private String category2;

    /** 客户分类-交易关系 */
    private String category3;

    /** 客户养殖规模 */
    private String guimo;

    private String personId;

    /** A代表有效， I失效 */
    private String status;
    /**
     * 公司名称
     * @return
     */
    @XmlElement(name = "NAME")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    /**
     * 公司ID
     * @param
     */
    @XmlElement(name = "ORG_ID")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }



    /**
     * 公司短码
     * @param
     */
    @XmlElement(name = "SHORT_CODE")
    public String getCompanyShortCode() {
        return companyShortCode;
    }

    public void setCompanyShortCode(String companyShortCode) {
        this.companyShortCode = companyShortCode;
    }


    /**
     * 客户编码
     * @return
     */
    @XmlElement(name = "CUST_NUMBER")
    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }



    /**
     * 客户名称
     * @return
     */
    @XmlElement(name = "CUST_NAME")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    /**
     * 客户别名
     * @return
     */
    @XmlElement(name = "KNOWN_AS")
    public String getCustomerKnowAs() {
        return customerKnowAs;
    }

    public void setCustomerKnowAs(String customerKnowAs) {
        this.customerKnowAs = customerKnowAs;
    }


    /**
     * 纳税登记编号或个人身份证
     * @return
     */
    @XmlElement(name = "TAX_REFERENCE")
    public String getTaxReference() {
        return taxReference;
    }

    public void setTaxReference(String taxReference) {
        this.taxReference = taxReference;
    }


    /**
     * 省
     * @return
     */
    @XmlElement(name = "PROVINCE")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 市
     * @return
     */
    @XmlElement(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    /**
     * 县
     * @return
     */
    @XmlElement(name = "COUNTY")
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }


    /**
     * 收货地址
     * @return
     */
    @XmlElement(name = "ADDRESS1")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    /**
     *联系人姓名
     * @return
     */
    @XmlElement(name = "CONTACT_PERSON")
    public String getContactPerson() {
        return contactPerson;
    }


    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    /**
     * 联系人电话
     * @return
     */
    @XmlElement(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @XmlElement(name = "CATEGORY1")
    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    @XmlElement(name = "CATEGORY2")
    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    @XmlElement(name = "CATEGORY3")
    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    @XmlElement(name = "GUIMO")
    public String getGuimo() {
        return guimo;
    }

    public void setGuimo(String guimo) {
        this.guimo = guimo;
    }


    @XmlElement(name = "PERSON_ID")
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @XmlElement(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}