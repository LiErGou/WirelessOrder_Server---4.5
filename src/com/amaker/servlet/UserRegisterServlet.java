package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.UserDao;
import com.amaker.dao.impl.UserDaoImpl;
import com.amaker.dao.impl.UserRegisterImpl;
import com.amaker.entity.User;
/**
 * 
 * @author ����־
 * ��Ӧ Android�ͻ��˷���������
 */
public class UserRegisterServlet extends HttpServlet {
	int lf[]={0,0,0,0};						//ϲ����ζ
	int df[]={0,0,0,0};						//��ϲ����ζ
	int lm[]={0,0,0,0,0};						//ϲ��ʳ��
	int dm[]={0,0,0,0,0,0};						//��ϲ��ʳ��
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		UserDao dao = new UserDaoImpl();
		// ��ÿͻ����������
		System.out.println("��ÿͻ����������");
		String a[]={"","",""};
		String username = request.getParameter("account");
		String password = request.getParameter("password");
		int gender=Integer.valueOf(request.getParameter("gender")).intValue();
		int likeFlavor =  1;
		int dislikeFlavor =1; 
		int likeMaterial = 1;
		int dislikeMaterial = 1;
		//���տͻ��˷���ϲ����ζ���飬����ȡ��������
		for(int i=0;i<lf.length;i++){
			lf[i]=Integer.valueOf(request.getParameter("lf["+i+"]")).intValue();
			System.out.print(lf[i]+" ");
		}
		System.out.println("");
		//���տͻ��˷�����ϲ����ζ���飬����ȡ��������
		for(int i=0;i<df.length;i++){
			df[i]=Integer.valueOf(request.getParameter("df["+i+"]")).intValue();
			System.out.print(df[i]+" ");
		}
		System.out.println("");
		//���տͻ��˷���ϲ��ʳ�����飬����ȡ��������
		for(int i=0;i<lm.length;i++){
			lm[i]=Integer.valueOf(request.getParameter("lm["+i+"]")).intValue();
			System.out.print(lm[i]+" ");
		}
		System.out.println("");
		//���տͻ��˷�����ϲ��ʳ�����飬����ȡ��������
		for(int i=0;i<dm.length;i++){
			dm[i]=Integer.valueOf(request.getParameter("dm["+i+"]")).intValue();
			System.out.print(dm[i]+" ");
		}
		System.out.println("");
		UserRegisterImpl ur=new UserRegisterImpl();
		//User u = dao.login(username, password);
		User u=ur.register(username, password,lf,df,lm,dm,gender);
		if(u!=null){
			// ��Ӧ�ͻ������ݣ���¼�ɹ�
			out.print(build(u));
		}else{
			// ��Ӧ�ͻ������ݣ���¼ʧ��
			out.print("0");
		}
		out.flush();
		out.close();
	}
	
	
	private String build(User u){
		String userMsg = "";
		userMsg+="id="+u.getId();
		userMsg+=";";
		userMsg+="name="+u.getName();
		return userMsg;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	public void init() throws ServletException {
	}
	
	public UserRegisterServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	
}
