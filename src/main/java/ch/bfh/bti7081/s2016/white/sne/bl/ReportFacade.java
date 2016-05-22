package ch.bfh.bti7081.s2016.white.sne.bl;

import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.RecordDAO;
import java.util.Date;

public class ReportFacade {
	
	private RecordDAO recordDAO;
	
	public ReportFacade(RecordDAO recordDAO) {
		this.recordDAO = recordDAO;
	}

	public Report getAvailableEmployees(Date from, Date to) {
		// TODO(jan): implement this method
		return null;
	}

	public Report getSickLeaves(Date from, Date to) {
		// TODO(jan): implement this method
		return null;
	}

	public Report getIncidents(Date from, Date to) {
		// TODO(jan): implement this method
		Report report = new Report();
		report.setFrom(from);
		report.setTo(to);
		report.setReportName("Incidents");
		report.setRecords(this.recordDAO.getIncidents(from, to));
		return report;
	}

	public Report getEntriesExits(Date from, Date to) {
		// TODO(jan): implement this method
		return null;
	}

	public Report getPatientCount(Date from, Date to) {
		// TODO(jan): implement this method
		return null;
	}

	public Report getEffort(Date from, Date to) {
		// TODO(jan): implement this method
		return null;
	}

	public Report getCashFlow(Date from, Date to) {
		// TODO(jan): implement this method
		return null;
	}

}
