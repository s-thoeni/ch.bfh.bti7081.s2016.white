package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration.DashboardReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;

public interface ConfigurationFacade {	
	public Configuration getConfig(User user);
	
	public void setConfig(List<DashboardReportConfig> reports, List<Alarm> alarms, User user);
	
	public void setConfig(List<DashboardReportConfig> reports, User user);
}
