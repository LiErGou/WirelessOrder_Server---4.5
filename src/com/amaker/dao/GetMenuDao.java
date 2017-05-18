package com.amaker.dao;

import java.sql.SQLException;
import java.util.List;

import com.amaker.entity.DishMenu;

public interface GetMenuDao {
	public List<DishMenu> getMenuList(int guestId) ;
}
