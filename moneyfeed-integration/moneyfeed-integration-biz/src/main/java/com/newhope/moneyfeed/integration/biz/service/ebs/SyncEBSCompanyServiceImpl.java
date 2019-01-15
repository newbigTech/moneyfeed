package com.newhope.moneyfeed.integration.biz.service.ebs;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.integration.api.bean.ebs.baseData.EbsCompanyModel;
import com.newhope.moneyfeed.integration.api.dto.response.ebs.EBSCompanyRespListResult;
import com.newhope.moneyfeed.integration.api.service.SyncEBScompanyService;
import com.newhope.moneyfeed.integration.dal.dao.ebs.baseData.EbsCompanyDao;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liming on 2018/7/16.
 */
@Service
public class SyncEBSCompanyServiceImpl implements SyncEBScompanyService {

    private final static Logger logger= LoggerFactory.getLogger(SyncEBSCompanyServiceImpl.class);


    @Autowired
    private EbsCompanyDao ebsCompanyDao;


    @Override
    public EBSCompanyRespListResult selectComapny(EbsCompanyModel companyModel){

        logger.info("---------查询需要同步的EBS公司------------");
        EBSCompanyRespListResult result=new EBSCompanyRespListResult();
        try {
            //EbsCompanyModel ebsCompanyModel=new EbsCompanyModel();
            final List<EbsCompanyModel> select = ebsCompanyDao.select(companyModel);
            if(CollectionUtils.isNotEmpty(select)){
                result.setDataList(select);
            }
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMsg(ResultCode.SUCCESS.getDesc());
        }catch (BizException e) {
            logger.error("查询需要同步的EBS公司出错", e);
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("查询需要同步的EBS公司出错：" , e);
            result.setCode(ResultCode.FAIL.getCode());
            result.setMsg(ResultCode.FAIL.getDesc());
        }
        return result;
    }
}