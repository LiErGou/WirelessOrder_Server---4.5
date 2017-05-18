import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import com.amaker.entity.CheckTable;
import com.amaker.entity.DishMenu;
import com.amaker.entity.User;
import com.amaker.util.DBUtil;

public class test {
	public static void main(String args[]) {
		new test().getCustomziedMenuList(1);
	}



	@SuppressWarnings("finally")
	public List<DishMenu> getCustomziedMenuList(int guestId) {
		List<DishMenu> list = null;
		try {
			list = getLikeMaterial(guestId);
			for (int j = 0; j < list.size(); j++) {
				System.out.println((j+1)+list.get(j).getName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return list;
		}

	}

	private List<DishMenu> getLikeMaterial(int guestId)
			throws SQLException {
		String sql = " select likeFlavorId from rel_guest_likeflavortbl where guestId = ?";
		String sql2 = " select id,typeId,name,price,pic,remark,discribe,grade,flavor,material"
				+ " from menutbl where flavor = ?";
		List<DishMenu> list =new ArrayList<DishMenu>(); 
//		int likeMaterialId;
		int likeFlavorId;
		// ���ݿ����ӹ�����
		DBUtil util = new DBUtil();
		// �������
		Connection conn = util.openConnection();
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
		// ���ò�ѯ����
			pstmt.setInt(1, guestId);
			ResultSet rs = pstmt.executeQuery();
			DishMenu menu=null;
			// ��ϲ�gʳ������Ų��λ
			while (rs.next()) {
				likeFlavorId = rs.getInt(1);
				System.out.println("likeFlavorId is:"+likeFlavorId);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				// ���ò�ѯ����
				pstmt2.setInt(1, likeFlavorId);
				ResultSet rs2 = pstmt2.executeQuery();
				// ����ʹ�����Ӳ�Ʒ
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
//		String sql2 = " select likeFlavorId from rel_guest_likeflavortbl where guestId = ?";
//		// ���ݿ����ӹ�����
//		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
//		// ���ò�ѯ����
//		pstmt2.setInt(1, guestId);
//		ResultSet rs2 = pstmt2.executeQuery();
//		// ��ϲ����ζ����Ų��λ
//		while (rs2.next()) {
//			likeFlavorId = rs2.getInt(1);
//			System.out.println("like flavor id:"+likeFlavorId);
//			for (int i =0; i<list.size(); i++) {
//				if (list.get(i).getFlavorId() == likeFlavorId&&i!=0) {
//					System.out.println("like flavor:"+list.get(i).getName());
//					for (int j = i, k = 0; k < 2 & j > 0; k++) {
//						menu = list.get(j);
//						list.set(j, list.get(j - 1));
//						list.set(j - 1, menu);
//						j--;
//					}
//					i--;
//				}
//
//			}
//		}

}
