package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class Configuration {
	
	public class DashboardReportConfig {
		private ReportType dashboardReport;
		private ReportTimeframe dashboardReportsspan;
	
		public DashboardReportConfig(ReportType dashboardReport,ReportTimeframe dashboardReportsspan) {
			this.dashboardReport = dashboardReport;
			this.dashboardReportsspan = dashboardReportsspan;
		}
		
		public ReportType getDashboardReportType() {
			return dashboardReport;		
		}
		
		public void setReportType(ReportType type){
			this.dashboardReport = type;
		}
		
		public ReportTimeframe getDashboardReportTime() {
			return dashboardReportsspan;
		}
		
		public void setReportTime(ReportTimeframe span) {
			this.dashboardReportsspan = span;
		}
		
	}

	private List<DashboardReportConfig> reports;
	private List<Alarm> alarms;

	/**
	 * Instantiate a empty configuration
	 */
	public Configuration(){
		this.reports = null;
		this.alarms = null;
	}
	
	public Configuration(List<DashboardReportConfig> reports, List<Alarm> alarms) {
		this.reports = reports;
		this.alarms = alarms;
	}

	public void save() {

	}

	public List<Alarm> getAlarms() {
		return this.alarms;
	}

	public void setAlarms(List<Alarm> alarms) {
		this.alarms = alarms;
	}
	
	public List<DashboardReportConfig> getReports() {
		return this.reports;
	}
	
	public void setReports(List<DashboardReportConfig> reports) {
		this.reports = reports;
	}

}
