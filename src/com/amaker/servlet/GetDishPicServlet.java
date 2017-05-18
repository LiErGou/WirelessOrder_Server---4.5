package com.amaker.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;

import org.apache.commons.io.IOUtils;

import com.amaker.dao.GetHotMenuDao;
import com.amaker.dao.impl.GetHotMenuDaoImpl;
import com.amaker.entity.DishMenu;

public class GetDishPicServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String image = request.getParameter("image");
		System.out.println("获得图片请求"+image);
		/**
		 * 演示响应字节数据
		 */
		// 把一张图片读取到字节数组中;
		returnPic(response,image);
	}
    public void returnPic(HttpServletResponse response,String image){

		InputStream in = null;
		ClassLoader cl = this.getClass().getClassLoader();
		 //相对/classes
		in = cl.getResourceAsStream("image/"+image+".png");
		try {
			IOUtils.copy(in, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
