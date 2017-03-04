package com.coffee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * chrome test
 * @author coffee<br>2017年1月8日下午1:48:39
 */
@Controller
@RequestMapping("chrome")
public class ChromeController {
	
	@RequestMapping("html")
	@ResponseBody
	public String parseHtml(HttpServletRequest request){
		
		return "success";
	}
}
