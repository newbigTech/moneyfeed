package com.newhope.moneyfeed.user.dal.dao.platform;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmLabelModel;
import com.newhope.moneyfeed.user.api.dto.request.platform.LabelPageDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmLabelDtoResult;
import com.newhope.moneyfeed.user.dal.BaseDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface UcPmLabelDao extends BaseDao<UcPmLabelModel> {

    PageList<UcPmLabelDtoResult> labelList(@Param("param") LabelPageDtoReq requestBody, PageBounds bounds);
    
    List<UcPmLabelModel> queryLabelByCusomerId(@Param("customerId") Long customerId);
}