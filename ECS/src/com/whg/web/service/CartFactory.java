package com.whg.web.service;

import java.util.Map;

public class CartFactory {
	public static Cart getCart(Map<String,Object> session){
		Cart cart=(Cart)session.get("cart");
		if(cart==null){
			cart=new ShoppingCart();
			session.put("cart", cart);
		}
		return cart;
	}
}
