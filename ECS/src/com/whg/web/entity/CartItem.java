package com.whg.web.entity;

import java.io.Serializable;

public class CartItem implements Serializable{
	private Product pro;
	private int qty;
	private boolean buy=true;
	public boolean isBuy() {
		return buy;
	}
	public void setBuy(boolean buy) {
		this.buy = buy;
	}
	public Product getPro() {
		return pro;
	}
	public void setPro(Product pro) {
		this.pro = pro;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
	this.qty = qty;
}
}
