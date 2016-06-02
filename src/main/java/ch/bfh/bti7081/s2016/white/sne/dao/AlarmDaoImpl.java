package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.enums.Operator;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class AlarmDaoImpl implements AlarmDao {

	private Connection connection;
	private Statement stm;

	public AlarmDaoImpl() {
		// Load JDBC Driver
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC class not found " + e.getMessage());
		}
	}

	public List<Alarm> getAlarms(User user) {
		List<Alarm> alarms = new ArrayList<Alarm>();

		/*
		 * alarmID INTEGER PRIMARY KEY AUTOINCREMENT, reportType NVARCHAR(128)
		 * NOT NULL, alarmTimeFrame NVARCHAR(128) NOT NULL, comperator
		 * NVARCHAR(128) NOT NULL, errorValue INTEGER NOT NULL, warnValue
		 * INTEGER NOT NULL, userID INTEGER NOT NULL,
		 */
		try {
			try {
				this.connection = DriverManager.getConnection("jdbc:sqlite:db/conf.db");
			} catch (SQLException e) {
				System.out.println("Could not set connection to conf.db " + e.getMessage());
			}
			stm = connection.createStatement();
			String query = " SELECT a.reportType, a.alarmTimeFrame, a.comperator, a.errorValue, a.warnValue FROM Alarm AS a INNER JOIN User AS u ON a.userID = u.userID WHERE u.userName == '"
					+ user.getUserName() + "';";

			System.out.println(query);

			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				ReportType rt = ReportType.valueOf(rs.getString("reportType"));
				ReportTimeframe rtf = ReportTimeframe.valueOf(rs.getString("alarmTimeFrame"));
				Operator op = Operator.fromFriendlyName(rs.getString("comperator"));
				
				int error = rs.getInt("errorValue");
				int warn = rs.getInt("warnValue");
				System.out.println("Result: " + op + " , " + error + ", " + warn);
				ReportConfig rc = new ReportConfig(rt, rtf);
				Alarm al = new Alarm(rc, error, warn, op);
				alarms.add(al);
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

		return alarms;
	}

	@Override
	public void storeAlarms(List<Alarm> alarms, User user) {
		try {
			try {
				this.connection = DriverManager.getConnection("jdbc:sqlite:db/conf.db");
			} catch (SQLException e) {
				System.out.println("Could not set connection to conf.db " + e.getMessage());
			}
			stm = connection.createStatement();

			// get userName id
			ResultSet rs = stm.executeQuery(" SELECT userID FROM User WHERE userName == '" + user.getUserName() + "';");
			int id = rs.getInt("userID");

			// delete old ones
			stm.execute(" DELETE FROM Alarm WHERE userID == " + id + ";");

			for (Alarm alarm : alarms) {
				String query;
				StringBuffer sb = new StringBuffer();
				sb.append("INSERT INTO Alarm");
				sb.append("( reportType, alarmTimeFrame, comperator, errorValue, warnValue, userId) VALUES");
				sb.append("(\"");
				sb.append(alarm.getAlarmReportConfig().getReportType());
				sb.append("\",\"");
				sb.append(alarm.getAlarmReportConfig().getReportTimeframe());
				sb.append("\",\"");
				sb.append(alarm.getOperator());
				sb.append("\",");
				sb.append(alarm.getErrorValue());
				sb.append(",");
				sb.append(alarm.getWarningValue());
				sb.append(",");
				sb.append(id);
				sb.append(");");

				query = sb.toString();

				System.out.println(query);
				stm.execute(query);
			}

		} catch (SQLException e) {
			System.out.println("Could not execute setConfig query " + e.getMessage());
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
	}
}