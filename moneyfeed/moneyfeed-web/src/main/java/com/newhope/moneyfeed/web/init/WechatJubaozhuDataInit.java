package com.newhope.moneyfeed.web.init;

import com.alibaba.fastjson.JSON;
import com.newhope.cache.core.Cache;
import com.newhope.cache.core.remote.redis.RedisOperCache;
import com.newhope.commons.lang.env.EnvUtils;
import com.newhope.moneyfeed.biz.wechat.WechatService;
import com.newhope.moneyfeed.common.constant.WechatConstant;
import com.newhope.moneyfeed.common.util.wechat.WechatUtil;
import com.newhope.moneyfeed.common.vo.Button;
import com.newhope.moneyfeed.common.vo.ClickButton;
import com.newhope.moneyfeed.common.vo.Menu;
import com.newhope.moneyfeed.common.vo.ViewButton;
import org.apache.commons.lang.StringUtils;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.wxmenu.JwMenuAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**  
* @ClassName: StartupRunnerInit  
* @Description: spring boot启动时初始化微信
*    
*/
@Component
public class WechatJubaozhuDataInit implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(WechatJubaozhuDataInit.class);


	@Autowired
	WechatService wechatService;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	@Qualifier("wechatCache")
	Cache wechatCache;

	public RedisOperCache getRedisCache(){
		return (RedisOperCache)wechatCache;
	}
	
	@Value("${newhope.wechat.appid}")
	private String appid;
	@Value("${newhope.wechat.appscret}")
	private String appscret;
	@Value("${newhope.wechat.domain}")
	private String domain;
	@Value("${newhope.wechat.wechattoken}")
	private String wechattoken;
	@Value("${newhope.wechat.hompageurl}")
	private String hompageurl;
	@Value("${newhope.wechat.uploadimg}")
	private String uploadimg;
	@Value("${newhope.wechat.imgserver}")
	private String imgserver;



	private static final Long MILLISECONDS = System.currentTimeMillis();

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppscret() {
		return appscret;
	}

	public void setAppscret(String appscret) {
		this.appscret = appscret;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getHompageurl() {
		return hompageurl;
	}

	public void setHompageurl(String hompageurl) {
		this.hompageurl = hompageurl;
	}

	public String getUploadimg() {
		return uploadimg;
	}

	public void setUploadimg(String uploadimg) {
		this.uploadimg = uploadimg;
	}

	public String getImgserver() {
		return imgserver;
	}

	public void setImgserver(String imgserver) {
		this.imgserver = imgserver;
	}

	public String getWechattoken() {
		return wechattoken;
	}

	public void setWechattoken(String wechattoken) {
		this.wechattoken = wechattoken;
	}

	/**  
	* @Title: settingWechart  
	* @Description: 微信用到的常量初始化
	* @param
	* @return void    返回类型  
	* @throws  
	*/
	@PostConstruct
	public void settingWechat(){
		//微信APPID
		WechatConstant.APPID = getAppid();
		//微信APPSCRET
		WechatConstant.APPSCRET = getAppscret();
		//域名
		WechatConstant.DOMAIN = getDomain();
		//与微信配置一样
		WechatConstant.WECHAT_TOKEN = getWechattoken();
		//网址
		WechatConstant.HOMEPAGE_URL = getHompageurl();
		//服务器上存放上传图片的文件夹
		WechatConstant.UPLOAD_FILE = getUploadimg();
		//图片服务器
		WechatConstant.FILE_SERVER = getImgserver();
				
		WechatConstant.settingUrlJubaozhu();
	}
	
	@Override
	public void run(String... args) throws Exception {
		if(!this.getEnabled()){
			return;
		}
		if (EnvUtils.dev()) {
			//如果是开发环境使用自己配置的微信信息
			//微信APPID
			WechatConstant.APPID = "wxe797893e8c08f15f";
			//微信APPSCRET
			WechatConstant.APPSCRET = "1ee4fa979e24601d1a7dda2ea31c8273";
			//域名
			WechatConstant.DOMAIN = "dongqilin.wx.sczhou.com";
			//与微信配置一样
			WechatConstant.WECHAT_TOKEN = "wechatzhuxiaoer";
			//网址
			WechatConstant.HOMEPAGE_URL = "http://dongqilin.wx.sczhou.com";
			//服务器上存放上传图片的文件夹
			WechatConstant.UPLOAD_FILE = "D:/temp/xx/trade/";
			//图片服务器
			WechatConstant.FILE_SERVER = " http://dongqilin.wx.sczhou.com";

			WechatConstant.settingUrlJubaozhu();
			//初始化聚宝猪开发环境
			initJubaozhu();
		}else{
		    //初始化
		    initJubaozhu();
		}
	}


	/**  
	* @Title: getEnabled  
	* @Description: 判断是否初始化微信接口访问环境。
	* @return Boolean    返回类型  
	* @throws  
	*/
	private Boolean getEnabled(){
		if(applicationContext == null){
			return true;
		}
		String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
		return !Arrays.asList(activeProfiles).contains("automate");	
	}
	

	/**  
	* @Title: createMenu  
	* @Description: 构造一个自定义菜单
	* @param
	* @return void    返回类型  
	* @throws  
	*/
	private void createMenu() {

		//默认菜单----------------------------------------------------------------------------------------------------
		Menu menu = new Menu();
		//		// 一级菜单

		ViewButton left = new ViewButton();
		left.setName("买饲料");
		left.setType("view");
		left.setUrl(WechatConstant.WECHAT_INDEX);
		ViewButton middle = new ViewButton();
		middle.setName("产品目录");
		middle.setType("view");
		middle.setUrl("");
		ViewButton right = new ViewButton();
		right.setName("关于我们");
		right.setType("view");
		right.setUrl("");

		ViewButton middleBirdsFeed = new ViewButton();
		middleBirdsFeed.setName("禽料");
		middleBirdsFeed.setType("view");
		middleBirdsFeed.setUrl(WechatConstant.BIRDSFEED);

		ViewButton middlePigFeed = new ViewButton();
		middlePigFeed.setName("猪料");
		middlePigFeed.setType("view");
		middlePigFeed.setUrl(WechatConstant.PIGFEED);

		ViewButton middleFishFeed = new ViewButton();
		middleFishFeed.setName("水产料");
		middleFishFeed.setType("view");
		middleFishFeed.setUrl(WechatConstant.FISHFEED);

		ViewButton rightCompanyInfo = new ViewButton();
		rightCompanyInfo.setName("企业信息");
		rightCompanyInfo.setType("view");
		rightCompanyInfo.setUrl(WechatConstant.COMPANYINFO);

		ViewButton rightFeedbackInfo = new ViewButton();
		rightFeedbackInfo.setName("意见反馈");
		rightFeedbackInfo.setType("view");
		rightFeedbackInfo.setUrl(WechatConstant.FEEDBACK);

		ClickButton rightContactUs = new ClickButton();
		rightContactUs.setName("联系我们");
		rightContactUs.setType("click");
		rightContactUs.setKey(WechatConstant.CONTACTUS);


		// 一级菜单加入总菜单
		middle.setSub_button(new Button[]{middlePigFeed,middleBirdsFeed,middleFishFeed});
		right.setSub_button(new Button[]{rightCompanyInfo,rightFeedbackInfo,rightContactUs});
		menu.setButton(new Button[] { left,middle,right });

		String menuString = JSON.toJSONString(menu);
		logger.info("菜单是：" + menuString);
		int result = 0;
		try {
			String token= (String) wechatService.getToken();
			result = WechatUtil.createMenuDefault(token, menuString);
		} catch (Exception e) {
			logger.error("创建默认菜单异常", e);
		}
		if (result == 0) {
			logger.info("创建默认菜单成功 " + menuString);
			System.out.println("");
		} else {
			logger.info("创建默认菜单失败" + result);
		}
	}
	
	private void initJubaozhu() throws Exception, WexinReqException {
		initAccessToken();
		//初始化菜单
		JwMenuAPI.deleteMenu(wechatService.getToken());
		createMenu();
	}
	
	
	/**  
	* @Title: initAccessToken  
	* @Description: 初始化微信接口访问token
	* @param @throws Exception    设定文件  
	* @return void    返回类型  
	* @throws  
	*/
	private void initAccessToken() throws Exception {
		//初始化微信接口访问token
		String token = WechatUtil.getAccessTokenJubaozhu();
		if (StringUtils.isNotEmpty(token)) {
			// 更新token
			getRedisCache().set(WechatConstant.ACCESS_TOKEN, token,2*60*60);
			//更新jsticket
			String jsticket = WechatUtil.getTicket(token);
			getRedisCache().set(WechatConstant.JSTICKET, jsticket,2*60*60);
		} else {
			logger.error("AccessToken没有获取到token");
		}
	}

}
