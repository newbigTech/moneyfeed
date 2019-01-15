package com.newhope.moneyfeed.common.constant;

import java.util.Date;

/**
 * @ClassName: WechartConstant
 * @Description: 微信用到的常量
 * @author luoxl
 * @date 2016年9月22日 上午10:32:21
 *
 */
public class WechatConstant {
    //微信APPID2
    public static String APPID_BAJIETONG;
    //微信APPSCRET2
    public static String APPSCRET_BAJIETONG;
    //域名2
    public static String DOMAIN_BAJIETONG;
    //与微信配置一样2
    public static String WECHAT_TOKEN_BAJIETONG;
    //网址2
    public static String HOMEPAGE_URL_BAJIETONG;

    //APP端微信APPID
    public static String APPID_JBZ_APP;
    //APP端微信APPSCRET
    public static String APPSCRET_JBZ_APP;
    
    //微信APPID
    public static String APPID;
    //微信APPSCRET
    public static String APPSCRET;
    //域名
    public static String DOMAIN;
    //与微信配置一样
    public static String WECHAT_TOKEN;
    //网址
    public static String HOMEPAGE_URL;
    //服务器上存放上传图片的文件夹
    public static String UPLOAD_FILE;
    //映射 发布子目录
    public static String PUBLICH_IMG;
    //图片服务器
    public static String FILE_SERVER;
    //微信网页授权取code回调url
    public static String GET_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=redirect_uri_replace&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
    // 网页授权TOKEN获得请求链接
    public static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    //取临时素材接口
    public static String GET_IMG_URL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=token_replace&media_id=media_id_replace";
    // 获得访问token请求链接
    public static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // 创建菜单请求链接
    public static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //个性化菜单请求链接
    public static String CREATE_PERSONAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";
    // 网页授权获得用户信息请求链接
    public static String GET_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    //获取jsapi_ticket的请不该链接
    public static String GET_JSTICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=access_token_replace&type=jsapi";
    //token获得用户信息请求链接
    public static String GET_USER_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=token_replace&openid=openId_replace&lang=zh_CN";
    //模板消息参数颜色
    public static String MSG_PARAM_COLOR = "#5a5a5a";
    //标题颜色
    public static String MSG_TOP_COLOR = "#5a5a5a";
    //客服电话
    public static String SERVICE_PHONE = "18780287856";
    //access_token
    public static final String ACCESS_TOKEN = "access_token";
    //jsticket
    public static final String JSTICKET = "jsticket";
    //access_token
    public static final String ACCESS_TOKEN_BAJIETONG = "access_token_bajietong";
    //jsticket
    public static final String JSTICKET_BAJIETONG = "jsticket_bajietong";
    //APP端access_token
    public static final String ACCESS_TOKEN_JBZ_APP = "access_token_jbz_app";
    //当前时间
    private static final Long MILLISECONDS  = System.currentTimeMillis();

    public static  String WECHAT_INDEX;

    public static final String CONTACTUS="contactUs";

    public static final String CONTACTUSTEXT="咨询信息或是使用遇到困难，请联系我们:028-68308294 。服务时间:周一至周五 9:00-17:30";

    //微信提醒提前分钟数
    public static final int WX_TIME_SPAN_ONE = 1;
    public static final int WX_TIME_SPAN_FIVE = 5;
    public static final int WX_TIME_SPAN_FIFTEEN = 15;
    public static final int WX_TIME_SPAN_TWEENTY = 20;
    public static final int WX_TIME_SPAN_AFTER = -1;

    //禽饲料
    public static String BIRDSFEED;
    //猪饲料
    public static String PIGFEED;
    //鱼饲料
    public static String FISHFEED;
    //企业信息
    public static String COMPANYINFO;
    //意见反馈
    public static String FEEDBACK;
    //使用帮助
    public static String LOGIN_INDEX;

    public static void settingUrlJubaozhu(){
        WECHAT_INDEX=HOMEPAGE_URL+"/static/html/home/index.html?_d="+ MILLISECONDS;
        BIRDSFEED=HOMEPAGE_URL+"/static/html/shop/goods.html?typeId=1503&?_d="+MILLISECONDS;
        PIGFEED=HOMEPAGE_URL+"/static/html/shop/goods.html?typeId=1502&?_d="+MILLISECONDS;
        FISHFEED=HOMEPAGE_URL+"/static/html/shop/goods.html?typeId=1505&?_d="+MILLISECONDS;
        COMPANYINFO=HOMEPAGE_URL+"/static/html/shop/index.html?_d="+MILLISECONDS;
        FEEDBACK=HOMEPAGE_URL+"/static/html/help/feedback/accounts.html?_d="+ MILLISECONDS;
        LOGIN_INDEX=HOMEPAGE_URL+"/static/html/wechat/login_phone.html?_d="+MILLISECONDS;
    }

}










