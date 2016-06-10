package ch.bfh.bti7081.s2016.white.sne.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.FinancialRecord;
import ch.bfh.bti7081.s2016.white.sne.data.PatientRecord;
import ch.bfh.bti7081.s2016.white.sne.data.PersonalRecord;
import ch.bfh.bti7081.s2016.white.sne.data.Report;

/**
 * Loads and saves the configuration of the user.
 * 
 * @author mcdizzu
 *
 */
public class ReportDaoImpl extends AbstractDAO implements ReportDao {

	private static final String DB_NAME = "dwh.db";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static final String SELECT_AVAILABLE_EMPLOYEE = "SELECT e.employeeFirstName, e.employeeSurName, t.treatmentDate FROM Treatment AS t INNER JOIN Employee AS e ON t.employeeID == e.employeeID WHERE t.treatmentDate >= ? AND t.treatmentDate <= ?";
	private static final String SELECT_SICK_LEAVES = "";
	private static final String SELECT_INCIDENTS = "";
	private static final String SELECT_PATIENT_COUNT = "";
	private static final String SELECT_FINANCE = "SELECT journalID, journalDate, effort, return, cashFlow FROM Journal WHERE journalDate >= ? AND journalDate <= ?";

	public ReportDaoImpl() {
		super();
	}

	@Override
	public Report<PersonalRecord> getAvailableEmployee(Date from, Date to) {

		Report<PersonalRecord> result = new Report<PersonalRecord>("Anwesendes Personal");
		List<PersonalRecord> records = new ArrayList<PersonalRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			// get data
			stm = con.prepareStatement(SELECT_AVAILABLE_EMPLOYEE);
			stm.setString(1, sdf.format(from));
			stm.setString(2, sdf.format(to));
			rs = stm.executeQuery();

			// parse results
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

		result.setRecords(records);
		return result;
	}

	@Override
	public Report<PersonalRecord> getSickLeaves(Date from, Date to) {
		Report<PersonalRecord> result = new Report<PersonalRecord>("Abwesendes Personal");
		List<PersonalRecord> records = new ArrayList<PersonalRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			// get data
			// TODO sql statement
			stm = con.prepareStatement(SELECT_SICK_LEAVES);
			// stm.setString(1, sdf.format(from));
			// stm.setString(2, sdf.format(to));
			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {

				// TODO
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

		result.setRecords(records);
		return result;
	}

	@Override
	public Report<PatientRecord> getIncidents(Date from, Date to) {
		Report<PatientRecord> result = new Report<PatientRecord>("Notfälle");
		List<PatientRecord> records = new ArrayList<PatientRecord>();

		/*
		 * String query =
		 * "SELECT i.incidentId, t.treatmentId, i.description, t.treatmentDate "
		 * +
		 * "FROM Incident AS i INNER JOIN Treatment AS t ON i.treatmentId=t.treatmentId "
		 * + "WHERE t.treatmentDate >= '" + sdf.format(from) +
		 * "' AND t.treatmentDate <= '" + sdf.format(to) + "';";
		 */
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			// get data
			// TODO sql statement
			stm = con.prepareStatement(SELECT_INCIDENTS);
			// stm.setString(1, sdf.format(from));
			// stm.setString(2, sdf.format(to));
			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {

				// TODO

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

		result.setRecords(records);
		return result;
	}

	@Override
	public Report<PatientRecord> getPatientCount(Date from, Date to) {
		Report<PatientRecord> result = new Report<PatientRecord>("Aktuelle Patientanzahl");
		List<PatientRecord> records = new ArrayList<PatientRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			// get data
			// TODO sql statement
			stm = con.prepareStatement(SELECT_PATIENT_COUNT);
			// stm.setString(1, sdf.format(from));
			// stm.setString(2, sdf.format(to));
			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {

				// TODO

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
		result.setRecords(records);
		return result;
	}

	/**
	 * reads data from database accounting into FinancialRecord, aggregates the
	 * Records in a Report, report will only contain information about effort
	 * 
	 * @return Report without type, but containing FinancialRecords
	 */
	@Override
	public Report<FinancialRecord> getEffort(Date from, Date to) {
		Report<FinancialRecord> result = new Report<FinancialRecord>("Aufwand");
		List<FinancialRecord> records = new ArrayList<FinancialRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			// get data
			stm = con.prepareStatement(SELECT_FINANCE);
			stm.setString(1, sdf.format(from));
			stm.setString(2, sdf.format(to));
			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {
				FinancialRecord record = new FinancialRecord();
				try {
					record.setEffort(Float.parseFloat(rs.getString("effort")));
				} catch (NullPointerException npe) {
					System.out.println("Recieved no input from database: " + npe.getMessage());
				} catch (NumberFormatException nfe) {
					System.out.println("Could not parse effort as float: " + nfe.getMessage());
				}
				record.setSummary(1);
				try {
					record.setDate(sdf.parse(rs.getString("journalDate")));
					records.add(record);
				} catch (ParseException pe) {
					System.out.println("Could not parse date: " + pe.getMessage());
				}
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

		result.setRecords(records);
		return result;
	}

	/**
	 * reads data from database accounting into FinancialRecord, aggregates the
	 * Records in a Report, report will only contain information about return
	 * 
	 * @return Report without type, but containing FinancialRecords
	 */
	@Override
	public Report<FinancialRecord> getReturn(Date from, Date to) {
		Report<FinancialRecord> result = new Report<FinancialRecord>("Ertrag");
		List<FinancialRecord> records = new ArrayList<FinancialRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			// get data
			stm = con.prepareStatement(SELECT_FINANCE);
			stm.setString(1, sdf.format(from));
			stm.setString(2, sdf.format(to));
			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {
				FinancialRecord record = new FinancialRecord();
				try {
					record.setEffort(Float.parseFloat(rs.getString("return")));
				} catch (NullPointerException npe) {
					System.out.println("Recieved no input from database: " + npe.getMessage());
				} catch (NumberFormatException nfe) {
					System.out.println("Could not parse return as float: " + nfe.getMessage());
				}
				record.setSummary(1);
				try {
					record.setDate(sdf.parse(rs.getString("journalDate")));
					records.add(record);
				} catch (ParseException pe) {
					System.out.println("Could not parse date: " + pe.getMessage());
				}
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
		result.setRecords(records);
		return result;
	}

	/**
	 * reads data from database accounting into FinancialRecord, aggregates the
	 * Records in a Report, report will only contain information about cash-flow
	 * 
	 * @return Report without type, but containing FinancialRecords
	 */
	@Override
	public Report<FinancialRecord> getCashFlow(Date from, Date to) {
		Report<FinancialRecord> result = new Report<FinancialRecord>("Cash Flow");
		List<FinancialRecord> records = new ArrayList<FinancialRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			// get data
			stm = con.prepareStatement(SELECT_FINANCE);
			stm.setString(1, sdf.format(from));
			stm.setString(2, sdf.format(to));
			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {
				FinancialRecord record = new FinancialRecord();
				try {
					record.setEffort(Float.parseFloat(rs.getString("cashFlow")));
				} catch (NullPointerException npe) {
					System.out.println("Recieved no input from database: " + npe.getMessage());
				} catch (NumberFormatException nfe) {
					System.out.println("Could not parse cash-flow as float: " + nfe.getMessage());
				}
				record.setSummary(1);
				try {
					record.setDate(sdf.parse(rs.getString("journalDate")));
					record.setCashFlow(rs.getFloat("cashFlow"));
					records.add(record);
				} catch (ParseException pe) {
					System.out.println("Could not parse date: " + pe.getMessage());
				}
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

		result.setRecords(records);
		return result;
	}

	@Override
	public String getDbName() {
		return this.DB_NAME;
	}

}