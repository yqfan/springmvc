package com.yqfan.simplemvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yqfan.simplemvc.model.MyUser;

public class JdbcUserDao implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(JdbcUserDao.class);
	
	private DataSource dataSource;
	private PasswordEncoder passwordEncoder;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void insert(MyUser user) {
		String name = user.getUserName();
		String pass = passwordEncoder.encode(user.getPassWord());
		
		long votes = user.getTotalVotes();
		String role = "ROLE_USER";
		if (findByName(name) != null) {
			return;
		}
		
		String sql = "INSERT INTO users(username, password, votes, enabled) VALUES (?,?,?,TRUE)";
		String sql2 = "INSERT INTO user_roles(username, role) VALUES(?, ?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pass);
			ps.setLong(3,votes);
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
		String sql = "SELECT users.username,users.password,users.votes,user_roles.role FROM users, user_roles WHERE users.username = ? AND user_roles.username = ?";
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
				user.setTotalVotes(rs.getLong(3));
				user.setRole(rs.getString(4));
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

	@Override
	public Collection<MyUser> getAll() {
		ArrayList<MyUser> res = new ArrayList<MyUser>();
		String sql = "SELECT * from users";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("username");
				long cnt = rs.getLong("votes");
				MyUser myuser = new MyUser();
				myuser.setUserName(name);
				myuser.setTotalVotes(cnt);
				res.add(myuser);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		return res;
	}

}
