package ch.bfh.bti7081.s2016.white.sne.ui.model;

import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacade;
import java.util.Date;

public class ReportProvider {

	private ReportFacade facade;
	
	public ReportProvider(ReportFacade facade) {
		this.facade = facade;
	}
	
	public Report getAvailableEmployees(Date from, Date to) {
		return this.facade.getAvailableEmployees(from, to);
	}

	public Report getSickLeaves(Date from, Date to) {
		return this.facade.getSickLeaves(from, to);
	}

	public Report getIncidents(Date from, Date to) {
		return this.facade.getIncidents(from, to);
	}

	public Report getEntriesExits(Date from, Date to) {
		return this.facade.getEntriesExits(from, to);
	}

	public Report getPatientCount(Date from, Date to) {
		return this.facade.getPatientCount(from, to);
	}

	public Report getEffort(Date from, Date to) {
		return this.facade.getEffort(from, to);
	}

	public Report getCashFlow(Date from, Date to) {
		return this.facade.getCashFlow(from, to);
	}

}
