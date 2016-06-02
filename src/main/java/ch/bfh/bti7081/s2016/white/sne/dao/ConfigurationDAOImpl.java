package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

/**
 * Loads and saves the configuration of the user.
 * 
 * @author mcdizzu
 *
 */
public class ConfigurationDAOImpl implements ConfigurationDAO {

	private Connection connection;
	private Statement stm;

	public ConfigurationDAOImpl() {
		// Load JDBC Driver
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC class not found " + e.getMessage());
		}
	}

	@Override
	public Configuration getConfig() {
		return new Configuration();
	}

	@Override
	public Configuration getConfig(User user) {
		Configuration config = new Configuration();
		List<ReportConfig> reports = new ArrayList<ReportConfig>();

		try {
			try {
				this.connection = DriverManager.getConnection("jdbc:sqlite:db/conf.db");
			} catch (SQLException e) {
				System.out.println("Could not set connection to conf.db " + e.getMessage());
			}
			stm = connection.createStatement();
			String query = " SELECT c.tileNumber, c.reportType, c.reportTimeFrame FROM Configuration AS c INNER JOIN User AS u ON c.userID = u.userID WHERE u.userName == '"
					+ user.getUserName() + "';";

			// System.out.println(query);

			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				ReportType rt = ReportType.valueOf(rs.getString("reportType"));
				ReportTimeframe rtf = ReportTimeframe.valueOf(rs.getString("reportTimeFrame"));

				reports.add(new ReportConfig(rt, rtf));
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

		config.setReports(reports);

		return config;
	}

	@Override
	public void setConfig(List<ReportConfig> reports, User user) {		
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
			stm.execute(" DELETE FROM Configuration WHERE userID == " + id + ";");

			for (ReportConfig rc : reports) {
				String query;
				StringBuffer sb = new StringBuffer();
				sb.append("INSERT INTO CONFIGURATION");
				sb.append("( reportType, reportTimeFrame, userId) VALUES");
				sb.append("(\"");
				sb.append(rc.getReportType().toString());
				sb.append("\",\"");
				sb.append(rc.getReportTimeframe().toString());
				sb.append("\",");
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
