package com.whg.web.service;

import java.util.List;

import com.whg.web.entity.CartItem;

public interface Cart {
	//购买
	public abstract boolean buy(int pid) throws Exception;

	//变更数量
	public abstract void modify(int pid, int qty);

	//取消购买
	public abstract void delete(int pid);

	//恢复购买
	public abstract void recovery(int pid);

	//统计确认购买商品的金额
	public double getMoney();
//	节省金额
	public double getSale();
	//返回确认购买商品集合
	public abstract List<CartItem> getBuyList();

	//返回取消购买商品集合
	public abstract List<CartItem> getDelList();

}
