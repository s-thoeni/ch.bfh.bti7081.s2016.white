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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ReportDaoImpl.class);
	
	private static final String DB_NAME = "dwh.db";
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static final String SELECT_AVAILABLE_EMPLOYEES = "SELECT e.employeeFirstName, e.employeeSurName, t.treatmentDate FROM Treatment AS t INNER JOIN Employee AS e ON t.employeeID == e.employeeID WHERE t.treatmentDate >= ? AND t.treatmentDate <= ?";
	private static final String SELECT_SICK_LEAVES = "";
	private static final String SELECT_INCIDENTS = "SELECT i.incidentId, t.treatmentId, i.description, t.treatmentDate FROM Incident AS i INNER JOIN Treatment AS t ON i.treatmentId=t.treatmentId WHERE t.treatmentDate >= ? AND t.treatmentDate <= ?";
	private static final String SELECT_PATIENT_COUNT = "";
	private static final String SELECT_FINANCE = "SELECT journalID, journalDate, effort, return, cashFlow FROM Journal WHERE journalDate >= ? AND journalDate <= ?";

	public ReportDaoImpl() {
		super();
	}

	@Override
	public Report<PersonalRecord> getAvailableEmployee(Date from, Date to) {
		logger.debug("->");
		
		Report<PersonalRecord> result = new Report<PersonalRecord>("Anwesendes Personal");
		List<PersonalRecord> records = new ArrayList<PersonalRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			// get data
			stm = con.prepareStatement(SELECT_AVAILABLE_EMPLOYEES);
			stm.setString(1, sdf.format(from));
			stm.setString(2, sdf.format(to));
			
			// log query
			logger.debug(SELECT_AVAILABLE_EMPLOYEES);
			
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
					// log error
					logger.error("error while parsing date \n" + pe.getMessage(), pe);
				}
			}

		} catch (SQLException e) {
			// log error
			logger.error("select on database " + DB_NAME + " failed \n" + e.getMessage(), e);
		} finally {
			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// log error
				logger.error("failed to close sql-connection \n" + e.getMessage(), e);
			}
		}

		result.setRecords(records);
		logger.debug("<-");
		return result;
	}

	@Override
	public Report<PersonalRecord> getSickLeaves(Date from, Date to) {
		logger.debug("->");
		
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
			
			// log query
			logger.debug(SELECT_SICK_LEAVES);
			
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
					// log error
					logger.error("error while parsing date \n" + pe.getMessage(), pe);
				}
			}

		} catch (SQLException e) {
			// log error
			logger.error("select on database " + DB_NAME + " failed \n" + e.getMessage(), e);
		} finally {
			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// log error
				logger.error("failed to close sql-connection \n" + e.getMessage(), e);
			}
		}

		result.setRecords(records);
		logger.debug("<-");
		return result;
	}

	@Override
	public Report<PatientRecord> getIncidents(Date from, Date to) {
		logger.debug("->");
		
		Report<PatientRecord> result = new Report<PatientRecord>("Notfälle");
		List<PatientRecord> records = new ArrayList<PatientRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			// get data
			stm = con.prepareStatement(SELECT_INCIDENTS);
			stm.setString(1, sdf.format(from));
			stm.setString(2, sdf.format(to));
			
			// log query
			logger.debug(SELECT_INCIDENTS);
			
			rs = stm.executeQuery();
			
			// parse results
			while (rs.next()) {
				PatientRecord record = new PatientRecord();
				record.setIncident(rs.getString("description"));
				record.setSummary(1);
				try {
					record.setDate(sdf.parse(rs.getString("treatmentDate")));
					records.add(record);
				} catch (ParseException pe) {
					System.out.println("Could not parse date: " + pe.getMessage());
				}
			}

		} catch (SQLException e) {
			// log error
			logger.error("select on database " + DB_NAME + " failed \n" + e.getMessage(), e);
		} finally {
			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// log error
				logger.error("failed to close sql-connection \n" + e.getMessage(), e);
			}
		}

		result.setRecords(records);
		logger.debug("<-");
		return result;
	}

	@Override
	public Report<PatientRecord> getPatientCount(Date from, Date to) {
		logger.debug("->");
		
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
			
			// log query
			logger.debug(SELECT_PATIENT_COUNT);
			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {

				// TODO

			}

		} catch (SQLException e) {
			// log error
			logger.error("select on database " + DB_NAME + " failed \n" + e.getMessage(), e);
		} finally {
			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// log error
				logger.error("failed to close sql-connection \n" + e.getMessage(), e);
			}
		}
		result.setRecords(records);
		logger.debug("<-");
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
		logger.debug("->");
		
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
			
			// log query
			logger.debug(SELECT_FINANCE);
			
			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {
				FinancialRecord record = new FinancialRecord();
				try {
					record.setEffort(Float.parseFloat(rs.getString("effort")));
				} catch (NullPointerException npe) {
					// log error
					logger.error("Recieved no data from database \n" + npe.getMessage(), npe);
				} catch (NumberFormatException nfe) {
					// log error
					logger.error("Failed to parse effort as float \n" + nfe.getMessage(), nfe);
				}
				record.setSummary(1);
				try {
					record.setDate(sdf.parse(rs.getString("journalDate")));
					records.add(record);
				} catch (ParseException pe) {
					// log error
					logger.error("error while parsing date \n" + pe.getMessage(), pe);
				}
			}

		} catch (SQLException e) {
			// log error
			logger.error("select on database " + DB_NAME + " failed \n" + e.getMessage(), e);
		} finally {
			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// log error
				logger.error("failed to close sql-connection \n" + e.getMessage(), e);
			}
		}

		result.setRecords(records);
		logger.debug("<-");
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
		logger.debug("->");
		
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
			
			// log query
			logger.debug(SELECT_FINANCE);
			
			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {
				FinancialRecord record = new FinancialRecord();
				try {
					record.setEffort(Float.parseFloat(rs.getString("return")));
				} catch (NullPointerException npe) {
					// log error
					logger.error("Recieved no data from database \n" + npe.getMessage(), npe);
				} catch (NumberFormatException nfe) {
					// log error
					logger.error("Failed to parse return as float \n" + nfe.getMessage(), nfe);
				}
				record.setSummary(1);
				try {
					record.setDate(sdf.parse(rs.getString("journalDate")));
					records.add(record);
				} catch (ParseException pe) {
					// log error
					logger.error("error while parsing date \n" + pe.getMessage(), pe);
				}
			}

		} catch (SQLException e) {
			// log error
			logger.error("select on database " + DB_NAME + " failed \n" + e.getMessage(), e);
		} finally {
			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// log error
				logger.error("failed to close sql-connection \n" + e.getMessage(), e);
			}
		}
		result.setRecords(records);
		logger.debug("<-");
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
		logger.debug("->");
		
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
			
			// log query
			logger.debug(SELECT_FINANCE);
						
			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {
				FinancialRecord record = new FinancialRecord();
				try {
					record.setEffort(Float.parseFloat(rs.getString("cashFlow")));
				} catch (NullPointerException npe) {
					// log error
					logger.error("Recieved no data from database \n" + npe.getMessage(), npe);
				} catch (NumberFormatException nfe) {
					// log error
					logger.error("Failed to parse cash-flow as float \n" + nfe.getMessage(), nfe);
				}
				record.setSummary(1);
				try {
					record.setDate(sdf.parse(rs.getString("journalDate")));
					record.setCashFlow(rs.getFloat("cashFlow"));
					records.add(record);
				} catch (ParseException pe) {
					// log error
					logger.error("error while parsing date \n" + pe.getMessage(), pe);
				}
			}

		} catch (SQLException e) {
			// log error
			logger.error("select on database " + DB_NAME + " failed \n" + e.getMessage(), e);
		} finally {
			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// log error
				logger.error("failed to close sql-connection \n" + e.getMessage(), e);
			}
		}

		result.setRecords(records);
		logger.debug("<-");
		return result;
	}

	@Override
	public String getDbName() {
		logger.debug("->");
		logger.debug("<-");
		return this.DB_NAME;
	}

}