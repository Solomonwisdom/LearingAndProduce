package com.whg.web.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whg.web.dao.ItemDAO;
import com.whg.web.entity.CartItem;
import com.whg.web.entity.Item;
import com.whg.web.util.DBUtil;

public class JdbcItemDAO implements ItemDAO{
	//插入的d_item表单值
	public void save(List<CartItem> item,int order_id) throws SQLException {
		Connection conn=DBUtil.getConnection();
		for(CartItem i:item){
			PreparedStatement ps=conn.prepareStatement("insert into d_item(" +
					"order_id,product_id,product_name," +
					"dang_price,product_num,amount)" +
					"values(?,?,?,?,?,?)");
			ps.setInt(1, order_id);
			ps.setInt(2, i.getPro().getId());
			ps.setString(3, i.getPro().getProductName());
			ps.setDouble(4, i.getPro().getDangPrice());
			ps.setInt(5, i.getQty());
		ps.setDouble(6, i.getPro().getDangPrice()*i.getQty());
		ps.executeUpdate();
			
		}
	
	
	}
	//查找item内容
	public List<Item> findByUserId(int userId) throws SQLException{
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=conn.prepareStatement("select di.* " +
				"from d_item di join d_order do " +
				"on di.order_id=do.id where do.user_id=?");
		ps.setInt(1, userId);
		List<Item> list=new ArrayList<Item>();
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			Item i=new Item();
			i.setAmount(rs.getDouble("amount"));
			i.setDangPrice(rs.getDouble("dang_price"));
			i.setProductName(rs.getString("product_name"));
			i.setProductNum(rs.getInt("product_num"));
			list.add(i);
		}
		return list;
	}


	public List<Item> showAll() throws SQLException {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=conn.prepareStatement("select di.id, di.amount,di.dang_price," +
				"di.product_name,di.product_num,do.user_id,du.email " +
				"from (d_item di join d_order do " +
				"on di.order_id=do.id) join d_user du on do.user_id=du.id");
		List<Item> list=new ArrayList<Item>();
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			Item i=new Item();
			i.setId(rs.getInt("id"));
			i.setUserId(rs.getInt("user_id"));
			i.setAmount(rs.getDouble("amount"));
			i.setDangPrice(rs.getDouble("dang_price"));
			i.setProductName(rs.getString("product_name"));
			i.setProductNum(rs.getInt("product_num"));
			i.setEmail(rs.getString("email"));
			list.add(i);
		}
		return list;
	}

}
