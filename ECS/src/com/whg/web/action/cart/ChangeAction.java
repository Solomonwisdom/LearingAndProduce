package com.whg.web.action.cart;

import java.util.List;

import com.whg.web.action.BaseAction;
import com.whg.web.entity.CartItem;
import com.whg.web.service.Cart;
import com.whg.web.service.CartFactory;

public class ChangeAction extends BaseAction {
	private int pid;

	private int qty;

	// 删除
	public String delete() {
		Cart cart = CartFactory.getCart(session);
		cart.delete(pid);
		return "success";

	}

	// 恢复
	public String recovery() {
		Cart cart = CartFactory.getCart(session);
		cart.recovery(pid);
		return "success";
	}

	// 修改
	public String modify() {
		Cart cart = CartFactory.getCart(session);

		cart.modify(pid, qty);
		return "success";
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
}
