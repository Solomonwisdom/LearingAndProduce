package com.whg.web.interceptor;

import java.sql.Connection;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.whg.web.util.DBUtil;

public class TransactionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Connection conn = null;
		conn = DBUtil.getConnection();
		System.out.println("事务开启");
		try {
			conn.setAutoCommit(false);
			invocation.invoke();
			if(conn!=null && !conn.isClosed())
				conn.commit();
			System.out.println("提交事务");
		} catch (SQLException e) {
			e.printStackTrace();
			if(conn != null && !conn.isClosed())
				conn.rollback();
			System.out.println("事务回滚");
			
		}finally{
			DBUtil.close();
			System.out.println("释放conn");
		}
		return null;
	}

}
