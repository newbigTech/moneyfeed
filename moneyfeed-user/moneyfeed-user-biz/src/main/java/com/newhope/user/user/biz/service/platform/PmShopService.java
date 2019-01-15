package com.newhope.user.user.biz.service.platform;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.newhope.commons.lang.MD5Utils;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.exception.BizException;
import com.newhope.moneyfeed.user.api.bean.businessmanage.UcBmUserModel;
import com.newhope.moneyfeed.user.api.bean.businessmanage.UcBmUserRoleModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.dto.request.platform.ShopDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.ShopModifyDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.ShopPageDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmShopDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmShopListDtoResult;
import com.newhope.moneyfeed.user.api.enums.businessmanage.BmRoleCodeEnums;
import com.newhope.moneyfeed.user.api.enums.platform.ShopStatusEnums;
import com.newhope.moneyfeed.user.dal.BaseDao;
import com.newhope.moneyfeed.user.dal.dao.businessmanage.UcBmUserDao;
import com.newhope.moneyfeed.user.dal.dao.businessmanage.UcBmUserRoleDao;
import com.newhope.moneyfeed.user.dal.dao.client.UcShopDao;
import com.newhope.moneyfeed.user.dal.dao.platform.UcPmRoleDao;
import com.newhope.user.user.biz.service.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class PmShopService extends BaseService<UcShopModel> {

    public static Logger logger = LoggerFactory.getLogger(PmShopService.class);
    @Autowired
    UcShopDao ucShopDao;
    @Autowired
    UcBmUserDao ucBmUserDao;
    @Autowired
    UcBmUserRoleDao ucBmUserRoleDao;
    @Autowired
    UcPmRoleDao ucPmRole;

    @Override
    protected BaseDao<UcShopModel> getDao() {
        return ucShopDao;
    }

    @Transactional
    public DtoResult createShop(ShopDtoReq requestBody) {

        UcBmUserModel ucBmUserModel = new UcBmUserModel();

        UcShopModel ucShopModel = new UcShopModel();
        BeanUtils.copyProperties(requestBody,ucShopModel);
        ucShopModel.setEnable(Boolean.TRUE);
        //todo ebs 编号
        ucShopModel.setName(requestBody.getShopName());
        long insert = ucShopDao.insert(Arrays.asList(ucShopModel));
        if(insert<=0){
            logger.error("创建店铺失败");
            throw new BizException(ResultCode.FAIL.getCode(),"创建店铺失败");
        }

        List<UcShopModel> ucShopModels = ucShopDao.select(ucShopModel);
        if(CollectionUtils.isEmpty(ucShopModels)){
            logger.error("创建店铺后查询失败");
            throw new BizException(ResultCode.FAIL.getCode(),"创建店铺后查询失败");
        }
        //添加管理员
        ucBmUserModel.setMobile(requestBody.getMobile());
        ucBmUserModel.setEnable(Boolean.TRUE);
        ucBmUserModel.setName(requestBody.getAdminName());
        ucBmUserModel.setShopId(ucShopModels.get(0).getId());
        Long saveAdmin = saveAdmin(ucBmUserModel);
        UcShopModel oldModel = new UcShopModel();
        oldModel.setId(ucShopModels.get(0).getId());
        UcShopModel newModel = new UcShopModel();
        newModel.setManageUserId(saveAdmin.toString());
        long update = ucShopDao.update(oldModel, newModel);
        if(update<=0){
            logger.error("更新店铺管理员Id失败");
            throw new BizException(ResultCode.FAIL.getCode(),"更新店铺管理员Id失败");
        }
        return DtoResult.success();
    }

    public UcPmShopListDtoResult shopList(ShopPageDtoReq requestBody) {
        UcPmShopListDtoResult result = new UcPmShopListDtoResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());
        PageBounds pageBounds;
        if (requestBody.getPage() == null || requestBody.getPageSize() == null) {
            pageBounds = new PageBounds();
        } else {
            pageBounds = new PageBounds(requestBody.getPage().intValue(), requestBody.getPageSize());
        }
        PageList<UcPmShopDtoResult> resultPageList = ucShopDao.shopList(requestBody,pageBounds);
        result.setDataList(resultPageList);
        if (resultPageList.getPaginator() != null) {
            result.setPage((long) resultPageList.getPaginator().getPage());
            result.setTotalCount((long) resultPageList.getPaginator().getTotalCount());
            result.setTotalPage((long) resultPageList.getPaginator().getTotalPages());
        }
        return result;
    }

    /**
     *
     * 添加管理员
     * @Author: yyq
     * @Date  :Created in  2018/12/25 9:04
     * @Param :
     */
    private Long saveAdmin(UcBmUserModel ucBmUserModel){

        //判断手机号是否存在
        UcBmUserModel userMobile=new UcBmUserModel();
        userMobile.setMobile(ucBmUserModel.getMobile());
        userMobile.setEnable(Boolean.TRUE);
        List<UcBmUserModel> ucBmUserModels = ucBmUserDao.select(userMobile);
        if(CollectionUtils.isNotEmpty(ucBmUserModels)){
            logger.error("管理员手机号已存在");
            throw new BizException(ResultCode.USER_MOBILE_EXIST.getCode(),"管理员手机号已存在");
        }
        String password="";
        try {
            password= MD5Utils.encodeMD5Hex(MD5Utils.encodeMD5Hex("123456"));
        } catch (Exception e) {
            logger.error("默认密码加密失败");
            throw new BizException(ResultCode.FAIL.getCode(),"默认密码加密失败");
        }
        ucBmUserModel.setPassword(password);
        //添加管理员
        long insertUser = ucBmUserDao.insert(Arrays.asList(ucBmUserModel));
        if(insertUser<=0){
            logger.error("创建店铺管理员失败");
            throw new BizException(ResultCode.FAIL.getCode(),"创建店铺管理员失败");
        }
        List<UcBmUserModel> bmUserModels = ucBmUserDao.select(ucBmUserModel);
        if(CollectionUtils.isEmpty(bmUserModels)){
            logger.error("创建店铺管理员失败");
            throw new BizException(ResultCode.FAIL.getCode(),"创建店铺管理员失败");
        }

        //添加用户角色
        UcPmRoleModel ucPmRoleModel = new UcPmRoleModel();
        ucPmRoleModel.setCode(BmRoleCodeEnums.SHOP_ADMIN.getValue());
        ucPmRoleModel.setEnable(Boolean.TRUE);
        List<UcPmRoleModel> select = ucPmRole.select(ucPmRoleModel);
        if(CollectionUtils.isEmpty(select)){
            logger.error("用户角色code码不正确");
            throw new BizException(ResultCode.FAIL.getCode(),"用户角色code码不正确");
        }
        UcBmUserRoleModel ucBmUserRoleModel = new UcBmUserRoleModel();
        ucBmUserRoleModel.setEnable(Boolean.TRUE);
        ucBmUserRoleModel.setRoleId(select.get(0).getId());
        ucBmUserRoleModel.setUserId(bmUserModels.get(0).getId());
        long insert = ucBmUserRoleDao.insert(Arrays.asList(ucBmUserRoleModel));
        if(insert<=0){
            logger.error("创建用户角色失败");
            throw new BizException(ResultCode.FAIL.getCode(),"创建用户角色失败");
        }
        return bmUserModels.get(0).getId();
    }

    @Transactional
    public DtoResult modifyShop(ShopModifyDtoReq requestBody) {
        UcShopModel oldModel = new UcShopModel();
        oldModel.setId(requestBody.getShopId());
        UcShopModel newModel = new UcShopModel();
        BeanUtils.copyProperties(requestBody,newModel);
        if(StringUtils.isNotEmpty(requestBody.getShopName())){
            newModel.setName(requestBody.getShopName());
        }

        UcShopModel ucShopModel = new UcShopModel();
        ucShopModel.setId(requestBody.getShopId());
        List<UcPmShopDtoResult> ucShopModels = ucShopDao.shopDetail(ucShopModel);
        //修改了管理员名称
        if(CollectionUtils.isNotEmpty(ucShopModels) && StringUtils.isNotEmpty(ucShopModels.get(0).getAdminName())
                && !ucShopModels.get(0).getAdminName().equals(requestBody.getAdminName()) && StringUtils.isNotEmpty(requestBody.getAdminName())
                ){
            UcBmUserModel ucBmUserModel =new UcBmUserModel();
            ucBmUserModel.setMobile(ucShopModels.get(0).getMobile());
            ucBmUserModel.setEnable(Boolean.TRUE);
            List<UcBmUserModel> ucBmUserModels = ucBmUserDao.select(ucBmUserModel);
            if(CollectionUtils.isEmpty(ucBmUserModels)){
                logger.error("用户电话不正确");
                throw new BizException(ResultCode.FAIL.getCode(),"用户电话不正确");
            }
            UcBmUserModel oldUserModel = new UcBmUserModel();
            oldUserModel.setId(ucBmUserModels.get(0).getId());
            UcBmUserModel newUserModel = new UcBmUserModel();
            newUserModel.setName(requestBody.getAdminName());
            long updateUser = ucBmUserDao.update(oldUserModel, newUserModel);
        }
        //修改了管理员电话
        if(CollectionUtils.isNotEmpty(ucShopModels) && StringUtils.isNotEmpty(ucShopModels.get(0).getMobile())
                && !ucShopModels.get(0).getMobile().equals(requestBody.getMobile()) && StringUtils.isNotEmpty(requestBody.getMobile())
                ){
            //修改管理员信息
            UcBmUserModel ucBmUserModel =new UcBmUserModel();
            ucBmUserModel.setMobile(ucShopModels.get(0).getMobile());
            ucBmUserModel.setEnable(Boolean.TRUE);
            List<UcBmUserModel> ucBmUserModels = ucBmUserDao.select(ucBmUserModel);
            if(CollectionUtils.isEmpty(ucBmUserModels)){
                logger.error("用户电话不正确");
                throw new BizException(ResultCode.FAIL.getCode(),"用户电话不正确");
            }

            UcBmUserModel oldUserModel = new UcBmUserModel();
            oldUserModel.setId(ucBmUserModels.get(0).getId());
            UcBmUserModel newUserModel = new UcBmUserModel();
            newUserModel.setEnable(Boolean.FALSE);
            long updateUser = ucBmUserDao.update(oldUserModel, newUserModel);
            UcBmUserRoleModel oldBmUserModel = new UcBmUserRoleModel();
            oldBmUserModel.setUserId(ucBmUserModels.get(0).getId());
            UcBmUserRoleModel newBmUserModel = new UcBmUserRoleModel();
            newBmUserModel.setEnable(Boolean.FALSE);
            long updateUserRole = ucBmUserRoleDao.update(oldBmUserModel, newBmUserModel);
            //添加管理员
            ucBmUserModel.setMobile(requestBody.getMobile());
            ucBmUserModel.setEnable(Boolean.TRUE);
            ucBmUserModel.setName(requestBody.getAdminName());
            ucBmUserModel.setShopId(requestBody.getShopId());
            Long saveAdmin = saveAdmin(ucBmUserModel);
            newModel.setManageUserId(saveAdmin.toString());
            if(updateUser<=0 || updateUserRole<=0){
                logger.error("修改店铺信息失败");
                throw new BizException(ResultCode.FAIL.getCode(),"修改店铺信息失败");
            }
        }


        long update = ucShopDao.update(oldModel, newModel);

        if(update<=0){
            logger.error("修改店铺信息失败");
            throw new BizException(ResultCode.FAIL.getCode(),"修改店铺信息失败");
        }
        //todo 给管理员发送短信
        return DtoResult.success();
    }

    public UcPmShopDtoResult shopDetail(Long shopId) {
        UcPmShopDtoResult result = new UcPmShopDtoResult();

        UcShopModel ucShopModel = new UcShopModel();
        ucShopModel.setId(shopId);
        List<UcPmShopDtoResult> ucShopModels = ucShopDao.shopDetail(ucShopModel);
        if(CollectionUtils.isEmpty(ucShopModels)){
            logger.error("店铺详情查询失败");
            throw new BizException(ResultCode.FAIL.getCode(),"店铺详情查询失败");
        }
        UcPmShopDtoResult ucShop = ucShopModels.get(0);
        BeanUtils.copyProperties(ucShop,result);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getDesc());
        return result;
    }
}
