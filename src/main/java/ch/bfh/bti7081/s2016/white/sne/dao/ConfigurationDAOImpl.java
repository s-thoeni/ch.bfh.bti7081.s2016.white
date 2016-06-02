package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.PatientRecord;
import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

/**
 * Loads and saves the configuration of the user. 
 * @author mcdizzu
 *
 */
public class ConfigurationDAOImpl implements ConfigurationDAO {

	private Connection connection;

	public ConfigurationDAOImpl() {
		// Load JDBC Driver
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC class not found " + e.getMessage());
		}

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:db/conf.db");
		} catch (SQLException e) {
			System.out.println("Could not set connection to conf.db " + e.getMessage());
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
			Statement stm = connection.createStatement();

			String query = " SELECT c.tileNumber, c.reportType, c.reportTimeFrame FROM Configuration AS c INNER JOIN User AS u ON c.userID = u.userID WHERE u.userName == '" + user.getUserName()
					+ "';";

			//System.out.println(query);

			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				ReportType rt = ReportType.valueOf(rs.getString("reportType"));
				ReportTimeframe rtf = ReportTimeframe.valueOf(rs.getString("reportTimeFrame"));

				reports.add(new ReportConfig(rs.getInt("tileNumber"), rt, rtf));
			}
		} catch (SQLException e) {
			System.out.println("Could not execute getConfig query " + e.getMessage());
		}

		config.setReports(reports);

		return config;
	}

	@Override
	public void setConfig(List<ReportConfig> reports, User user) {

		// do nothing if there are no report config
		if (reports.size() > 0) {
			try {
				Statement stm = connection.createStatement();

				// get userName id
				ResultSet rs = stm.executeQuery(" SELECT userID FROM User WHERE userName == '" + user.getUserName() + "';");
				int id = rs.getInt("userID");

				// insert report config
				String query = " INSERT INTO Configuration (tileNumber, reportType, reportTimeFrame, userID) VALUES" + "";

				for (ReportConfig rc : reports) {
					query += "(" + rc.getId() + ", '" + rc.getReportType().toString() + "', '" + rc.getReportTimeframe().toString() + "', " + id + "),";
				}

				query = query.substring(0, query.length() - 1) + ";";

				//System.out.println(query);

				rs = stm.executeQuery(query);
			} catch (SQLException e) {
				System.out.println("Could not execute setConfig query " + e.getMessage());
			}
		}
	}

}
