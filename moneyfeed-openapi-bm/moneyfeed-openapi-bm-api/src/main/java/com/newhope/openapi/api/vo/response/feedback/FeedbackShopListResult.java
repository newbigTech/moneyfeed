package com.newhope.openapi.api.vo.response.feedback;


import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class FeedbackShopListResult extends Result{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ShopModel> shopModels;

	public static class ShopModel {
		 /** 主键ID */
		@ApiModelProperty(name = "id",notes = "主键ID")
	    private Long id;
	    
	    /** 店铺编号,自动生成，以S开始+6行政区码+6位流水号 */
		@ApiModelProperty(name = "code",notes = "店铺编号")
	    private String code;

	    /** 店铺名称 */
		@ApiModelProperty(name = "name",notes = "店铺名称")
	    private String name;

	    /** 联系人 */
		@ApiModelProperty(name = "contactPerson",notes = "联系人")
	    private String contactPerson;

	    /** 联系电话 */
		@ApiModelProperty(name = "contactTel",notes = "联系电话")
	    private String contactTel;

	    /** 店铺简介 */
		@ApiModelProperty(name = "intro",notes = "店铺简介")
	    private String intro;

	    /** 是否可用 */
		@ApiModelProperty(name = "enable",notes = "是否可用")
	    private Boolean enable;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getContactPerson() {
			return contactPerson;
		}

		public void setContactPerson(String contactPerson) {
			this.contactPerson = contactPerson;
		}

		public String getContactTel() {
			return contactTel;
		}

		public void setContactTel(String contactTel) {
			this.contactTel = contactTel;
		}

		public String getIntro() {
			return intro;
		}

		public void setIntro(String intro) {
			this.intro = intro;
		}

		public Boolean getEnable() {
			return enable;
		}

		public void setEnable(Boolean enable) {
			this.enable = enable;
		}

	}

	public List<ShopModel> getShopModels() {
		return shopModels;
	}

	public void setShopModels(List<ShopModel> shopModels) {
		this.shopModels = shopModels;
	}

	
}