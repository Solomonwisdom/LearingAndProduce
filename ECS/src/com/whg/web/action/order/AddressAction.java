package com.whg.web.action.order;

import java.util.Date;
import java.util.List;

import com.whg.web.action.BaseAction;
import com.whg.web.common.Constant;
import com.whg.web.dao.AddressDAO;
import com.whg.web.dao.ItemDAO;
import com.whg.web.dao.OrderDAO;
import com.whg.web.entity.Address;
import com.whg.web.entity.CartItem;
import com.whg.web.entity.Order;
import com.whg.web.entity.User;
import com.whg.web.impl.JdbcAddressDAO;
import com.whg.web.impl.JdbcItemDAO;
import com.whg.web.impl.JdbcOrderDAO;
import com.whg.web.service.Cart;
import com.whg.web.service.CartFactory;

public class AddressAction extends BaseAction {
	private Order order;
	private String select;
	private int order_id;
	private double cost;
	private Address address = new Address();
	public String execute() throws Exception {
		User user = (User) session.get("user");
		// 提交订单order
		int userId = user.getId();
		order.setUserId(userId);
		Date date = new Date();
		Long time = date.getTime();
		order.setOrderTime(time);
		order.setStatus(Constant.STATUS);
		order.setOrderDesc("fdf");
		Cart cart = CartFactory.getCart(session);
		cost = cart.getMoney();
		order.setTotalPrice(cost);
		OrderDAO dao = new JdbcOrderDAO();
		order_id = dao.save(order);
		// 保存地址address
		if (select.equals("0")) {
			address.setUserId(order.getUserId());
			address.setAddress(order.getAddress());
			address.setMobile(order.getMobile());
			address.setPhone(order.getPhone());
			address.setPostal(order.getPostal());
			address.setReceiveName(order.getReceiveName());
			AddressDAO daoa = new JdbcAddressDAO();
			daoa.save(address);
		}
		// 保存订单item
		List<CartItem> buyList = cart.getBuyList();
		ItemDAO daoi = new JdbcItemDAO();
		daoi.save(buyList, order_id);
		session.remove("cart");
		return "success";

	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}
