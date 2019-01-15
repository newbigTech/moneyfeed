package com.newhope.pay.biz.service;

import java.util.List;

public interface BaseService<T> {
	
public boolean save(List<T> modelList);
	
	boolean remove(T model);
	
	List<T> query(T model);
	
	boolean update(T oldModel, T newModel);
	
	long count(T model);
	
	long pages(T model);
	
	Long pages(Long count);

    Long pages(Long count, Integer pageSize);
	
}