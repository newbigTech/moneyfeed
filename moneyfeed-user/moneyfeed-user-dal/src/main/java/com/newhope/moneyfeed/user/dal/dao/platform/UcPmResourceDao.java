package com.newhope.moneyfeed.user.dal.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newhope.moneyfeed.user.api.bean.platform.UcPmResourceModel;
import com.newhope.moneyfeed.user.api.dto.request.platform.ResourceQueryDtoReq;
import com.newhope.moneyfeed.user.api.exbean.platform.UcPmResourceExModel;
import com.newhope.moneyfeed.user.dal.BaseDao;

public interface UcPmResourceDao extends BaseDao<UcPmResourceModel> {

	List<UcPmResourceModel> searchResource(@Param("resourceQueryReq")ResourceQueryDtoReq resourceQueryReq);
	
	List<UcPmResourceExModel> searchRoleResource(@Param("resourceQueryReq")ResourceQueryDtoReq resourceQueryReq);
	
}