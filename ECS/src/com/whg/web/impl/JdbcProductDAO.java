package com.whg.web.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whg.web.dao.ProductDAO;
import com.whg.web.entity.Product;
import com.whg.web.util.DBUtil;

public class JdbcProductDAO implements ProductDAO {

	public int save(Product product) throws SQLException {
		int id = -1;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into d_product"
				+ "(product_name,description,add_time,"
				+ "fixed_price,dang_price,"
				+ "keywords,has_delected,product_pic)"
				+ "values(?,?,?,?,?,?,?,?)");
		ps.setString(1, product.getProductName());
		ps.setString(2, product.getDescription());
		ps.setLong(3, product.getAddTime());
		ps.setDouble(4, product.getFixedPrice());
		ps.setDouble(5, product.getDangPrice());
		ps.setString(6, product.getKeywords());
		ps.setInt(7, product.getHasDeleted());
		ps.setString(8, product.getProductPic());
		ps.executeUpdate();
		ps = conn.prepareStatement("select max(id) from d_product");
		ResultSet rst = ps.executeQuery();
		if(rst.next())
			id = rst.getInt(1);
		return id;
	}

	@Override
	public void update(Product product) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("update d_product set product_name=?,"
				+ "description=?, fixed_price=?,dang_price=?,product_pic=? where id=?");
		ps.setString(1, product.getProductName());
		ps.setString(2, product.getDescription());
		ps.setDouble(3, product.getFixedPrice());
		ps.setDouble(4, product.getDangPrice());
		ps.setString(5, product.getProductPic());
		ps.setInt(6, product.getId());
		ps.executeUpdate();
	}

	@Override
	public void delete(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from d_product where id=?");
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	public List<Product> findNewProduct(int top) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from d_product "
				+ "where has_deleted=0 order " + "by add_time limit 0,?");
		ps.setInt(1, top);
		ResultSet rs = ps.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while (rs.next()) {
			Product pro = new Product();
			pro.setId(rs.getInt("id"));
			pro.setProductName(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
			list.add(pro);
		}
	
		return list;
	}

	public List<Product> findHot() throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("select dp.* from "
				+ "d_item di join d_product " + "dp on di.product_id=dp.id "
				+ "group by product_id order by "
				+ "sum(product_num) desc limit 0,4");
		ResultSet rs = ps.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while (rs.next()) {
			Product p = new Product();
			p.setProductPic(rs.getString("product_pic"));
			p.setProductName(rs.getString("product_name"));
			p.setFixedPrice(rs.getDouble("fixed_price"));
			p.setDangPrice(rs.getDouble("dang_price"));
			list.add(p);
		}
	
		return list;
	}

	public List<Product> findNewHot() throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("select dp.* from d_item di right outer join d_product dp on di.product_id=dp.id group by dp.id order by sum(di.product_num) desc limit 0,9");
		ResultSet rs = ps.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while (rs.next()) {
			Product p = new Product();
			p.setProductName(rs.getString("product_name"));
			list.add(p);
		}
		

		return list;
	}
	public Product findById(int pid) throws SQLException {
	Connection conn=DBUtil.getConnection();
	PreparedStatement ps=conn.prepareStatement("select * from d_product where id=?");
	ps.setInt(1, pid);
	ResultSet rs=ps.executeQuery();
	Product p=new Product();
	if(rs.next()){
		p.setId(rs.getInt("id"));
		p.setFixedPrice(rs.getDouble("fixed_price"));
		p.setDangPrice(rs.getDouble("dang_price"));
		p.setProductName(rs.getString("product_name"));
	}
		return p;
	}

	public static void main(String[] args) throws Exception {
		ProductDAO dao = new JdbcProductDAO();
		List<Product> list = dao.findNewProduct(8);
		for (Product p : list) {
			System.out.println(p.getId());
		}
		list = dao.findHot();
		for (Product p : list) {
			System.out.println(p.getProductPic());

		}
		list = dao.findNewHot();
		for (Product p : list) {
			System.out.println(p.getProductName());

		}
		System.out.println(dao.findById(12).getProductName());
	}
}
