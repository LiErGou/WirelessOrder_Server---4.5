package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amaker.dao.GetHotMenuDao;
import com.amaker.dao.GetMenuDao;
import com.amaker.entity.CheckTable;
import com.amaker.entity.DishMenu;
import com.amaker.util.DBUtil;

public class GetHotMenuDaoImpl implements GetMenuDao{
	public List<DishMenu> getMenuList(int guestId) {
		// 查询SQL语句
		String sql =" select id,typeId,name,price,pic,remark,discribe,grade" +
				" from menutbl order by grade desc";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			List<DishMenu> list = new ArrayList<DishMenu>();
			DishMenu menu;
			int i=0;
			while (rs.next()) {
				int id = rs.getInt(1);
				int typeId=rs.getInt(2);
				String name=rs.getString(3);
				int price=rs.getInt(4);
				String pic=rs.getString(5);
				String remark=rs.getString(6);
				String discribe=rs.getString(7);
				int graded=rs.getInt(8);
				menu=new DishMenu(id,price,typeId,name,pic,remark, discribe,graded);
				list.add(menu);
				System.out.println(name+" "+graded);
				i++;
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}
}
