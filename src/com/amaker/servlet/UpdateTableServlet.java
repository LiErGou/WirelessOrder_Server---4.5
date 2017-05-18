package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;

import com.amaker.dao.UpdateDao;
import com.amaker.dao.impl.UpdateDaoImpl;
import com.amaker.entity.CheckTable;

public class UpdateTableServlet extends HttpServlet {

	// 构造方法
	public UpdateTableServlet() {
		super();
	}
	// 销毁方法
	public void destroy() {
		super.destroy();
	}
	// 响应Get请求
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		// 实例化dao
		UpdateDao dao = new UpdateDaoImpl();
		// 获得菜谱列表
		List list = dao.getTableList();
		
		// 拼XML格式数据
		out.println("<?xml version='1.0' encoding='UTF-8'?>");
		// 根节点
		out.println("<tablelist>");
			for (int i = 0; i <list.size(); i++) {
				CheckTable m = (CheckTable)list.get(i);
				out.println("<Table>");
					// 桌位id
					out.print("<id>");
						out.print(m.getId());
					out.println("</id>");
					// 人数
					out.print("<num>");
						out.print(m.getNum());
					out.println("</num>");
					// 状态位
			/*		out.print("<flag>");
						out.print(m.getFlag());
					out.println("</flag>");*/
					// 描述
					out.print("<description>");
						out.print(m.getDescription());
					out.println("</description>");
					//人数
					out.print("<seatNum>");
						out.print(m.getSeatNum());
					out.println("</seatNum>");
					
				out.println("</Table>");
			}
		out.println("</tablelist>");

		out.flush();
		out.close(); 
	}
	// 响应Post请求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	// 初始化方法
	public void init() throws ServletException {
	}
}
