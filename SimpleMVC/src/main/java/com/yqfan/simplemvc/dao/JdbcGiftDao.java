package com.yqfan.simplemvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import com.yqfan.simplemvc.HomeController;
import com.yqfan.simplemvc.model.Gift;

public class JdbcGiftDao implements GiftDao {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Gift gift) {
		// insert gift object into mysql. gift object only has 'title' and 'description' set.
		// get an id for gift.
		// get a dataurl for gift after the id, and update the item in mysql.
		String sql = "INSERT INTO gift " +
				"(TITLE, DESCRIP, DATAURL, OWNER, TOUCHCNT, VOTEURL) VALUES (?, ?, ?, ?, ?, ?)";
		String sqlcnt = "SELECT COUNT(*) FROM gift";
		String sqlupdate="UPDATE gift SET dataurl = ?, voteurl=? WHERE id = ?";
		
		long id = -1;
		String dataurl = null;
		String voteurl = null;
		Connection conn = null;
 
		try {
			// insert gift into mysql
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, gift.getTitle());
			ps.setString(2, gift.getDescription());
			ps.setString(3, gift.getDataUrl());
			ps.setString(4, gift.getOwner());
			ps.setLong(5, gift.getTouchCount());
			ps.setString(6, gift.getVotedUserUrl());
			ps.executeUpdate();
			ps.close();
			
			// get id of the new inserted item
			ps = conn.prepareStatement(sqlcnt);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getLong(1);
				gift.setId(id);
			}
			System.out.println("insert done, gift id="+gift.getId());
			rs.close();
			ps.close();
			
			// get dataurl for this gift
			dataurl = HomeController.getGiftAbsPath(gift);
			System.out.println("dataurl="+dataurl);
			voteurl = HomeController.getVoteAbsPath(gift);
			System.out.println("voteurl="+voteurl);
			gift.setDataUrl(dataurl);
			gift.setVotedUserUrl(voteurl);
			ps = conn.prepareStatement(sqlupdate);
			ps.setString(1, dataurl);
			ps.setString(2, voteurl);
			ps.setLong(3, id);
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
	public Gift findById(long id) {
		String sql = "SELECT * FROM gift WHERE ID = ?";
		 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			Gift gift = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				gift = new Gift(
					rs.getString("TITLE"), 
					rs.getString("DESCRIP")
				);
				gift.setId(rs.getLong("ID"));
				gift.setDataUrl(rs.getString("DATAURL"));
				gift.setOwner(rs.getString("OWNER"));
				gift.setVotedUserUrl(rs.getString("VOTEURL"));
				gift.setTouchCount(rs.getLong("TOUCHCNT"));
			}
			rs.close();
			ps.close();
			return gift;
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
	public Collection<Gift> findByTitle(String title) {
		String sql = "SELECT * FROM gift WHERE TITLE = ?";
		 
		Connection conn = null;
		ArrayList<Gift> res = new ArrayList<Gift>();
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			Gift gift = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				gift = new Gift(
					rs.getString("TITLE"), 
					rs.getString("DESCRIP")
				);
				gift.setId(rs.getLong("ID"));
				gift.setDataUrl(rs.getString("DATAURL"));
				gift.setOwner(rs.getString("OWNER"));
				gift.setVotedUserUrl(rs.getString("VOTEURL"));
				gift.setTouchCount(rs.getLong("TOUCHCNT"));
				res.add(gift);
			}
			rs.close();
			ps.close();
			return res;
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
	public Collection<Gift> getAll() {
		String sql = "SELECT * FROM gift";
		Connection conn = null;
		ArrayList<Gift> res = new ArrayList<Gift>();
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			Gift gift = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				gift = new Gift(
					rs.getString("TITLE"), 
					rs.getString("DESCRIP")
				);
				gift.setId(rs.getLong("ID"));
				gift.setDataUrl(rs.getString("DATAURL"));
				gift.setOwner(rs.getString("OWNER"));
				gift.setVotedUserUrl(rs.getString("VOTEURL"));
				gift.setTouchCount(rs.getLong("TOUCHCNT"));
				res.add(gift);
			}
			rs.close();
			ps.close();
			return res;
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
	public void updateItem(Gift gift) {
		long id = gift.getId();
		String title = gift.getTitle();
		String description = gift.getDescription();
		String owner = gift.getOwner();
		long touchCount = gift.getTouchCount();
		String dataurl = gift.getDataUrl();
		String voteurl = gift.getVotedUserUrl();
		
		String sql="UPDATE gift SET title=?, descrip=?, owner=?, dataurl=?, touchcnt=?, voteurl=? WHERE id = ?";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, description);
			ps.setString(3, owner);
			ps.setString(4, dataurl);
			ps.setLong(5, touchCount);
			ps.setString(6, voteurl);
			ps.setLong(7, id);
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
