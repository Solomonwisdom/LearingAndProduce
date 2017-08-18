package com.whg.web.action.user;

import com.whg.web.action.BaseAction;
import com.whg.web.dao.UserDAO;
import com.whg.web.entity.User;
import com.whg.web.impl.JdbcUserDAO;

public class CheckUCodeAction extends BaseAction {
	private String code;

	private String email;

	public String execute() throws Exception {
		UserDAO dao = new JdbcUserDAO();
		User user = dao.findByEmail(email);
		if (code.equals(user.getEmailVerifyCode())) {
			dao.modifyCode(email);
			session.put("user", user);
			return "success";
		} else {
			request.put("code_err", "验证码输入错误");
			//request每次提交都绑定一个user
			request.put("user",user );
			return "faile";
		}

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
