package com.whg.web.action.user;

import java.util.Date;

import com.whg.web.action.BaseAction;
import com.whg.web.common.Constant;
import com.whg.web.dao.UserDAO;
import com.whg.web.entity.User;
import com.whg.web.impl.JdbcUserDAO;
import com.whg.web.util.DigestUtil;

public class LoginAction extends BaseAction {
	private String uri;
	private String name;
	private String password;
	private Date time;
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String execute() throws Exception {
		time = new Date();
		UserDAO dao = new JdbcUserDAO();
		String pwd = DigestUtil.digestMD5(password);
		User user = dao.findByEmail(name);
		if (user != null && pwd.equals(user.getPassword())
				&& user.getEmailVerify().equals("YES")) {
			session.put("name", name);
			session.put("user", user);
			user.setLastLoginTime(System.currentTimeMillis());
			String ip = httpRequest.getRemoteAddr();
			user.setLastLoginIp(ip);
			new JdbcUserDAO().modifyLastLogin(user);
			if(user.getUserIntegral() == Constant.ADMIN)
				return "admin";
			else
				return "login";
		} else if (user != null && pwd.equals(user.getPassword())
				&& user.getEmailVerify().equals("NO")) {
			request.put("user", user);
			return "code";
		} else {

			request.put("login_err", "用户名或者密码错误");
			return "lose";

		}
	}

	// 登出
	public String out() {
		session.remove("user");
		session.remove("cart");
		session.remove("name");
		return "success";

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
