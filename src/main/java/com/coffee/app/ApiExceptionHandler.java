package com.coffee.app;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	// @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleException(Exception ex) {
		return ex.getMessage();
	}

	// @ExceptionHandler(RuntimeException.class)
	// @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	// @ResponseBody
	// public String handleUnexpectedServerError(RuntimeException ex) {
	// return ex.getMessage();
	// }
}