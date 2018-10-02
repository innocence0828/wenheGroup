package com.wh.service.app.impl;

import com.wh.dao.TimedTaskDao;
import com.wh.entity.BsBookFlow;
import com.wh.service.app.TimedTaskService;
import com.wh.utils.JDBCConnFacory;
import com.wh.utils.JdbcUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@Service
@Transactional
//此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class TimedTaskServiceImpl implements TimedTaskService {
	@Resource
	private TimedTaskDao taskDao;
	
	/**
	 * 删除COPY_BS_BOOKFLOW表
	 * @param
	 * @return
	 */
	@Override
	public void copyDeleteBookFlows() {
		// TODO Auto-generated method stub
		taskDao.copyDeleteBookFlows();
	}
	
	
	/**
	 * 插入COPY_BS_BOOKFLOW
	 * @param
	 * @return
	 */
	@Override
	public void copyAddAllData() {
		//复制另一张表
		taskDao.copyAddAllData();
		//插入另一个数据库的表
		taskDao.insertToTestDB();
	}  
	
	/**
	 * 插入到另一个数据库里
	 * @param
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public void dbwhjtTestAddAllData(List<BsBookFlow> basBookFlows) throws SQLException {
		JdbcUtils jdbcUtils = new JdbcUtils();
		Connection conn  = JDBCConnFacory.getConnection();
		String sql1 = "delete from bs_bookflow";
		boolean flag1 = jdbcUtils.updateByPreparedStatement(sql1, null);
		if(flag1){
			String sql2 = "INSERT INTO bs_bookflow VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				boolean flag2 = jdbcUtils.updateByPreparedStatement2(sql2, basBookFlows);
				System.out.println("导入"+flag2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("ba_versionInfo_数据库删除失败");
		}
		if(conn!=null)
		 conn.close();
		
	}
}
