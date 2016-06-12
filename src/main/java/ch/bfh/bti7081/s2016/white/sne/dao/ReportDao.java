package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.Date;

import ch.bfh.bti7081.s2016.white.sne.data.FinancialRecord;
import ch.bfh.bti7081.s2016.white.sne.data.PatientRecord;
import ch.bfh.bti7081.s2016.white.sne.data.PersonalRecord;
import ch.bfh.bti7081.s2016.white.sne.data.Report;

public interface ReportDao {
	public Report<PersonalRecord> getAvailableEmployee(Date from, Date to);
	
	public Report<PersonalRecord> getAbsentEmployees(Date from, Date to);
	
	public Report<PatientRecord> getIncidents(Date from, Date to);
	
	public Report<PatientRecord> getPatientCount(Date from, Date to);
	
	public Report<FinancialRecord> getEffort(Date from, Date to);
	
	public Report<FinancialRecord> getReturn(Date from, Date to);
	
	public Report<FinancialRecord> getCashFlow(Date from, Date to);
}