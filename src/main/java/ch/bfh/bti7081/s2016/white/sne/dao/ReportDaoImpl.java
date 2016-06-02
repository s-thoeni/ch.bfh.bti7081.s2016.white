package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ch.bfh.bti7081.s2016.white.sne.data.PatientRecord;
import ch.bfh.bti7081.s2016.white.sne.data.PersonalRecord;
import ch.bfh.bti7081.s2016.white.sne.data.Record;
import ch.bfh.bti7081.s2016.white.sne.data.Report;

/**
 * Loads and saves the configuration of the user.
 * 
 * @author mcdizzu
 *
 */
public class ReportDaoImpl implements ReportDao {

	private Connection connection;
	private Statement stm;

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public ReportDaoImpl() {
		// Load JDBC Driver
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC class not found " + e.getMessage());
		}
	}

	@Override
	public Report getAvailableEmployee(Date from, Date to) {
		Report result = new Report("Anwesendes Personal");

		List<Record> records = new ArrayList<Record>();
		try {
			this.connection = DriverManager.getConnection("jdbc:sqlite:db/care.db");
		} catch (SQLException e) {
			System.out.println("Could not set connection to care.db " + e.getMessage());
		}
		try {
			Statement stm = connection.createStatement();
			String query = "SELECT e.employeeFirstName, e.employeeSurName, t.treatmentDate " + "FROM Treatment AS t INNER JOIN Employee AS e ON t.employeeID == e.employeeID "
					+ "WHERE t.treatmentDate >= '" + sdf.format(from) + "' AND t.treatmentDate <= '" + sdf.format(to) + "';";
			System.out.println(query);
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				PersonalRecord record = new PersonalRecord();
				record.setAvailable(true);
				record.setSummary(1);
				record.setPersonName(rs.getString("employeeSurName") + " " + rs.getString("employeeFirstName"));

				try {
					record.setDate(sdf.parse(rs.getString("treatmentDate")));
					records.add(record);
				} catch (ParseException pe) {
					System.out.println("Could not parse date: " + pe.getMessage());
				}
			}
			result.setRecords(records);
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
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
		return result;
	}

	@Override
	public Report getSickLeaves(Date from, Date to) {
		Report result = new Report("Abwesendes Personal");

		List<Record> records = new ArrayList<Record>();
		try {
			this.connection = DriverManager.getConnection("jdbc:sqlite:db/care.db");
		} catch (SQLException e) {
			System.out.println("Could not set connection to care.db " + e.getMessage());
		}
		try {
			int i = 0;
			Statement stm = connection.createStatement();
			
			// TODO: Implement query to show abwesendes personal
			String query ="";
			//String query = "SELECT e.employeeFirstName, e.employeeSurName, t.treatmentDate "
			//		+ "FROM Treatment AS t INNER JOIN Employee AS e ON t.employeeID == e.employeeID"
			//		+ "WHERE t.treatmentDate >= '" + sdf.format(from) + "' AND t.treatmentDate <= '" + sdf.format(to) + "';";
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				PersonalRecord record = new PersonalRecord();
				record.setAvailable(true);
				record.setSummary(1);
				record.setPersonName(rs.getString("employeeSurName") + " " + rs.getString("employeeFirstName"));
				
				try {
					record.setDate(sdf.parse(rs.getString("treatmentDate")));
					records.add(record);
				} catch (ParseException pe) {
					System.out.println("Could not parse date: " + pe.getMessage());
				}
			}
			result.setRecords(records);
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
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
		return result;
	}

	@Override
	public Report getIncidents(Date from, Date to) {
		Report result = new Report("Notfälle");
		List<Record> records = new ArrayList<Record>();
		try {
			this.connection = DriverManager.getConnection("jdbc:sqlite:db/care.db");
		} catch (SQLException e) {
			System.out.println("Could not set connection to care.db " + e.getMessage());
		}
		try {
			int i = 0;
			Statement stm = connection.createStatement();
			// FIXME: typo in db: desciription instead of description
			String query = "SELECT i.incidentId, t.treatmentId, i.desciription, t.treatmentDate " + "FROM Incident AS i INNER JOIN Treatment AS t ON i.treatmentId=t.treatmentId "
					+ "WHERE t.treatmentDate >= '" + sdf.format(from) + "' AND t.treatmentDate <= '" + sdf.format(to) + "';";
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				PatientRecord record = new PatientRecord();
				record.setIncident("Incident " + ++i + ": " + rs.getString("desciription"));
				record.setSummary(1);
				try {
					record.setDate(sdf.parse(rs.getString("treatmentDate")));
					records.add(record);
				} catch (ParseException pe) {
					System.out.println("Could not parse date: " + pe.getMessage());
				}
			}
			result.setRecords(records);
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
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
		return result;
	}

	@Override
	public Report getEntriesExits(Date from, Date to) {
		Report result = new Report("Aktuelle Patientanzahl");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(to);
		int endDate = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(from);
		int startDate = calendar.get(Calendar.DAY_OF_YEAR);

		List<Record> records = new ArrayList<Record>();
		Random rand = new Random();

		for (int i = 0; i < 1000; ++i) {
			calendar.setTime(from);
			calendar.add(Calendar.DAY_OF_YEAR, rand.nextInt(endDate - startDate + 1));
			PatientRecord record = new PatientRecord();
			record.setIncident("Incident" + i);
			record.setDate(calendar.getTime());
			records.add(record);
		}
		result.setRecords(records);
		return result;
	}

	@Override
	public Report getPatientCount(Date from, Date to) {
		Report result = new Report("CashFlow");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(to);
		int endDate = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(from);
		int startDate = calendar.get(Calendar.DAY_OF_YEAR);

		List<Record> records = new ArrayList<Record>();
		Random rand = new Random();

		for (int i = 0; i < 1000; ++i) {
			calendar.setTime(from);
			calendar.add(Calendar.DAY_OF_YEAR, rand.nextInt(endDate - startDate + 1));
			PatientRecord record = new PatientRecord();
			record.setIncident("Incident" + i);
			record.setDate(calendar.getTime());
			records.add(record);
		}
		result.setRecords(records);
		return result;
	}

	@Override
	public Report getEffort(Date from, Date to) {
		Report result = new Report("Aufwände");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(to);
		int endDate = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(from);
		int startDate = calendar.get(Calendar.DAY_OF_YEAR);

		List<Record> records = new ArrayList<Record>();
		Random rand = new Random();

		for (int i = 0; i < 1000; ++i) {
			calendar.setTime(from);
			calendar.add(Calendar.DAY_OF_YEAR, rand.nextInt(endDate - startDate + 1));
			PatientRecord record = new PatientRecord();
			record.setIncident("Incident" + i);
			record.setDate(calendar.getTime());
			records.add(record);
		}
		result.setRecords(records);
		return result;
	}

	@Override
	public Report getCashFlow(Date from, Date to) {
		Report result = new Report("Suizide");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(to);
		int endDate = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(from);
		int startDate = calendar.get(Calendar.DAY_OF_YEAR);

		List<Record> records = new ArrayList<Record>();
		Random rand = new Random();

		for (int i = 0; i < 1000; ++i) {
			calendar.setTime(from);
			calendar.add(Calendar.DAY_OF_YEAR, rand.nextInt(endDate - startDate + 1));
			PatientRecord record = new PatientRecord();
			record.setIncident("Incident" + i);
			record.setDate(calendar.getTime());
			records.add(record);
		}
		result.setRecords(records);
		return result;
	}

}