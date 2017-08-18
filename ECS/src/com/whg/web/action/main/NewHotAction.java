package com.whg.web.action.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whg.web.dao.ProductDAO;
import com.whg.web.entity.Product;
import com.whg.web.impl.JdbcProductDAO;

public class NewHotAction {
	private List<Product> newHots=new ArrayList<Product>();
	public String execute() throws Exception{
		ProductDAO dao=new JdbcProductDAO();
		newHots=dao.findNewHot();
		return "success";
	}
	public List<Product> getNewHots() {
		return newHots;
	}
	public void setNewHots(List<Product> newHots) {
	this.newHots = newHots;
}

}
