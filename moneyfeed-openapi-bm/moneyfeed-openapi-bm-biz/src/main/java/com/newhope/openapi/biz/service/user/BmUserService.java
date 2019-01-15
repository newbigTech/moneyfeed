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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.ThirdSourceEnums;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.constant.StatisticsConstant;
import com.newhope.moneyfeed.common.util.ContextHolderUtil;
import com.newhope.moneyfeed.common.util.ExportUtil;
import com.newhope.moneyfeed.user.api.bean.platform.UcPmRoleModel;
import com.newhope.moneyfeed.user.api.constant.CommonConstant;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.EmployeeInfoDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.LoginByPassDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.LoginBySmscodeDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.ModifyEmployeeInfoDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.ModifyUserInfoBySmscodeDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.ResetPassWordDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.UserInfoDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.ResourceQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.RoleQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.PmResourceListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UcPmRoleListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.UserMenuDtoResult;
import com.newhope.moneyfeed.user.api.enums.platform.SysSourceTypeEnums;
import com.newhope.moneyfeed.user.api.exbean.platform.UcPmResourceExModel;
import com.newhope.openapi.api.vo.request.user.EmployeeInfoReq;
import com.newhope.openapi.api.vo.request.user.LoginByPassReq;
import com.newhope.openapi.api.vo.request.user.LoginBySmscodeReq;
import com.newhope.openapi.api.vo.request.user.ModifyEmployeeInfoReq;
import com.newhope.openapi.api.vo.request.user.ModifyUserInfoBySmscodeReq;
import com.newhope.openapi.api.vo.request.user.ResetPassWordReq;
import com.newhope.openapi.api.vo.response.user.BmRoleResult;
import com.newhope.openapi.api.vo.response.user.BmUserListResult;
import com.newhope.openapi.api.vo.response.user.BmUserMenuResult;
import com.newhope.openapi.api.vo.response.user.BmUserResult;
import com.newhope.openapi.api.vo.response.user.ClientRoleListResult;
import com.newhope.openapi.api.vo.response.user.ClientRoleResult;
import com.newhope.openapi.api.vo.response.user.PmResourceResult;
import com.newhope.openapi.biz.rpc.feign.user.RoleFeignClient;
import com.newhope.openapi.biz.rpc.feign.user.BmUserFeignClient;

@Service
public class BmUserService {

    private Logger logger = Logger.getLogger(BmUserService.class);

	@Autowired
	RSessionCache rSessionCache;
	
	@Autowired
	BmUserFeignClient userFeignClient;
	
	@Autowired
	RoleFeignClient roleFeignClient;
	public BmUserResult loginBySmscode(final LoginBySmscodeReq requestBody) {
		BmUserResult result = new BmUserResult();
		LoginBySmscodeDtoReq request = new LoginBySmscodeDtoReq();
		BeanUtils.copyProperties(requestBody, request);
		BaseResponse<UcBmUserDtoResult> feignResp = userFeignClient.loginBySmscode(request);
		
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		if(!feignResp.isSuccess()) {
			return result;
		}
		
		BeanUtils.copyProperties(feignResp.getData(), result);
		List<UcPmRoleDtoResult> roleDtoList = feignResp.getData().getRoleDtoList();
		List<BmRoleResult> roleList = new ArrayList<>();
		BmRoleResult role = null;
		for (UcPmRoleDtoResult ucBmRoleDtoResult : roleDtoList) {
			role = new BmRoleResult();
			BeanUtils.copyProperties(ucBmRoleDtoResult, role);
			role.setRoleCode(ucBmRoleDtoResult.getCode());
			roleList.add(role);
		}
		result.setRoleList(roleList);
		
		// 设置用户缓存
		rSessionCache.setUserInfo(ContextHolderUtil.getResponse(), feignResp.getData(), 
				feignResp.getData().getId() + "", SysSourceTypeEnums.BUSINESS.name());
		return result;
	}
	
	public BmUserMenuResult getUserMenu(Long userId, String mobile, List<Long> roleIds){
		BmUserMenuResult result = new BmUserMenuResult();
		UserInfoDtoReq userInfoDtoReq = new UserInfoDtoReq(userId, mobile, roleIds);
		BaseResponse<UserMenuDtoResult> feignResp = userFeignClient.getUserMenu(userInfoDtoReq);
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		if (null == feignResp.getData()) {
            return result;
        }
		BeanUtils.copyProperties(feignResp.getData(), result);
		return result;
	}
	
	public BmUserResult loginByPass(LoginByPassReq requestBody) {
		BmUserResult result = new BmUserResult();
		LoginByPassDtoReq request = new LoginByPassDtoReq();
		BeanUtils.copyProperties(requestBody, request);
		BaseResponse<UcBmUserDtoResult> feignResp = userFeignClient.loginByPass(request);
		
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		if(!feignResp.isSuccess()) {
			return result;
		}
		
		BeanUtils.copyProperties(feignResp.getData(), result);
		List<UcPmRoleDtoResult> roleDtoList = feignResp.getData().getRoleDtoList();
		List<BmRoleResult> roleList = new ArrayList<>();
		BmRoleResult role = null;
		for (UcPmRoleDtoResult ucBmRoleDtoResult : roleDtoList) {
			role = new BmRoleResult();
			BeanUtils.copyProperties(ucBmRoleDtoResult, role);
			role.setRoleCode(ucBmRoleDtoResult.getCode());
			roleList.add(role);
		}
		result.setRoleList(roleList);
		
		// 设置用户缓存
		rSessionCache.setUserInfo(ContextHolderUtil.getResponse(), feignResp.getData(), 
				feignResp.getData().getId() + "", SysSourceTypeEnums.BUSINESS.name());
		return result;
	}
	
	public Result resetPassWord(ResetPassWordReq requestBody) {
		Result result = new Result();
		
		ResetPassWordDtoReq request = new ResetPassWordDtoReq();
		BeanUtils.copyProperties(requestBody, request);
		BaseResponse<DtoResult> feignResp = userFeignClient.resetPassWord(request);
		BeanUtils.copyProperties(feignResp, result);
		return result;
	}

	public Result modifyUserInfo(ModifyUserInfoBySmscodeReq requestBody) {
		Result result = new Result();
		ModifyUserInfoBySmscodeDtoReq request = new ModifyUserInfoBySmscodeDtoReq();
		BeanUtils.copyProperties(requestBody, request);
		BaseResponse<DtoResult> feignResp = userFeignClient.modifyBmUserInfo(request);
		BeanUtils.copyProperties(feignResp, result);
		return result;
	}

	public BmUserListResult queryBmEmployeeInfoList(EmployeeInfoReq userInfoDtoReq) {
		BmUserListResult result = new BmUserListResult();
		EmployeeInfoDtoReq request = new EmployeeInfoDtoReq();
		UcBmUserDtoResult userInfo = (UcBmUserDtoResult)rSessionCache.getUserInfo();
		userInfoDtoReq.setShopId(userInfo.getShopId());
		BeanUtils.copyProperties(userInfoDtoReq, request);
		BaseResponse<UcBmUserListDtoResult> feignResp = userFeignClient.queryBmEmployeeInfoList(request);

		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		if(!feignResp.isSuccess()) {
			return result;
		}
		List<UcBmUserDtoResult> userList = feignResp.getData().getUserList();
		List<BmUserResult> resultUserList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(userList)) {
			for (UcBmUserDtoResult dtoResult : userList) {
				BmUserResult bmUserResult = new BmUserResult();
				BeanUtils.copyProperties(dtoResult, bmUserResult);
				List<BmRoleResult> roleList = new ArrayList<>();
				BmRoleResult role = null;
				List<UcPmRoleDtoResult> roleDtoList = dtoResult.getRoleDtoList();
				for (UcPmRoleDtoResult ucBmRoleDtoResult : roleDtoList) {
					if (ucBmRoleDtoResult != null) {
						role = new BmRoleResult();
						BeanUtils.copyProperties(ucBmRoleDtoResult, role);
						role.setRoleCode(ucBmRoleDtoResult.getCode());
						roleList.add(role);
					}
				}
				bmUserResult.setRoleList(roleList);
				resultUserList.add(bmUserResult);
			}
			result.setPage(feignResp.getData().getPage());
			result.setTotalCount(feignResp.getData().getTotalCount());
			result.setTotalPage(feignResp.getData().getTotalPage());
			result.setUserList(resultUserList);
		}
		return result;
	}

	public BmUserResult queryBmEmployeeInfo(Long userId) {
		BmUserResult result = new BmUserResult();
		BaseResponse<UcBmUserDtoResult> feignResp = userFeignClient.queryBmEmployeeInfo(userId);
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		if(!feignResp.isSuccess()) {
			return result;
		}

		BeanUtils.copyProperties(feignResp.getData(), result);
		List<UcPmRoleDtoResult> roleDtoList = feignResp.getData().getRoleDtoList();
		List<BmRoleResult> roleList = new ArrayList<>();
		BmRoleResult role = null;
		for (UcPmRoleDtoResult ucBmRoleDtoResult : roleDtoList) {
			if(ucBmRoleDtoResult!=null) {
				role = new BmRoleResult();
				BeanUtils.copyProperties(ucBmRoleDtoResult, role);
				role.setRoleCode(ucBmRoleDtoResult.getCode());
				roleList.add(role);
			}
		}
		result.setRoleList(roleList);
		return result;
	}

	public Result addBmEmployeeInfo(ModifyEmployeeInfoReq userInfoDtoReq) {
		Result result = new Result();
		ModifyEmployeeInfoDtoReq request = new ModifyEmployeeInfoDtoReq();
		BeanUtils.copyProperties(userInfoDtoReq, request);
		BaseResponse<DtoResult> feignResp = userFeignClient.addBmEmployeeInfo(request);
		BeanUtils.copyProperties(feignResp, result);
		return result;
	}

	public Result modifyBmEmployeeInfo(ModifyEmployeeInfoReq userInfoDtoReq) {
		Result result = new Result();
		ModifyEmployeeInfoDtoReq request = new ModifyEmployeeInfoDtoReq();
		BeanUtils.copyProperties(userInfoDtoReq, request);
		BaseResponse<DtoResult> feignResp = userFeignClient.modifyBmEmployeeInfo(request);
		BeanUtils.copyProperties(feignResp, result);
		return result;
	}

    public void exportBmEmployeeInfoList(EmployeeInfoReq req, HttpServletResponse response) {
        req.setPage(null);
        req.setPageSize(null);
        List<Long> roleIds = new ArrayList<>();
        if(req.getRoleIdStr()!=null){
            String[] split = req.getRoleIdStr().split(",");
            for (String roleId :split) {
                roleIds.add(Long.valueOf(roleId));
            }
            req.setRoleIds(roleIds);
        }
        BmUserListResult result = queryBmEmployeeInfoList(req);
        if (ResultCode.SUCCESS.getCode().equals(result.getCode()) && CollectionUtils.isNotEmpty(result.getUserList())) {{
            List<BmUserResult> bmUserResultList = result.getUserList();
            List<Map<String, Object>> content = new ArrayList<>();
            Map<String, Object> map;
            for (BmUserResult data : bmUserResultList) {
                map = new HashMap<>();
                map.put("id",data.getId() == null ? CommonConstant.BLANK_STRING :  "\t" + data.getId() );
                map.put("name",data.getName() == null ? CommonConstant.BLANK_STRING : "\t" + data.getName());
                map.put("mobile",data.getMobile() == null ? CommonConstant.BLANK_STRING : "\"" + data.getMobile() + "\"");
                map.put("status",data.getStatus() == null ? CommonConstant.BLANK_STRING : "\"" + (data.getStatus()?"正常":"离职") + "\"");
                List<BmRoleResult> roleList = data.getRoleList();
                if(CollectionUtils.isEmpty(roleList)) {
                    map.put("role",CommonConstant.BLANK_STRING);
                }else {
                    StringBuffer roles = new StringBuffer();
                    for (int i = 0; i < roleList.size(); i++) {
                        if(i==roleList.size()-1) {
                            roles.append(roleList.get(i).getName());
                        }else {
                            roles.append(roleList.get(i).getName()+",");
                        }
                    }
                    map.put("role",roles.toString());
                }
                content.add(map);
            }
            // map映射key
            String mapKey = "id,name,mobile,status,role";

            try {
                final OutputStream os = response.getOutputStream();
                ExportUtil.responseSetProperties(StatisticsConstant.BM_EXPORT_SHOP, response);
                ExportUtil.doExport(content, StatisticsConstant.COLUMNS_BM_EXPORT_SHOP,
                        StatisticsConstant.CSV_COLUMN_SEPARATOR, StatisticsConstant.CSV_RN, mapKey, os);
            } catch (Exception e) {
                logger.error("[BmUserOpenController.exportBmEmployeeInfoList]:下载文件异常", e);
            }
        }}
    }
    
    public ClientRoleListResult getClientRole(){
    	ClientRoleListResult result = new ClientRoleListResult();
    	RoleQueryDtoReq roleParam = new RoleQueryDtoReq();
    	roleParam.setEnable(true);
    	roleParam.setSourceType(SysSourceTypeEnums.BUSINESS.name());
    	roleParam.setPage(null);
    	roleParam.setPageSize(null);
    	BaseResponse<UcPmRoleListDtoResult> feignResult = roleFeignClient.getRole(roleParam);
    	if(feignResult.isSuccess()&&feignResult.getData()!=null
    			&&CollectionUtils.isNotEmpty(feignResult.getData().getRoleList())){
    		for(UcPmRoleModel role : feignResult.getData().getRoleList()){
    			ClientRoleResult roleResult = new ClientRoleResult();
    			roleResult.setCode(role.getCode());
    			roleResult.setComment(role.getComment());
    			roleResult.setId(role.getId());
    			roleResult.setName(role.getName());
    			result.getRoleList().add(roleResult);
    		}
    	}
    	result.setCode(feignResult.getCode());
    	result.setMsg(feignResult.getMsg());
    	return result;
    }
    
    public PmResourceResult getRoleResource(Long roleId){
		PmResourceResult result = new PmResourceResult();
		result.setName("根目录");
		result.setId(0L);
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		ResourceQueryDtoReq resourceParam = new ResourceQueryDtoReq();
		resourceParam.setEnable(true);
		resourceParam.setSourceType(SysSourceTypeEnums.BUSINESS.name());
		BaseResponse<PmResourceListDtoResult> feignResult = roleFeignClient.getResourceByRole(SysSourceTypeEnums.BUSINESS.name(),roleId);
		if(feignResult.isSuccess()){
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
}
