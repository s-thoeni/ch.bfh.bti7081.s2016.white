package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration.DashboardReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportTimeframe;
import ch.bfh.bti7081.s2016.white.sne.data.enums.ReportType;

public class ConfigurationDAOImpl implements ConfigurationDAO {

	@Override
	public Configuration getConfig() {
		return new Configuration();
	}

	@Override
	public Configuration getConfig(User user) {
		Configuration config = new Configuration();
		List<Alarm> alarms;
		List<DashboardReportConfig> reports = new ArrayList<DashboardReportConfig>();
		
		alarms = null;
		
		
		//DUMMYDATA for no User Parameter -> Delete when Query is done
		
		reports.add(config.new DashboardReportConfig(ReportType.AVAILABLE_EMPLOYEES, ReportTimeframe.YESTERDAY));
		reports.add(config.new DashboardReportConfig(ReportType.CASHFLOW, ReportTimeframe.YESTERDAY));
		reports.add(config.new DashboardReportConfig(ReportType.SICK_LEAVES, ReportTimeframe.YESTERDAY));
		reports.add(config.new DashboardReportConfig(ReportType.EFFORT, ReportTimeframe.YESTERDAY));
		reports.add(config.new DashboardReportConfig(ReportType.INCIDENTS, ReportTimeframe.YESTERDAY));
		reports.add(config.new DashboardReportConfig(ReportType.ENTRIES_EXITS, ReportTimeframe.YESTERDAY));
		
		// TODO SQL Query get from DB
		
		config.setAlarms(alarms);
		config.setReports(reports);
		
		
		
		return config;
	}

	@Override
	public void setConfig(List<DashboardReportConfig> reports, List<Alarm> alarms, User user) {

		// TODO SQL Query save in DB

	}

	@Override
	public void setConfig(List<DashboardReportConfig> reports,  User user) {
		// TODO SQL Query save in DB
		
	}

}
