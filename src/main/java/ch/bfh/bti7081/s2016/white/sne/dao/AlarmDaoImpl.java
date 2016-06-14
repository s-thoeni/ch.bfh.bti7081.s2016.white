package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.enums.Operator;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Class implementing database access for alarm configuration
 * 
 * @author team white
 *
 */
public class AlarmDaoImpl extends AbstractDAO implements AlarmDao {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(AlarmDaoImpl.class);

	/**
	 * database name as constant
	 */
	private static final String DB_NAME = "conf.db";
	
	/**
	 * SQL query for getting alarms of specified user from database
	 */
	private static final String SELECT_ALARMS = "SELECT a.reportType, a.alarmTimeFrame, a.comperator, a.errorValue, a.warnValue FROM Alarm AS a INNER JOIN User AS u ON a.userID = u.userID WHERE u.userName == ?";
	
	/**
	 * SQL query for deleting alarms of specified user from database
	 */
	private static final String DELETE_ALARMS = "DELETE FROM Alarm WHERE userID == ?";
	
	/**
	 * SQL query for storing alarms of specified user in database
	 */
	private static final String INSERT_ALARMS = "INSERT INTO Alarm ( reportType, alarmTimeFrame, comperator, errorValue, warnValue, userId) VALUES (?,?,?,?,?,?)";

	/**
	 * Default constructor
	 * 
	 * @throws SneException
	 */
	public AlarmDaoImpl() throws SneException {
		super();
	}

	public List<Alarm> getAlarms(User user) throws SneException {
		logger.debug("->");

		List<Alarm> alarms = new ArrayList<Alarm>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection(DB_NAME);
			// get alarms
			stm = con.prepareStatement(SELECT_ALARMS);
			stm.setString(1, user.getUserName());

			// log query
			logger.debug(SELECT_ALARMS);

			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {
				ReportType rt = ReportType.valueOf(rs.getString("reportType"));
				ReportTimeframe rtf = ReportTimeframe.valueOf(rs.getString("alarmTimeFrame"));
				Operator op = Operator.fromFriendlyName(rs.getString("comperator"));

				int error = rs.getInt("errorValue");
				int warn = rs.getInt("warnValue");
				// System.out.println("Result: " + op + " , " + error + ", " +
				// warn);
				ReportConfig rc = new ReportConfig(rt, rtf);
				Alarm al = new Alarm(rc, error, warn, op);
				alarms.add(al);
			}

		} catch (SQLException e) {
			// log error
			logger.error("select on database " + DB_NAME + " failed \n" + e.getMessage(), e);
			throw new SneException("could not retrieve alarms from database! ", e);
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
		return alarms;
	}
	
	/**
	 * Delete all set alarms of given user from database
	 * 
	 * @param userId as int
	 * @throws SneException
	 */
	private void deleteAlarms(int userId) throws SneException {
		logger.debug("->");

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		// delete old alarms
		try {
			con = getConnection(DB_NAME);
			stm = con.prepareStatement(DELETE_ALARMS);
			stm.setInt(1, userId);

			// log query
			logger.debug(DELETE_ALARMS);

			stm.execute();
		} catch (SQLException e) {
			// log error
			logger.error("delete on database " + DB_NAME + " failed \n" + e.getMessage(), e);
			throw new SneException("Could not delete alarms! ", e);
		} finally {
			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// log error
				logger.error("failed to close sql-connection", e);
				throw new SneException("Failed to close database connection! Your data might be in danger!", e);
			}
		}
		logger.debug("<-");
	}

	@Override
	public void storeAlarms(List<Alarm> alarms, User user) throws SneException {
		logger.debug("->");

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		int id = getUserId(DB_NAME, user);

		// delete old alarms
		deleteAlarms(id);

		// insert alarms
		for (Alarm alarm : alarms) {
			try {
				con = getConnection(DB_NAME);
				stm = con.prepareStatement(INSERT_ALARMS);
				stm.setString(1, alarm.getAlarmReportConfig().getReportType().toString());
				stm.setString(2, alarm.getAlarmReportConfig().getReportTimeframe().toString());
				stm.setString(3, alarm.getOperator().toString());
				stm.setInt(4, alarm.getErrorValue());
				stm.setInt(5, alarm.getWarningValue());
				stm.setInt(6, id);

				// log query
				logger.debug(INSERT_ALARMS);

				stm.execute();
			} catch (SQLException e) {
				// log error
				logger.error("insert on database " + DB_NAME + " failed \n" + e.getMessage(), e);
				throw new SneException("Was not able to store alarms! ", e);
			} finally {
				try {
					close(rs, stm, con);
				} catch (SQLException e) {
					// log error
					logger.error("failed to close sql-connection", e);
					throw new SneException("Failed to close database connection! Your data might be in danger!", e);
				}
			}
		}
		logger.debug("<-");
	}
}