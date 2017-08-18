package com.whg.web.action.main;

import java.sql.SQLException;
import java.util.List;

import com.whg.web.dao.BookDAO;
import com.whg.web.entity.Book;
import com.whg.web.impl.JdbcBookDAO;

public class BookAction {
	private List<Book> books;
	private int num=2;
	public String execute() throws Exception{
		BookDAO dao=new JdbcBookDAO();
		books=dao.findBook(num);
		return "success";
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
