package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amaker.dao.GetHistoryMenuDao;
import com.amaker.dao.GetMenuDao;
import com.amaker.entity.DishMenu;
import com.amaker.util.DBUtil;

public class GetHistoryMenuDaoImpl implements GetMenuDao{
	
	public List<DishMenu> getMenuList(int guestId) {
		// ��ѯSQL���
		String sql = " select orderedDishId from rel_guest_orderhistory where guestId = "
				+ guestId + " order by orderTimes desc";
		String sql2 = " select id,typeId,name,price,pic,remark,discribe,grade,flavor,material"
				+ " from menutbl where id = ?";
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			int id;

			List<DishMenu> list = new ArrayList<DishMenu>();
			DishMenu menu;
			int i = 0;
			while (rs.next()) {
				// ȡ�õ�����Ĳ�Ʒid
				id = rs.getInt(1);
				// ��Ʒ��Ϣ
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				// ���ò�ѯ����
				pstmt2.setInt(1, id);
				ResultSet rs2 = pstmt2.executeQuery();
				// ����ʹ�����Ӳ�Ʒ
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
