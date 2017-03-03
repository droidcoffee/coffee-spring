package com.coffee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("coffee")
public class HelloController {

	@RequestMapping("hello")
	@ResponseBody
	public String sayHello(HttpServletRequest request) {
		String exception = request.getParameter("exception");
		if (exception != null) {
			System.out.println(1 / 0);
		}
		return "你好, 世界";
	}

}
