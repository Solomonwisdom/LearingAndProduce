package com.whg.web.action.admin;

import com.whg.web.dao.BookDAO;
import com.whg.web.entity.Book;
import com.whg.web.impl.JdbcBookDAO;

import java.util.List;

/**
 * Created by wanghaogang on 2017/7/4.
 */
public class ProductListAction {

    private List<Book> bookList;
    public String execute() throws Exception {
        BookDAO bdao =new JdbcBookDAO();
        bookList = bdao.showAll();
        return "success";
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
