package com.coffee.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@RequestMapping("file")
@Controller
public class FileController {

	/**
	 * 返回方法名
	 * 
	 * @param request
	 * @return 文件名
	 */
	@RequestMapping("uploadCrash")
	@ResponseBody
	public String springUpload(HttpServletRequest request) {
		String resurnFileName = "";
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			for (Iterator<String> it = multiRequest.getFileNames(); it.hasNext();) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(it.next());
				if (file != null) {
					resurnFileName = "E:/springUpload/" + file.getOriginalFilename();
					// 上传
					try {
						file.transferTo(new File(resurnFileName));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return resurnFileName;
	}

	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public String springUpload(@RequestParam("file") MultipartFile file) {
		long startTime = System.currentTimeMillis();
		try {
			System.out.println("fileName：" + file.getOriginalFilename());
			String path = "E:/springUpload/" + file.getOriginalFilename();

			File newFile = new File(path);
			// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			file.transferTo(newFile);
			long endTime = System.currentTimeMillis();
			System.out.println("方法二的运行时间：" + String.valueOf(endTime - startTime) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "111";
	}

}
