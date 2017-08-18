package com.whg.web.action.main;

import java.sql.SQLException;
import java.util.List;

import com.whg.web.dao.ProductDAO;
import com.whg.web.entity.Product;
import com.whg.web.impl.JdbcProductDAO;

public class NewProductAction {
	private int top=8;
	private List<Product> pros;
	public String execute() throws Exception{
//		Thread.sleep(2000);
		ProductDAO dao=new JdbcProductDAO();
		pros=dao.findNewProduct(top);
		return "success";
	}


	public List<Product> getPros() {
		return pros;
	}


	public void setPros(List<Product> pros) {
		this.pros = pros;
	}


	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
}
