package com.whg.web.action.cart;

import com.whg.web.action.BaseAction;
import com.whg.web.service.Cart;
import com.whg.web.service.CartFactory;

public class BuyAction extends BaseAction{
	private int pid;
	private boolean ok=true;
	//购买商品
	public String execute() throws Exception{
		Cart cart=CartFactory.getCart(session);
		ok=cart.buy(pid);
		return "success";
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
}
