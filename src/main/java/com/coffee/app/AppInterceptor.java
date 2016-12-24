package com.coffee.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * app项目公用拦截器
 * 
 * @author coffee<br>
 *         2016年12月24日下午6:07:43
 */
public class AppInterceptor extends HandlerInterceptorAdapter {

	Logger log = LoggerFactory.getLogger("AppInterceptor");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		super.preHandle(request, response, handler);
		log.info(request.getRequestURI());
		log.info(request.getParameterMap() + "");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		Object result = request.getAttribute("responseBody");
		log.info(result + "");
		System.out.println(result);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}