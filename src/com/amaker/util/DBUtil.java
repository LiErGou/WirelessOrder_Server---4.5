package com.amaker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author hz.guo
 *	数据库工具类
 */
public class DBUtil {
	
	/*
	 * 关闭数据库连接
	 */
	public void closeConn(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 打开数据库连接
	 */
	public Connection openConnection() {
		Properties prop = new Properties();
		String driver = null;
		String url = null;
		String username = null;
		String password = null;
		System.out.println("开始连接数据库。");
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream(
					"DBConfig.properties"));

			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			Class.forName(driver);
			Connection conn11 = DriverManager.getConnection(url, username, password);
			System.out.println(conn11+"连接成功");
			return conn11;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
