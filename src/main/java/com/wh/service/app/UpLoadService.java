package com.wh.service.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UpLoadService {

	
	/**
	 * 创建文件夹
	 * @param request
	 * @param file
	 * @return
	 */
	Boolean createFile(HttpServletRequest request, MultipartFile file);
	

	

	
}
