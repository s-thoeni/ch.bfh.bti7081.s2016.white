package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
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

public class ReportDaoImpl implements ReportDao {

	@Override
	public Report getAvailableEmployee(Date from, Date to) {
		Report result = new Report("Anwesendes Personal");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(to);
		int endDate = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(from);
		int startDate = calendar.get(Calendar.DAY_OF_YEAR);

		List<Record> records = new ArrayList<Record>();
		Random rand = new Random();

		for (int i = 0; i < 1000; ++i) {
			calendar.setTime(from);
			if ((endDate - startDate) > 0) {
				calendar.add(Calendar.DAY_OF_YEAR, rand.nextInt(endDate - startDate));
			}
			PersonalRecord record = new PersonalRecord();
			record.setPersonName("John Doe");
			record.setAvailable(rand.nextBoolean());
			if (!record.isAvailable()) {
				record.setUnavailableReason("Dead");
			}
			record.setDate(calendar.getTime());
			records.add(record);
		}
		result.setRecords(records);
		return result;
	}

	@Override
	public Report getSickLeaves(Date from, Date to) {
		Report result = new Report("Abwesendes Personal");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(to);
		int endDate = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(from);
		int startDate = calendar.get(Calendar.DAY_OF_YEAR);

		List<Record> records = new ArrayList<Record>();
		Random rand = new Random();

		// TODO(jan): mock this stuff for real
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
	public Report getIncidents(Date from, Date to) {
		Report result = new Report("Notfälle");

		List<Record> records = new ArrayList<Record>();

		/*
		 * Calendar calendar = Calendar.getInstance(); calendar.setTime(to); int
		 * endDate = calendar.get(Calendar.DAY_OF_YEAR); calendar.setTime(from);
		 * int startDate = calendar.get(Calendar.DAY_OF_YEAR); Random rand = new
		 * Random();
		 * 
		 * for (int i = 0; i < 1000; ++i) { calendar.setTime(from); if ((endDate
		 * - startDate) > 0) { calendar.add(Calendar.DAY_OF_YEAR,
		 * rand.nextInt(endDate - startDate)); } PatientRecord record = new
		 * PatientRecord(); record.setIncident("Incident" + i);
		 * record.setDate(calendar.getTime()); records.add(record); }
		 */

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:example.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// statement.executeUpdate("drop table if exists person");
			// statement.executeUpdate("create table person (id integer, name
			// string)");
			// statement.executeUpdate("insert into person values(1, 'leo')");
			// statement.executeUpdate("insert into person values(2, 'yui')");
			ResultSet rs = statement.executeQuery("select * from incident");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			while (rs.next()) {
				try {
				    Date date = df.parse(rs.getString("one"));
				    PatientRecord record = new PatientRecord();
					record.setIncident("Incident" + rs.getInt("two"));
					record.setDate(date);
					records.add(record);
					
					// read the result set
					System.out.println("DATE " + record.getDate());
					System.out.println("VALUE " + record.getIncident());
				} catch (Exception e) {
				   e.printStackTrace();
				}				
			}
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}

		}
		result.setRecords(records);
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