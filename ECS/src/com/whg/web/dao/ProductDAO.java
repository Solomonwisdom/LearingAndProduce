package com.whg.web.dao;

import java.sql.SQLException;
import java.util.List;
import com.whg.web.entity.Product;

public interface ProductDAO {
    public List<Product> findNewProduct(int top) throws SQLException;
    public List<Product> findHot() throws SQLException;
    public List<Product> findNewHot() throws SQLException;
    public Product findById(int pid) throws SQLException;
    public int save(Product product) throws SQLException;
    public void update(Product product) throws SQLException;
    public void delete(int id) throws SQLException;
}
