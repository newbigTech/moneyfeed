package com.newhope.moneyfeed.common.config;

import com.alibaba.fastjson.JSON;
import com.newhope.moneyfeed.common.annotation.ShowCostTime;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Component
public class HttpMessageSender {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static RequestConfig requestConfig;
	
	private final int connectionPoolSize = 10;

	/** 接入超时时间*/
	private final int ConnectTimeout = 1 * 5 * 1000;

	/** 链接断开超时时间*/
	private final int SocketTimeout = 1 * 60 * 6 * 1000;
	
	private final int ConnectionRequestTimeout = 1 * 60 * 6 * 1000;
	
	private final String Charset = "utf-8";

	/** ssl链接配置 */
	private static SSLConnectionSocketFactory sslConnectionSocketFactory;

	@PostConstruct
	public void init(){
		/*
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();

		// 设置连接池大小
		connMgr.setMaxTotal(connectionPoolSize);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
		*/

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(ConnectTimeout);
		// 设置读取超时
		configBuilder.setSocketTimeout(SocketTimeout);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(ConnectionRequestTimeout);
		// 在提交请求之前 测试连接是否可用
		// configBuilder.setStaleConnectionCheckEnabled(true);
		requestConfig = configBuilder.build();
		enableSSL();
	}

	/**
	 * 启用ssl
	 */
	private void enableSSL() {
		try {
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[] { manager }, null);
			sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * 发送 post请求
	 */
	public String postRequest(String url, Map<String, String> paramMap, boolean jsonSubmit) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		// 创建httppost
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		httpPost.setProtocolVersion(HttpVersion.HTTP_1_1);
		CloseableHttpResponse response = null;
		try {
			if (!jsonSubmit) {
				// 创建参数队列
				List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
				// 设置请求参数
				if (paramMap != null) {
					for (Entry<String, String> entry : paramMap.entrySet()) {
						// 得到参数名
						String key = entry.getKey();
						String value = entry.getValue();
						requestParams.add(new BasicNameValuePair(key, value));
					}
				}
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(requestParams, Charset);
				httpPost.setEntity(formEntity);
				httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=" + Charset);
			} else {
				StringEntity stringEntity = new StringEntity(JSON.toJSONString(paramMap), Charset);
				httpPost.setEntity(stringEntity);
				httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=" + Charset);
			}
			httpPost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, Charset);
		} catch(Throwable e) {
			throw new RuntimeException(e);
		} finally {
			closeResources(httpClient, response);
		}
	}

	/**
	 * 发送 post请求
	 */
	public String postRequest(String url, String jsonStr) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		// 创建httppost
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		httpPost.setProtocolVersion(HttpVersion.HTTP_1_1);
		CloseableHttpResponse response = null;
		try {
			StringEntity stringEntity = new StringEntity(jsonStr, Charset);
			httpPost.setEntity(stringEntity);
			httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=" + Charset);
			httpPost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, Charset);
		} catch(Throwable e) {
			throw new RuntimeException(e);
		} finally {
			closeResources(httpClient, response);
		}
	}


	/**
	 * 发送 post请求
	 */
	public String postDeleteRequest(String url) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		// 创建httppost
		HttpDelete httpdelete=new HttpDelete();
		httpdelete.setConfig(requestConfig);
		httpdelete.setProtocolVersion(HttpVersion.HTTP_1_1);
		CloseableHttpResponse response = null;
		try {
			httpdelete.setURI(URI.create(url));
			httpdelete.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=" + Charset);
			httpdelete.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
			response = httpClient.execute(httpdelete);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, Charset);
		} catch(Throwable e) {
			throw new RuntimeException(e);
		} finally {
			closeResources(httpClient, response);
		}
	}


	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 * @param url
	 * @param
	 * @return
	 */
	@ShowCostTime
	public String postRequestXml(String url, String xml) throws Exception {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		// 创建httppost
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		httpPost.setProtocolVersion(HttpVersion.HTTP_1_1);
		CloseableHttpResponse response = null;
		try {
			StringEntity stringEntity = new StringEntity(xml, Charset);
			httpPost.setEntity(stringEntity);
			httpPost.setHeader(HTTP.CONTENT_TYPE, "application/xml;charset=" + Charset);
			httpPost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, Charset);
		} catch(Throwable e) {
			throw new Exception(e);
		} finally {
			closeResources(httpClient, response);
		}
	}



	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果(自定义config，设置代理，方便抓包)
	 * @param url
	 * @param
	 * @return
	 */
	public String postRequestXml(String url, String xml,String poxyUrl,Integer port) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		// 创建httppost
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfigCopy = RequestConfig.copy(requestConfig)
				.setProxy(new HttpHost(poxyUrl, port))
				.build();
		httpPost.setConfig(requestConfigCopy);
		httpPost.setProtocolVersion(HttpVersion.HTTP_1_1);
		CloseableHttpResponse response = null;
		try {
			StringEntity stringEntity = new StringEntity(xml, Charset);
			httpPost.setEntity(stringEntity);
			httpPost.setHeader(HTTP.CONTENT_TYPE, "application/xml;charset=" + Charset);
			httpPost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, Charset);
		} catch(Throwable e) {
			throw new RuntimeException(e);
		} finally {
			closeResources(httpClient, response);
		}
	}

	/**
	 * 发送 get请求
	 * @param urlValue
	 * @param paramMap
	 */
	public String getRequest(String urlValue, Map<String, String> paramMap) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		CloseableHttpResponse response = null;
		try {
			StringBuilder url = new StringBuilder(urlValue);
			if (paramMap != null && paramMap.size() > 0) {
				boolean firstAppend = true;
				for (Entry<String, String> entry : paramMap.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					if (firstAppend) {
						if (url.indexOf("?") != -1) {
							url.append("&").append(key).append("=").append(value);
						} else {
							url.append("?").append(key).append("=").append(value);
						}
						firstAppend = false;
					} else {
						url.append("&").append(key).append("=").append(value);
					}
				}
			}
			// 创建httpget.
			HttpGet httpGet = new HttpGet(url.toString());
			httpGet.setConfig(requestConfig);
			httpGet.setProtocolVersion(HttpVersion.HTTP_1_1);
			httpGet.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
			// 执行get请求.
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, Charset);
		} catch(Throwable e) {
			throw new RuntimeException(e);
		} finally {
			closeResources(httpClient, response);
		}
	}

	/**
	 * 发送 get请求，下载文件
	 * @param urlValue
	 * @param paramMap
	 * @return
	 */
	public byte[] getFileBytes(String urlValue, Map<String, String> paramMap) {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		CloseableHttpResponse response = null;
		try {
			StringBuilder url = new StringBuilder(urlValue);
			if (paramMap != null && paramMap.size() > 0) {
				boolean firstAppend = true;
				for (Entry<String, String> entry : paramMap.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					if (firstAppend) {
						if (url.indexOf("?") != -1) {
							url.append("&").append(key).append("=").append(value);
						} else {
							url.append("?").append(key).append("=").append(value);
						}
						firstAppend = false;
					} else {
						url.append("&").append(key).append("=").append(value);
					}
				}
			}
			// 创建httpget.
			HttpGet httpGet = new HttpGet(url.toString());
			httpGet.setConfig(requestConfig);
			httpGet.setProtocolVersion(HttpVersion.HTTP_1_1);
			httpGet.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
			// 执行get请求.
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			byte[] bytes = EntityUtils.toByteArray(entity);
			return bytes;
		} catch(Throwable e) {
			throw new RuntimeException(e);
		} finally {
			closeResources(httpClient, response);
		}
	}

	/**
	 * 重写方法，取消SSL验证
	 */
	private static X509TrustManager manager = new X509TrustManager() {

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

		}

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}
	};

	/**
	 * 发送SSL请求
	 */
	public String postSSLRequest(String reqURL, Map<String, Object> paramMap, boolean jsonSubmit) {
		RequestConfig reqConfig = RequestConfig.custom().setExpectContinueEnabled(true)
				.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();

		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sslConnectionSocketFactory)
				.build();

		// 设置连接池
		PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(
				socketFactoryRegistry);
		clientConnectionManager.setMaxTotal(connectionPoolSize);
		clientConnectionManager.setDefaultMaxPerRoute(connectionPoolSize);
		// 获取httpclient
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(clientConnectionManager)
				.setDefaultRequestConfig(reqConfig).build();
		HttpPost httpPost = new HttpPost(reqURL);
		httpPost.setConfig(requestConfig);
		httpPost.setProtocolVersion(HttpVersion.HTTP_1_1);
		CloseableHttpResponse response = null;
		try {
			if (!jsonSubmit) {
				// 创建参数队列
				List<NameValuePair> requestParams = new ArrayList<NameValuePair>();

				// 设置请求参数
				if (paramMap != null) {
					for (Entry<String, Object> entry : paramMap.entrySet()) {
						// 得到参数名
						String key = entry.getKey();
						String value = entry.getValue().toString();
						requestParams.add(new BasicNameValuePair(key, value));
					}
				}
				// 表单编码实体
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(requestParams, Charset);
				httpPost.setEntity(formEntity);
				httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=" + Charset);
			} else {
				StringEntity stringEntity = new StringEntity(JSON.toJSONString(paramMap), Charset);
				httpPost.setEntity(stringEntity);
				httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=" + Charset);
			}
			httpPost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
			// 执行请求
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, Charset);
		} catch(Throwable e) {
			throw new RuntimeException(e);
		} finally {
			// 关闭连接,释放资源
			closeResources(httpClient, response);
		}
	}

	/**
	 * 发送SSL请求
	 */
	public String postSSLRequest(String reqURL, String jsonStr) {
		RequestConfig reqConfig = RequestConfig.custom().setExpectContinueEnabled(true)
				.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();

		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sslConnectionSocketFactory)
				.build();

		// 设置连接池
		PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(
				socketFactoryRegistry);
		clientConnectionManager.setMaxTotal(connectionPoolSize);
		clientConnectionManager.setDefaultMaxPerRoute(connectionPoolSize);

		// 获取httpclient
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(clientConnectionManager)
				.setDefaultRequestConfig(reqConfig).build();
		HttpPost httpPost = new HttpPost(reqURL);
		httpPost.setConfig(requestConfig);
		httpPost.setProtocolVersion(HttpVersion.HTTP_1_1);
		CloseableHttpResponse response = null;
		try {
			StringEntity stringEntity = new StringEntity(jsonStr, Charset);
			httpPost.setEntity(stringEntity);
			httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
			httpPost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
			// 执行请求
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, Charset);
		} catch(Throwable e) {
			throw new RuntimeException(e);
		} finally {
			// 关闭连接,释放资源
			closeResources(httpClient, response);
		}
	}

	/**
	 * 发送 ssl get请求
	 * @param urlValue
	 * @param paramMap
	 */
	public String getSSLRequest(String urlValue, Map<String, String> paramMap) {
		RequestConfig reqConfig = RequestConfig.custom().setExpectContinueEnabled(true)
				.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sslConnectionSocketFactory)
				.build();
		// 设置连接池
		PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(
				socketFactoryRegistry);
		clientConnectionManager.setMaxTotal(connectionPoolSize);
		clientConnectionManager.setDefaultMaxPerRoute(connectionPoolSize);
		// 获取httpclient
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(clientConnectionManager)
				.setDefaultRequestConfig(reqConfig).build();
		CloseableHttpResponse response = null;
		try {
			StringBuilder url = new StringBuilder(urlValue);
			if (paramMap != null && paramMap.size() > 0) {
				boolean firstAppend = true;
				for (Entry<String, String> entry : paramMap.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					if (firstAppend) {
						if (url.indexOf("?") != -1) {
							url.append("&").append(key).append("=").append(value);
						} else {
							url.append("?").append(key).append("=").append(value);
						}
						firstAppend = false;
					} else {
						url.append("&").append(key).append("=").append(value);
					}
				}
			}
			// 创建httpget.
			HttpGet httpGet = new HttpGet(url.toString());
			httpGet.setConfig(requestConfig);
			httpGet.setProtocolVersion(HttpVersion.HTTP_1_1);
			httpGet.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
			// 执行get请求.
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, Charset);
		} catch(Throwable e) {
			throw new RuntimeException(e);
		} finally {
			// 关闭连接,释放资源
			closeResources(httpClient, response);
		}
	}

	/**
	 * 关闭资源 
	 * @param httpClient
	 * @param response
	 */
	private void closeResources(CloseableHttpClient httpClient, CloseableHttpResponse response) {
		try {
			if (response != null) {
				response.close();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		} catch (IOException e) {
			log.error("", e);
		}
	}

}
