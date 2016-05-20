package ch.bfh.bti7081.s2016.white.sne.data;

import java.util.List;

public class Configuration {

	private List<Report> dashboardReports;
	private List<Alarm> alarms;

	public Configuration(List<Report> dashboardReports, List<Alarm> alarms) {
		this.dashboardReports = dashboardReports;
		this.alarms = alarms;
	}

	public void save() {

	}

	public List<Report> getDashboardReports() {
		return this.dashboardReports;
	}

	public void setDashboardReports(List<Report> dashboardReports) {
		this.dashboardReports = dashboardReports;
	}

	public List<Alarm> getAlarms() {
		return this.alarms;
	}

	public void setAlarms(List<Alarm> alarms) {
		this.alarms = alarms;
	}

}
