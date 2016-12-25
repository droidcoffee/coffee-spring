package com.coffee.app;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * 
 * @author coffee<br>
 *         2016年12月25日下午4:42:48
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

	/**
	 * 改变ReturnValueHandlers的顺序<br>
	 * 加入自定义RequestResponseBodyMethodProcessorExt
	 * {@link ServletInvocableHandlerMethod#invokeAndHandle(org.springframework.web.context.request.ServletWebRequest, org.springframework.web.method.support.ModelAndViewContainer, Object...)<br> {
	 * @link HandlerMethodReturnValueHandlerComposite#getReturnValueHandler(MethodParameter)}
	 */
	@SuppressWarnings("unchecked")
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter handlerAdapter = super.requestMappingHandlerAdapter();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new StringHttpMessageConverter());
		RequestResponseBodyMethodProcessorExt processor = new RequestResponseBodyMethodProcessorExt(messageConverters);
		try {
			Method getDefaultReturnValueHandlers = handlerAdapter.getClass().getDeclaredMethod("getDefaultReturnValueHandlers");
			getDefaultReturnValueHandlers.setAccessible(true);
			List<HandlerMethodReturnValueHandler> defaultReturnValueHandlers = (List<HandlerMethodReturnValueHandler>) getDefaultReturnValueHandlers.invoke(handlerAdapter);
			defaultReturnValueHandlers.add(0, processor);
			handlerAdapter.setReturnValueHandlers(defaultReturnValueHandlers);
			// handlerAdapter.getReturnValueHandlers().add(0, processor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return handlerAdapter;
	}

}
