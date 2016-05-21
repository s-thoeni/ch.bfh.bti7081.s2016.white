package ch.bfh.bti7081.s2016.white.sne.ui.model;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

/**
 * Provider for dashboard functionality. Needs to be initialized with a Configuration. Reads configured reports with their summary
 * 
 * @author thons1
 *
 */
public class DashboardProvider {
	private ReportFacade facade;
	
	private List<Report> reports;

	public List<Report> getReports() {
		return reports;
	}

	public DashboardProvider(Configuration config) {		
		ReportType[] types = config.getDashboardReportTypes();
		facade = new ReportFacadeImpl();
		this.reports = facade.getReports(types, ReportTimeframe.YESTERDAY, true);	
	}
}