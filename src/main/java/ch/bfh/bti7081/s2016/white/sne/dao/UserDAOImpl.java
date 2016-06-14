package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Loads and provides userlist and passwords
 * Implements connection to data backend (sqlite3)
 * 
 * @author shepd1
 *
 */

public class UserDAOImpl extends AbstractDAO implements UserDAO {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);
	
	/**
	 * database name as constant
	 */
	private static final String DB_NAME = "conf.db";
	
	/**
	 * SQL query for getting alarms of specified user 
	 */
	private static final String SELECT_USERS = "SELECT userName, userPassword FROM User;";


	/**
	 * Default constructor
	 * @throws SneException 
	 * 
	 */
	public UserDAOImpl() throws SneException  {
		super();
	}
	
	@Override
	public ArrayList<User> getUserlist() throws SneException {
		logger.debug("->");
		
		ArrayList<User> userList = new ArrayList<User>();
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			con = getConnection(DB_NAME);
			stm = con.prepareStatement(SELECT_USERS);
			
			// log query
			logger.debug(SELECT_USERS);

			rs = stm.executeQuery();

			while (rs.next()) {
				String username = rs.getString("userName");
				String password = rs.getString("userPassword");
				System.out.println(rs.getString("userName"));
				System.out.println(rs.getString("userPassword"));
				userList.add(new User(username,password));
			}
		} catch (SQLException e) {
			// log error
			logger.error("select on database " + DB_NAME + " failed \n" + e.getMessage(), e);
			throw new SneException("could not retrieve users from database! ", e);
		} finally {
			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// log error
				logger.error("failed to close sql-connection \n" + e.getMessage(), e);
				throw new SneException("could not close database connection! Your data might be in danger! ", e);
			}		
		}
		logger.debug("<-");
		return userList;
	}
}
