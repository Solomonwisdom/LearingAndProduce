package com.whg.web.util;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DBUtil {
	private static DataSource ds;
	//在同一个线程中保存某个对象
	private static final ThreadLocal<Connection>
		connLocal = new ThreadLocal<Connection>();
	static {
		Properties props = new Properties();
		try {
			// 记载连接池参数！实例化连接池对象
			props.load(DBUtil.class.getClassLoader().getResourceAsStream(
					"dbcp.properties"));
			ds = BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		// 连接池中获取Connection
		Connection conn=connLocal.get();
		while(conn==null || conn.isClosed()){
			conn=ds.getConnection();
		}
		connLocal.set(conn);
		return conn;

	}

	// 释放Connection
	public static void close() throws SQLException {
		Connection conn = connLocal.get();
		connLocal.set(null);
		if(conn != null && !conn.isClosed()){
			conn.close();
		}
	}
}