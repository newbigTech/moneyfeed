package com.newhope.moneyfeed.user.api.dto.response.platform;

import java.io.Serializable;

public class PmResourceDtoResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2885871577685816515L;

	private Long id;
	
    private String name;

    private String type;

    private Long parentId;

	private boolean selected;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
