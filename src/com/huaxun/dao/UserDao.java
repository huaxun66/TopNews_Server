package com.huaxun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.huaxun.bean.UserBean;
import com.huaxun.db.DataSourceImpl;

public class UserDao {
	private String SqlDriver;
	private String SqlUrl;
	private String SqlUsername;
	private String SqlPassword;

	public UserDao() {
		try {
			Properties prop = new Properties();
			prop.load(this.getClass().getResourceAsStream("db.properties"));
			SqlDriver = prop.getProperty("SqlDriver");
			SqlUrl = prop.getProperty("SqlUrl");
			SqlUsername = prop.getProperty("SqlUsername");
			SqlPassword = prop.getProperty("SqlPassword");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<UserBean> getAllUsers() throws Exception {
		List<UserBean> users = new ArrayList<UserBean>();
		String sql = "SELECT username, password, email, gender FROM userinfo";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
//			conn = DataSourceImpl.getInstance().getConnection();
			Class.forName(SqlDriver);			
			conn = DriverManager.getConnection(SqlUrl, SqlUsername, SqlPassword);			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserBean user = new UserBean();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				users.add(user);
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed! ");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return users;
	}

	public UserBean findUserByName(String username) throws Exception {
		UserBean user = null;
		String sql = "SELECT username, password, email, gender, usericon FROM userinfo where username = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
//			conn = DataSourceImpl.getInstance().getConnection();
			Class.forName(SqlDriver);			
			conn = DriverManager.getConnection(SqlUrl, SqlUsername, SqlPassword);		
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserBean();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setUsericon(rs.getString("usericon"));
				System.out.println("-------" + user.toString() + "--------");
			}			
		} catch (SQLException e) {
			System.out.println("Connection Failed! ");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return user;
	}
	
	public String createUser(String username, String password, String email, String gender, boolean containUserIcon) 
			throws Exception {
		String result;
		String sql = "INSERT INTO userinfo VALUES (?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement ps = null;
		try {
//			conn = DataSourceImpl.getInstance().getConnection();
			Class.forName(SqlDriver);			
			conn = DriverManager.getConnection(SqlUrl, SqlUsername, SqlPassword);		
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, gender);
			if (containUserIcon) {
				ps.setString(5, "e:\\topnews\\usericon\\" + username + "_icon.png");
			} else {
				ps.setString(5, "null");
			}
			
			int ret = ps.executeUpdate(); //ret代表更新的的计数
			if (ret != 0) {
				result = "注册成功";
			} else {
				result = "注册失败(插入数据库失败)";
			}		
		} catch (SQLException e) {
			System.out.println("Connection Failed! ");
			result = "注册失败(连接数据库失败)";
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return result;
	}
	
	public UserBean userLogin(String username, String password) throws Exception {
		UserBean user = null;
		String sql = "SELECT username, password, email, gender, usericon FROM userinfo where username = ? AND password = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
//			conn = DataSourceImpl.getInstance().getConnection();
			Class.forName(SqlDriver);			
			conn = DriverManager.getConnection(SqlUrl, SqlUsername, SqlPassword);		
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserBean();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setUsericon(rs.getString("usericon"));
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed! ");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return user;
	}
	
	
}
