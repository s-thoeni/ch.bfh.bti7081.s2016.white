package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.Date;

import ch.bfh.bti7081.s2016.white.sne.data.Report;

public interface ReportDao {
	public Report getAvailableEmployee(Date from, Date to);
	
	public Report getSickLeaves(Date from, Date to);
	
	public Report getIncidents(Date from, Date to);
	
	public Report getEntriesExits(Date from, Date to);
	
	public Report getPatientCount(Date from, Date to);
	
	public Report getEffort(Date from, Date to);
	
	public Report getReturn(Date from, Date to);
	
	public Report getCashFlow(Date from, Date to);
}