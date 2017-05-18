package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amaker.util.DBUtil;

public class ReserveDaoImpl {
	public boolean reserveTab(String personNo,String telString,String reserveDate,String name){
		boolean isSuccess=false;
		String sql = " insert into reservetbl(id,personNo,telString,reserveDate,name)values(?,?,?,?,?) ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			int id=0;
			String sql2 = " select max(id) as id  from reservetbl ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);
			if(rs.next()){
				id = rs.getInt(1);
			}
			id++;
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, id);
			pstmt.setString(2, personNo);
			pstmt.setString(3, telString);
			pstmt.setString(4, reserveDate);
			pstmt.setString(5, name);
			pstmt.executeUpdate();
			isSuccess=true;
			return isSuccess; 
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return false;
	}
}
