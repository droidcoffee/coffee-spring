package com.coffee.app;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 * Controller添加ResponseBody以后会调用该方法, 处理返回值
 * 
 * @author coffee<br>
 *         2016年12月24日下午5:14:24
 */
public class RequestResponseBodyMethodProcessorExt extends RequestResponseBodyMethodProcessor {

	public RequestResponseBodyMethodProcessorExt(List<HttpMessageConverter<?>> messageConverters) {
		super(messageConverters);
	}

	public RequestResponseBodyMethodProcessorExt(List<HttpMessageConverter<?>> messageConverters, ContentNegotiationManager contentNegotiationManager) {
		super(messageConverters, contentNegotiationManager);
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws IOException,
			HttpMediaTypeNotAcceptableException {
		super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		request.setAttribute("responseBody", returnValue);
	}
}
