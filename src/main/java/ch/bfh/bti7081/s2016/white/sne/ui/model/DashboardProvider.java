package ch.bfh.bti7081.s2016.white.sne.ui.model;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration.DashboardReportConfig;
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
		
		List<DashboardReportConfig> reportConfigurations = config.getReports();
				
		//ToDo: Shit with List<DashboardReportConfig> for DashboardProvider -> Presenter and so on...
		
		facade = new ReportFacadeImpl();
		//this.reports = facade.getReports(types, ReportTimeframe.YESTERDAY, true);	
	}
	
	/**
	 * returns the report corresponding to the given name. 
	 * @param name
	 * @return Report or null if not found
	 */
	public Report getReportByName(String name){
		for(Report report: reports){
			if(report.getName().equals(name))
				return report;
		}
		return null;
	}
}