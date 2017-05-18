package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amaker.dao.OrderDao;
import com.amaker.entity.Order;
import com.amaker.entity.OrderDetail;
import com.amaker.util.DBUtil;
/**
 * @author ����־
 * ��͹���DAOʵ����
 */
public class OrderDaoImpl implements OrderDao {

	// ��������Ϣ���Żض���ID
	public int saveOrder(Order o) {
		// ���SQL���
		String sql = " insert into ordertbl(Ordertime,userid,tableId,personNum,isGuest)values(?,?,?,?,?) ";
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, o.getOrderTime());
			pstmt.setInt(2, o.getUserId());
			pstmt.setInt(3, o.getTableId());
			pstmt.setInt(4, o.getPersonNum());
			pstmt.setInt(5, o.isGuest());
			// ִ�и���
			pstmt.executeUpdate();
			// ���ض������
			String sql2 = " select max(id) as id  from ordertbl ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);
			if(rs.next()){
				int id = rs.getInt(1);
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return 0;
	}
	// �������б�
	public void saveOrderDetail(OrderDetail od,int pr,int mety,String name) {
		// ���SQL���
		String sql = " insert into orderdetailtbl(orderId,menuId,num,remark,price,menutypeid,menuname)values(?,?,?,?,?,?,?) ";
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setInt(1, od.getOrderId());
			pstmt.setInt(2, od.getMenuId());
			pstmt.setInt(3, od.getNum());
			pstmt.setString(4, od.getRemark());
			pstmt.setInt(5, pr);
			pstmt.setInt(6, mety);
			pstmt.setString(7, name);
			// ִ�и���
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
	
	// ��������״̬������
	public void updateTableStatus(int tableId) {
		// ����SQL���
		String sql = " update tabletbl set flag=1,description='�Ͳ�' where id = ? ";
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setInt(1, tableId);
			// ִ�и���
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
	// ��������״̬������
	public void updateTableStatus2(int orderId) {
		// ����SQL���
		String sql = " update tabletbl set flag = 0,description='����' where id = "+
							" ( select tableId from ordertbl where id = ?) ";
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try {
			// ���Ԥ�������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setInt(1, orderId);
			// ִ�и���
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
}
