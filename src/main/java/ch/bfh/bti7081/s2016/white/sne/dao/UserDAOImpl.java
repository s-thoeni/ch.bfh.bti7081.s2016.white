package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ch.bfh.bti7081.s2016.white.sne.data.User;

public class UserDAOImpl implements UserDAO {
	
	private Connection connection;
	private Statement stm;

	public UserDAOImpl() {
		// Load JDBC Driver
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC class not found " + e.getMessage());
		}
	}
	
	@Override
	public ArrayList<User> getUserlist() {
		
		ArrayList<User> userList = new ArrayList<User>();

		try {
			try {
				this.connection = DriverManager.getConnection("jdbc:sqlite:db/conf.db");
			} catch (SQLException e) {
				System.out.println("Could not set connection to conf.db " + e.getMessage());
			}
			stm = connection.createStatement();
			String query = " SELECT u.userName, u.userPassword FROM User AS u;";

			// System.out.println(query);

			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				String username = rs.getString("userName");
				String password = rs.getString("userPassword");
				System.out.println(rs.getString("userName"));
				System.out.println(rs.getString("userPassword"));
				userList.add(new User(username,password));
			}
		} catch (SQLException e) {
			System.out.println("Could not execute getConfig query " + e.getMessage());
		} finally {
			try {
				if (this.stm != null)
					this.stm.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (this.connection != null)
					this.connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return userList;
		
	}
}
