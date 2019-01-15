package com.newhope.pay.biz.service.impl;

import com.newhope.moneyfeed.pay.dal.BaseDao;
import com.newhope.pay.biz.service.BaseService;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T>{

	protected abstract BaseDao<T> getDao();
	
	public boolean save(List<T> modelList) {
		if(modelList.size() == 0) {
			return true;
		}
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
		return (count / 10 + ((count % 10 != 0) ? 1 : 0));
	}

    public Long pages(Long count, Integer pageSize) {
        return (count / pageSize + ((count % pageSize != 0) ? 1 : 0));
    }
}