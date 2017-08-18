package com.whg.web.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whg.web.dao.UserDAO;
import com.whg.web.entity.User;
import com.whg.web.util.DBUtil;

public class JdbcUserDAO implements UserDAO {

	public void save(User user) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into d_user"
				+ "(email,nickname,password,"
				+ "user_integral,is_email_verify,"
				+ "email_verify_code,last_login_time," + "last_login_ip)"
				+ "values(?,?,?,?,?,?,?,?)");
		ps.setString(1, user.getEmail());
		ps.setString(2, user.getNickname());
		ps.setString(3, user.getPassword());
		ps.setInt(4, user.getUserIntegral());
		ps.setString(5, user.getEmailVerify());
		ps.setString(6, user.getEmailVerifyCode());
		ps.setLong(7, user.getLastLoginTime());
		ps.setString(8, user.getLastLoginIp());
		ps.executeUpdate();
		

	}

	public User findByEmail(String email) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("select*from d_user"
				+ "  where email=?");
		ps.setString(1, email);
		User user = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setUserIntegral(rs.getInt("user_integral"));
			user.setNickname(rs.getString("nickname"));
			user.setEmailVerify(rs.getString("is_email_verify"));
			user.setEmailVerifyCode(rs.getString("email_verify_code"));
			user.setLastLoginTime(rs.getLong("last_login_time"));
			user.setLastLoginIp(rs.getString("last_login_ip"));
		}

		return user;
	}

	public User findById(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from d_user"
				+ "  where id=?");
		ps.setInt(1, id);
		User user = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setUserIntegral(rs.getInt("user_integral"));
			user.setNickname(rs.getString("nickname"));
			user.setEmailVerify(rs.getString("is_email_verify"));
			user.setEmailVerifyCode(rs.getString("email_verify_code"));
			user.setLastLoginTime(rs.getLong("last_login_time"));
			user.setLastLoginIp(rs.getString("last_login_ip"));
		}

		return user;
	}

	@Override
	public void modifyPassword(int id, String password) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("update d_user "
				+ "set password=? " + "where id=?");
		ps.setString(1, password);
		ps.setInt(2, id);
		ps.executeUpdate();
	}

	@Override
	public void deleteById(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from d_user where id=?");
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	public void modifyCode(String email) throws SQLException {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=conn.prepareStatement("update d_user set is_email_verify='YES' where email=?");
		ps.setString(1,email);
		ps.executeUpdate();
	
	}

	@Override
	public void modifyLastLogin(User user) throws SQLException {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=conn.prepareStatement("update d_user set last_login_time=?,last_login_ip=? where id=?");
		ps.setLong(1, user.getLastLoginTime());
		ps.setString(2, user.getLastLoginIp());
		ps.setInt(3, user.getId());
		ps.executeUpdate();
	}

	@Override
	public List<User> showAll() throws SQLException {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=conn.prepareStatement("select id,email,nickname,last_login_time,last_login_ip from d_user");
		ResultSet rs=ps.executeQuery();
		List<User> userList = new ArrayList<>();
		while(rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setNickname(rs.getString("nickname"));
			user.setLastLoginTime(rs.getLong("last_login_time"));
			user.setLastLoginIp(rs.getString("last_login_ip"));
			userList.add(user);
		}
		return userList;
	}

}
