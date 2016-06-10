package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ch.bfh.bti7081.s2016.white.sne.data.User;

abstract class AbstractDAO {
	
	private static final String SELECT_USER_ID = "SELECT userID FROM User WHERE userName == ?";
	
	public AbstractDAO() {
		// Load JDBC Driver
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC class not found " + e.getMessage());
		}
	}

	abstract public String getDbName();

	protected Connection getConnection() throws SQLException  {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:db/" + getDbName());
		} catch (SQLException up) {
			up.printStackTrace();
			System.out.println("Was not able to open connection.");
			throw up;
		}
		return connection;
	}
	
	protected void close(ResultSet rs, PreparedStatement stm, Connection con) throws SQLException {
			try {
				if(rs != null)
				rs.close();
				if(stm != null)
				stm.close();
				if(con != null)
				con.close();
			} catch (SQLException up) {
				up.printStackTrace();
				System.out.println("Was not able to close all resources.");
				throw up;
			}
		
	}
	
	protected int getUserId(User user) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		int id = 0;

		// get userName id
		try {
			con = getConnection();
			stm = con.prepareStatement(SELECT_USER_ID);
			stm.setString(1, user.getUserName());
			rs = stm.executeQuery();
			id = rs.getInt("userID");
		} catch (SQLException e) {
			System.out.println("Could not execute query " + e.getMessage());
		} finally {
			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// TODO
				e.printStackTrace();
			}
		}
		return id;
	}

}
