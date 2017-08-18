package com.whg.web.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.whg.web.dao.BookDAO;
import com.whg.web.entity.Book;
import com.whg.web.util.DBUtil;

public class JdbcBookDAO implements BookDAO {

	public void save(Book book) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into d_book"
					+ "(id,author,publishing,publish_time,"
					+ "word_number,which_edtion,"
					+ "total_page,isbn,author_summary,catalogue)"
					+ "values(?,?,?,?,?,?,?,?,?,?)");
		ps.setInt(1, book.getId());
		ps.setString(2, book.getAuthor());
		ps.setString(3, book.getPublishing());
		ps.setLong(4, book.getPublishTime());
		ps.setString(5, book.getWordNumber());
		ps.setString(6, book.getWhichEdtion());
		ps.setString(7, book.getTotalPage());
		ps.setString(8, book.getIsbn());
		ps.setString(9, book.getAuthorSummary());
		ps.setString(10, book.getCatalogue());
		ps.executeUpdate();
	}

	@Override
	public void update(Book book) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("update d_book set author=?,"
					+ "publishing=?,word_number=?,which_edtion=?,total_page=?,isbn=?,"
					+ "author_summary=?,catalogue=? where id=?");
		ps.setString(1, book.getAuthor());
		ps.setString(2, book.getPublishing());
		ps.setString(3, book.getWordNumber());
		ps.setString(4, book.getWhichEdtion());
		ps.setString(5, book.getTotalPage());
		ps.setString(6, book.getIsbn());
		ps.setString(7, book.getAuthorSummary());
		ps.setString(8, book.getCatalogue());
		ps.setInt(9, book.getId());
		ps.executeUpdate();
	}

	public List<Book> findBook(int num) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("select  dp.id,product_pic,product_name,"
						+ "author,publishing,publish_time,"
						+ "catalogue,fixed_price,dang_price "
						+ "from d_product dp join d_book "
						+ "db on dp.id=db.id");
		ResultSet rs = ps.executeQuery();
		List<Book> books = new ArrayList<Book>();
		while (rs.next()) {
			Book b = new Book();
			b.setId(rs.getInt("id"));
			b.setProductPic(rs.getString("product_pic"));
			b.setProductName(rs.getString("product_name"));
			b.setAuthor(rs.getString("author"));
			b.setPublishing(rs.getString("publishing"));
			b.setPublishTime(rs.getLong("publish_time"));
			b.setCatalogue(rs.getString("catalogue"));
			b.setFixedPrice(rs.getDouble("fixed_price"));
			b.setDangPrice(rs.getLong("dang_price"));
			books.add(b);
		}

		List<Book> list = new ArrayList<Book>();
		for(int i=0;i<books.size();i++){
			int size = books.size();
			Random ran = new Random();
			Book book = books.get(ran.nextInt(size));
			books.remove(book);
			list.add(book);
			if(list.size()==num){
				break;
			}
		}
		return list;
	}

	public List<Book> findUpByCatId(int pid, int size, int page) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("select dp.id,"
				+ "dp.product_name,dp.fixed_price,dp.dang_price,"
				+ "dp.product_pic,dp.description,db.author,"
				+ "db.publishing,db.publish_time from  "
				+ "d_category_product dcp join d_product dp "
				+ "on dcp.product_id=dp.id join d_book db on "
				+ "db.id=dp.id where dcp.cat_id=? order by dp.id limit ?,?");
		ps.setInt(1, pid);
		ps.setInt(2, size * (page - 1));
		ps.setInt(3, size);
		ResultSet rs = ps.executeQuery();
		List<Book> list = new ArrayList<Book>();
		while (rs.next()) {
			Book b = new Book();
			b.setId(rs.getInt("id"));
			b.setProductName(rs.getString("product_name"));
			b.setFixedPrice(rs.getDouble("fixed_price"));
			b.setDangPrice(rs.getDouble("dang_price"));
			b.setProductPic(rs.getString("product_pic"));
			b.setDescription(rs.getString("description"));
			b.setAuthor(rs.getString("author"));
			b.setPublishing(rs.getString("publishing"));
			b.setPublishTime(rs.getLong("publish_time"));
			list.add(b);

		}
		
		return list;

	}

	@Override
	public List<Book> findUpByCatId(int pid) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("select dp.id,"
				+ "dp.product_name,dp.fixed_price,dp.dang_price,"
				+ "dp.product_pic,dp.description,db.author,"
				+ "db.publishing,db.publish_time from  "
				+ "d_category_product dcp join d_product dp "
				+ "on dcp.product_id=dp.id join d_book db on "
				+ "db.id=dp.id where dcp.cat_id=? order by dp.id");
		ps.setInt(1, pid);
		ResultSet rs = ps.executeQuery();
		List<Book> bookList = new ArrayList<Book>();
		while (rs.next()) {
			Book b = new Book();
			b.setId(rs.getInt("id"));
			b.setProductName(rs.getString("product_name"));
			b.setFixedPrice(rs.getDouble("fixed_price"));
			b.setDangPrice(rs.getDouble("dang_price"));
			b.setProductPic(rs.getString("product_pic"));
			b.setDescription(rs.getString("description"));
			b.setAuthor(rs.getString("author"));
			b.setPublishing(rs.getString("publishing"));
			b.setPublishTime(rs.getLong("publish_time"));
			bookList.add(b);
		}
		return bookList;
	}

	public List<Book> findDownByCatId(int pid, int size, int page) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("select dp.id,"
				+ "dp.product_name,dp.fixed_price,dp.dang_price,"
				+ "dp.product_pic,dp.description,db.author,"
				+ "db.publishing,db.publish_time from  "
				+ "d_category_product dcp join d_product dp "
				+ "on dcp.product_id=dp.id join d_book db on "
				+ "db.id=dp.id where dcp.cat_id=? order by dp.id desc limit ?,?");
		ps.setInt(1, pid);
		ps.setInt(2, size * (page - 1));
		ps.setInt(3, size);
		ResultSet rs = ps.executeQuery();
		List<Book> list = new ArrayList<Book>();
		while (rs.next()) {
			Book b = new Book();
			b.setId(rs.getInt("id"));
			b.setProductName(rs.getString("product_name"));
			b.setFixedPrice(rs.getDouble("fixed_price"));
			b.setDangPrice(rs.getDouble("dang_price"));
			b.setProductPic(rs.getString("product_pic"));
			b.setDescription(rs.getString("description"));
			b.setAuthor(rs.getString("author"));
			b.setPublishing(rs.getString("publishing"));
			b.setPublishTime(rs.getLong("publish_time"));
			list.add(b);
		}
		return list;

	}

	public int count(int pid) throws SQLException{
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("select count(*) from  "
				+ "d_category_product dcp join d_product dp "
				+ "on dcp.product_id=dp.id join d_book db on "
				+ "db.id=dp.id where dcp.cat_id=?");
		ps.setInt(1, pid);
		ResultSet rs=ps.executeQuery();
		int count=0;
		while(rs.next()){
			count=rs.getInt("count(*)");
		}
		return count;
		
	}

	@Override
	public List<Book> showAll() throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("select  dp.id, dp.product_name,"
				+ "db.author, db.isbn "
				+ "from d_product dp join d_book "
				+ "db on dp.id=db.id");
		ResultSet rs = ps.executeQuery();
		List<Book> list = new ArrayList<Book>();
		while(rs.next()) {
			Book b = new Book();
			b.setId(rs.getInt("id"));
			b.setProductName(rs.getString("product_name"));
			b.setAuthor(rs.getString("author"));
			b.setIsbn(rs.getString("isbn"));
			list.add(b);
		}
		return list;
	}

	@Override
	public void delete(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from d_book where id=?");
		ps.setInt(1, id);
		ps.execute();
	}

	public Book findById(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("select  dp.id,product_pic,product_name,"
						+ "author,publishing,publish_time,catalogue,fixed_price,"
						+ "dang_price,db.word_number,db.total_page,db.isbn,db.author_summary,"
						+ "db.which_edtion, dp.description "
						+ "from d_product dp join d_book "
						+ "db on dp.id=db.id where dp.id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		List<Book> books = new ArrayList<Book>();
		Book b = null;
		if (rs.next()) {
			b = new Book();
			b.setId(rs.getInt("id"));
			b.setProductPic(rs.getString("product_pic"));
			b.setProductName(rs.getString("product_name"));
			b.setAuthor(rs.getString("author"));
			b.setPublishing(rs.getString("publishing"));
			b.setPublishTime(rs.getLong("publish_time"));
			b.setCatalogue(rs.getString("catalogue"));
			b.setFixedPrice(rs.getDouble("fixed_price"));
			b.setDangPrice(rs.getDouble("dang_price"));
			b.setWordNumber(rs.getString("word_number"));
			b.setTotalPage(rs.getString("total_page"));
			b.setIsbn(rs.getString("isbn"));
			b.setAuthorSummary(rs.getString("author_summary"));
			b.setWhichEdtion(rs.getString("which_edtion"));
			b.setDescription(rs.getString("description"));
			books.add(b);
		}
		return b;
	}

	public static void main(String[] args) throws SQLException {
		BookDAO dao = new JdbcBookDAO();
		List<Book> list = dao.findUpByCatId(1, 4, 2);
		for (Book b : list) {
			System.out.println(b.getProductName());
		}
	}

}
