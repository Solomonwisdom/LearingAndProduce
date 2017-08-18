package com.whg.web.dao;

import java.sql.SQLException;

import com.whg.web.entity.Order;

public interface OrderDAO {
    public int save(Order order) throws SQLException;
}
