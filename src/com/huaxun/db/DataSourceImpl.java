package com.huaxun.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataSourceImpl {
	// 数据源
	private DataSource ds;
	private static DataSourceImpl instance;

	// 构造函数
	private DataSourceImpl() {
	}

	// 获得数据源
	public static DataSourceImpl getInstance() {
		// 单例模式
		if (instance == null) {
			instance = new DataSourceImpl();
		}
		return instance;
	}

	// 获得连接
	public Connection getConnection() throws Exception {
		if (ds == null) {
			// 初始化JNDI的查找工具对象
			InitialContext ctx = new InitialContext();
			// 通过JNDI名称查找数据源对象
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql");
		}

		return ds.getConnection();
	}

	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

