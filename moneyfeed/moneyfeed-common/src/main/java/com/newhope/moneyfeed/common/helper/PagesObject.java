package com.newhope.moneyfeed.common.helper;

import java.util.List;

public class PagesObject<T> {

    private List<T> objects;
    private int totalPages;
    private int totalCount;
    /**
     * 当前页
     */
    private int page;
	public List<T> getObjects() {
		return objects;
	}
	public void setObjects(List<T> objects) {
		this.objects = objects;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
