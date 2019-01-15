package com.newhope.user.user.biz.service;

import java.util.List;

import com.newhope.moneyfeed.user.api.constant.CommonConstant;
import com.newhope.moneyfeed.user.dal.BaseDao;


public abstract class BaseService<T> {

	protected abstract BaseDao<T> getDao();
	
	public boolean save(List<T> modelList) {
		return (modelList.size() == getDao().insert(modelList));
	}
	
	public boolean remove(T model) {
		return (getDao().delete(model) > 0);
	}
	
	public List<T> query(T model) {
		return getDao().select(model);
	}
	
	public boolean update(T oldModel, T newModel) {
		return (getDao().update(oldModel, newModel) > 0);
	}
	
	public long count(T model) {
		return getDao().count(model);
	}
	
	public long pages(T model) {
		long count = getDao().count(model);
		return pages(count);
	}
	
	public Long pages(Long count) {
		return (count / CommonConstant.PAGE_SIZE + ((count % CommonConstant.PAGE_SIZE != 0) ? 1 : 0));
	}

    public Long pages(Long count, Integer pageSize) {
        return (count / pageSize + ((count % pageSize != 0) ? 1 : 0));
    }

}