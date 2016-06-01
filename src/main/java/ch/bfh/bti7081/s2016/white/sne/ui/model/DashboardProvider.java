package ch.bfh.bti7081.s2016.white.sne.ui.model;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ReportFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.Report;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;

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
		List<ReportConfig> reportConfigurations = config.getReports();

		this.facade = new ReportFacadeImpl();
		
		this.reports = new ArrayList<Report>();
		for(ReportConfig conf: reportConfigurations){
			//System.out.println(conf.getDashboardReportType());
			try {
				this.reports.add(facade.getReport(conf.getReportType(), conf.getReportTimeframe(), true));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * returns the report corresponding to the given name. 
	 * @param name
	 * @return Report or null if not found
	 */
	public Report getReportByName(String name){
		for(Report report: reports){
			//System.out.println(report.getName());
			if(report.getName().equals(name))
				return report;
		}
		return null;
	}
}