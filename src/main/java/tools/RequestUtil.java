package tools;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	
	private RequestUtil() {
		
	}
	
	public static String getString(ServletRequest request, String paramName, String alternativeValue) {
		String paramValue = request.getParameter(paramName);
		if (Utility.isEmptyString(paramValue)) {
			return alternativeValue;
		} else {
			return paramValue;
		}
	}
	
	public static Integer getInteger(ServletRequest request, String paramName, Integer alternativeValue) {
		String paramValue = request.getParameter(paramName);
		
		if (!Utility.isEmptyString(paramValue)) {
			try {
				return Integer.parseInt(paramValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return alternativeValue;
	}
	
	public static float getFloat(ServletRequest request, String paramName, float alternativeValue){
		String paramValue = request.getParameter(paramName);
		
		if (!Utility.isEmptyString(paramValue)) {
			try {
				return Float.parseFloat(paramValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return alternativeValue;
	}
	
	public static Integer getPositiveInteger(ServletRequest request, String paramName, Integer alternativeValue) {
		String paramValue = request.getParameter(paramName);
		
		if (!Utility.isEmptyString(paramValue)) {
			try {
				int tempValue = Integer.parseInt(paramValue);
				if (tempValue >= 0) {
					return tempValue;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return alternativeValue;
	}
	
	public static Boolean getBoolean(ServletRequest request, String paramName, Boolean alternativeValue) {
		String paramValue = request.getParameter(paramName);
		
		if (!Utility.isEmptyString(paramValue)) {
			try {
				return Boolean.parseBoolean(paramValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return alternativeValue;
	}
}
