package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class Configuration {

	private ReportType[] dashboardReports;
	private List<Alarm> alarms;

	/**
	 * Instantiate a empty configuration
	 */
	public Configuration(){
		this.dashboardReports = null;
		this.alarms = null;
	}
	
	public Configuration(ReportType[] dashboardReports, List<Alarm> alarms) {
		this.dashboardReports = dashboardReports;
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

	public ReportType[] getDashboardReportTypes() {
		return dashboardReports;		
	}
	
	public void setReportTypes(ReportType[] types){
		this.dashboardReports = types;
	}

}
