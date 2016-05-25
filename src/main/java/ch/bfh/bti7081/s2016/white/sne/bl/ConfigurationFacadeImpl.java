package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.dao.ConfigurationDAOImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration.DashboardReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;

public class ConfigurationFacadeImpl implements ConfigurationFacade{
	private ConfigurationDAOImpl dao = new ConfigurationDAOImpl();
	
	@Override
	public Configuration getConfig(User user) {
		return dao.getConfig(user);
	}

	@Override
	public void setConfig(List<DashboardReportConfig> reports, List<Alarm> alarms, User user) {
		dao.setConfig(reports, alarms, user);
	}

	@Override
	public void setConfig(List<DashboardReportConfig> reports, User user) {
		dao.setConfig(reports, user);
	}

}
