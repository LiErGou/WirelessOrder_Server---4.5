package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.amaker.dao.ChangeTableDao;
import com.amaker.util.DBUtil;
/**
 * @author 郭宏志
 * 完成转台功能
 */
public class ChangeTableDaoImpl implements ChangeTableDao {

	public void changeTable(int orderId, int tableId) {
		// 更新SQL语句
		
		String sql = " update tabletbl set flag = 0,description='空桌' where id = "+
		  " (select tableId from ordertbl  as ot where ot.id = ?)";
		String sql2 = " update ordertbl set tableId = ? where id = ? ";
		String sql3 = " update tabletbl set flag = 1,description='就餐' where id = ?";
		
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		
		try {
			conn.setAutoCommit(false);//在一个事务里进行多个操作。就必然将setAutoCommit的参数设置成false，在多个操作的最后调用conn.commit()方法，进行手动提交
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			// 更新桌位状态
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			
			// 设置参数
			pstmt.setInt(1, tableId);
			pstmt.setInt(2, orderId);
			// 更新订单表
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, tableId);
			// 更新桌位状态
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {//当try语句中出现异常是时，会执行catch中的语句，java运行时系统会自动将catch括号中的Exception e 初始化，也就是实例化Exception类型的对象。e是此对象引用名称。然后e（引用）会自动调用Exception类中指定的方法，也就出现了e.printStackTrace() ;。
			                       //,printStackTrace()方法的意思是：在命令行打印异常信息在程序中出错的位置及原因
			e.printStackTrace();
			try {
				conn.rollback();//回滚，当conn.commit()失败时回滚从而保证数据库的完整性，避免表在没有提交也没有回滚的情况下锁死
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			util.closeConn(conn);
		}
	}
}
