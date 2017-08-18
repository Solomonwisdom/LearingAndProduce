package com.whg.web.action.order;

import java.util.List;

import com.whg.web.action.BaseAction;
import com.whg.web.entity.CartItem;
import com.whg.web.service.Cart;
import com.whg.web.service.CartFactory;

public class CheckOutAction extends BaseAction{
	private List<CartItem> buyList;
	private double cost;
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public List<CartItem> getBuyList() {
		return buyList;
	}
	public void setBuyList(List<CartItem> buyList) {
		this.buyList = buyList;
	}
	public String execute(){
		Cart cart=CartFactory.getCart(session);
		buyList=cart.getBuyList();
		cost=cart.getMoney();
		return "success";

	}
}
