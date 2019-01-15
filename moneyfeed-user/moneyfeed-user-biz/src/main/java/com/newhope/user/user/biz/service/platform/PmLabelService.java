package com.newhope.user.user.biz.service.platform;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmLabelModel;
import com.newhope.moneyfeed.user.api.dto.request.platform.LabelDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.LabelModifyDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.LabelPageDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmLabelDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmLabelListDtoResult;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.platform.UcPmLabelDao;
import com.newhope.user.user.biz.service.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Create by yyq on 2018/12/20
 */
@Service
public class PmLabelService extends BaseService<UcPmLabelModel> {

    public static Logger logger = LoggerFactory.getLogger(PmLabelService.class);
    @Autowired
    UcPmLabelDao ucPmLabelDao;
    @Override
    protected BaseDao<UcPmLabelModel> getDao() {
        return ucPmLabelDao;
    }

    /**
     * 新建标签
     *
     * @Author: yyq
     * @Date  :Created in  2018/12/20 9:14
     * @Param :
     */
    @Transactional
    public DtoResult createLabel(LabelDtoReq requestBody) {

        UcPmLabelModel oldModel = new UcPmLabelModel();
        oldModel.setEnable(Boolean.TRUE);
        oldModel.setName(requestBody.getName());
        oldModel.setCreateType(requestBody.getCreateType());
        oldModel.setTargetType(requestBody.getTargetType());
        List<UcPmLabelModel> ucPmLabelModels = ucPmLabelDao.select(oldModel);

        if(CollectionUtils.isNotEmpty(ucPmLabelModels)){
            logger.error("标签已存在");
            throw new BizException(ResultCode.FAIL.getCode(),"标签已存在");
        }

        UcPmLabelModel ucPmLabelModel = new UcPmLabelModel();
        BeanUtils.copyProperties(requestBody,ucPmLabelModel);
        ucPmLabelModel.setEnable(Boolean.TRUE);
        long insert = ucPmLabelDao.insert(Arrays.asList(ucPmLabelModel));
        if(insert<=0){
            logger.error("创建标签失败");
            throw new BizException(ResultCode.FAIL.getCode(),"创建标签失败");
        }
        return DtoResult.success();
    }

    /**
     * 查询标签分页列表
     *
     * @Author: yyq
     * @Date  :Created in  2018/12/20 9:23
     * @Param :
     */
    public UcPmLabelListDtoResult labelList(LabelPageDtoReq requestBody) {
        UcPmLabelListDtoResult result = new UcPmLabelListDtoResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());

        PageBounds pageBounds;
        if (requestBody.getPage() == null || requestBody.getPageSize() == null) {
            pageBounds = new PageBounds();
        } else {
            pageBounds = new PageBounds(requestBody.getPage().intValue(), requestBody.getPageSize());
        }
        PageList<UcPmLabelDtoResult> resultPageList = ucPmLabelDao.labelList(requestBody, pageBounds);
        result.setDataList(resultPageList);
        if (resultPageList.getPaginator() != null) {
            result.setPage((long) resultPageList.getPaginator().getPage());
            result.setTotalCount((long) resultPageList.getPaginator().getTotalCount());
            result.setTotalPage((long) resultPageList.getPaginator().getTotalPages());
        }
        return result;
    }

    /**
     * 修改标签
     * @Author: yyq
     * @Date  :Created in  2018/12/20 10:11
     * @Param :
     */
    @Transactional
    public DtoResult modifyLabel(LabelModifyDtoReq requestBody) {

        UcPmLabelModel oldModel =new UcPmLabelModel();
        oldModel.setId(requestBody.getId());
        UcPmLabelModel newModel =new UcPmLabelModel();
        BeanUtils.copyProperties(requestBody,newModel);
        long update = ucPmLabelDao.update(oldModel, newModel);
        if(update<=0){
            logger.error("更新标签失败");
            throw new BizException(ResultCode.FAIL.getCode(),"更新标签失败");
        }
        return DtoResult.success();
    }
    
    public List<UcPmLabelModel> queryLabelByCusomerId(Long customerId){
    	return ucPmLabelDao.queryLabelByCusomerId(customerId);
    }
}
