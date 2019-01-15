package com.newhope.moneyfeed.biz.wechat;

import com.google.common.collect.Lists;
import com.newhope.moneyfeed.api.bean.SysWechatEventDataModel;
import com.newhope.moneyfeed.api.enums.wechat.WechatEventEnums;
import com.newhope.moneyfeed.api.service.wechat.WechatEventService;
import com.newhope.moneyfeed.common.concurrent.ManagedThreadPool;
import com.newhope.moneyfeed.common.constant.WechatConstant;
import com.newhope.moneyfeed.dal.dao.uc.SysWechatEventDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by liming on 2019/1/7.
 */
@Service
public class WechatEventServiceImpl implements WechatEventService {

    @Autowired
    private ManagedThreadPool managedThreadPool;

    @Autowired
    private SysWechatEventDataDao sysWechatEventDataDao;

    /**
     * 保存关注微信公众号事件
     *
     * @param openId
     */
    @Override
    public void saveSysWechatSubscirptEvent(String openId) {

        /**
         * 异步调用不影响主流程
         */
        managedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                SysWechatEventDataModel sysWechatEventDataModel=new SysWechatEventDataModel();
                sysWechatEventDataModel.setCreateTime(new Date());
                sysWechatEventDataModel.setEvnId(WechatEventEnums.SUBCRIPT.getVal());
                sysWechatEventDataModel.setOpenId(openId);
                sysWechatEventDataModel.setWechatId(WechatConstant.APPID);
                sysWechatEventDataDao.insert(Lists.newArrayList(sysWechatEventDataModel));
            }
        });
    }
}