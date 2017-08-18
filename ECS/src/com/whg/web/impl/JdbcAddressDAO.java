package com.whg.web.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whg.web.dao.AddressDAO;
import com.whg.web.entity.Address;
import com.whg.web.util.DBUtil;

public class JdbcAddressDAO implements AddressDAO {
	// 保存新地址
	public void save(Address address) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into "
				+ "d_receive_address(user_id," + "receive_name,full_address,"
				+ "postal_code,mobile,phone)values(?,?,?,?,?,?)");
		ps.setInt(1, address.getUserId());
		ps.setString(2, address.getReceiveName());
		ps.setString(3, address.getAddress());
		ps.setString(4, address.getPostal());
		ps.setString(5, address.getMobile());
		ps.setString(6, address.getPhone());
		ps.executeUpdate();

	}

	// 通过用户ID查找地址
	public List<Address> findAll(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("select*from d_receive_address where user_id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		List<Address> list = new ArrayList<Address>();
		while (rs.next()) {
			Address a = new Address();
			a.setId(rs.getInt("id"));
			a.setUserId(rs.getInt("user_id"));
			a.setAddress(rs.getString("full_address"));
			a.setMobile(rs.getString("mobile"));
			a.setPhone(rs.getString("phone"));
			a.setReceiveName(rs.getString("receive_name"));
			a.setPostal(rs.getString("postal_code"));
			list.add(a);
		}
		return list;
	}

	// 通过地址ID查找地址
	public Address findById(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("select*from d_receive_address where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Address a = new Address();
		while (rs.next()) {
			a.setId(rs.getInt("id"));
			a.setUserId(rs.getInt("user_id"));
			a.setAddress(rs.getString("full_address"));
			a.setMobile(rs.getString("mobile"));
			a.setPhone(rs.getString("phone"));
			a.setReceiveName(rs.getString("receive_name"));
			a.setPostal(rs.getString("postal_code"));

		}
		return a;
	}
//测试
	public static void main(String[] args) throws SQLException {
		AddressDAO dao = new JdbcAddressDAO();
		List<Address> list = dao.findAll(6);
		for (Address a : list) {
			System.out.println(a.getAddress());
		}
		System.out.println(dao.findById(1).getReceiveName());
		Address address = new Address();
		address.setUserId(6);
		address.setAddress("ss");
		address.setMobile("ss");
		address.setPhone("ss");
		address.setPostal("ss");
		address.setReceiveName("dfdf");
		dao.save(address);
	}

}
