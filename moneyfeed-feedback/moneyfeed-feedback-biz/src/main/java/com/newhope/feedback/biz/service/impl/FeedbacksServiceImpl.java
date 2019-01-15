package com.newhope.feedback.biz.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.internal.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.newhope.feedback.biz.common.constant.Constants;
import com.newhope.feedback.biz.common.utils.DateUtil;
import com.newhope.feedback.biz.rpc.feign.user.ShopFeignClient;
import com.newhope.feedback.biz.rpc.feign.user.UserFeignClient;
import com.newhope.feedback.biz.rpc.feign.wechat.WechatMsgFeignClient;
import com.newhope.feedback.biz.service.FeedbacksService;
import com.newhope.moneyfeed.api.dto.request.wechat.WechatMsgDtoReq;
import com.newhope.moneyfeed.api.dto.response.DtoResult;
import com.newhope.moneyfeed.api.enums.ResultCode;
import com.newhope.moneyfeed.api.enums.wechat.WechatMsgTypeEnums;
import com.newhope.moneyfeed.api.vo.base.BaseResponse;
import com.newhope.moneyfeed.common.cache.RSessionCache;
import com.newhope.moneyfeed.common.constant.CommonConstant;
import com.newhope.moneyfeed.feedback.api.bean.CsUserFeedbackSolvesModel;
import com.newhope.moneyfeed.feedback.api.bean.CsUserFeedbacksModel;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackDistributedDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackPageDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.FeedbackStatusDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.ShopPageDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.request.UserPageDtoReq;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackDetailDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackItemDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackShopListDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackShopListDtoResult.ShopModel;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackUserListDtoResult;
import com.newhope.moneyfeed.feedback.api.dto.response.FeedbackUserListDtoResult.UserModel;
import com.newhope.moneyfeed.feedback.api.enums.ChannelEnum;
import com.newhope.moneyfeed.feedback.api.enums.DatastatusCodeEnums;
import com.newhope.moneyfeed.feedback.api.enums.FeedbackStatusEnum;
import com.newhope.moneyfeed.feedback.api.enums.FeedbackTypeEnum;
import com.newhope.moneyfeed.feedback.api.enums.MessageLabelEnum;
import com.newhope.moneyfeed.feedback.api.enums.MessageLabelTypeEnum;
import com.newhope.moneyfeed.feedback.api.exbean.CsUserFeedbacksExModel;
import com.newhope.moneyfeed.feedback.dal.CsUserFeedbackSolvesDao;
import com.newhope.moneyfeed.feedback.dal.CsUserFeedbacksDao;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserModel;
import com.newhope.moneyfeed.user.api.bean.client.UcClientUserThirdAccountModel;
import com.newhope.moneyfeed.user.api.bean.client.UcShopModel;
import com.newhope.moneyfeed.user.api.dto.request.client.ClientUserQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.request.client.ShopQueryDtoReq;
import com.newhope.moneyfeed.user.api.dto.response.businessmanage.UcBmUserDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserCacheDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserListDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ClientUserThirdAccountDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.CustomerExDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.client.ShopDtoListResult;
import com.newhope.moneyfeed.user.api.dto.response.client.UserVisitShopDtoResult;
import com.newhope.moneyfeed.user.api.dto.response.platform.*;
import com.newhope.moneyfeed.user.api.enums.businessmanage.BmRoleCodeEnums;

@Service
public class FeedbacksServiceImpl implements FeedbacksService{
	
	private static final Logger logger = LoggerFactory.getLogger(FeedbacksServiceImpl.class);
	
	private static final String DH = ",";
	
	@Autowired
	CsUserFeedbacksDao csUserFeedbacksDao;
	
	@Autowired
	CsUserFeedbackSolvesDao csUserFeedbackSolvesDao;
	
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    
	@Autowired
	RSessionCache rSessionCache;
	
	@Autowired
	UserFeignClient userFeignClient;
	
	@Autowired
	ShopFeignClient shopFeignClient;
	
	@Autowired
	WechatMsgFeignClient wechatMsgFeignClient;
	
//	@Value("${feedback.templateid}")
//	private String templateId;
	
//	@Value("${feedback.customer.phone}")
//	private String phone;
	
	/**
	 * 售后留言新增
	 * @param req
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public DtoResult feedbackReqAdd(FeedbackDtoReq req) {
		DtoResult result = new DtoResult();
		ClientUserCacheDtoResult clientUserCacheDtoResult = null;
		Object obj = rSessionCache.getUserInfo();  //前端缓存
		if (obj == null) {
			if(ChannelEnum.MALL.getCode().equals(req.getChannel())){
				result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
				result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
				return result;
			}
		}else{
			clientUserCacheDtoResult = (ClientUserCacheDtoResult) obj;
		}
		
		//生成留言编号
		String feedbackNumber = generateFeedbackNumber(req.getFeedbackTime());
		//保存
		CsUserFeedbacksModel csUserFeedbacksModel = buildCsUserFeedbacksModel(req , clientUserCacheDtoResult);
		csUserFeedbacksModel.setFeedbackNumber(feedbackNumber); //流水号
		
		csUserFeedbacksDao.insertSingle(csUserFeedbacksModel);
		
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		
		return result;
	}
	
	
	/**
	 * 
	 * 留言编号
	 * @return
	 */
	public String generateFeedbackNumber(Date feedbackTime){
		String dateStr = DateUtil.getDateStr(new Date(), DateUtil.YYMMDD);
		//查询最大编号
		StringBuffer feedbackNumber =new StringBuffer();
        String key = feedbackNumber.append(Constants.LY).append(dateStr).toString();
        String flowNo = getFlowNoByRedis(key);
        feedbackNumber.append(flowNo);
        logger.info("-------------上市申请单据号为[{}]",feedbackNumber.toString());
		
		return feedbackNumber.toString();
	}
	
	 /**
     * REDIS生成不重复的流水号
     * @param key
     * @return
     */
    private String getFlowNoByRedis(String key){
        Long sn = redisTemplate.boundValueOps("moneyfeed-feedback:feedbackNumber:" + key).increment(1);
        if (sn == 1) {
            redisTemplate.boundValueOps(key).expireAt(getEndOfTheDay());
        }
        return String.format("%04d", sn);
    }

    /**
     * 获取当天的最后一秒
     * @return
     */
    private Date getEndOfTheDay(){
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDate.now().atTime(23, 59, 59);
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return  Date.from(zdt.toInstant());
    }
    
    /**
     * 根据用户id查询用户信息 （只包含用户信息）
     * @param userId
     * @return
     */
    public UcClientUserModel queryUserInfos(Long userId){
    	UcClientUserModel ucClientUserModel = null;
    	ClientUserQueryDtoReq userDtoReq = new ClientUserQueryDtoReq();
    	userDtoReq.setId(userId);
		BaseResponse<ClientUserListDtoResult> queryUserInfos = userFeignClient.queryUserInfos(userDtoReq);
		if(queryUserInfos != null && queryUserInfos.isSuccess() && queryUserInfos.getData() != null ){
			List<UcClientUserModel> users = queryUserInfos.getData().getUsers();
			if(!CollectionUtils.isEmpty(users)){
			    ucClientUserModel = users.get(0);
				return ucClientUserModel;
			}
		}
		return ucClientUserModel;
    }
    
    /**
     * 获取商户信息
     * @param shopId
     * @return
     */
    public UcShopModel queryShop(Long shopId){
    	UcShopModel ucShopModel = null;
    	ShopQueryDtoReq userDtoReq = new ShopQueryDtoReq();
    	userDtoReq.setId(shopId);
		BaseResponse<ShopDtoListResult> shop = shopFeignClient.queryShop(userDtoReq);
    	if(shop != null && shop.isSuccess() && shop.getData() != null ){
    		List<UcShopModel> ucShopModelList = shop.getData().getShops();
    		if(!CollectionUtils.isEmpty(ucShopModelList)){
    			ucShopModel = ucShopModelList.get(0);
    			return ucShopModel;
    		}
    	}
    	return ucShopModel;
    }
    
    /**
     * 包含用户和客户信息
     * @param userId
     * @return
     */
    public ClientUserCacheDtoResult assemblyUserCache(Long userId){
    	ClientUserCacheDtoResult clientUserCacheDtoResult = null;
		BaseResponse<ClientUserCacheDtoResult> queryUserInfos = userFeignClient.assemblyUserCache(userId);
		if(queryUserInfos != null && queryUserInfos.isSuccess() && queryUserInfos.getData() != null ){
			clientUserCacheDtoResult = queryUserInfos.getData();
			return clientUserCacheDtoResult;
		}
		return clientUserCacheDtoResult;
    }
    
    /**
     * 根据数据权限过滤状态
     * @param req
     * @param obj
     */
    public void validateFeedStatus(FeedbackPageDtoReq req , Object obj){
    	List<UcPmRoleDtoResult> roleDtoList = null;
    	if(obj instanceof UcBmUserDtoResult){
    		UcBmUserDtoResult user = (UcBmUserDtoResult) obj;
    		roleDtoList = user.getRoleDtoList();
    	}else if(obj instanceof UcPmUserDtoResult){
    		UcPmUserDtoResult user = (UcPmUserDtoResult) obj;
    		roleDtoList = user.getRoleDtoList();
    	}
    	boolean isAdmin = false;
    	//TODO 数据权限
    	if(!CollectionUtils.isEmpty(roleDtoList)){
    		for (UcPmRoleDtoResult ucBmRoleDtoResult : roleDtoList) {
				if(BmRoleCodeEnums.ADMIN.getValue().equals(ucBmRoleDtoResult.getCode())){
					isAdmin = true;
					break;
				}
			}
    	}
    	List<String> feedbackStatusList = req.getFeedbackStatusList();
    	List<String> allCanStatus = new ArrayList<String>();
		if(!isAdmin){
			if(!CollectionUtils.isEmpty(feedbackStatusList)){
				for (String string : feedbackStatusList) {
					if(FeedbackStatusEnum.PENDING.getCode().equals(string) 
							|| FeedbackStatusEnum.SHOP_CLOSED.getCode().equals(string)){
						allCanStatus.add(string);
					}
				}
			}
			if(CollectionUtils.isEmpty(allCanStatus)){
				allCanStatus.add(FeedbackStatusEnum.PENDING.getCode());
				allCanStatus.add(FeedbackStatusEnum.SHOP_CLOSED.getCode());
			}
			req.setFeedbackStatusList(allCanStatus);
		}
    }
	
	/**
	 * 客户留言列表查询
	 * @param req
	 */
	public FeedbackDtoResult feedbackList(FeedbackPageDtoReq req) {
		FeedbackDtoResult result = new FeedbackDtoResult();
		//TODO 获取客户信息
		Object obj = rSessionCache.getUserInfo();
    	if (obj == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return result;
		}
    	
    	//数据权限控制列表状态查询
//    	validateFeedStatus(req , obj);
    	
		PageBounds pageBounds = new PageBounds(req.getPage().intValue(), req.getPageSize(), Order.formString(""), false);
		List<CsUserFeedbacksExModel> list = csUserFeedbacksDao.queryFeedbackList(req, pageBounds);
		Integer totalCount = csUserFeedbacksDao.queryFeedbackCount(req);
		
		Paginator paginator = new Paginator(req.getPage().intValue(), req.getPageSize(), totalCount);
		PageList<CsUserFeedbacksExModel> pageList = new PageList<CsUserFeedbacksExModel>(paginator);
		pageList.addAll(list);
		
		List<FeedbackItemDtoResult>  feedbackItemList = new ArrayList<FeedbackItemDtoResult>();
		if(!CollectionUtils.isEmpty(pageList)){
			//列表查询参数封装
			wrapFeedbackItemList(pageList , feedbackItemList);
			
			result.setPage(Long.parseLong(pageList.getPaginator().getPage() + ""));
			result.setTotalCount(Long.parseLong(pageList.getPaginator().getTotalCount() + ""));
			result.setTotalPage(Long.parseLong(pageList.getPaginator().getTotalPages() + ""));
			result.setFeedbackItemList(feedbackItemList);
		}else{
			result.setPage(0L);
			result.setTotalCount(0L);
			result.setTotalPage(0L);
			result.setFeedbackItemList(feedbackItemList);	
		}
		
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		return result;
	}
	
	/**
	 * 列表查询封装
	 * @param pageList
	 * @param feedbackItemList
	 */
	public void wrapFeedbackItemList(PageList<CsUserFeedbacksExModel> pageList , List<FeedbackItemDtoResult>  feedbackItemList){

		SimpleDateFormat datetimeFormat = new SimpleDateFormat( "yyyy-MM-dd" );
		Map<Long , ClientUserCacheDtoResult> userIdToDtoResultMap = new HashMap<Long , ClientUserCacheDtoResult>(); //用户信息
		Map<Long , UcShopModel> shopIdToUcShopModelMap = new HashMap<Long , UcShopModel>(); //商户信息
		
		for(CsUserFeedbacksExModel csUserFeedbacksExModel : pageList){
			CsUserFeedbackSolvesModel csUserFeedbackSolvesModel = csUserFeedbacksExModel.getCsUserFeedbackSolvesModel(); //处理对象
			FeedbackItemDtoResult feedbackItemDtoResult = new FeedbackItemDtoResult();
			Long userId = csUserFeedbacksExModel.getUserId();
			if(userId != null){
				ClientUserCacheDtoResult queryUserInfos = userIdToDtoResultMap.get(userId);
				if(queryUserInfos == null){
					queryUserInfos = assemblyUserCache(userId);
					if(queryUserInfos != null){
						userIdToDtoResultMap.put(userId, queryUserInfos);
					}
				}
				if(queryUserInfos != null){
					UcClientUserModel user = queryUserInfos.getUser();
					if(user != null){
						feedbackItemDtoResult.setMobile(user.getMobile()); //手机号码
						feedbackItemDtoResult.setUserName(user.getName());
					}
					CustomerExDtoResult customer = queryUserInfos.getCustomer();
					if(customer != null && customer.getCustomer() != null){
						feedbackItemDtoResult.setCustomerName(customer.getCustomer().getName()); //客户名称
					}
				}
			}
			if(ChannelEnum.OFFICIAL_ACCOUNTS.getCode().equals(csUserFeedbacksExModel.getChannel())){ //公众号手机和名称处理
				feedbackItemDtoResult.setMobile(csUserFeedbacksExModel.getOfficialAccountsMobile());
				feedbackItemDtoResult.setUserName(csUserFeedbacksExModel.getOfficialAccountsName());
			}
			Long shopId = csUserFeedbacksExModel.getShopId();
			Long intentionShopId = csUserFeedbacksExModel.getIntentionShopId();
			if(shopId != null){
				UcShopModel queryShop = shopIdToUcShopModelMap.get(shopId);
				if(queryShop == null){
					queryShop = queryShop(shopId);
					if(queryShop != null){
						shopIdToUcShopModelMap.put(shopId, queryShop);
					}
				}
				if(queryShop != null){
					feedbackItemDtoResult.setShopName(queryShop.getName()); //分配商户名称
				}
			}
			if(intentionShopId != null){
				UcShopModel queryShop = shopIdToUcShopModelMap.get(intentionShopId);
				if(queryShop == null){
					queryShop = queryShop(intentionShopId);
					if(queryShop != null){
						shopIdToUcShopModelMap.put(intentionShopId, queryShop);
					}
				}
				if(queryShop != null){
					feedbackItemDtoResult.setIntentionShopName(queryShop.getName()); //所属商户名称
				}
			}
			feedbackItemDtoResult.setMessageLabelType(csUserFeedbacksExModel.getMessageLabelType());
			feedbackItemDtoResult.setMessageLabelTypeName(MessageLabelTypeEnum.getDescByCode(csUserFeedbacksExModel.getMessageLabelType()));
			feedbackItemDtoResult.setIntentionShopId(intentionShopId);
			feedbackItemDtoResult.setShopId(shopId);
			feedbackItemDtoResult.setChannel(csUserFeedbacksExModel.getChannel());
			feedbackItemDtoResult.setChannelName(ChannelEnum.getDescByCode(csUserFeedbacksExModel.getChannel()));
			feedbackItemDtoResult.setFeedbackNumber(csUserFeedbacksExModel.getFeedbackNumber()); //留言编号
			feedbackItemDtoResult.setId(csUserFeedbacksExModel.getId());
			feedbackItemDtoResult.setFeedbackDesc(csUserFeedbacksExModel.getFeedbackDesc());
			feedbackItemDtoResult.setMessageLabel(buildMessageLable(csUserFeedbacksExModel.getMessageLabel())); //留言标签
			feedbackItemDtoResult.setFeedbackStatus(csUserFeedbacksExModel.getFeedbackStatus());
			feedbackItemDtoResult.setFeedbackStatusName(FeedbackStatusEnum.getDescByCode(csUserFeedbacksExModel.getFeedbackStatus()));
			if(csUserFeedbacksExModel.getFeedbackTime() != null){
				String formatDate = datetimeFormat.format(csUserFeedbacksExModel.getFeedbackTime());
				feedbackItemDtoResult.setFeedbackTime(formatDate);
			}
			if(csUserFeedbackSolvesModel != null){
				feedbackItemDtoResult.setSolveType(csUserFeedbackSolvesModel.getSolveType());
				feedbackItemDtoResult.setSolveTypeName(FeedbackTypeEnum.getDescByCode(csUserFeedbackSolvesModel.getSolveType()));
			}
			feedbackItemDtoResult.setUserId(csUserFeedbacksExModel.getUserId());
			
			feedbackItemList.add(feedbackItemDtoResult);
		}
		
	}
	
	/**
	 * 留言标签处理
	 * @param messageLabel
	 * @return
	 */
	public String buildMessageLable(String messageLabel){
		if(StringUtils.isEmpty(messageLabel)){
			return "";
		}
		StringBuffer sb = new StringBuffer("");
		String[] messageLabels = messageLabel.split(DH);
		for (String ml : messageLabels) {
			if(!StringUtils.isEmpty(ml)){
				String descByCode = MessageLabelEnum.getDescByCode(ml);
				if(!StringUtils.isEmpty(descByCode)){
					sb.append(descByCode).append(DH);
				}
			}
		}
		String string = sb.toString();
		if(string.endsWith(DH)){
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}
	
	/**
	 * 商户完结
	 * @param req
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public DtoResult feedbackStatusModify(FeedbackStatusDtoReq req) {
		DtoResult result = new DtoResult();
		
		//TODO 获取客户信息
		Object obj = rSessionCache.getUserInfo();
    	if (obj == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return result;
		}
    	
    	Long userId = null;
    	if(obj instanceof UcBmUserDtoResult){
    		UcBmUserDtoResult user = (UcBmUserDtoResult) obj;
    		userId = user.getId();
    	}else if(obj instanceof UcPmUserDtoResult){
    		UcPmUserDtoResult user = (UcPmUserDtoResult) obj;
    		userId = user.getId();
    	}
    	if(userId == null){
    		result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return result;
    	}
    	
		//查询当前留言记录是否存在
		CsUserFeedbacksModel csUserFeedbacksModel = csUserFeedbacksDao.selectById(req.getId());
		if(csUserFeedbacksModel == null){
			result.setCode(ResultCode.LY_NOT_EXIST.getCode());
			result.setMsg(ResultCode.LY_NOT_EXIST.getDesc());
			return result;
		}
		// 商户完结
		CsUserFeedbacksModel newModel = new CsUserFeedbacksModel();
		newModel.setFeedbackStatus(FeedbackStatusEnum.SHOP_CLOSED.getCode()); 
		newModel.setModifier(userId);
		newModel.setGmtModified(new Date());
		csUserFeedbacksDao.update(csUserFeedbacksModel, newModel);
		
		CsUserFeedbackSolvesModel csUserFeedbackSolvesModel = buildCsUserFeedbackSolvesModel(req , csUserFeedbacksModel , userId);
		csUserFeedbackSolvesDao.insertSingle(csUserFeedbackSolvesModel);
		
		//TODO 微信推送信息 (根据传值判断)
		if(req.getSendWechatMsg()){
			String openId = getOpenId(csUserFeedbacksModel.getUserId());
			if(!StringUtils.isEmpty(openId)){
//				String dateStr = DateUtil.getDateStr(csUserFeedbacksModel.getFeedbackTime(), DateUtil.YYYY_MM_DD);
//				Map<String, String> paramMap = new HashMap<String, String>();
//				paramMap.put("keyword1", ); //解决日期
//				paramMap.put("keyword2", phone); //处理内容
				sendWechatMsg(openId , Lists.newArrayList(getDateStr(csUserFeedbacksModel.getFeedbackTime())));  
			}
		}
		
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		
		return result;
	}
	
	public String getDateStr(Date date){
		if(date == null){
			return "";
		}
		Calendar calendar = Calendar.getInstance(); 
        //把当前时间添加到calerdar对象中
        calendar.setTime(date);  
//	    int year=calendar.get(Calendar.YEAR);//获取年份
	    int month=calendar.get(Calendar.MONTH)+1;//获取月份，因为从0开始的，所以要加1
	    int day = calendar.get(Calendar.DAY_OF_MONTH); //日
		return month + "月" + day + "日";
	}
	
	
	
	/**
	 * 获取微信openid
	 * @param userId
	 * @return
	 */
	public String getOpenId(Long userId){
		if(userId == null){
			logger.error("userId为空，微信推送消息失败.");
			return null;
		}
		BaseResponse<ClientUserThirdAccountDtoResult> queryUserThirdAccount = userFeignClient.queryUserThirdAccount(userId, null, CommonConstant.HTTP_HEADER_SOURCE_WECHAT);
		if(queryUserThirdAccount == null || queryUserThirdAccount.getData() == null 
				|| queryUserThirdAccount.getData().getAccount() == null || StringUtils.isEmpty(queryUserThirdAccount.getData().getAccount().getThirdAccount()) ){
			logger.error("根据userId获取第三方账户失败.");
			return null;
		}
		UcClientUserThirdAccountModel account = queryUserThirdAccount.getData().getAccount();
		String thirdAccount = account.getThirdAccount();
		return thirdAccount;
	}
	
	/**
	 * 推送微信接口
	 * @param openid
	 * @param paramMap
	 */
	public void sendWechatMsg(String openId , List<String> params){
		WechatMsgDtoReq wechatMsgDtoReq = new WechatMsgDtoReq();
		wechatMsgDtoReq.setOpenid(openId);
		wechatMsgDtoReq.setParams(params); //多个参数必须按顺序放入
		wechatMsgDtoReq.setWechatMsgTypeEnums(WechatMsgTypeEnums.FEEDBACK_ING); //端
		BaseResponse<DtoResult> sendWechatMsg = wechatMsgFeignClient.sendWechatMsg(wechatMsgDtoReq);
		if(sendWechatMsg.isSuccess()){
			logger.error("============处理完成微信推送消息成功============");
		}else{
			logger.error("============处理完成微信推送消息失败，msg：" + sendWechatMsg.getMsg());
		}
	}
	
	
	/**
	 * 详情页查询
	 * @param uid
	 */
	public FeedbackDetailDtoResult feedbackDetail(Long id) {
		FeedbackDetailDtoResult result = new FeedbackDetailDtoResult();
		
		//TODO 获取客户信息
		Object obj = rSessionCache.getUserInfo();
    	if (obj == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return result;
		}
    	
		//查询当前留言记录是否存在
		CsUserFeedbacksExModel csUserFeedbacksExModel = csUserFeedbacksDao.queryFeedbackById(id);
		if(csUserFeedbacksExModel == null){
			result.setCode(ResultCode.LY_NOT_EXIST.getCode());
			result.setMsg(ResultCode.LY_NOT_EXIST.getDesc());
			return result;
		}
		
		CsUserFeedbackSolvesModel csUserFeedbackSolvesModel = csUserFeedbacksExModel.getCsUserFeedbackSolvesModel();
		
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		result.setCreateTime(datetimeFormat.format(csUserFeedbacksExModel.getGmtCreated()));
		result.setFeedbackDesc(csUserFeedbacksExModel.getFeedbackDesc());
		result.setFeedbackNumber(csUserFeedbacksExModel.getFeedbackNumber());
		result.setFeedbackStatus(csUserFeedbacksExModel.getFeedbackStatus());
		result.setMessageLabel(buildMessageLable(csUserFeedbacksExModel.getMessageLabel())); //留言标签
		result.setFeedbackStatusName(FeedbackStatusEnum.getDescByCode(csUserFeedbacksExModel.getFeedbackStatus()));
		result.setMessageLabelType(csUserFeedbacksExModel.getMessageLabelType());
		result.setMessageLabelTypeName(MessageLabelTypeEnum.getDescByCode(csUserFeedbacksExModel.getMessageLabelType()));
		result.setChannel(csUserFeedbacksExModel.getChannel());
		result.setChannelName(ChannelEnum.getDescByCode(csUserFeedbacksExModel.getChannel()));
		if(csUserFeedbackSolvesModel != null){
			result.setFinishTime(datetimeFormat.format(csUserFeedbackSolvesModel.getGmtCreated()));
			result.setSolveDesc(csUserFeedbackSolvesModel.getSolveDesc());
			result.setSolveType(csUserFeedbackSolvesModel.getSolveType());
			result.setSolveTypeName(FeedbackTypeEnum.getDescByCode(csUserFeedbackSolvesModel.getSolveType()));
		}else{
			if(FeedbackStatusEnum.PLATFORM_CLOSED.getCode().equals(csUserFeedbacksExModel.getFeedbackStatus()) 
					&& csUserFeedbacksExModel.getDistributedCreated() != null){
				result.setFinishTime(datetimeFormat.format(csUserFeedbacksExModel.getDistributedCreated()));  //平台完结时间
			}
		}
		result.setUserId(csUserFeedbacksExModel.getUserId());
		Long userId = csUserFeedbacksExModel.getUserId();
		if(userId != null){
			ClientUserCacheDtoResult queryUserInfos = assemblyUserCache(userId);
			if(queryUserInfos != null){
				UcClientUserModel user = queryUserInfos.getUser();
				if(user != null){
					result.setMobile(user.getMobile()); //手机号码
					result.setUserName(user.getName());
				}
				CustomerExDtoResult customer = queryUserInfos.getCustomer();
				if(customer != null && customer.getCustomer() != null){
					result.setCustomerName(customer.getCustomer().getName());//所属公司(即为客户名称)
				}
			}
		}
		Long shopId = csUserFeedbacksExModel.getShopId();
		Long intentionShopId = csUserFeedbacksExModel.getIntentionShopId();
		Map<Long , UcShopModel> shopIdToUcShopModelMap = new HashMap<Long , UcShopModel>(); //商户信息
		if(shopId != null){
			UcShopModel queryShop = shopIdToUcShopModelMap.get(shopId);
			if(queryShop == null){
				queryShop = queryShop(shopId);
				if(queryShop != null){
					shopIdToUcShopModelMap.put(shopId, queryShop);
				}
			}
			if(queryShop != null){
				result.setShopName(queryShop.getName()); //分配商户名称
			}
		}
		if(intentionShopId != null){
			UcShopModel queryShop = shopIdToUcShopModelMap.get(intentionShopId);
			if(queryShop == null){
				queryShop = queryShop(intentionShopId);
				if(queryShop != null){
					shopIdToUcShopModelMap.put(intentionShopId, queryShop);
				}
			}
			if(queryShop != null){
				result.setIntentionShopName(queryShop.getName()); //所属商户名称
			}
		}
		result.setDistributedDesc(csUserFeedbacksExModel.getDistributedDesc()); // 分配/平台完结备注
		if(ChannelEnum.OFFICIAL_ACCOUNTS.getCode().equals(csUserFeedbacksExModel.getChannel())){ //公众号手机和名称处理
			result.setMobile(csUserFeedbacksExModel.getOfficialAccountsMobile());
			result.setUserName(csUserFeedbacksExModel.getOfficialAccountsName());
		}
		
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
		
		return result;
	} 
	
	/**
	 * 封装留言对象
	 */
	public CsUserFeedbacksModel buildCsUserFeedbacksModel(FeedbackDtoReq req , ClientUserCacheDtoResult clientUserCacheDtoResult){
		CsUserFeedbacksModel csUserFeedbacksModel = new CsUserFeedbacksModel();
		
		Date now = new Date();
		
		if(clientUserCacheDtoResult != null){
			UcClientUserModel user = clientUserCacheDtoResult.getUser();
			if(user != null){
				csUserFeedbacksModel.setUserId(user.getId());
			}
			UserVisitShopDtoResult userVisitShopDtoResult = clientUserCacheDtoResult.getVisitShop();
			if(userVisitShopDtoResult != null){
				
			}
			if(userVisitShopDtoResult != null &&  userVisitShopDtoResult.getShop() != null){
				UcShopModel visitShop = userVisitShopDtoResult.getShop(); //用户当前登陆商户
				csUserFeedbacksModel.setIntentionShopId(visitShop.getId()); // 当前登陆用户所属意向商户id
			}
			csUserFeedbacksModel.setCreator(clientUserCacheDtoResult.getUser().getId());
			csUserFeedbacksModel.setModifier(clientUserCacheDtoResult.getUser().getId());
		}
		if(ChannelEnum.OFFICIAL_ACCOUNTS.getCode().equals(req.getChannel())){
			csUserFeedbacksModel.setOfficialAccountsMobile(req.getOfficialAccountsMobile());
			csUserFeedbacksModel.setOfficialAccountsName(req.getOfficialAccountsName());
		}
		if(req.getIntentionShopId() != null){
			csUserFeedbacksModel.setIntentionShopId(req.getIntentionShopId());
		}
		csUserFeedbacksModel.setMessageLabelType(req.getMessageLabelType());
		csUserFeedbacksModel.setChannel(req.getChannel());
		csUserFeedbacksModel.setFeedbackDesc(req.getFeedbackDesc());
		csUserFeedbacksModel.setMessageLabel(req.getMessageLabel()); //留言标签，多个以逗号分隔
		csUserFeedbacksModel.setFeedbackStatus(FeedbackStatusEnum.DISTRIBUTED.getCode()); //待分配
		csUserFeedbacksModel.setFeedbackTime(req.getFeedbackTime());
		csUserFeedbacksModel.setOrderId(req.getOrderId());
		
		csUserFeedbacksModel.setVersion(0L);
		csUserFeedbacksModel.setGmtCreated(now);
		csUserFeedbacksModel.setGmtModified(now);
		csUserFeedbacksModel.setDataStatus(DatastatusCodeEnums.normal.getCode());
		
		return csUserFeedbacksModel;
	}
	
	public CsUserFeedbackSolvesModel buildCsUserFeedbackSolvesModel(FeedbackStatusDtoReq req , CsUserFeedbacksModel csUserFeedbacksModel , Long userId){
		CsUserFeedbackSolvesModel csUserFeedbackSolvesModel = new CsUserFeedbackSolvesModel();
		Date now = new Date();
		
		csUserFeedbackSolvesModel.setFeedbackId(csUserFeedbacksModel.getId());
		csUserFeedbackSolvesModel.setSolveDesc(req.getSolveDesc());
		csUserFeedbackSolvesModel.setSolveTime(now);
		csUserFeedbackSolvesModel.setSolveType(req.getSolveType());
		csUserFeedbackSolvesModel.setSolveUserId(userId);
		csUserFeedbackSolvesModel.setCreator(userId);
		csUserFeedbackSolvesModel.setModifier(userId);
		
		csUserFeedbackSolvesModel.setDataStatus(DatastatusCodeEnums.normal.getCode());
		csUserFeedbackSolvesModel.setGmtCreated(now);
		csUserFeedbackSolvesModel.setGmtModified(now);
		csUserFeedbackSolvesModel.setVersion(0L);
		
		return csUserFeedbackSolvesModel;
	}


//	@Override
//	public DtoResult feedbackExport(FeedbackPageDtoReq dtoReq , HttpServletResponse response) {
//		DtoResult result = new DtoResult();
//		FeedbackDtoResult resultBase = feedbackList(dtoReq);
//		
//	    List<FeedbackItemDtoResult> allTotal = resultBase.getFeedbackItemList();
//	    
//	    Map<String, Class<?>> map = new HashMap<String, Class<?>>();
//	    map.put("客户留言列表",FeedbackItemDtoResult.class);
//	    Map exDataList=new LinkedHashMap();
//	    exDataList.put("客户留言列表",allTotal);
//	    ExportMultipleSheetExcel exportMultipleSheetExcel=new ExportMultipleSheetExcel(map,exDataList);
//	    String fileName = "客户留言列表" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xlsx";
//	    try {
//			exportMultipleSheetExcel.write(response, fileName).dispose();
//		} catch (IOException e) {
//			logger.error("[FeedbacksService.feedbackExport]留言列表导出异常", e);
//			result.setCode(ResultCode.FAIL.getCode());
//			result.setMsg(ResultCode.FAIL.getDesc());
//		}
	    
//	    return null;
//    }
	
	/**
	 * 分配商户/平台完结
	 */
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public DtoResult feedbackDistributedModify(FeedbackDistributedDtoReq dtoReq) {
		DtoResult result = new DtoResult();
		
		//TODO 获取客户信息
		Object obj = rSessionCache.getUserInfo();
    	if (obj == null) {
			result.setCode(ResultCode.USER_LOGIN_REQUIRED.getCode());
			result.setMsg(ResultCode.USER_LOGIN_REQUIRED.getDesc());
			return result;
		}
    	Long userId = null;
    	if(obj instanceof UcBmUserDtoResult){
    		UcBmUserDtoResult user = (UcBmUserDtoResult) obj;
    		userId = user.getId();
    	}else if(obj instanceof UcPmUserDtoResult){
    		UcPmUserDtoResult user = (UcPmUserDtoResult) obj;
    		userId = user.getId();
    	}
    	
		//查询当前留言记录是否存在
		CsUserFeedbacksModel csUserFeedbacksModel = csUserFeedbacksDao.selectById(dtoReq.getId());
		if(csUserFeedbacksModel == null){
			result.setCode(ResultCode.LY_NOT_EXIST.getCode());
			result.setMsg(ResultCode.LY_NOT_EXIST.getDesc());
			return result;
		}
		
		//分配商户
		CsUserFeedbacksModel newModel = new CsUserFeedbacksModel();
		if(FeedbackStatusEnum.DISTRIBUTED.getCode().equals(dtoReq.getFeedbackStatus())){
			newModel.setFeedbackStatus(FeedbackStatusEnum.PENDING.getCode());
			newModel.setShopId(dtoReq.getShopId());
		}else if(FeedbackStatusEnum.PLATFORM_CLOSED.getCode().equals(dtoReq.getFeedbackStatus())){
			newModel.setFeedbackStatus(FeedbackStatusEnum.PLATFORM_CLOSED.getCode()); //平台完结
		}
		newModel.setDistributedDesc(dtoReq.getDistributedDesc());
		newModel.setGmtModified(new Date());
		newModel.setModifier(userId);
		newModel.setDistributedCreator(userId);
		newModel.setDistributedCreated(new Date());
		csUserFeedbacksDao.update(csUserFeedbacksModel, newModel);
		
		//如果是关闭，则需要推送微信
		if(dtoReq.getSendWechatMsg() && FeedbackStatusEnum.PLATFORM_CLOSED.getCode().equals(dtoReq.getFeedbackStatus())){
			String openId = getOpenId(csUserFeedbacksModel.getUserId());
			if(!StringUtils.isEmpty(openId)){
//				String dateStr = DateUtil.getDateStr(csUserFeedbacksModel.getFeedbackTime(), DateUtil.YYYY_MM_DD);
//				Map<String, String> paramMap = new HashMap<String, String>();
//				paramMap.put("keyword1", getDateStr(csUserFeedbacksModel.getFeedbackTime())); //解决日期
//				paramMap.put("keyword2", phone); //处理内容
				sendWechatMsg(openId , Lists.newArrayList(getDateStr(csUserFeedbacksModel.getFeedbackTime())));  
			}
		}
		
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getDesc());
			
		return result;
	}

	/**
	 * 联系人模糊查询
	 */
	@Override
	public FeedbackUserListDtoResult queryUserList(UserPageDtoReq dtoReq) {
		FeedbackUserListDtoResult result = new FeedbackUserListDtoResult();
		
		List<UserModel> users = new ArrayList<UserModel>();
		ClientUserQueryDtoReq userDtoReq = new ClientUserQueryDtoReq();
		BeanUtils.copyProperties(dtoReq, userDtoReq);
		BaseResponse<ClientUserListDtoResult> queryUserInfos = userFeignClient.queryUserInfos(userDtoReq);
		if(queryUserInfos != null && queryUserInfos.isSuccess() && queryUserInfos.getData() != null ){
			List<UcClientUserModel> ucClientUserModels = queryUserInfos.getData().getUsers();
			if(!CollectionUtils.isEmpty(ucClientUserModels)){
				for (UcClientUserModel ucClientUserModel : ucClientUserModels) {
					UserModel user = new UserModel();
					BeanUtils.copyProperties(ucClientUserModel, user);
					users.add(user);
				}
			}
			result.setCode(queryUserInfos.getCode());
			result.setMsg(queryUserInfos.getMsg());
		}
		result.setUsers(users);
		return result;
	}

	/**
	 * 商户模糊查询
	 */
	@Override
	public FeedbackShopListDtoResult queryShopList(ShopPageDtoReq dtoReq) {
		FeedbackShopListDtoResult result = new FeedbackShopListDtoResult();
		
		List<ShopModel> shopModels = new ArrayList<ShopModel>();
		ShopQueryDtoReq shopDtoReq = new ShopQueryDtoReq();
		BeanUtils.copyProperties(dtoReq, shopDtoReq);
		BaseResponse<ShopDtoListResult> shopDtoListResult = shopFeignClient.queryShop(shopDtoReq);
		if(shopDtoListResult != null && shopDtoListResult.isSuccess() && shopDtoListResult.getData() != null ){
			List<UcShopModel> ucShopModels = shopDtoListResult.getData().getShops();
			if(!CollectionUtils.isEmpty(ucShopModels)){
				for (UcShopModel ucShopModel : ucShopModels) {
					ShopModel shopModel = new ShopModel();
					BeanUtils.copyProperties(ucShopModel, shopModel);
					shopModels.add(shopModel);
				}
			}
			result.setCode(shopDtoListResult.getCode());
			result.setMsg(shopDtoListResult.getMsg());
		}
		result.setShopModels(shopModels);
		return result;
	}
}
