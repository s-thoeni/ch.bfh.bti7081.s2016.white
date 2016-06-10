package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.enums.Operator;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class AlarmDaoImpl extends AbstractDAO implements AlarmDao {

	private final String DB_NAME = "conf.db";
	private static final String SELECT_ALARMS = "SELECT a.reportType, a.alarmTimeFrame, a.comperator, a.errorValue, a.warnValue FROM Alarm AS a INNER JOIN User AS u ON a.userID = u.userID WHERE u.userName == ?";
	private static final String DELETE_ALARMS = "DELETE FROM Alarm WHERE userID == ?";
	private static final String INSERT_ALARMS = "INSERT INTO Alarm ( reportType, alarmTimeFrame, comperator, errorValue, warnValue, userId) VALUES (?,?,?,?,?,?)";

	public AlarmDaoImpl() {
		super();
	}

	public List<Alarm> getAlarms(User user) {
		List<Alarm> alarms = new ArrayList<Alarm>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			// get alarms
			stm = con.prepareStatement(SELECT_ALARMS);
			stm.setString(1, user.getUserName());
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
			System.out.println("Could not execute query " + e.getMessage());
		} finally {

			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// TODO
				e.printStackTrace();
			}
		}
		return alarms;
	}
	
	private void deleteAlarms(int userId) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		// delete old alarms
		try {
			con = getConnection();
			stm = con.prepareStatement(DELETE_ALARMS);
			stm.setInt(1, userId);
			stm.execute();
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
	}
	 

	@Override
	public void storeAlarms(List<Alarm> alarms, User user) {

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		int id = getUserId(user);

		// delete old alarms
		deleteAlarms(id);

		// insert alarms
		for (Alarm alarm : alarms) {
			try {
				con = getConnection();
				stm = con.prepareStatement(INSERT_ALARMS);
				stm.setString(1, alarm.getAlarmReportConfig().getReportType().toString());
				stm.setString(2, alarm.getAlarmReportConfig().getReportTimeframe().toString());
				stm.setString(3, alarm.getOperator().toString());
				stm.setInt(4, alarm.getErrorValue());
				stm.setInt(5, alarm.getWarningValue());
				stm.setInt(6, id);

				stm.execute();
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
		}
	}

	@Override
	public String getDbName() {
		return this.DB_NAME;
	}
}