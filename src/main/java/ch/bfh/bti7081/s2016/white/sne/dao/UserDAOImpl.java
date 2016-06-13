package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Loads and provides userlist and passwords
 * Implements connection to data backend (sqlite3)
 * 
 * @author shepd1
 *
 */

public class UserDAOImpl implements UserDAO {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(Alarm.class);
	
	/**
	 * database name as constant
	 */
	private static final String DB_NAME = "conf.db";
	
	private Connection connection;
	private Statement stm;

	/**
	 * Default constructor
	 * 
	 */
	public UserDAOImpl() {
		// Load JDBC Driver
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC class not found " + e.getMessage());
		}
	}
	
	@Override
	public ArrayList<User> getUserlist() throws SneException {
		
		logger.debug("->");
		
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
			logger.error("select on database " + DB_NAME + " failed \n" + e.getMessage(), e);
			throw new SneException("Was not able to store alarms! ", e);
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
				logger.error("failed to close sql-connection", se);
				throw new SneException("Failed to close database connection! Your data might be in danger!", se);
			}		
		}
		
		logger.debug("<-");
		return userList;
	}
}
