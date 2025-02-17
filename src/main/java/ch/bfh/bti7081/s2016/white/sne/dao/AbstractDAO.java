package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Abstract class for data access objects. Implements functionality that all
 * DAOs have in common.
 * 
 * @author team white
 *
 */
abstract class AbstractDAO {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(AbstractDAO.class);

	/**
	 * SQL query for getting user-id of specified user from database
	 */
	private static final String SELECT_USER_ID = "SELECT userID FROM User WHERE userName == ?";

	/**
	 * Default constructor
	 * 
	 * @throws SneException
	 */
	public AbstractDAO() throws SneException {
		// Load JDBC Driver
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// log error
			logger.error("JDBC class not found \n" + e.getMessage(), e);
			throw new SneException("We could not connect to the database! ", e);
		}
	}

	/**
	 * Establishes connection to SQL database.
	 * 
	 * @return Connection to database
	 * @throws SQLException
	 * @throws SneException
	 */
	protected Connection getConnection(String dbName) throws SQLException, SneException {
		logger.debug("->");

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:db/" + dbName);
		} catch (SQLException up) {
			// log error
			logger.error("Was not able to open connection \n" + up.getMessage(), up);

			throw new SneException("Was not able to open database connection! ", up);
		}
		logger.debug("<-");
		return connection;
	}

	/**
	 * Closes the connection to the SQL database.
	 * 
	 * @param rs
	 * @param stm
	 * @param con
	 * @throws SQLException
	 */
	protected void close(ResultSet rs, PreparedStatement stm, Connection con) throws SQLException {
		logger.debug("->");

		try {
			if (rs != null)
				rs.close();
			if (stm != null)
				stm.close();
			if (con != null)
				con.close();
		} catch (SQLException up) {
			// log error
			logger.error("Was not able to close all resources \n" + up.getMessage(), up);

			throw up;
		}
		logger.debug("<-");

	}

	/**
	 * Returns the user ID for a given user object.
	 * 
	 * @param user
	 * @return Integer with user ID
	 * @throws SneException
	 */
	protected int getUserId(String dbName, User user) throws SneException {
		logger.debug("->");

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		int id = 0;

		// get userName id
		try {
			con = getConnection(dbName);
			stm = con.prepareStatement(SELECT_USER_ID);
			stm.setString(1, user.getUserName());

			// log query
			logger.debug(SELECT_USER_ID);

			rs = stm.executeQuery();
			id = rs.getInt("userID");
		} catch (SQLException e) {
			// log error
			logger.error("select on database failed \n" + e.getMessage(), e);
			throw new SneException("Could not retrieve user from database! ", e);
		} finally {
			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// log error
				logger.error("failed to close sql-connection \n" + e.getMessage(), e);
				throw new SneException("failed to close database connection! Your data might be in danger! ", e);
			}
		}
		logger.debug("<-");
		return id;
	}

}
