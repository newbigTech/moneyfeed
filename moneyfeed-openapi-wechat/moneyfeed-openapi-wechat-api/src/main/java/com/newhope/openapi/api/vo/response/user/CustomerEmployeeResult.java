package com.newhope.openapi.api.vo.response.user;


import java.util.Date;

import com.newhope.moneyfeed.api.vo.response.Result;

import io.swagger.annotations.ApiModelProperty;


public class CustomerEmployeeResult extends Result {

	private static final long serialVersionUID = 104267490966620334L;

	@ApiModelProperty("主键Id")
	private Long customerClientUserId;
	
    @ApiModelProperty("员工名称")
    private String employeeName;

    @ApiModelProperty("员工电话，即帐号")
    private String employeePhone;
    
    @ApiModelProperty("true-正常，false-禁用")
    private Boolean enable;
    
    @ApiModelProperty("来源")
    private String source;
    
    @ApiModelProperty("创建时间")
    private Date gmtCreated;
    
    @ApiModelProperty("员工的用户主键Id")
	private Long clientUserId;
    
    @ApiModelProperty("用户所属的角色ID")
    private Long roleId;
    
    @ApiModelProperty("用户所属的角色名称")
    private String roleName;
    
    @ApiModelProperty("激活状态")
    private String activated;
    
    @ApiModelProperty("状态:NORMAL-正常；NOT_CONFIRMED-待确认")
    private String status;

	public Long getCustomerClientUserId() {
		return customerClientUserId;
	}

	public void setCustomerClientUserId(Long customerClientUserId) {
		this.customerClientUserId = customerClientUserId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public Long getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(Long clientUserId) {
		this.clientUserId = clientUserId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getActivated() {
		return activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    
}