package com.whg.web.action.user;

import com.whg.web.action.BaseAction;
import com.whg.web.common.Constant;
import com.whg.web.dao.UserDAO;
import com.whg.web.entity.User;
import com.whg.web.impl.JdbcUserDAO;
import com.whg.web.util.DigestUtil;
import com.whg.web.util.EmailUtil;
import com.whg.web.util.VerifyUtil;

//注册
public class RegisterAction extends BaseAction {
	private User user;

	public String execute() throws Exception {
		// 密码加密
		String pwd = DigestUtil.digestMD5(user.getPassword());
		user.setPassword(pwd);
		// 非表单项设置
		user.setUserIntegral(Constant.NORMAL);
		user.setEmailVerify("NO");
		String code = VerifyUtil.createVerifyCode();
		user.setEmailVerifyCode(code);
		user.setLastLoginTime(System.currentTimeMillis());
		String ip = httpRequest.getRemoteAddr();
		user.setLastLoginIp(ip);
		UserDAO dao = new JdbcUserDAO();
		dao.save(user);
		EmailUtil.sendEmail(user.getEmail(), user.getEmailVerifyCode());
		return "success";

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
