package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.Date;

import ch.bfh.bti7081.s2016.white.sne.data.FinancialRecord;
import ch.bfh.bti7081.s2016.white.sne.data.PatientRecord;
import ch.bfh.bti7081.s2016.white.sne.data.PersonalRecord;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

/**
 * Loads data used for reporting from data backend
 * 
 * @author mcdizzu
 *
 */
public interface ReportDao {
	/**
	 * Reads data about available employees from data backend for given time range
	 * Result is returned as Report containing PersonalRecords.
	 * 
	 * @param to - start of time frame
	 * @param from - end of time frame
	 * @return Report<PersonalRecord>
	 * @throws SneException
	 */
	public Report<PersonalRecord> getAvailableEmployee(Date from, Date to) throws SneException;
	
	/**
	 * Reads data about absent employees from data backend for given time range.
	 * Result is returned as Report containing PersonalRecords.
	 * 
	 * @param to - start of time frame
	 * @param from - end of time frame
	 * @return Report<PersonalRecord>
	 * @throws SneException
	 */
	public Report<PersonalRecord> getAbsentEmployees(Date from, Date to) throws SneException;
	
	/**
	 * Reads data about incidents from data backend for given time range. 
	 * Result is returned as Report containing PatientRecords.
	 * 
	 * @param to - start of time frame
	 * @param from - end of time frame
	 * @return Report<PatientRecord>
	 * @throws SneException
	 */
	public Report<PatientRecord> getIncidents(Date from, Date to) throws SneException;
	
	/**
	 * Reads data about patient count from data backend for given time range.
	 * Returns the data as Report containing PatientRecords.
	 * 
	 * @param to - start of time frame
	 * @param from - end of time frame
	 * @return Report<PatientRecord>
	 * @throws SneException
	 */
	public Report<PatientRecord> getPatientCount(Date from, Date to) throws SneException;
	
	/**
	 * reads data from data backend, inserts it into FinancialRecord, aggregates the
	 * Records in a Report, report will only contain information about effort.
	 * 
	 * @param to - start of time frame
	 * @param from - end of time frame
	 * @return Report<FinancialRecord>
	 * @throws SneException
	 */
	public Report<FinancialRecord> getEffort(Date from, Date to) throws SneException;
	
	/**
	 * reads data from data backend into FinancialRecord, aggregates the
	 * Records in a Report, report will only contain information about return.
	 * 
	 * @param to - start of time frame
	 * @param from - end of time frame
	 * @return Report<FinancialRecord>
	 * @throws SneException
	 */
	public Report<FinancialRecord> getReturn(Date from, Date to) throws SneException;
	
	/**
	 * reads data from data backend into FinancialRecord, aggregates the
	 * Records in a Report, report will only contain information about cash-flow.
	 * 
	 * @param to - start of time frame
	 * @param from - end of time frame
	 * @return Report<FinancialRecord>
	 * @throws SneException
	 */
	public Report<FinancialRecord> getCashFlow(Date from, Date to) throws SneException;
}