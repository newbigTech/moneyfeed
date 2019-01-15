package com.newhope.moneyfeed.biz.wechat;

import com.alibaba.fastjson.JSONObject;
import com.newhope.cache.core.Cache;
import com.newhope.cache.core.remote.redis.RedisOperCache;
import com.newhope.commons.lang.DateUtils;
import com.newhope.moneyfeed.api.enums.AppCodeTypeEnums;
import com.newhope.moneyfeed.api.enums.AppSourceEnums;
import com.newhope.moneyfeed.api.vo.response.wechat.WechatJsticketResult;
import com.newhope.moneyfeed.api.vo.response.wechat.WechatTokenResult;
import com.newhope.moneyfeed.common.constant.WechatConstant;
import com.newhope.moneyfeed.common.util.wechat.WechatUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class WechatService {
    public static Logger logger = LoggerFactory.getLogger(WechatService.class);

    @Autowired
    @Qualifier("wechatCache")
    Cache wechatCache;


    public RedisOperCache getRedisOperCache(){
        return (RedisOperCache)wechatCache;
    }


    /**
     * 取得微信的token
     *
     * @param
     * @param
     * @return
     * @throws Exception
     */
    public String getToken() throws Exception {
           String  token = (String) wechatCache.get(WechatConstant.ACCESS_TOKEN);
        if (StringUtils.isEmpty(token)) {
             token = WechatUtil.getAccessTokenJubaozhu();
             if(StringUtils.isNotEmpty(token)){
                 getRedisOperCache().set(WechatConstant.ACCESS_TOKEN,token,2*60*60);
             }
        }
        return token;
    }

    /**
     * @param @param  appCode
     * @param @return
     * @param @throws Exception    设定文件
     * @return String    返回类型
     * @throws
     * @Title: getJsapiTicket
     * @Description: 得到微信JS接口ticket
     */
    public String getJsapiTicket() throws Exception {
        String ticket = (String) wechatCache.get(WechatConstant.JSTICKET);
        if (StringUtils.isEmpty(ticket)) {
            //更新jsticket
            String token =getToken();
            ticket= WechatUtil.getTicket(token);
            if(StringUtils.isNotEmpty(ticket)){
                getRedisOperCache().set(WechatConstant.JSTICKET,ticket,2*60*60);
            }
        }
            return ticket;
    }


    /**
     * @Title: uploadWechartImg
     * @Description: 上传图片到自己的服务器
     * @param @param mediaId 微信媒体ID
     * onlyLoad：null上传并插入
     * @param @return
     * @param @throws Exception    设定文件
     * @return String    返回类型
     * @throws
     */
    /*public MediaResult uploadWechartFile(MediaReq mediaReq) throws Exception {
		String mediaId = mediaReq.getMediaId();
		FileTypeEnums type = FileTypeEnums.valueOf(mediaReq.getType());
		//上传目录以当前年月日命名
		String currentDir = "";
		String ossFileType = "";
		if(type== FileTypeEnums.IMAGE){
			currentDir = "wechat/image/" + DateUtils.date2Str(DateUtils.yyyyMMdd);
			ossFileType = AliyunFileTypeEnums.IMG.getValue();
		}else{
			currentDir = "wechat/voice/" + DateUtils.date2Str(DateUtils.yyyyMMdd);
			ossFileType = AliyunFileTypeEnums.VOICE.getValue();

		}
		File file =new File(WechatConstant.UPLOAD_FILE + currentDir);
		try{

			if(!file.getParentFile().exists()) {
				logger.info("目标文件所在的目录不存在，则创建父目录");
				if(file.getParentFile().mkdirs()) {
					logger.info("创建父目录成功");
				}
			}
			//如果文件夹不存在则创建
			if  (!file .exists()  && !file .isDirectory())
			{
			    logger.info("目录不存在创建目录");
			    file .mkdir();
			}
		}catch(Exception e){
			logger.error("[WechatService.uploadWechartFile出现错误]", e);
			throw new Exception(e.getMessage());
		}
		//上传图片
		MediaResult result = new MediaResult();
		WxDwonload w = JwMediaAPI.downMedia(getToken(mediaReq.getAppCode(), AppSourceEnums.WECHAT.name()),
				mediaId, WechatConstant.UPLOAD_FILE + currentDir + "/");
		//是否加水印
		if(mediaReq.getMarkFlag() !=null && mediaReq.getMarkFlag()){
			//加水印的IMG
			List<String> imgList = new ArrayList<String>();
			imgList.add(WechatConstant.UPLOAD_FILE + currentDir + "/" + w.getFileName());

			PicMarkDtoReq picMarkDtoReq = new PicMarkDtoReq();
			picMarkDtoReq.setShopId(mediaReq.getShopId());
			picMarkDtoReq.setUrlList(imgList);
			warterMark(picMarkDtoReq, currentDir);
		}
		//上次aliyun oss 服务
		AliyunOssDtoReq aliyunOssDtoReq = new AliyunOssDtoReq();
		aliyunOssDtoReq.setMainProjectName(CommonConstant.OSS_MAIN_FOLDER);
		aliyunOssDtoReq.setFileType(ossFileType);
		aliyunOssDtoReq.setObjectName(w.getFileName());
		String url = aliyunOssService.uploadImg(WechatConstant.UPLOAD_FILE + currentDir + "/" + w.getFileName(), aliyunOssDtoReq);

		PicSaveDtoReq picSaveDtoReq = new PicSaveDtoReq(url,
				com.newhope.zhuxiaoer.trade.api.enums.file.FileTypeEnums.IMG.getValue());
//		PicSaveDtoReq picSaveDtoReq = new PicSaveDtoReq(WechatConstant.FILE_SERVER + "/"+ currentDir + "/" + w.getFileName(),
//				com.newhope.zhuxiaoer.trade.api.enums.file.FileTypeEnums.IMG.getValue());
		PicDtoResult picDtoResult = picFacade.savePicInfo(Lists.newArrayList(picSaveDtoReq));
		result.setPicId(picDtoResult.getPicInfos().get(0).getId());

		// 返回图片路径,该路径是一个JSP网页可引用显示图片的路径
		result.setMediaUrl(url);
		result.setMediaPath(currentDir + w.getFileName());
		result.setCode(ResultCode.SUCCESS.name());


		return result;
	}
	*/
    /**
     * @Title: warterMark
     * @Description: 打水印
     * @param @param picMarkDtoReq
     * @param @param currentDir    设定文件
     * @return void    返回类型
     * @throws
     */
	/*public void warterMark(PicMarkDtoReq picMarkDtoReq, String currentDir) {
		//查询店铺
		ShopDtoReq shopDtoReq = new ShopDtoReq();
		shopDtoReq.setShopId(picMarkDtoReq.getShopId());
		ShopDtoResult shopDtoResult = null;
		try {
			shopDtoResult = shopFacade.getShop(shopDtoReq);
		} catch (ParamException e) {
			logger.error("[WechatService.warterMark出现错误]", e);
		} catch (RuntimeException e) {
			logger.error("[WechatService.warterMark出现错误]", e);
		}
		if(shopDtoResult.getShops() == null || shopDtoResult.getShops().isEmpty()){
			logger.error("[WechatService.warterMark]错误,查询不到店铺:"+picMarkDtoReq.getShopId());
			return;
		}
		String text = StringUtils.isEmpty(shopDtoResult.getShops().get(0).getKeyWords()) ? shopDtoResult.getShops().get(0).getName() : shopDtoResult.getShops().get(0).getKeyWords();
		for (String url : picMarkDtoReq.getUrlList()) {
			try {
				ImageMarkUtils.markText(url, text, currentDir);
			} catch (Exception e) {
				logger.error("[WechatService.warterMark出现错误]", e);
			}
		}
	}*/

    /**
     * @param
     * @return void    返回类型
     * @throws
     * @Title: refreshToken
     * @Description: 刷新微信的token
     */
    public void refreshToken() {
//		logger.info("[WechatService.refreshToken]>>>>>>>>>>");
        try {
            //----------------刷新的token,jsticket-------------
            WechatTokenResult wechatTokenResult = (WechatTokenResult) wechatCache.get(WechatConstant.ACCESS_TOKEN);
            if (wechatTokenResult != null) {
                Calendar c1 = Calendar.getInstance();
                Calendar c2 = Calendar.getInstance();
                c1.setTime(new Date());
                c2.setTime(wechatTokenResult.getTokenDate());
                int diff = DateUtils.dateDiff('s', c1, c2);
                int expire = 7200 - diff;
                if (expire <= 3600) {
                    String token = WechatUtil.getAccessTokenJubaozhu();
                    if (StringUtils.isNotEmpty(token)) {
                        // 更新token
                        wechatTokenResult.setToken(token);
                        wechatTokenResult.setTokenDate(new Date());
                        wechatCache.set(WechatConstant.ACCESS_TOKEN, wechatTokenResult);

                        //更新jsticket
                        String jsticket = WechatUtil.getTicket(token);
                        WechatJsticketResult wechatJsticketResult = new WechatJsticketResult();
                        wechatJsticketResult.setJsticket(jsticket);
                        wechatJsticketResult.setJsticketDate(new Date());
                        wechatCache.set(WechatConstant.JSTICKET, wechatJsticketResult);
                    } else {
                        logger.error("AccessToken没有获取到token");
                    }
                } else {

                }
            } else {
                logger.error("缓存中token为空");
            }

//			//----------------刷新八戒通的token,jsticket-------------
//			WechatTokenResult wechatTokenResultBajietong = (WechatTokenResult) wechatCache.get(WechatConstant.ACCESS_TOKEN_BAJIETONG);
//			if (wechatTokenResultBajietong != null) {
//				Calendar c1 = Calendar.getInstance();
//				Calendar c2 = Calendar.getInstance();
//				c1.setTime(new Date());
//				c2.setTime(wechatTokenResultBajietong.getTokenDate());
//				int diff = DateUtils.dateDiff('s', c1, c2);
//				int expire = 7200 - diff;
//				if (expire <= 3600) {
//					String token = WechatUtil.getAccessTokenBajietong();
//					if (StringUtils.isNotEmpty(token)) {
//						// 更新token
//						wechatTokenResultBajietong.setToken(token);
//						wechatTokenResultBajietong.setTokenDate(new Date());
//						wechatCache.set(WechatConstant.ACCESS_TOKEN_BAJIETONG, wechatTokenResultBajietong);
//
//						//更新jsticket
//						String jsticket = WechatUtil.getTicket(token);
//						WechatJsticketResult wechatJsticketResultBajietong = new WechatJsticketResult();
//						wechatJsticketResultBajietong.setJsticket(jsticket);
//						wechatJsticketResultBajietong.setJsticketDate(new Date());
//						wechatCache.set(WechatConstant.JSTICKET_BAJIETONG, wechatJsticketResultBajietong);
//					} else {
//						logger.error("AccessToken没有获取到token");
//					}
//				} else {
//
////				}
//			}else{
//				logger.error("缓存中token为空");
//			}
        } catch (Exception e) {
            logger.error("定时刷新微信接口访问token时出现错误", e);
        }
    }

    public JSONObject getAccessTokenForAuth(String code, String appcode, String appsource) {
        if (AppSourceEnums.WECHAT.name().equals(appsource)) {
            return WechatUtil.getAccessTokenForAuthJubaozhu(code);
        }
        return null;
    }
}











