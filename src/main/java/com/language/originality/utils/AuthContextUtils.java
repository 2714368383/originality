package com.language.originality.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.language.originality.constant.AuthConstant;

/**
 * 
 * @author zkx 获取当前请求的httprequest
 *
 */
public class AuthContextUtils {

	/**
	 * 获取request
	 * 
	 * @return com.language.originality.utils.AuthContextUtils.getCurrentContextRequest()
	 */
	private static HttpServletRequest getCurrentContextRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			return request;
		}
		return null;
	}

	/**
	 * 获取token的值
	 * @return
	 */
	public static String getAccessToken() {
		HttpServletRequest currentContextRequest = getCurrentContextRequest();
		if (null != currentContextRequest) {
			return currentContextRequest.getHeader(AuthConstant.accessToken);
		}
		return "";
	}
}
