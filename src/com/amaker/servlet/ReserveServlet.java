package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.UserDao;
import com.amaker.dao.impl.ReserveDaoImpl;
import com.amaker.dao.impl.UserDaoImpl;
import com.amaker.entity.User;

public class ReserveServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String personNo = request.getParameter("personNo");
		String telString = request.getParameter("telString");
		String reserveDate = request.getParameter("reserveDate");
		String name = request.getParameter("name");
		System.out.println("personNo:"+personNo+" telString"+telString+" reserveDate"+reserveDate);
		new ReserveDaoImpl().reserveTab(personNo, telString, reserveDate,name);
		PrintWriter out = response.getWriter();
		out.print("success");
		out.flush();
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	public void init() throws ServletException {
	}
	

	public void destroy() {
		super.destroy();
	}
}
