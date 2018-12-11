package com.wh.service.app.impl;


import com.wh.dao.BaVersionDao;
import com.wh.entity.BaVersionInfo;
import com.wh.service.app.UpLoadService;
import com.wh.service.web.BaVersionService;
import com.wh.service.web.imp.DownLoadFile;
import com.wh.utils.FileUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Service("UpLoadServiceImpl")
//此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class UpLoadServiceImpl implements UpLoadService {

	@Resource
	private FileUtil  fileUtil;


	/**
	 * 创建文件夹
	 * @param request
	 * @param file
	 * @return
	 */
	@Override
	public Boolean createFile(HttpServletRequest request, MultipartFile file) {
		// TODO Auto-generated method stub
		Boolean flag = false;
		try {
			// 上传文件路径
			String path = request.getServletContext().getRealPath("/images1/");
			fileUtil.createFile(request,file,path);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			flag = false;
		}
		return flag;

	}

	
	

}
