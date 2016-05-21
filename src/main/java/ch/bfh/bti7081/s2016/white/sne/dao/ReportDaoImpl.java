package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.Date;

import ch.bfh.bti7081.s2016.white.sne.data.Report;

public class ReportDaoImpl implements ReportDao {

	@Override
	public Report getAvailableEmployee(Date from, Date to) {
		return new Report("Anwesendes Personal");
	}

	@Override
	public Report getSickLeaves(Date from, Date to) {
		return new Report("Abwesendes Personal");
	}

	@Override
	public Report getIncidents(Date from, Date to) {
		return new Report("Notfälle");
	}

	@Override
	public Report getEntriesExits(Date from, Date to) {
		return new Report("Aktuelle Patientanzahl");
	}

	@Override
	public Report getPatientCount(Date from, Date to) {
		return new Report("CashFlow aktuel");
	}

	@Override
	public Report getEffort(Date from, Date to) {
		return new Report("Aufwände aktuelles Quartal");
	}

	@Override
	public Report getCashFlow(Date from, Date to) {
		return new Report("Suizide heute");
	}

}