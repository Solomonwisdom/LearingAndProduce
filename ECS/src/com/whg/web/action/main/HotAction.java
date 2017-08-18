package com.whg.web.action.main;

import java.util.ArrayList;
import java.util.List;

import com.whg.web.dao.ProductDAO;
import com.whg.web.entity.Product;
import com.whg.web.impl.JdbcProductDAO;

public class HotAction {
	private List<Product> hots=new ArrayList<>();
	public String execute() throws Exception{
		ProductDAO dao= new JdbcProductDAO();
		hots=dao.findHot();
		return "success";
	}
	public List<Product> getHots() {
		return hots;
	}
	public void setHots(List<Product> hots) {
		this.hots = hots;
	}

}
