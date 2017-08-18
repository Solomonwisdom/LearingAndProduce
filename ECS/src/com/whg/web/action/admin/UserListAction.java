package com.whg.web.action.admin;

import com.whg.web.entity.User;
import com.whg.web.impl.JdbcUserDAO;

import java.util.List;

/**
 * Created by wanghaogang on 2017/7/4.
 */
public class UserListAction {
    private List<User> userList;
    public String execute() throws Exception {
        userList = new JdbcUserDAO().showAll();
        return "success";
    }
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
