package com.whg.web.action.cart;
import java.util.ArrayList;
import java.util.List;
import com.whg.web.action.BaseAction;
import com.whg.web.entity.CartItem;
import com.whg.web.service.Cart;
import com.whg.web.service.CartFactory;

public class CartListAction extends BaseAction {
	private List<CartItem> buyList;
	private List<CartItem> delList;
	private double cost;
	private double sale;
	private int pid;
	private int size=0;
//商品列表
	public String execute() {
		Cart cart = CartFactory.getCart(session);
		buyList = cart.getBuyList();
		delList = cart.getDelList();
		size=buyList.size();
		cost = cart.getMoney();
		sale = cart.getSale();
		return "success";
	}
//删除商品
	public String del() {
		Cart cart = CartFactory.getCart(session);
		cart.delete(pid);
		return "success";

	}

	public List<CartItem> getBuyList() {
		return buyList;
	}

	public void setBuyList(List<CartItem> buyList) {
		this.buyList = buyList;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public List<CartItem> getDelList() {
		return delList;
	}

	public void setDelList(List<CartItem> delList) {
		this.delList = delList;
	}
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

}
