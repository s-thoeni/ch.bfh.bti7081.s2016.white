package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
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
		List<ReportConfig> reports = new ArrayList<ReportConfig>();
		
		//DUMMYDATA for no User Parameter -> Delete when Query is done
		
		reports.add(new ReportConfig(ReportType.AVAILABLE_EMPLOYEES, ReportTimeframe.YESTERDAY));
		reports.add(new ReportConfig(ReportType.CASHFLOW, ReportTimeframe.YESTERDAY));
		reports.add(new ReportConfig(ReportType.SICK_LEAVES, ReportTimeframe.YESTERDAY));
		reports.add(new ReportConfig(ReportType.EFFORT, ReportTimeframe.YESTERDAY));
		reports.add(new ReportConfig(ReportType.INCIDENTS, ReportTimeframe.YESTERDAY));
		reports.add(new ReportConfig(ReportType.ENTRIES_EXITS, ReportTimeframe.YESTERDAY));
		
		// TODO SQL Query get from DB
		
		config.setReports(reports);
		
		
		
		return config;
	}

	@Override
	public void setConfig(List<ReportConfig> reports,  User user) {
		// TODO SQL Query save in DB
		
	}

}
