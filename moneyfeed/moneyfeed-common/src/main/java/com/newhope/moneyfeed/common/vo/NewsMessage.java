package com.newhope.moneyfeed.common.vo;

import java.util.List;

/**  
* @ClassName: NewsMessage  
* @Description: 图文消息个数，限制为10条以内
* @author luoxl
* @date 2016年9月22日 下午3:12:16  
*    
*/
public class NewsMessage extends BaseMessage{
	private int ArticleCount;
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<News> getArticles() {
		return Articles;
	}
	public void setArticles(List<News> articles) {
		Articles = articles;
	}
	private List<News> Articles;
}
