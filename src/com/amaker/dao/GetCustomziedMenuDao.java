package com.amaker.dao;

import java.util.List;

import com.amaker.entity.DishMenu;

public interface GetCustomziedMenuDao {
	public List<DishMenu> getCustomziedMenuList(int guestId) ;	
}
