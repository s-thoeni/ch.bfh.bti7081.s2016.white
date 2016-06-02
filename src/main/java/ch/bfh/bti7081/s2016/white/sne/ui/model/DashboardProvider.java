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
	
	private List<ReportConfig> reportConfigurations;
	
	public List<Report> getReports() {		
		List<Report> reports= new ArrayList<Report>();
		for(ReportConfig conf: getReportConfigurations()){
			//System.out.println(conf.getDashboardReportType());
			try {
				reports.add(facade.getReport(conf.getReportType(), conf.getReportTimeframe(), true));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return reports;
	}
	
	public DashboardProvider(Configuration config) {
		this.setReportConfigurations(config.getReports());
		this.facade = new ReportFacadeImpl();	
	}
	
	/**
	 * returns the report corresponding to the given name. 
	 * @param name
	 * @return Report or null if not found
	 */
	public Report getReportByName(String name){
		for(Report report: getReports()){
			//System.out.println(report.getName());
			if(report.getName().equals(name))
				return report;
		}
		return null;
	}

	public List<ReportConfig> getReportConfigurations() {
		return reportConfigurations;
	}

	public void setReportConfigurations(List<ReportConfig> reportConfigurations) {
		this.reportConfigurations = reportConfigurations;
	}
}