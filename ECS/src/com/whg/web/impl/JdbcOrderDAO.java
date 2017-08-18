package com.whg.web.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.whg.web.dao.OrderDAO;
import com.whg.web.entity.Order;
import com.whg.web.util.DBUtil;

public class JdbcOrderDAO implements OrderDAO{

	public int save(Order order) throws SQLException {
		int id=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=conn.prepareStatement("insert into " +
				"d_order(user_id,status,order_time," +
				"order_desc,total_price,receive_name," +
				"full_address,postal_code,mobile,phone) values(?,?,?,?,?,?,?,?,?,?)");
		ps.setInt(1, order.getUserId());
		ps.setInt(2, order.getStatus());
		ps.setLong(3, order.getOrderTime());
		ps.setString(4, order.getOrderDesc());
		ps.setDouble(5, order.getTotalPrice());
		ps.setString(6, order.getReceiveName());
		ps.setString(7, order.getAddress());
		ps.setString(8, order.getPostal());
		ps.setString(9, order.getMobile());
		ps.setString(10, order.getPhone());
		int i=ps.executeUpdate();
		
		if(i==1){
			ps=conn.prepareStatement("select last_insert_id()");
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				id=rs.getInt(1);
			}
			
		}
		return id;
		
	}

}
