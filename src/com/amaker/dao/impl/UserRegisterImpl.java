package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amaker.dao.UserDao;
import com.amaker.dao.UserRegister;
import com.amaker.entity.User;
import com.amaker.util.DBUtil;
/**
 * 
 * @author 郭宏志
 * 用户登录DAO实现类
 */
public class UserRegisterImpl implements UserRegister {
	
	/**
	 * 注册中
	 */

	public User register(String account, String password,int[] lf,int[] df,int[] lm,int[] dm,int genderInt){		// 查询SQL语句
		if(isGuestExsit(account)){
			return null;
		}
		String gender;
		if(genderInt==1){
			gender="男";
		}else gender="女";
		String sql = " insert into guesttbl(id,account,password,name,gender,permission,remark)values(?,?,?,?,?,?,?) ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			int id=0;
			String sql2 = " select max(id) as id  from guesttbl ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);
			if(rs.next()){
				id = rs.getInt(1);
			}
			id++;
			int permission=5;
			String remark="无";
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, id);
			pstmt.setString(2, account);
			pstmt.setString(3, password);
			pstmt.setString(4, account);
			pstmt.setString(5, gender);
			pstmt.setInt(6, permission);
			pstmt.setString(7, remark);
//			pstmt.setInt(8, likeFlavor);
//			pstmt.setInt(9, dislikeFlavor);
//			pstmt.setInt(10, likeMaterial);
//			pstmt.setInt(11, dislikeMaterial);
			// 执行更新
			pstmt.executeUpdate();
			
			//设置用户喜爱口味数据表
			String sql3;
			PreparedStatement pstmt3;
			for(int i=0;i<lf.length ;i++){
				if(lf[i]!=0){
					sql3 = " insert into rel_guest_likeFlavortbl(guestID,likeFlavorID)values(?,?) ";
					pstmt3 = conn.prepareStatement(sql3);
					pstmt3.setInt(1, id);
					pstmt3.setInt(2, i+1);
					pstmt3.executeUpdate();
				}
			}
			//设置用户不喜爱口味数据表
			for(int i=0;i<df.length ;i++){
				if(df[i]!=0){
					sql3 = " insert into rel_guest_dislikeFlavortbl(guestID,dislikeFlavorID)values(?,?) ";
					pstmt3 = conn.prepareStatement(sql3);
					pstmt3.setInt(1, id);
					pstmt3.setInt(2, i+1);
					pstmt3.executeUpdate();
				}
			}
			//设置用户喜爱食材数据表
			for(int i=0;i<lm.length ;i++){
				if(lm[i]!=0){
					sql3 = " insert into rel_guest_likeMaterialtbl(guestID,likeMaterialID)values(?,?) ";
					pstmt3 = conn.prepareStatement(sql3);
					pstmt3.setInt(1, id);
					pstmt3.setInt(2, i+1);
					pstmt3.executeUpdate();
				}
			}
			//设置用户不喜爱食材数据表
			for(int i=0;i<dm.length ;i++){
				if(dm[i]!=0){
					sql3 = " insert into rel_guest_dislikeMaterialtbl(guestID,dislikeMaterialID)values(?,?) ";
					pstmt3 = conn.prepareStatement(sql3);
					pstmt3.setInt(1, id);
					pstmt3.setInt(2, i+1);
					pstmt3.executeUpdate();
				}
			}
			User u = new User();
			u.setId(id);
			u.setAccount(account);
			u.setPassword(password);
			u.setName(account);
			u.setPermission(permission);
			u.setRemark(remark);
			u.setDf(df);
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}
	private boolean isGuestExsit(String account){
		String sql = " select name from guesttbl where account=? ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置查询参数
			pstmt.setString(1, account);
			ResultSet rs = pstmt.executeQuery();
			// 判断用户是否存在
			if (rs.next()) {
				// 获得用户信息
				return true;
			}else return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
	}
		return true;
	}
}
