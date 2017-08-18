package com.whg.web.action.admin;

import com.whg.web.dao.UserDAO;
import com.whg.web.impl.JdbcUserDAO;

/**
 * Created by wanghaogang on 2017/7/5.
 */
public class UserDelAction {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    public String execute() throws Exception {
        UserDAO userDAO = new JdbcUserDAO();
        userDAO.deleteById(id);
        return "success";
    }
}
