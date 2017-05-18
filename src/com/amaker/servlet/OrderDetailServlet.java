package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.OrderDao;
import com.amaker.dao.impl.OrderDaoImpl;
import com.amaker.entity.OrderDetail;
import com.amaker.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class OrderDetailServlet extends HttpServlet {

	
	public OrderDetailServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String orderId = request.getParameter("orderId");
		String menuId = request.getParameter("menuId");
		System.out.println("menuId"+menuId);
		String num = request.getParameter("num");
		String remark = request.getParameter("remark");
		String userId = request.getParameter("userId");
		String tmp = request.getParameter("isGuest");
		int isGuest = 0;
		if(tmp.equals("yes")){
			isGuest=1;
		}
		if(tmp.equals("no")){
			isGuest=0;
		}
		
		OrderDao dao = new OrderDaoImpl();
		
		OrderDetail od = new OrderDetail();
		
		od.setMenuId(Integer.parseInt(menuId));
		od.setOrderId(Integer.parseInt(orderId));
		od.setNum(Integer.parseInt(num));
		od.setRemark(remark);
		DBUtil util=new DBUtil();
		Connection con=util.openConnection();
		int price=0;
		int menutypeid=0;
		int grade = 0;
		String name=null;
		try
		{
			String sql="select price,typeId,name,grade from menutbl where id=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(menuId));
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				price=rs.getInt(1);
				menutypeid=rs.getInt(2);
				name=rs.getString(3);
				grade=rs.getInt(4);
				grade++;
				String sql2 = 
						" update menutbl set grade="+grade+" where id = ?";
				PreparedStatement pst2=con.prepareStatement(sql2);
				pst2.setInt(1, Integer.parseInt(menuId));
				pst2.executeUpdate();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			util.closeConn(con);
		}
		dao.saveOrderDetail(od,price,menutypeid,name);
		
		out.print(remark);
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	
	public void init() throws ServletException {
		
	}

}
