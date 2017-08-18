package com.whg.web.action.cart;

import java.sql.SQLException;
import java.util.List;

import com.whg.web.dao.ItemDAO;
import com.whg.web.entity.Item;
import com.whg.web.impl.JdbcItemDAO;

public class FindItemAction {
	private List<Item> items;

	private int userId;

	public String execute() throws Exception {
		ItemDAO dao = new JdbcItemDAO();
		items = dao.findByUserId(userId);
		return "success";
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
