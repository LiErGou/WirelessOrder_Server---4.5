package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.amaker.dao.ChangeTableDao;
import com.amaker.util.DBUtil;
/**
 * @author ����־
 * ���ת̨����
 */
public class ChangeTableDaoImpl implements ChangeTableDao {

	public void changeTable(int orderId, int tableId) {
		// ����SQL���
		
		String sql = " update tabletbl set flag = 0,description='����' where id = "+
		  " (select tableId from ordertbl  as ot where ot.id = ?)";
		String sql2 = " update ordertbl set tableId = ? where id = ? ";
		String sql3 = " update tabletbl set flag = 1,description='�Ͳ�' where id = ?";
		
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		
		try {
			conn.setAutoCommit(false);//��һ����������ж���������ͱ�Ȼ��setAutoCommit�Ĳ������ó�false���ڶ��������������conn.commit()�����������ֶ��ύ
			// ���Ԥ�������
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			// ������λ״̬
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			
			// ���ò���
			pstmt.setInt(1, tableId);
			pstmt.setInt(2, orderId);
			// ���¶�����
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, tableId);
			// ������λ״̬
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {//��try����г����쳣��ʱ����ִ��catch�е���䣬java����ʱϵͳ���Զ���catch�����е�Exception e ��ʼ����Ҳ����ʵ����Exception���͵Ķ���e�Ǵ˶����������ơ�Ȼ��e�����ã����Զ�����Exception����ָ���ķ�����Ҳ�ͳ�����e.printStackTrace() ;��
			                       //,printStackTrace()��������˼�ǣ��������д�ӡ�쳣��Ϣ�ڳ����г����λ�ü�ԭ��
			e.printStackTrace();
			try {
				conn.rollback();//�ع�����conn.commit()ʧ��ʱ�ع��Ӷ���֤���ݿ�������ԣ��������û���ύҲû�лع������������
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			util.closeConn(conn);
		}
	}
}
