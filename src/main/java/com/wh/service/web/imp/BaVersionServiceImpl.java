package com.wh.service.web.imp;


import com.wh.dao.BaVersionDao;
import com.wh.entity.BaVersionInfo;
import com.wh.service.web.BaVersionService;
import com.wh.utils.DateUtils;
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


@Service
@Transactional
//此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class BaVersionServiceImpl implements BaVersionService {
	@Resource
	private BaVersionDao baVersionDao;
	/**
	 * 插入版本更新表数据
	 * @param
	 * @return
	 */
	@Override
	public void verInertData(BaVersionInfo versionInfo) {
		// TODO Auto-generated method stub
		baVersionDao.verInertData( versionInfo);
	}
	
	/**
	 * 查询版本更新信息
	 * @param
	 * @return
	 */
	@Override
	public List<BaVersionInfo> getVerQuery(String fileName) {
		// TODO Auto-generated method stub
		return baVersionDao.getVerQuery(fileName);
	}
	
	/**
	 * 删除版本更新信息
	 * @param
	 * @return
	 */
	@Override
	public void delVer(String f_Id) {
		// TODO Auto-generated method stub
		baVersionDao.delVer(f_Id);
	}
	
	/**
	 * 下载路径
	 * @param
	 * @return
	 */
	@Override
	public String downQuery(String path) {
		// TODO Auto-generated method stub
		return baVersionDao.downQuery(path);
	}

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
			String path = request.getServletContext().getRealPath("/images/");
			// 上传文件名
			String filename = file.getOriginalFilename();
			File filepath = new File(path, filename);
			// 判断路径是否存在，如果不存在就创建一个
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// 将上传文件保存到一个目标文件当中
			file.transferTo(new File(path + File.separator +DateUtils.getShortYMDHMS()+filename));
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			flag = false;
		}
		return flag;
		
	}

	/**
	 * 下载逻辑语句
	 * @param request
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<byte[]> downLoadFile(String path, String typeStr,
                                               HttpServletRequest request, BaVersionService countService) {
		//现在就创建一个线程模型，但是现在里面没有线程
		ExecutorService executorService = Executors.newCachedThreadPool();// 无限制大小线程池
		try {
			Future<ResponseEntity<byte[]>> future = executorService.submit(new DownLoadFile(path,typeStr,request,countService));
			return future.get();
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
			return null;  
		}finally {
			executorService.shutdown();
        }
		
		
		
	}
	
	/**
	 * 删除文件
	 * @param path
	 * @return
	 */
	@Override
	public Boolean deleteFile(String path) {
		 Boolean flag = false;  
        File file = new File(path);  
         // 路径为文件且不为空则进行删除  
         if (file.isFile() && file.exists()) {
             file.delete();  
             flag = true;  
         }  
		return flag;
	}
	
	


	
	
	

}
