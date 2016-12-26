package com.coffee.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@RequestMapping("file")
@Controller
public class FileController {

	private String getCrashDir() {
		String crashDir = "";
		if (isLinux()) {
			crashDir = "/app/logs/crash/";
		} else {
			crashDir = "E:/springUpload/";
		}
		return crashDir;
	}

	/**
	 * 返回方法名
	 * 
	 * @param request
	 * @return 文件名
	 */
	@RequestMapping("uploadCrash")
	@ResponseBody
	public String springUpload(HttpServletRequest request) {
		String toSaveFileName = "";
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
					toSaveFileName = getCrashDir() + file.getOriginalFilename();
					try {
						File tmp = new File(toSaveFileName);
						if (tmp.exists() == false) {
							if (tmp.getParentFile().exists() == false) {
								tmp.getParentFile().mkdirs();
							}
							tmp.createNewFile();
						}
						file.transferTo(tmp);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return toSaveFileName;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("queryCrashFiles")
	public String queryCrashFiles(HttpServletRequest request) {
		String crashDir = getCrashDir();
		File crashFile = new File(crashDir);
		ArrayList<String> files = new ArrayList<>();
		files.addAll(Arrays.asList(crashFile.list()));
		request.setAttribute("items", files);
		return "crashFiles";
	}

	/**
	 * 查询指定log文件的内容
	 * 
	 * @return 返回文件内容
	 */
	@RequestMapping("queryLogContent")
	@ResponseBody
	public String queryLogContent(HttpServletRequest request) {
		String logFile = request.getParameter("id");
		File log = new File(getCrashDir(), logFile);
		StringBuilder sb = new StringBuilder();
		try {
			FileInputStream fin = new FileInputStream(log);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("<br>");
			}
			fin.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 删除指定文件 , 并查询剩下的文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteLogFile")
	public String deleteLogFile(HttpServletRequest request) {
		String logFile = request.getParameter("id");
		File log = new File(getCrashDir(), logFile);
		log.delete();
		return queryCrashFiles(request);
	}

	public static boolean isLinux() {
		return !System.getProperty("os.name").toLowerCase().startsWith("windows");
	}

}
