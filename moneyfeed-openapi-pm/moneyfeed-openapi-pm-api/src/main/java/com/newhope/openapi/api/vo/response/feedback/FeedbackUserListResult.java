package com.newhope.openapi.api.vo.response.feedback;


import java.util.List;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;

public class FeedbackUserListResult extends Result{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<UserModel> users;

	public static class UserModel {
		 /** 主键ID */
		@ApiModelProperty(name = "id",notes = "主键ID")
	    private Long id;
	    
	    /** 用户编号,自动生成,日期+5位流水号             */
		@ApiModelProperty(name = "code",notes = "用户编号")
	    private String code;

	    /** 用户账号,自动生成 8位随机码 */
		@ApiModelProperty(name = "account",notes = " 用户账号")
	    private String account;

	    /** 手机号 */
		@ApiModelProperty(name = "mobile",notes = " 手机号 ")
	    private String mobile;

	    /** 用户名 */
		@ApiModelProperty(name = "name",notes = " 用户名")
	    private String name;

	    /** 用户来源 */
		@ApiModelProperty(name = "source",notes = " 用户来源 ")
	    private String source;

	    /** 是否可用 */
		@ApiModelProperty(name = "enable",notes = " 是否可用")
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

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public Boolean getEnable() {
			return enable;
		}

		public void setEnable(Boolean enable) {
			this.enable = enable;
		}
		
	}

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}

	
}