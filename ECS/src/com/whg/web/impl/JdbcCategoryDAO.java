package com.whg.web.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whg.web.dao.CategoryDAO;
import com.whg.web.entity.Category;
import com.whg.web.util.DBUtil;

public class JdbcCategoryDAO implements CategoryDAO{

	public void save(int product_id, int cat_id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into d_category_product"
				+"(product_id, cat_id)"+" values(?,?)");
		ps.setInt(1, product_id);
		ps.setInt(2, cat_id);
		ps.executeUpdate();
	}

	@Override
	public void delete(int product_id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from d_category_product where product_id=?");
		ps.setInt(1, product_id);
		ps.executeUpdate();
	}

	@Override
	public String queryCat(int product_id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("select cat_id from d_category_product where product_id=?");
		ps.setInt(1, product_id);
		ResultSet rs = ps.executeQuery();
		String category ="";
		while(rs.next()) {
			int cat_id = rs.getInt(1);
			if(!category.isEmpty())
				category += ",";
			category += cat_id;
		}
		return category;
	}

	public List<Category> findAll() throws SQLException {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=conn.prepareCall("select * from d_category order by turn");
		List<Category> list=new ArrayList<Category>();
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			Category cat=new Category();
			cat.setId(rs.getInt("id"));
			cat.setEnName(rs.getString("en_name"));
			cat.setName(rs.getString("name"));
			cat.setParentId(rs.getInt("parent_id"));
			cat.setTurn(rs.getInt("turn"));
			cat.setDescription(rs.getString("description"));
			list.add(cat);

		}
		return list;
	}
	public List<Category> findByParentId(int id) throws SQLException{
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=conn.prepareStatement("select dc.*, " +
				"count(dcp.product_id) as " +
				"pnum from d_category dc left " +
				"outer join d_category_product " +
				"dcp on dcp.cat_id=dc.id where " +
				"parent_id=? group by dc.id order by turn");
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		List<Category> list=new ArrayList<Category>();
		while(rs.next()){
			Category cat = new Category();
			cat.setId(rs.getInt("id"));
			cat.setName(rs.getString("name"));
			cat.setEnName(rs.getString("en_name"));
			cat.setTurn(rs.getInt("turn"));
			cat.setDescription(rs.getString("description"));
			cat.setParentId(rs.getInt("parent_id"));
			//获取统计值
			cat.setPnum(rs.getInt("pnum"));
			list.add(cat);
		}

		return list;
		
		
	}
	public static void main(String[] args) throws SQLException{
		CategoryDAO dao=new JdbcCategoryDAO();
		List<Category> list=dao.findAll();
		for(Category c:list){
			System.out.println(c.getTurn());
		}
	}

}
