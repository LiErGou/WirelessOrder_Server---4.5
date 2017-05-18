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
 * @author ����־
 *	��ɸ������ݹ���
 */
public class UpdateDaoImpl implements UpdateDao {
	// ��ò˵��б�
	public List getMenuList() {
		// ��ѯSQL���
		String sql =" select id,name,typeId,price,pic,remark from menutbl ";
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			Statement pstmt = conn.createStatement();
			// ִ�в�ѯ
			ResultSet rs = pstmt.executeQuery(sql);
			// �ж϶�����ϸ
			List list = new ArrayList();//List �ǽӿڲ���new����ֻ�ܸ�ֵ������List�ǽӿ� ������Ҫ��ֵ��List ʵ������ô��ȻҪ������ ��ʵ�����ľ�������ˣ��� ArrayList����List��һ��ʵ�֣���̬��һ�ֱ�����ʽ
			while (rs.next()) {
				// ��ò˵���Ϣ
				
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
			return list;//try���������return ��������returnȥִ��finally�е���䣬�ٻ���ִ��return
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}
	// ��ò����б�
	public List getTableList() {//�¼ӵ�table�Ļ�ȡ���ݿ��table����,�ر����ݿ����Ӳ�����try catch,����֤��ȷ�ԣ�ps.servlet�е�updatetable��ûд��com.amaker.entity�е�checktable��Ϊtable���ʵ��checktable
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
