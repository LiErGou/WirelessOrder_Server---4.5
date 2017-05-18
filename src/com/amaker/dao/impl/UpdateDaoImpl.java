package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amaker.dao.UpdateDao;
import com.amaker.entity.DishMenu;
import com.amaker.util.DBUtil;
import com.amaker.entity.CheckTable;

/**
 * @author 郭宏志
 *	完成更新数据功能
 */
public class UpdateDaoImpl implements UpdateDao {
	// 获得菜单列表
	public List getMenuList() {
		// 查询SQL语句
		String sql =" select id,name,typeId,price,pic,remark from menutbl ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			Statement pstmt = conn.createStatement();
			// 执行查询
			ResultSet rs = pstmt.executeQuery(sql);
			// 判断订单详细
			List list = new ArrayList();//List 是接口不能new对象，只能赋值给它，List是接口 ，所以要赋值给List 实例，那么当然要赋给它 ，实现它的具体对象了，而 ArrayList就是List的一个实现，多态的一种表现形式
			while (rs.next()) {
				// 获得菜单信息
				
				int id = rs.getInt(1);
				int typeId = rs.getInt(3);
				int price = rs.getInt(4);
				String name = rs.getString(2);
				String pic = rs.getString(5);
				String remark = rs.getString(6);
				
				DishMenu m = new DishMenu();
				m.setId(id);
				m.setName(name);
				m.setPic(pic);
				m.setPrice(price);
				m.setRemark(remark);
				m.setTypeId(typeId);
				
				list.add(m);
			}
			return list;//try语句中遇到return 会先跳过return去执行finally中的语句，再回来执行return
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}
	// 获得餐桌列表
	public List getTableList() {//新加的table的获取数据库的table数据,关闭数据库连接采用了try catch,待验证正确性，ps.servlet中的updatetable还没写，com.amaker.entity中的checktable作为table表的实体checktable
		String sql=" select id,num,flag,description,seatNum from tabletbl where tabletbl.flag=0 ";
		DBUtil util=new DBUtil();
		Connection conn=util.openConnection();
		//Statement pstmt=null;
		//ResultSet rs=null;
		try{		
			 
			Statement pstmt = conn.createStatement();
			ResultSet rs=pstmt.executeQuery(sql);
			List list= new ArrayList();
			while(rs.next())
			{
				int id = rs.getInt(1);
				int num=rs.getInt(2);
				int flag=rs.getInt(3);
				String description=rs.getString(4);
				int seatNum=rs.getInt(5);
				CheckTable t=new CheckTable();
				t.setDescription(description);
				t.setId(id);
				t.setFlag(flag);
				t.setNum(num);
				t.setSeatNum(seatNum);
				list.add(t);
			}
			return list;
		}
			catch(SQLException e)
			{
				e.printStackTrace();
			} finally {
				util.closeConn(conn);
			}
			
		
		return null;
	}
}
