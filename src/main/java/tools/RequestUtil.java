package tools;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	
	public static String GetString(ServletRequest request, String paramName, String alternativeValue) {
		String paramValue = request.getParameter(paramName);
		if (Utility.isEmptyString(paramValue)) {
			return alternativeValue;
		} else {
			return paramValue;
		}
	}
	
	public static Integer GetInteger(ServletRequest request, String paramName, Integer alternativeValue) {
		String paramValue = request.getParameter(paramName);
		
		if (!Utility.isEmptyString(paramValue)) {
			try {
				alternativeValue = Integer.parseInt(paramValue);
			} catch (Exception e) {
				// do nothing.
			}
		}
		return alternativeValue;
	}
	
	public static float getFloat(ServletRequest request, String paramName, float alternativeValue){
		String paramValue = request.getParameter(paramName);
		
		if (!Utility.isEmptyString(paramValue)) {
			try {
				alternativeValue = Float.parseFloat(paramValue);
			} catch (Exception e) {
				// do nothing.
			}
		}
		return alternativeValue;
	}
	
	public static Integer GetPositiveInteger(ServletRequest request, String paramName, Integer alternativeValue) {
		String paramValue = request.getParameter(paramName);
		
		if (!Utility.isEmptyString(paramValue)) {
			try {
				int tempValue = Integer.parseInt(paramValue);
				if (tempValue >= 0) {
					alternativeValue = tempValue;
				}
			} catch (Exception e) {
				// do nothing.
			}
		}
		return alternativeValue;
	}
	
	public static Boolean GetBoolean(ServletRequest request, String paramName, Boolean alternativeValue) {
		String paramValue = request.getParameter(paramName);
		
		if (!Utility.isEmptyString(paramValue)) {
			try {
				alternativeValue = Boolean.parseBoolean(paramValue);
			} catch (Exception e) {
				// do nothing
			}
		}
		
		return alternativeValue;
	}
	
	public static String getPageUrl(HttpServletRequest request) {
		return getPageUrl(request, null);
	}
	
	public static String getPageUrl(HttpServletRequest request, String specialServletPath) {
		String queryString = request.getQueryString() == null ? "" : request.getQueryString().replaceAll("([&]*)page=\\d+", "")
				.replace("?&", "?");
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		
		if (Utility.isEmptyString(specialServletPath)) {
			specialServletPath = servletPath;
		} else {
			specialServletPath = servletPath.substring(0, servletPath.lastIndexOf("/") + 1) + specialServletPath;
		}
		
		String pageUrl = specialServletPath + "?page=";
		if (!Utility.isEmptyString(queryString)) {
			pageUrl = specialServletPath + "?" + queryString + "&page=";
		}
		
		return contextPath + pageUrl;
	}

	/*
	 * Example: http://www.qingshuxuetang.com/demo/Student/CourseStudy?courseId=1019
	 * Return: http://www.qingshuxuetang.com/demo/Student/
	 */
	public static String getParentUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		int index = request.getServletPath().lastIndexOf("/");
		if (index != -1) {
			servletPath = servletPath.substring(0, index);
		}
		
		return contextPath + servletPath;
	}
	
	/*
	 * Example: http://www.qingshuxuetang.com/demo/Student/CourseStudy?courseId=1019
	 * Return: http://www.qingshuxuetang.com
	 */
	public static String getRootUrl(HttpServletRequest request) {
		String uri = request.getScheme()
				+ "://"
				+ request.getServerName()
				+ ("http".equals(request.getScheme()) && request.getServerPort() == 80 ? "" : ":" + request.getServerPort());
		
		return uri;
	}
}
