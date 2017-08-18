package com.whg.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.whg.web.entity.CartItem;
import com.whg.web.entity.Item;

public interface ItemDAO {
    public  void save(List<CartItem> item, int order_id) throws SQLException;
    public List<Item> findByUserId(int userId) throws SQLException;
    public List<Item> showAll() throws SQLException;
}
