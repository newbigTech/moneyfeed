package com.newhope.openapi.biz.service.user;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.constant.StatisticsConstant;
import com.newhope.moneyfeed.common.util.ExportUtil;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.constant.CommonConstant;
import com.newhope.moneyfeed.user.api.dto.request.platform.ResourceQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.RoleQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.platform.PmResourceListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleListDtoResult;
import com.newhope.moneyfeed.user.api.enums.platform.SysSourceTypeEnums;
import com.newhope.moneyfeed.user.api.exbean.platform.UcPmResourceExModel;
import com.newhope.openapi.api.vo.request.user.PmRoleQueyReq;
import com.newhope.openapi.api.vo.response.user.PmResourceResult;
import com.newhope.openapi.biz.rpc.feign.user.PmRoleFeignClient;

@Service
public class PmRoleService {
	private Logger logger = Logger.getLogger(PmRoleService.class);
	@Autowired
	RSessionCache rSessionCache;
	
	@Autowired
	PmRoleFeignClient pmRoleFeignClient;
	
	public PmResourceResult getRoleResource(String sourceType, Long roleId){
		PmResourceResult result = new PmResourceResult();
		result.setName("根目录");
		result.setId(0L);
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		ResourceQueryDtoReq resourceParam = new ResourceQueryDtoReq();
		resourceParam.setEnable(true);
		resourceParam.setSourceType(sourceType);
		BaseResponse<PmResourceListDtoResult> feignResult = pmRoleFeignClient.getResource(resourceParam);
		if(feignResult.isSuccess()){
			if(roleId!=null){
				feignResult = pmRoleFeignClient.getResourceByRole(sourceType,roleId);
				if(feignResult.isSuccess()){
					result = assemblyResource(result, feignResult.getData().getResourceList());
					return result;
				}
			}
			result = assemblyResource(result, feignResult.getData().getResourceList());
		}
		return result;
	}
	
	public PmResourceResult assemblyResource(PmResourceResult resourceResult, List<UcPmResourceExModel> resourceList) {
		Iterator<UcPmResourceExModel> iterator = resourceList.iterator();
		while (iterator.hasNext()) {
			UcPmResourceExModel menu = iterator.next();
			if (resourceResult.getId().equals(menu.getParentId())) {
				PmResourceResult subMenu = new PmResourceResult();
				subMenu.setSelected(menu.isSelected());
				subMenu.setName(menu.getName());
				subMenu.setId(menu.getId());
				subMenu.setType(menu.getType());
				subMenu.setResourceCode(menu.getCode());
				resourceResult.getSubList().add(subMenu);
				assemblyResource(subMenu, resourceList);
			}
		}
		return resourceResult;
	}
	
	public void exportRole(PmRoleQueyReq req, HttpServletResponse response){
		RoleQueryDtoReq queryParam = new RoleQueryDtoReq();
		queryParam.setPage(null);
		queryParam.setPageSize(null);
		queryParam.setSourceType(req.getSourceType());
		queryParam.setId(req.getId());
		queryParam.setName(req.getName());
		BaseResponse<UcPmRoleListDtoResult> feignResult = pmRoleFeignClient.getRoleList(queryParam);
		if(feignResult.isSuccess()&&feignResult.getData()!=null
				&&CollectionUtils.isNotEmpty(feignResult.getData().getRoleList())){
			List<Map<String, Object>> content = new ArrayList<>();
			Map<String, Object> map;
			for(UcPmRoleModel role:feignResult.getData().getRoleList()){
				map = new HashMap<>();
				map.put("id",role.getId() == null ? CommonConstant.BLANK_STRING : "\"" + role.getId() + "\"");
				map.put("sourceType",role.getSourceType()== null ? CommonConstant.BLANK_STRING : "\"" + SysSourceTypeEnums.valueOf(role.getSourceType()).getDesc() + "\"");
				map.put("name",role.getName() == null ? CommonConstant.BLANK_STRING : "\"" + role.getName() + "\"");
				map.put("comment",role.getComment() == null ? CommonConstant.BLANK_STRING : "\"" + role.getComment() + "\"");
				content.add(map);
			}
			String mapKey = "id,sourceType,name,comment";
			try {
				final OutputStream os = response.getOutputStream();
				ExportUtil.responseSetProperties(StatisticsConstant.PM_EXPORT_ROLE, response);
				ExportUtil.doExport(content, StatisticsConstant.COLUMNS_PM_EXPORT_ROLE,
                        StatisticsConstant.CSV_COLUMN_SEPARATOR, StatisticsConstant.CSV_RN, mapKey, os);
			} catch (Exception e) {
				logger.error("[PmRoleService.exportRole]:下载文件异常", e);
			}
		}
	}
}
