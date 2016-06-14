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
import ch.bfh.bti7081.s2016.white.sne.data.enums.AbsenceReason;
import ch.bfh.bti7081.s2016.white.sne.data.enums.IncidentType;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Loads data used for reporting from database
 * Implements connection to data backend (sqlite3)
 * 
 * @author mcdizzu
 *
 */
public class ReportDaoImpl extends AbstractDAO implements ReportDao {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ReportDaoImpl.class);

	/**
	 * Database name as constant
	 */
	private static final String DB_NAME = "dwh.db";
	
	/**
	 * Simple date formatter
	 */
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * SQL query for getting information about available employees from database
	 */
	private static final String SELECT_AVAILABLE_EMPLOYEES = "SELECT e.employeeFirstName, e.employeeSurName, t.treatmentDate FROM Treatment AS t INNER JOIN Employee AS e ON t.employeeID=e.employeeID WHERE t.treatmentDate >= ? AND t.treatmentDate <= ?";
	
	/**
	 * SQL query for getting information about sick leaves from database
	 */
	private static final String SELECT_ABSENCES = "SELECT e.employeeFirstName, e.employeeSurName, a.absenceDate, ar.reasonID FROM Absence AS a INNER JOIN Employee AS e ON a.employeeID=e.employeeID INNER JOIN AbsenceReason AS ar ON a.absenceReason=ar.reasonID WHERE a.absenceDate >= ? AND a.absenceDate <= ?";
	
	/**
	 * SQL query for getting information about incidents from database
	 */
	private static final String SELECT_INCIDENTS = "SELECT i.incidentId, it.typeID, it.typeName, t.treatmentId, i.description, t.treatmentDate FROM Incident AS i INNER JOIN Treatment AS t ON i.treatmentId=t.treatmentId INNER JOIN IncidentType AS it ON i.typeID=it.typeID WHERE t.treatmentDate >= ? AND t.treatmentDate <= ?";
	
	/**
	 * SQL query for getting information about patients from database
	 */
	private static final String SELECT_PATIENT_COUNT = "SELECT p.patientFirstName, p.patientSurName, t.treatmentDate FROM Treatment AS t INNER JOIN Patient AS p ON p.patientId = t.patientId WHERE t.treatmentDate >= ? AND t.treatmentDate <= ?";
	
	/**
	 * SQL query for getting information about finance from database
	 */
	private static final String SELECT_FINANCE = "SELECT journalID, journalDate, effort, return, cashFlow FROM Journal WHERE journalDate >= ? AND journalDate <= ?";

	/**
	 * Default constructor
	 * 
	 * @throws SneException
	 */
	public ReportDaoImpl() throws SneException {
		super();
	}
	
	@Override
	public Report<PersonalRecord> getAvailableEmployee(Date from, Date to) throws SneException {
		logger.debug("->");

		Report<PersonalRecord> result = new Report<PersonalRecord>("Anwesendes Personal");
		List<PersonalRecord> records = new ArrayList<PersonalRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection(DB_NAME);
			// get data
			stm = con.prepareStatement(SELECT_AVAILABLE_EMPLOYEES);
			String f = sdf.format(from);
			String t = sdf.format(to);
			stm.setString(1, f);
			stm.setString(2, t);

			// log query
			logger.debug(SELECT_AVAILABLE_EMPLOYEES + " 1=" + f +  " 2=" + t);

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

			throw new SneException("Error retrieving report data from database! ", e);
		} finally {
			try {
				close(rs, stm, con);
			} catch (SQLException e) {
				// log error
				logger.error("failed to close sql-connection \n" + e.getMessage(), e);

				throw new SneException("Error closing database connection, your data might be in danger! ", e);
			}
		}

		result.setRecords(records);
		logger.debug("<-");
		return result;
	}
	
	@Override
	public Report<PersonalRecord> getAbsentEmployees(Date from, Date to) throws SneException {
		logger.debug("->");

		Report<PersonalRecord> result = new Report<PersonalRecord>("Abwesendes Personal");
		List<PersonalRecord> records = new ArrayList<PersonalRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection(DB_NAME);
			
			// get data
			stm = con.prepareStatement(SELECT_ABSENCES);
			String f = sdf.format(from);
			String t = sdf.format(to);
			stm.setString(1, f);
			stm.setString(2, t);

			// log query
			logger.debug(SELECT_ABSENCES + " 1=" + f +  " 2=" + t);
			
			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {

				// TODO
				PersonalRecord record = new PersonalRecord();
				record.setAvailable(true);
				record.setSummary(1);
				record.setPersonName(rs.getString("employeeSurName") + " " + rs.getString("employeeFirstName"));
				record.setAbsenceReason(AbsenceReason.values()[rs.getInt("reasonID")-1]);

				try {
					record.setDate(sdf.parse(rs.getString("absenceDate")));
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
	public Report<PatientRecord> getIncidents(Date from, Date to) throws SneException {
		logger.debug("->");

		Report<PatientRecord> result = new Report<PatientRecord>("Notfälle");
		List<PatientRecord> records = new ArrayList<PatientRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection(DB_NAME);
			
			// get data
			stm = con.prepareStatement(SELECT_INCIDENTS);
			String f = sdf.format(from);
			String t = sdf.format(to);
			stm.setString(1, f);
			stm.setString(2, t);
			
			// log query
			logger.debug(SELECT_INCIDENTS + " 1=" + f +  " 2=" + t);

			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {
				PatientRecord record = new PatientRecord();
				record.setIncident(rs.getString("description"));
				record.setIncidentType(IncidentType.values()[rs.getInt("typeID")-1]);
//				record.setPatientName(rs.getString("patientSurName") + " " + rs.getString("patientFirstName"));
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
	public Report<PatientRecord> getPatientCount(Date from, Date to) throws SneException {
		logger.debug("->");

		Report<PatientRecord> result = new Report<PatientRecord>("Aktuelle Patientanzahl");
		List<PatientRecord> records = new ArrayList<PatientRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection(DB_NAME);
			// get data
			stm = con.prepareStatement(SELECT_PATIENT_COUNT);
			String f = sdf.format(from);
			String t = sdf.format(to);
			stm.setString(1, f);
			stm.setString(2, t);
			
			// log query
			logger.debug(SELECT_PATIENT_COUNT + " 1=" + f +  " 2=" + t);

			rs = stm.executeQuery();

			// parse results
			while (rs.next()) {
				PatientRecord record = new PatientRecord();
				record.setPatientName(rs.getString("patientSurName") + " " + rs.getString("patientFirstName"));
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
	public Report<FinancialRecord> getEffort(Date from, Date to) throws SneException {
		logger.debug("->");

		Report<FinancialRecord> result = new Report<FinancialRecord>("Aufwand");
		List<FinancialRecord> records = new ArrayList<FinancialRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection(DB_NAME);
			// get data
			stm = con.prepareStatement(SELECT_FINANCE);
			String f = sdf.format(from);
			String t = sdf.format(to);
			stm.setString(1, f);
			stm.setString(2, t);
			
			// log query
			logger.debug(SELECT_FINANCE + " 1=" + f +  " 2=" + t);

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

	@Override
	public Report<FinancialRecord> getReturn(Date from, Date to) throws SneException {
		logger.debug("->");

		Report<FinancialRecord> result = new Report<FinancialRecord>("Ertrag");
		List<FinancialRecord> records = new ArrayList<FinancialRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection(DB_NAME);
			// get data
			stm = con.prepareStatement(SELECT_FINANCE);
			String f = sdf.format(from);
			String t = sdf.format(to);
			stm.setString(1, f);
			stm.setString(2, t);
			
			// log query
			logger.debug(SELECT_FINANCE + " 1=" + f +  " 2=" + t);

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

	@Override
	public Report<FinancialRecord> getCashFlow(Date from, Date to) throws SneException {
		logger.debug("->");

		Report<FinancialRecord> result = new Report<FinancialRecord>("Cash Flow");
		List<FinancialRecord> records = new ArrayList<FinancialRecord>();

		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			con = getConnection(DB_NAME);
			// get data
			stm = con.prepareStatement(SELECT_FINANCE);
			String f = sdf.format(from);
			String t = sdf.format(to);
			stm.setString(1, f);
			stm.setString(2, t);
			
			// log query
			logger.debug(SELECT_FINANCE + " 1=" + f +  " 2=" + t);

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

}