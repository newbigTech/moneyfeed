package com.newhope.moneyfeed.user.dal;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public abstract interface BaseDao<T> {
	
	/**
	 * 根据条件删除数据
	 * @param model
	 * @return
	 */
	long delete(T model);

	/**
	 * 插入数据列表
	 * @param model
	 * @return
	 */
    long insert(List<T> model);
    
    /**
     * 根据条件查询数据列表
     * @param model
     * @return
     */
    List<T> select(T model);

    /**
     * 根据条件更新数据
     * @param oldModel
     * @param newModel
     * @return
     */
    long update(@Param("oldModel") T oldModel, @Param("newModel") T newModel);

    /**
     * 根据条件更新数据,包含空值
     * @param oldModel
     * @param newModel
     * @return
     */
    long updateIncludeNull(@Param("oldModel") T oldModel, @Param("newModel") T newModel);

    /**
     * 根据调教查询数据条数
     * @param model
     * @return
     */
    long count(T model);
    
    /**
     * 根据条件查询数据ex列表
     * @param model
     * @return
     */
    List<T> selectEx(T model);
}