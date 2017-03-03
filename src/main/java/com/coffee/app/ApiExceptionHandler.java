package com.coffee.app;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * app全局异常
 * 
 * @author coffee<br>
 *         2017年2月10日上午11:32:45
 */
@ControllerAdvice
public class ApiExceptionHandler {

	private Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class.getSimpleName());

	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	// @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleException(Exception ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		ex.printStackTrace(pw);
		pw.flush();
		sw.flush();
		// 打印错误信息
		log.error(sw.toString());
		return ex.getMessage();
	}

	// @ExceptionHandler(RuntimeException.class)
	// @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	// @ResponseBody
	// public String handleUnexpectedServerError(RuntimeException ex) {
	// return ex.getMessage();
	// }
}