package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Loads and saves the configuration of the user in database.
 * 
 * @author mcdizzu
 * 
 */
public class ConfigurationDAOImpl extends AbstractDAO implements ConfigurationDAO {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ConfigurationDAOImpl.class);
	
	/**
	 * database name as constant
	 */
	private String DB_NAME = "conf.db";
	
	/**
	 * SQL query for getting configuration of specified user from database
	 */
	private static final String SELECT_CONFIG = "SELECT c.tileNumber, c.reportType, c.reportTimeFrame FROM Configuration AS c INNER JOIN User AS u ON c.userID = u.userID WHERE u.userName == ?";
	
	/**
	 * SQL query for deleting configuration of specified user from database
	 */
	private static final String DELETE_CONFIG = "DELETE FROM Configuration WHERE userID == ?";
	
	/**
	 * SQL query for storing configuration of specified user from database
	 */
	private static final String INSERT_CONFIG = "INSERT INTO CONFIGURATION ( reportType, reportTimeFrame, userId) VALUES (?, ?, ?)";
	
	/**
	 * Default constructor
	 * 
	 * @throws SneException
	 */
	public ConfigurationDAOImpl() throws SneException {
		super();
	}

	@Override
	public Configuration getConfig() {
		logger.debug("->");
		logger.debug("<-");
		return new Configuration();
	}

	@Override
	public Configuration getConfig(User user) throws SneException {
		logger.debug("->");
		Configuration config = new Configuration();
		List<ReportConfig> reports = new ArrayList<ReportConfig>();
		
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection(DB_NAME);
			
			// get config
			stm = con.prepareStatement(SELECT_CONFIG);
			stm.setString(1, user.getUserName());
			
			// log query
			logger.debug(SELECT_CONFIG);
			rs = stm.executeQuery();
			
			// parse results
			while (rs.next()) {
				ReportType rt = ReportType.valueOf(rs.getString("reportType"));
				ReportTimeframe rtf = ReportTimeframe.valueOf(rs.getString("reportTimeFrame"));

				reports.add(new ReportConfig(rt, rtf));
			}
		} catch (SQLException e) {
			// log error
			logger.error("select on database " + DB_NAME + " failed \n" + e.getMessage(), e);
			throw new SneException("Was not able to retrieve user config", e);
		} finally {

			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// log error
				logger.error("failed to close sql-connection \n" + e.getMessage(), e);
				throw new SneException("Failed to close database connection! Your data might be in danger!", e);
			}
		}

		config.setReports(reports);
		
		logger.debug("<-");
		return config;
	}
	
	/**
	 * Deletes configuration of specified user from database.
	 * 
	 * @param userId as int
	 * @throws SneException
	 */
	private void deleteConfig(int userId) throws SneException {
		logger.debug("->");
		
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		// delete old alarms
		try {
			con = getConnection(DB_NAME);
			stm = con.prepareStatement(DELETE_CONFIG);
			stm.setInt(1, userId);
			
			// log query
			logger.debug(DELETE_CONFIG);
			
			stm.execute();
		} catch (SQLException e) {
			// log error
			logger.error("delete on database " + DB_NAME + " failed \n" + e.getMessage(), e);
			throw new SneException("failed to delete current config", e);
		} finally {
			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// log error
				logger.error("failed to close sql-connection \n" + e.getMessage(), e);
				throw new SneException("Failed to close database connection! Your data might be in danger!", e);
			}
		}
		logger.debug("<-");
	}

	@Override
	public void setConfig(List<ReportConfig> reports, User user) throws SneException {		
		logger.debug("->");
		
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		int id = getUserId(DB_NAME, user);

		// delete old config
		deleteConfig(id);

		// insert config
		
		for (ReportConfig rc : reports) {
			try {
				con = getConnection(DB_NAME);
				stm = con.prepareStatement(INSERT_CONFIG);
				
				stm.setString(1, rc.getReportType().toString());
				stm.setString(2, rc.getReportTimeframe().toString());
				stm.setInt(3, id);

				// log query
				logger.debug(INSERT_CONFIG);
				
				stm.execute();
			} catch (SQLException e) {
				// log error
				logger.error("insert on database " + DB_NAME + " failed \n" + e.getMessage(), e);
				throw new SneException("Failed to write user config", e);
			} finally {
				try {
					close(rs, stm, con);
				} catch (SQLException e) {
					// log error
					logger.error("failed to close sql-connection \n" + e.getMessage(), e);
				}
			}
		}
		logger.debug("<-");
	}
}
