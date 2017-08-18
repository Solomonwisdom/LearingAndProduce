package com.whg.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.whg.web.entity.User;

public  interface  UserDAO {
    public List<User> showAll() throws SQLException;
    public void save(User user) throws SQLException;
    public User findByEmail(String email) throws SQLException;
    public void modifyCode(String Email) throws SQLException;
    public void modifyLastLogin(User user) throws SQLException;
    public User findById(int id) throws SQLException;
    public void modifyPassword(int id, String password) throws SQLException;
    public void deleteById(int id) throws SQLException;
}
