package com.newhope.moneyfeed.order.api.dto.response.order;

import java.util.List;

import com.newhope.moneyfeed.api.dto.response.DtoResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "验证锁库返回对象")
public class OrderValidateRepositoryDtoResult extends DtoResult {

	private static final long serialVersionUID = -2583081177503157178L;
	
	@ApiModelProperty(name = "repositoryLowFlag", notes = "库存不够,true不够,false够")
	private Boolean repositoryLowFlag;
	
	@ApiModelProperty(name = "materialName", notes = "库存不够的物料名")
	private List<String> materialName;

	public Boolean getRepositoryLowFlag() {
		return repositoryLowFlag;
	}

	public List<String> getMaterialName() {
		return materialName;
	}

	public void setRepositoryLowFlag(Boolean repositoryLowFlag) {
		this.repositoryLowFlag = repositoryLowFlag;
	}

	public void setMaterialName(List<String> materialName) {
		this.materialName = materialName;
	}

}
