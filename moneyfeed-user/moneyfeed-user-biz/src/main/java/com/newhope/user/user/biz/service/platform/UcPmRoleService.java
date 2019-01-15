package com.newhope.user.user.biz.service.platform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleResourceModel;
import com.newhope.moneyfeed.user.api.dto.request.platform.RolePostDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.RoleQueryDtoReq;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.platform.UcPmRoleDao;
import com.newhope.user.user.biz.service.BaseService;

@Service
public class UcPmRoleService extends BaseService<UcPmRoleModel> {

	@Autowired
	UcPmRoleDao ucPmRoleDao;

	@Autowired
	UcPmRoleResourceService roleResourceService;

	@Override
	protected BaseDao<UcPmRoleModel> getDao() {
		return ucPmRoleDao;
	}

	public PageList<UcPmRoleModel> queryRole(RoleQueryDtoReq queryParam) {
		PageBounds pageBounds = null;
		queryParam.setEnable(true);
		queryParam.setPage(null);
		queryParam.setPageSize(null);
		if (null != queryParam.getPage() && null != queryParam.getPageSize()) {
			pageBounds = new PageBounds(queryParam.getPage().intValue(), queryParam.getPageSize());
			PageList<UcPmRoleModel> pageList = ucPmRoleDao.queryRole(queryParam, pageBounds);
			return pageList;
		}else{
			PageList<UcPmRoleModel> pageList = ucPmRoleDao.queryRole(queryParam);
			return pageList;
		}
	}
	
	public PageList<UcPmRoleModel> getRoleList(RoleQueryDtoReq queryParam) {
		PageBounds pageBounds = null;
		if (null != queryParam.getPage() && null != queryParam.getPageSize()) {
			pageBounds = new PageBounds(queryParam.getPage().intValue(), queryParam.getPageSize());
			PageList<UcPmRoleModel> pageList = ucPmRoleDao.getRoleList(queryParam, pageBounds);
			return pageList;
		}else{
			PageList<UcPmRoleModel> pageList = ucPmRoleDao.getRoleList(queryParam);
			return pageList;
		}
	}

	public void addRole(RolePostDtoReq data) {
		UcPmRoleModel saveRole = new UcPmRoleModel();
		saveRole.setName(data.getName());
		saveRole.setComment(data.getComment());
		saveRole.setSourceType(data.getSourceType());
		saveRole.setEnable(data.getEnable());
		if (save(Arrays.asList(saveRole))) {
			if (CollectionUtils.isNotEmpty(data.getResourceIdList())) {
				List<UcPmRoleResourceModel> roleResourceList = new ArrayList<>();
				for (Long resourceId : data.getResourceIdList()) {
					UcPmRoleResourceModel roleResource = new UcPmRoleResourceModel();
					roleResource.setEnable(true);
					roleResource.setRoleId(saveRole.getId());
					roleResource.setResourceId(resourceId);
					roleResourceList.add(roleResource);
				}
				roleResourceService.save(roleResourceList);
			}
		}
	}

	public void modifyRole(RolePostDtoReq data) {
		UcPmRoleModel newRole = new UcPmRoleModel();
		newRole.setName(data.getName());
		newRole.setComment(data.getComment());
		newRole.setSourceType(data.getSourceType());
		newRole.setEnable(data.getEnable());
		UcPmRoleModel oldRole = new UcPmRoleModel();
		oldRole.setId(data.getId());
		if (update(oldRole, newRole)) {
			UcPmRoleResourceModel delParam = new UcPmRoleResourceModel();
			delParam.setRoleId(data.getId());
			roleResourceService.remove(delParam);
			if (CollectionUtils.isNotEmpty(data.getResourceIdList())) {
				List<UcPmRoleResourceModel> roleResourceList = new ArrayList<>();
				for (Long resourceId : data.getResourceIdList()) {
					UcPmRoleResourceModel roleResource = new UcPmRoleResourceModel();
					roleResource.setEnable(true);
					roleResource.setRoleId(data.getId());
					roleResource.setResourceId(resourceId);
					roleResourceList.add(roleResource);
				}
				roleResourceService.save(roleResourceList);
			}
		}
	}
}
