package com.wh.controller.app;

import com.wh.entity.BaVersionInfo;
import com.wh.service.app.UpLoadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/upLoadController")
public class UpLoadController {

	@Resource(name ="UpLoadServiceImpl")
	private UpLoadService countService;
	String stringRtrue;
   


	/**
	 * 上传文件会自动绑定到MultipartFile中
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/uploadFile", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String uploadFile(HttpServletRequest request, BaVersionInfo baVersionInfo, @RequestParam(value="file",required=false) MultipartFile file) throws Exception  {
		String result =null;
		String basePath =  request.getScheme()+"://"+request.getServerName()+":"+ request.getServerPort()+request.getContextPath()+"/";
		// 如果文件不为空，写入上传路径
		if (!file.isEmpty()) {
			//文件有没有上传成功
			Boolean flagFile =	countService.createFile(request,file);
			if(flagFile){
				boolean regflag = (baVersionInfo.getF_Vertitle() == null && err0("请输入名字"))
						|| (baVersionInfo.getF_Verid() == null && err0("请输版本号"));

					}
				}

		return result;
	}
	


	/**
	 * 工具类
	 * @param string
	 * @return
	 */
	private boolean err0(String string) {
		// TODO Auto-generated method stub
		stringRtrue = string;
		return true;
	}

}
