package com.wh.utils;

import com.wh.entity.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
/**
 * 创建文件工具类
 */
@Service
public class FileUtil {
    /**
     * @param request
     * @param文件 file
     * @param路径 image
     * @return
     */
    public Result createFile(HttpServletRequest request, MultipartFile file, String path)  {
        // TODO Auto-generated method stub
        Result result = null;
        try {
            // 上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //路径加文件名
           String filePath =DateUtils.getShortYMDHMS()+filename;
            // 将上传文件保存到一个目标文件当中
            file.transferTo(new File(path +File.separator +filePath));
            result = new Result(true,filePath);
        } catch (Exception e) {
            result = new Result(false,e.getMessage());
        }
        return result;

    }
}
