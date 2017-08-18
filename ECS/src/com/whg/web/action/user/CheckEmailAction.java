package com.whg.web.action.user;

import java.sql.SQLException;

import com.whg.web.dao.UserDAO;
import com.whg.web.entity.User;
import com.whg.web.impl.JdbcUserDAO;

public class CheckEmailAction {

	private String email;
	private boolean ok=false;
	public String execute() throws Exception{
		UserDAO dao=new JdbcUserDAO();
		User user=dao.findByEmail(email);
		if(user==null){
			ok=true;
		}else{
			ok=false;
		}
		return "success";

	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
}
