package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amaker.dao.GetMenuDao;
import com.amaker.entity.DishMenu;
import com.amaker.util.DBUtil;

public class GetLikeMaterialDaoImpl implements GetMenuDao{
	public List<DishMenu> getMenuList(int guestId){
		String sql = " select likeMaterialId from rel_guest_likematerialtbl where guestId = ?";
		String sql2 = " select id,typeId,name,price,pic,remark,discribe,grade,flavor,material"
				+ " from menutbl where material = ?";
		List<DishMenu> list =new ArrayList<DishMenu>(); 
		int likeMaterialId;
		int likeFlavorId;
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
		// 设置查询参数
			pstmt.setInt(1, guestId);
			ResultSet rs = pstmt.executeQuery();
			DishMenu menu=null;
			// 将喜歡食材往上挪两位
			while (rs.next()) {
				likeMaterialId = rs.getInt(1);
				System.out.println("likeMaterialId is:"+likeMaterialId);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				// 设置查询参数
				pstmt2.setInt(1, likeMaterialId);
				ResultSet rs2 = pstmt2.executeQuery();
				// 按点餐次数添加菜品
				while (rs2.next()) {
					int id = rs2.getInt(1);
					int typeId = rs2.getInt(2);
					String name = rs2.getString(3);
					int price = rs2.getInt(4);
					String pic = rs2.getString(5);
					String remark = rs2.getString(6);
					String discribe = rs2.getString(7);
					int graded = rs2.getInt(8);
					int flavorId = rs2.getInt(9);
					int materialId = rs2.getInt(10);
					menu = new DishMenu(id, price, typeId, name, pic, remark,
							discribe, graded);
					menu.setFlavorId(flavorId);
					menu.setMaterialId(materialId);
					list.add(menu);
				}
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
