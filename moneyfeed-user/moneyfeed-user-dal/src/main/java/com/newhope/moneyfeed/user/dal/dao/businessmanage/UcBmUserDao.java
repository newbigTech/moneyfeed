package com.newhope.moneyfeed.user.dal.dao.businessmanage;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.user.api.bean.businessmanage.UcBmUserModel;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.EmployeeInfoDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.UserShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserDtoResult;
import com.newhope.moneyfeed.user.dal.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UcBmUserDao extends BaseDao<UcBmUserModel> {
	
	List<UcBmUserModel> queryBmUserByShopId(@Param("queryParam") UserShopQueryDtoReq userShopQueryDtoReq);

    PageList<UcBmUserDtoResult> queryBmEmployeeInfoList(@Param("queryParam")EmployeeInfoDtoReq employeeInfoDtoReq, PageBounds pageBounds);
}