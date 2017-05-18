package com.amaker.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;

import com.amaker.dao.GetCustomziedMenuDao;
import com.amaker.dao.GetHistoryMenuDao;
import com.amaker.dao.GetHotMenuDao;
import com.amaker.dao.GetMenuDao;
import com.amaker.dao.impl.GetCustomziedMenuDaoImpl;
import com.amaker.dao.impl.GetHistoryMenuDaoImpl;
import com.amaker.dao.impl.GetHotMenuDaoImpl;
import com.amaker.dao.impl.GetLikeFlavorDaoImpl;
import com.amaker.dao.impl.GetLikeMaterialDaoImpl;
import com.amaker.entity.DishMenu;
import com.sun.faces.facelets.util.Path;


public class GetMenusServlet extends HttpServlet {
	private int userId=0;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int tabIndex = Integer.valueOf(request.getParameter("tabIndex")).intValue();
		userId = Integer.valueOf(request.getParameter("userId")).intValue();
		String returnJSON=null;
		switch (tabIndex){
		case 0:
			returnJSON=getMenuJSON(new GetHotMenuDaoImpl());
			break;
		case 1:
			returnJSON=getMenuJSON(new GetCustomziedMenuDaoImpl());
			break;
		case 2:
			returnJSON=getMenuJSON(new GetLikeFlavorDaoImpl());
			break;
		case 3:
			returnJSON=getMenuJSON(new GetLikeMaterialDaoImpl());
			break;
		case 4:
			returnJSON=getMenuJSON(new GetHistoryMenuDaoImpl());
			break;
		}
		returnMenu(returnJSON,request,response);
	}
	//返回热门菜品
	public void returnMenu(String returnJSON,HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.print(returnJSON);
		out.flush();
		out.close();
	}
	//返回个性菜品
	public String getMenuJSON(GetMenuDao getMenu) throws JSONException {
		List<DishMenu> list = getMenu.getMenuList(userId);
		JSONArray json = new JSONArray(list);
		return json.toString(2);
	}

}
