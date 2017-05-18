package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amaker.dao.GetCustomziedMenuDao;
import com.amaker.dao.GetHotMenuDao;
import com.amaker.dao.GetMenuDao;
import com.amaker.entity.CheckTable;
import com.amaker.entity.DishMenu;
import com.amaker.util.DBUtil;

public class GetCustomziedMenuDaoImpl implements GetMenuDao{
	public List<DishMenu> getMenuList(int guestId) {
		List<DishMenu> list = null;
		try {
			list = onceOrdered(guestId);
			for (int j = 0; j < list.size(); j++) {
				System.out.println((j+1)+list.get(j).getName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return list;
		}

	}

	private List<DishMenu> onceOrdered(int guestId) throws SQLException {
		// 查询SQL语句
		// by orderTimes desc， where guestId = 1
		String sql = " select orderedDishId from rel_guest_orderhistory where guestId = "
				+ guestId + " order by orderTimes desc";
		String sql2 = " select id,typeId,name,price,pic,remark,discribe,grade,flavor,material"
				+ " from menutbl where id = ?";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			int id;

			List<DishMenu> list = new ArrayList<DishMenu>();
			DishMenu menu;
			int i = 0;
			while (rs.next() && i < 10) {
				// 取得点餐最多的菜品id
				id = rs.getInt(1);
				// 菜品信息
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				// 设置查询参数
				pstmt2.setInt(1, id);
				ResultSet rs2 = pstmt2.executeQuery();
				// 按点餐次数添加菜品
				while (rs2.next()) {
					id = rs2.getInt(1);
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
				i++;

			}
			// 如果点餐历史不足10条记录，则从热门菜品种获得
			String sql3 = " select id,typeId,name,price,pic,remark,discribe,grade,flavor,material"
					+ " from menutbl order by grade desc";

			Statement pstmt3 = conn.createStatement();
			ResultSet rs3 = pstmt3.executeQuery(sql3);
			while (rs3.next() && i < 10) {
				id = rs3.getInt(1);
				int typeId = rs3.getInt(2);
				String name = rs3.getString(3);
				int price = rs3.getInt(4);
				String pic = rs3.getString(5);
				String remark = rs3.getString(6);
				String discribe = rs3.getString(7);
				int graded = rs3.getInt(8);
				int flavorId = rs3.getInt(9);
				int materialId = rs3.getInt(10);
				menu = new DishMenu(id, price, typeId, name, pic, remark,
						discribe, graded);
				menu.setFlavorId(flavorId);
				menu.setMaterialId(materialId);
				//判断菜品是否已经在点餐历史中出现 
				if (isMenuExsit(list,menu)){
				
				}else {
					list.add(menu);
					i++;
				}
			}
			list=moveDislike(list,guestId);
			list=moveLike(list,guestId);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}
	//判断菜品是否已经在点餐历史中出现 
	private boolean isMenuExsit(List<DishMenu> list,DishMenu menu){
		for (int j = 0; j < list.size(); j++) {
			if(list.get(j).getId()==menu.getId()){
				return true;
			}
		}
		return false;
	}

	private List<DishMenu> moveDislike(List<DishMenu> list, int guestId)
			throws SQLException {
		int dislikeMaterialId;
		int dislikeFlavorId;
		String sql = " select dislikeMaterialId from rel_guest_dislikematerialtbl where guestId = ?";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		DishMenu menu = null;
		DishMenu tmp = null;
		// 获得连接
		Connection conn = util.openConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 设置查询参数
		pstmt.setInt(1, guestId);
		ResultSet rs = pstmt.executeQuery();

		// 将不喜歡食材往下挪两位
		while (rs.next()) {
			dislikeMaterialId = rs.getInt(1);
			System.out.println("dislike material id:"+dislikeMaterialId);
			for (int i =list.size()-1; i+1>0; i--) {
//				System.out.println("name:"+list.get(i).getName()+" MaterialId" +
//						""+list.get(i).getMaterialId());
				if (list.get(i).getMaterialId() == dislikeMaterialId&&i!=list.size()-1) {
					System.out.println("dislike materrial:"+list.get(i).getName());
					int size = list.size();
					// list.remove(i);
					for (int j = i, k = 0; k < 2 & j < size - 1; k++) {
						menu = list.get(j);
						list.set(j, list.get(j + 1));
						list.set(j + 1, menu);
						j++;
					}
					i++;
				}

			}
		}
		String sql2 = " select dislikeFlavorId from rel_guest_dislikeflavortbl where guestId = ?";
		// 数据库连接工具类
		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		// 设置查询参数
		pstmt2.setInt(1, guestId);
		ResultSet rs2 = pstmt2.executeQuery();
		// 移除不喜歡口味
		while (rs2.next()) {
			dislikeFlavorId = rs2.getInt(1);
			System.out.println("dislike flavor id:"+dislikeFlavorId);
			for (int i =list.size()-1; i+1>0; i--) {
				if (list.get(i).getFlavorId() == dislikeFlavorId&&i!=list.size()-1){
					System.out.println("dislike flavor:"+list.get(i).getName());
					int size = list.size();
					// list.remove(i);
					for (int j = i, k = 0; k < 2 & j < size - 1; k++) {
						menu = list.get(j);
						list.set(j, list.get(j + 1));
						list.set(j + 1, menu);
						j++;
					}
					i++;
				}
					
			}
		}
		return list;
	}
	
	private List<DishMenu> moveLike(List<DishMenu> list, int guestId)
			throws SQLException {
		String sql = " select likeMaterialId from rel_guest_likematerialtbl where guestId = ?";
		int likeMaterialId;
		int likeFlavorId;
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		DishMenu menu = null;
		DishMenu tmp = null;
		// 获得连接
		Connection conn = util.openConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 设置查询参数
		pstmt.setInt(1, guestId);
		ResultSet rs = pstmt.executeQuery();
		int[] hasMoved=new int[list.size()];
		// 将喜歡食材往上挪两位
		while (rs.next()) {
			likeMaterialId = rs.getInt(1);
			System.out.println("like material id:"+likeMaterialId);
			for (int i =0; i<list.size(); i++) {
				if (list.get(i).getMaterialId() == likeMaterialId&&i!=0) {
					System.out.println("like materrial:"+list.get(i).getName());
					for (int j = i, k = 0; k < 2 & j > 0; k++) {
						menu = list.get(j);
						list.set(j, list.get(j - 1));
						list.set(j - 1, menu);
						j--;
					}
					i--;

				}

			}
		}
		String sql2 = " select likeFlavorId from rel_guest_likeflavortbl where guestId = ?";
		// 数据库连接工具类
		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		// 设置查询参数
		pstmt2.setInt(1, guestId);
		ResultSet rs2 = pstmt2.executeQuery();
		// 将喜欢口味向上挪两位
		while (rs2.next()) {
			likeFlavorId = rs2.getInt(1);
			System.out.println("like flavor id:"+likeFlavorId);
			for (int i =0; i<list.size(); i++) {
				if (list.get(i).getFlavorId() == likeFlavorId&&i!=0) {
					System.out.println("like flavor:"+list.get(i).getName());
					for (int j = i, k = 0; k < 2 & j > 0; k++) {
						menu = list.get(j);
						list.set(j, list.get(j - 1));
						list.set(j - 1, menu);
						j--;
					}
					i--;
				}

			}
		}
		return list;
	}
}
