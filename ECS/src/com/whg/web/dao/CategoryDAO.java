package com.whg.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.whg.web.entity.Category;

public interface CategoryDAO {
    public List<Category> findAll() throws SQLException;
    public List<Category> findByParentId(int id) throws SQLException;
    public void save(int product_id, int cat_id) throws SQLException;
    public void delete(int product_id) throws SQLException;
    public String queryCat(int product_id) throws SQLException;
}
