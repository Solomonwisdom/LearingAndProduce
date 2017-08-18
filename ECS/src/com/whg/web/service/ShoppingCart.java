package com.whg.web.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whg.web.dao.ProductDAO;
import com.whg.web.entity.CartItem;
import com.whg.web.entity.Product;
import com.whg.web.impl.JdbcProductDAO;

public class ShoppingCart implements Cart {
	private List<CartItem> list = new ArrayList<CartItem>();

	// 购买
	public boolean buy(int pid) throws Exception {
		// TODO 判断是否购买过此商品
		// 未购买过，根据Pid封装成一个CartItem
		CartItem item=new CartItem();
		ProductDAO dao=new JdbcProductDAO();
		Product pro=dao.findById(pid);
		for(CartItem it:list){
			if(pro.getId()==it.getPro().getId()){
				return false;
			}
		}
		item.setPro(pro);
		item.setQty(1);
		item.setBuy(true);
		list.add(item);
		return true;
	}

	// 变更数量
	public void modify(int pid, int qty) {
		// TODO 根据Pid找到CartItem元素,
		// 修改qty属性
		for (CartItem item : list) {
			if (item.getPro().getId() == pid) {
				item.setQty(qty);
			}
		}

	}

	// 取消购买
	public void delete(int pid) {
		// TODO 根据Pid找到CartItem元素
		// TODO 将buy属性修改为false
		for (CartItem item : list) {
			if (item.getPro().getId() == pid) {
				item.setBuy(false);
			}
		}
	}

	// 恢复购买
	public void recovery(int pid) {
		// TODO 根据Pid找到CartItem元素
		// TODO 将buy属性修改为true
		for (CartItem item : list) {
			if (item.getPro().getId() == pid) {
				item.setBuy(true);
			}
		}
	}

	// 统计确认购买商品的金额
	public double getMoney(){
		double count=0;
		for(CartItem item:list){
			if(item.isBuy()==true){
				double mon=item.getPro().getDangPrice()*item.getQty();
				count+=mon;
			}
		}
		return count;
	}
	//节省金额
	public double getSale(){
		double count=0;
		for(CartItem item:list){
			if(item.isBuy()==true){
				double mon=(item.getPro().getFixedPrice()-item.getPro().getDangPrice())*item.getQty();
				count+=mon;
			}
		}
		return count;
	}
	// 返回确认购买商品集合

	public List<CartItem> getBuyList() {
		// 返回一个集合,集合元素需要buy=true
		ArrayList<CartItem> items = new ArrayList<CartItem>();
		for (CartItem item : list) {
			if (item.isBuy()) {
				items.add(item);
			}
		}
		return items;
	}

	// 返回取消购买商品集合

	public List<CartItem> getDelList() {
		// 返回一个集合,集合元素需要buy=false
		ArrayList<CartItem> items = new ArrayList<CartItem>();
		for (CartItem item : list) {
			if (!item.isBuy()) {
				items.add(item);
			}
		}
		return items;
	}

}
