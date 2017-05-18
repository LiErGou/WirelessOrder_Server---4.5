package com.amaker.dao;

import java.sql.SQLException;
import java.util.List;

import com.amaker.entity.DishMenu;

public interface GetHistoryMenuDao {
	public List<DishMenu> getHistoryMenu(int guestId) throws SQLException;
}
