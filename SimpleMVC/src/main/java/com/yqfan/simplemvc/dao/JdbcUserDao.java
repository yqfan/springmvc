package com.yqfan.simplemvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yqfan.simplemvc.model.MyUser;

public class JdbcUserDao implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(JdbcUserDao.class);
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void insert(MyUser user) {
		String name = user.getUserName();
		String pass = user.getPassWord();
		String role = "ROLE_USER";
		if (findByName(name) != null) {
			return;
		}
		
		String sql = "INSERT INTO users(username, password, enabled) VALUES (?,?,TRUE)";
		String sql2 = "INSERT INTO user_roles(username, role) VALUES(?, ?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pass);
			ps.executeUpdate();
			ps.close();
			
			ps = conn.prepareStatement(sql2);
			ps.setString(1, name);
			ps.setString(2, role);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public MyUser findByName(String userName) {
		String sql = "SELECT users.username,users.password,user_roles.role FROM users, user_roles WHERE users.username = ? AND user_roles.username = ?";
		Connection conn = null;
		MyUser user = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  userName);
			ps.setString(2, userName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new MyUser();
				user.setUserName(rs.getString(1));
				user.setPassWord(rs.getString(2));
				user.setRole(rs.getString(3));
			}
			rs.close();
			ps.close();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public void updateItem(MyUser user) {
		String name = user.getUserName();
		String pass = user.getPassWord();
		long cnt = user.getTotalVotes();
		String role = "ROLE_USER";
		
		String sql1 = "UPDATE users SET password=?, votes=? where username=?";
		String sql2 = "UPDATE user_roles SET role=? where username=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setString(1, pass);
			ps.setLong(2, cnt);
			ps.setString(3, name);
			ps.executeUpdate();
			ps.close();
			
			ps = conn.prepareStatement(sql2);
			ps.setString(1,  role);
			ps.setString(2, name);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}

}
