package com.newhope.moneyfeed.common.vo;

import java.util.Date;

/**  
* @ClassName: WechartMsgVO  
* @Description: 微信消息推送数据传输类
* @author luoxl
* @date 2016年10月18日 下午4:17:20  
*    
*/
public class WechatMsgVO{
	//创建者
	private Integer creator;
	//微信消息接收者ID
	private String openid;
	//微信消息接收圈子ID
	private Integer circleId;
	//订单ID
	private Integer orderId;
	//商品id
	private Integer productId;
	//用户父ID
	private Integer userId;
	//要跳转的页面路径
	private String url;
	private String url2;
	/** 价格有效日 */
    private Date priceDate;
    //备注
    private String remark;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getCircleId() {
		return circleId;
	}
	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl2() {
		return url2;
	}
	public void setUrl2(String url2) {
		this.url2 = url2;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Date getPriceDate() {
		return priceDate;
	}
	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
