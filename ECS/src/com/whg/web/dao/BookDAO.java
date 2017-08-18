package com.whg.web.dao;

import java.sql.SQLException;
import java.util.List;
import com.whg.web.entity.Book;

public interface BookDAO {
    public List<Book> findBook(int num) throws SQLException;
    public List<Book> findUpByCatId(int pid, int size, int page) throws SQLException;
    public List<Book> findUpByCatId(int pid) throws SQLException;
    public List<Book> findDownByCatId(int pid, int size, int page) throws SQLException;
    public int count(int pid) throws SQLException;
    public List<Book> showAll() throws SQLException;
    public void save(Book book) throws SQLException;
    public void update(Book book) throws SQLException;
    public void delete(int id) throws SQLException;
    public Book findById(int id) throws SQLException;
}
