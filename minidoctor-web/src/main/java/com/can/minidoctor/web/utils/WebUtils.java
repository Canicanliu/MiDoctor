package com.can.minidoctor.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Web工具类
 * 
 * @author liziliang
 * 
 */
public class WebUtils {
	protected static Logger LOG = LoggerFactory.getLogger(WebUtils.class);

	/**
	 * 获取Cookie
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) {
		LOG.info("begin invoke getCookie, cookieName=" + cookieName);
		// 读cookie
		String cookieValue = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				//LOG.info("load Cookie:" + c.getName() + "=" + c.getValue());
				if (c.getName().equalsIgnoreCase(cookieName)) {
					cookieValue = c.getValue();
					break;
				}

			}
		}

		LOG.info("getCookie invoked, cookieValue=" + cookieValue);

		return cookieValue;
	}

	/**
	 * 保存Cookie
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue) {
		LOG.info("begin invoke addCookie, cookieName=" + cookieName + ",cookieValue=" + cookieValue);
		String domainStr = null;
//		try {
//			LOG.info("access url="+request.getRequestURL().toString());
//			domainStr = getTopDomainWithoutSubdomain(request.getRequestURL().toString());
//		} catch (MalformedURLException e) {
//			LOG.error("解释域名出错", e);
//		}
		domainStr = getReuqestDoamin(request);
		String domain1 = domainStr.substring(7, domainStr.length() - 1);

		LOG.info("get the domain is=" + domain1);
		Cookie cookie = new Cookie(cookieName, cookieValue);
//		Cookie cookie1 = new Cookie(cookieName, cookieValue);
		if (domainStr != null) {
			cookie.setDomain(domain1);
//			cookie1.setDomain("cmms2.midea.com");
		}

		//int maxAge = 30 * 24 * 60 * 60;
		int maxAge = 60 * 60;
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
//		cookie1.setMaxAge(maxAge);
//		cookie1.setPath("/");
		response.addCookie(cookie);
//		response.addCookie(cookie1);

		LOG.info("Cookie saved! >> cookieName=" + cookieName + ",cookieValue=" + cookieValue + ",domain=" + cookie.getDomain());

		LOG.info("test getCookie >>cookieName=" + cookieName + ",cookieValue=" + getCookie(request, cookieName));
	}

	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                 String cookieValue, Integer expire) {
		LOG.info("begin invoke addCookie, cookieName=" + cookieName + ",cookieValue=" + cookieValue);
		String domainStr = null;
		domainStr = getReuqestDoamin(request);
		String domain1 = domainStr.substring(7, domainStr.length() - 1);

		LOG.info("get the domain is=" + domain1);
		Cookie cookie = new Cookie(cookieName, cookieValue);
		if (domainStr != null) {
			cookie.setDomain(domain1);
		}

		cookie.setMaxAge(expire);
		cookie.setPath("/");
		response.addCookie(cookie);

		LOG.info("Cookie saved! >> cookieName=" + cookieName + ",cookieValue=" + cookieValue + ",domain=" + cookie.getDomain());

		LOG.info("test getCookie >>cookieName=" + cookieName + ",cookieValue=" + getCookie(request, cookieName));
	}

	/**
	 * 判断请求是否为异常、ajax请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 向response输出内容
	 * 
	 * @param response
	 * @param jsonData
	 */
	public static void printRespData(HttpServletResponse response, String jsonData) {
		response.setContentType("text/json; charset=UTF-8");
		LOG.info("response输出内容:{}",jsonData);
		PrintWriter out;
		try {
			out = response.getWriter();

			out.print(jsonData);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOG.error("输出response失败", e);
		}

	}
	
	/**
	 * 向response输出HTML或 js内容
	 * 
	 * @param response
	 * @param jsonData
	 */
	public static void printHtmlData(HttpServletResponse response, String htmlData) {
		response.setContentType("text/html; charset=UTF-8");
		LOG.info("response输出内容:{}",htmlData);
		PrintWriter out;
		try {
			out = response.getWriter();

			out.print(htmlData);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOG.error("输出response失败", e);
		}

	}

	//获取访问请求的域名
	public static String getReuqestDoamin(HttpServletRequest request){
		StringBuffer url = request.getRequestURL();  
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString(); 
		return tempContextUrl;
	}
	
	// 获取顶级域名
	private static String getTopDomainWithoutSubdomain(String url) throws MalformedURLException {

		String host = new URL(url).getHost().toLowerCase();
		Pattern pattern = Pattern.compile(
				"[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络)");
		Matcher matcher = pattern.matcher(host);
		while (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	public static String getRemoteIp(HttpServletRequest request) {
		String clientIp = request.getHeader("X-Real-IP");
		if(clientIp == null || clientIp.length() == 0 ||"unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getRemoteAddr();
		}

		if(clientIp == null || clientIp.length() == 0 ||"unknown".equalsIgnoreCase(clientIp)) {
			try {
				clientIp = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				LOG.info("获取本地ip失败。e:{}", e.getMessage());
				return "127.0.0.1";
			}
		}
		return clientIp;
	}


}
