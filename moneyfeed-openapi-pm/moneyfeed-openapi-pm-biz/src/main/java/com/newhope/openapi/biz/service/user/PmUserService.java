package com.newhope.openapi.biz.service.user;

import java.io.OutputStream;
import java.util.*;

import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.common.constant.StatisticsConstant;
import com.newhope.moneyfeed.common.helper.DateUtils;
import com.newhope.moneyfeed.common.util.ExportUtil;
import com.newhope.moneyfeed.user.api.constant.CommonConstant;
import com.newhope.moneyfeed.user.api.dto.request.businessmanage.*;
import com.newhope.moneyfeed.user.api.dto.request.platform.LabelDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.LabelModifyDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.platform.LabelPageDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.*;
import com.newhope.openapi.api.enums.LabelCreateTypeEnums;
import com.newhope.openapi.api.vo.request.user.*;
import com.newhope.openapi.api.vo.response.label.LabelListResult;
import com.newhope.openapi.api.vo.response.label.LabelResult;
import com.newhope.openapi.api.vo.response.user.*;
import com.newhope.openapi.biz.rpc.feign.user.BmUserFeignClient;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.api.vo.response.Result;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.util.ContextHolderUtil;
import com.newhope.moneyfeed.user.api.enums.platform.SysSourceTypeEnums;
import com.newhope.openapi.biz.rpc.feign.user.PmUserFeignClient;

import javax.servlet.http.HttpServletResponse;

@Service
public class PmUserService {
	private Logger logger = Logger.getLogger(PmUserService.class);
	@Autowired
	RSessionCache rSessionCache;
	
	@Autowired
	BmUserFeignClient bmUserFeignClient;

	@Autowired
	PmUserFeignClient pmUserFeignClient;
	
	public PmUserMenuResult getUserMenu(Long userId, String mobile, List<Long> roleIds){
		PmUserMenuResult result = new PmUserMenuResult();
		UserInfoDtoReq userInfoDtoReq = new UserInfoDtoReq(userId, mobile, roleIds);
		BaseResponse<UserMenuDtoResult> feignResp = pmUserFeignClient.getUserMenu(userInfoDtoReq);
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		if (null == feignResp.getData()) {
            return result;
        }
		BeanUtils.copyProperties(feignResp.getData(), result);
		return result;
	}
	
	public PmUserResult loginByPass(LoginByPassReq requestBody) {
		PmUserResult result = new PmUserResult();
		LoginByPassDtoReq request = new LoginByPassDtoReq();
		BeanUtils.copyProperties(requestBody, request);
		BaseResponse<UcPmUserDtoResult> feignResp = pmUserFeignClient.loginByPass(request);
		
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		
		if(!feignResp.isSuccess()) {
			return result;
		}
		
		BeanUtils.copyProperties(feignResp.getData(), result);
		List<UcPmRoleDtoResult> roleDtoList = feignResp.getData().getRoleDtoList();
		List<PmRoleIntroResult> roleList = new ArrayList<>();
		PmRoleIntroResult role = null;
		for (UcPmRoleDtoResult ucBmRoleDtoResult : roleDtoList) {
			role = new PmRoleIntroResult();
			BeanUtils.copyProperties(ucBmRoleDtoResult, role);
			roleList.add(role);
		}
		result.setRoleList(roleList);
		
		// 设置用户缓存
		rSessionCache.setUserInfo(ContextHolderUtil.getResponse(), feignResp.getData(), 
				feignResp.getData().getId() + "", SysSourceTypeEnums.PLATFORM.name());
		return result;
	}
	
	public Result resetPassWord(ResetPassWordReq requestBody) {
		Result result = new Result();
		
		ResetPassWordDtoReq request = new ResetPassWordDtoReq();
		BeanUtils.copyProperties(requestBody, request);
		BaseResponse<DtoResult> feignResp = pmUserFeignClient.resetPassWord(request);
		BeanUtils.copyProperties(feignResp, result);
		return result;
	}

    public Result modifyUserInfo(ModifyUserInfoBySmscodeReq requestBody) {
		Result result = new Result();
		ModifyUserInfoBySmscodeDtoReq request = new ModifyUserInfoBySmscodeDtoReq();
		BeanUtils.copyProperties(requestBody, request);
		BaseResponse<DtoResult> feignResp = pmUserFeignClient.modifyPmUserInfo(request);
		BeanUtils.copyProperties(feignResp, result);
		return result;
    }

    /**
	 * 创建标签
     * @Author: yyq
     * @Date  :Created in  2018/12/25 10:52
     * @Param :
     */
	public Result createLabel(LabelReq requestBody) {
		Result result = new Result();
		LabelDtoReq request = new LabelDtoReq();
		BeanUtils.copyProperties(requestBody, request);
		BaseResponse<DtoResult> feignResp = pmUserFeignClient.createLabel(request);
		BeanUtils.copyProperties(feignResp, result);
		return result;
	}
	/**
	 *
	 * 标签列表
	 * @Author: yyq
	 * @Date  :Created in  2018/12/25 10:52
	 * @Param :
	 */
	public UcPmLabelListResult labelList(LabelPageReq requestBody) {
		UcPmLabelListResult result = new UcPmLabelListResult();
		LabelPageDtoReq request = new LabelPageDtoReq();
		BeanUtils.copyProperties(requestBody, request);
		BaseResponse<UcPmLabelListDtoResult> feignResp = pmUserFeignClient.labelList(request);
		BeanUtils.copyProperties(feignResp.getData(), result);
		return result;
	}

	/**
	 * 修改标签
	 * @Author: yyq
	 * @Date  :Created in  2018/12/25 10:52
	 * @Param :
	 */
	public Result modifyLabel(LabelModifyReq requestBody) {
		Result result = new Result();
		LabelModifyDtoReq request = new LabelModifyDtoReq();
		BeanUtils.copyProperties(requestBody, request);
		BaseResponse<DtoResult> feignResp = pmUserFeignClient.modifyLabel(request);
		BeanUtils.copyProperties(feignResp, result);
		return result;
	}

    public LabelListResult manualLabelList() {

        LabelListResult result = new LabelListResult();
        LabelPageDtoReq request = new LabelPageDtoReq();
        request.setCreateType(LabelCreateTypeEnums.MANUAL_LABEL.getDesc());
		request.setEnable(true);
        BaseResponse<UcPmLabelListDtoResult> feignResp = pmUserFeignClient.labelList(request);
        if(CollectionUtils.isNotEmpty(feignResp.getData().getDataList())){
            List list = new ArrayList();
            for (UcPmLabelDtoResult dtoResult: feignResp.getData().getDataList()){
                LabelResult labelResult = new LabelResult();
                BeanUtils.copyProperties(dtoResult,labelResult);
                list.add(labelResult);
            }
            result.setDataList(list);
        }
        result.setCode(feignResp.getCode());
        result.setMsg(feignResp.getMsg());
        return result;
    }

	public Result exportLabel(LabelPageReq requestBody, HttpServletResponse response) {
		requestBody.setPage(null);
		requestBody.setPageSize(null);
		UcPmLabelListResult result =  labelList(requestBody);
		if (ResultCode.SUCCESS.getCode().equals(result.getCode()) && CollectionUtils.isNotEmpty(result.getDataList()) ) {{
			List<UcPmLabelResult> dataList = result.getDataList();
			List<Map<String, Object>> content = new ArrayList<>();
			Map<String, Object> map;
			for (Object dataResult : dataList) {

				UcPmLabelDtoResult data = new UcPmLabelDtoResult();
				BeanUtils.copyProperties(dataResult,data);
				map = new HashMap<>();
				map.put("name",StringUtils.isEmpty(data.getName()) ? CommonConstant.BLANK_STRING :   data.getName() );
				map.put("targetType",StringUtils.isEmpty(data.getTargetType())  ? CommonConstant.BLANK_STRING : data.getTargetType());
				map.put("createType",StringUtils.isEmpty(data.getCreateType())? CommonConstant.BLANK_STRING : data.getCreateType());
				map.put("count",data.getCount() == null ? CommonConstant.BLANK_STRING : data.getCount());
				map.put("comment",StringUtils.isEmpty(data.getComment()) ? CommonConstant.BLANK_STRING : data.getComment());
				map.put("gmtCreated",data.getGmtCreated()==null ? CommonConstant.BLANK_STRING : DateUtils.formatDate(data.getGmtCreated()));
				content.add(map);
			}

			// map映射key
			String mapKey = "name,targetType,createType,count,comment,gmtCreated";
			try {
				final OutputStream os = response.getOutputStream();
				ExportUtil.responseSetProperties(StatisticsConstant.PM_EXPORT_LABEL, response);
				ExportUtil.doExport(content, StatisticsConstant.COLUMNS_PM_EXPORT_LABEL,
						StatisticsConstant.CSV_COLUMN_SEPARATOR, StatisticsConstant.CSV_RN, mapKey, os);
			} catch (Exception e) {
				logger.error("[PmUserService.exportLabel]:下载文件异常", e);
			}
		}}

		return Result.success();

	}

    public PmUserListResult queryPmEmployeeInfoList(EmployeeInfoReq userInfoDtoReq) {
		PmUserListResult result = new PmUserListResult();
		EmployeeInfoDtoReq request = new EmployeeInfoDtoReq();
		BeanUtils.copyProperties(userInfoDtoReq, request);
		BaseResponse<UcBmUserListDtoResult> feignResp = bmUserFeignClient.queryBmEmployeeInfoList(request);

		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		if(!feignResp.isSuccess()) {
			return result;
		}
		List<UcBmUserDtoResult> userList = feignResp.getData().getUserList();
		List<PmUserResult> resultUserList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(userList)){
			for (UcBmUserDtoResult dtoResult : userList) {
				PmUserResult pmUserResult = new PmUserResult();
				BeanUtils.copyProperties(dtoResult,pmUserResult);
				List<PmRoleIntroResult> roleList = new ArrayList<>();
				PmRoleIntroResult role = null;
				List<UcPmRoleDtoResult> roleDtoList = dtoResult.getRoleDtoList();
				for (UcPmRoleDtoResult ucBmRoleDtoResult : roleDtoList) {
					if(ucBmRoleDtoResult!=null) {
						role = new PmRoleIntroResult();
						BeanUtils.copyProperties(ucBmRoleDtoResult, role);
						roleList.add(role);
					}
				}
				pmUserResult.setRoleList(roleList);
				resultUserList.add(pmUserResult);
			}
			result.setPage(feignResp.getData().getPage());
			result.setTotalCount(feignResp.getData().getTotalCount());
			result.setTotalPage(feignResp.getData().getTotalPage());
			result.setUserList(resultUserList);
		}
		return result;
    }

	public PmUserResult queryPmEmployeeInfo(Long userId) {
		PmUserResult result = new PmUserResult();
		BaseResponse<UcBmUserDtoResult> feignResp = bmUserFeignClient.queryBmEmployeeInfo(userId);
		result.setCode(feignResp.getCode());
		result.setMsg(feignResp.getMsg());
		if(!feignResp.isSuccess()) {
			return result;
		}

		BeanUtils.copyProperties(feignResp.getData(), result);
		List<UcPmRoleDtoResult> roleDtoList = feignResp.getData().getRoleDtoList();
		List<PmRoleIntroResult> roleList = new ArrayList<>();
		PmRoleIntroResult role = null;
		for (UcPmRoleDtoResult ucBmRoleDtoResult : roleDtoList) {
			if(ucBmRoleDtoResult!=null) {
				role = new PmRoleIntroResult();
				BeanUtils.copyProperties(ucBmRoleDtoResult, role);
				roleList.add(role);
			}
		}
		result.setRoleList(roleList);
		return result;
	}

	public Result modifyPmEmployeeInfo(ModifyEmployeeInfoReq userInfoDtoReq) {
		Result result = new Result();
		ModifyEmployeeInfoDtoReq request = new ModifyEmployeeInfoDtoReq();
		BeanUtils.copyProperties(userInfoDtoReq, request);
		BaseResponse<DtoResult> feignResp = bmUserFeignClient.modifyBmEmployeeInfo(request);
		BeanUtils.copyProperties(feignResp, result);
		return result;
	}

	public void exportPmEmployeeInfoList(EmployeeInfoReq req, HttpServletResponse response) {
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
		PmUserListResult result = queryPmEmployeeInfoList(req);
		if (ResultCode.SUCCESS.getCode().equals(result.getCode()) && CollectionUtils.isNotEmpty(result.getUserList())) {{
			List<PmUserResult> pmUserResultList = result.getUserList();
			List<Map<String, Object>> content = new ArrayList<>();
			Map<String, Object> map;
			for (PmUserResult data : pmUserResultList) {
				map = new HashMap<>();
				map.put("id",data.getId() == null ? CommonConstant.BLANK_STRING :  "\t" + data.getId() );
				map.put("name",data.getName() == null ? CommonConstant.BLANK_STRING : "\t" + data.getName());
				map.put("mobile",data.getMobile() == null ? CommonConstant.BLANK_STRING : "\"" + data.getMobile() + "\"");
				map.put("status",data.getStatus() == null ? CommonConstant.BLANK_STRING : "\"" + (data.getStatus()?"正常":"离职") + "\"");
				map.put("shop",data.getShopName() == null ? CommonConstant.BLANK_STRING : "\"" + data.getShopName() + "\"");
				List<PmRoleIntroResult> roleList = data.getRoleList();
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
			String mapKey = "id,name,mobile,status,shop,role";

			try {
				final OutputStream os = response.getOutputStream();
				ExportUtil.responseSetProperties(StatisticsConstant.PM_BM_EXPORT_SHOP, response);
				ExportUtil.doExport(content, StatisticsConstant.PM_COLUMNS_BM_EXPORT_SHOP,
						StatisticsConstant.CSV_COLUMN_SEPARATOR, StatisticsConstant.CSV_RN, mapKey, os);
			} catch (Exception e) {
				logger.error("[PmUserService.exportPmEmployeeInfoList]:下载文件异常", e);
			}
		}}
	}
}
