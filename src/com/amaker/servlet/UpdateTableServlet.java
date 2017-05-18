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

	// ���췽��
	public UpdateTableServlet() {
		super();
	}
	// ���ٷ���
	public void destroy() {
		super.destroy();
	}
	// ��ӦGet����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		// ʵ����dao
		UpdateDao dao = new UpdateDaoImpl();
		// ��ò����б�
		List list = dao.getTableList();
		
		// ƴXML��ʽ����
		out.println("<?xml version='1.0' encoding='UTF-8'?>");
		// ���ڵ�
		out.println("<tablelist>");
			for (int i = 0; i <list.size(); i++) {
				CheckTable m = (CheckTable)list.get(i);
				out.println("<Table>");
					// ��λid
					out.print("<id>");
						out.print(m.getId());
					out.println("</id>");
					// ����
					out.print("<num>");
						out.print(m.getNum());
					out.println("</num>");
					// ״̬λ
			/*		out.print("<flag>");
						out.print(m.getFlag());
					out.println("</flag>");*/
					// ����
					out.print("<description>");
						out.print(m.getDescription());
					out.println("</description>");
					//����
					out.print("<seatNum>");
						out.print(m.getSeatNum());
					out.println("</seatNum>");
					
				out.println("</Table>");
			}
		out.println("</tablelist>");

		out.flush();
		out.close(); 
	}
	// ��ӦPost����
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	// ��ʼ������
	public void init() throws ServletException {
	}
}
